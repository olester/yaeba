package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.MontantNegatifException;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;

/**
 * Il s'agit de l'interface de l'ensemble des services concernant les operations
 * 
 * @author excilys
 */
public interface OperationService {

	/**
	 * Cette fonction retrouve l'operation associee a un identifiant
	 * 
	 * @param id
	 *            l'identifiant de l'operation recherchee
	 * @return l'operation correspondante
	 */
	Operation getOperationById(int id);

	/**
	 * Cette fonction recherche les operations pour un mois et une annee donnes
	 * 
	 * @param c
	 *            le compte dont on cherche les operations
	 * @param annee
	 *            l'annee definissant le critere de recherche
	 * @param mois
	 *            le mois definissant le critere de recherche
	 * @return la liste des operations concernees
	 */
	List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois);

	/**
	 * Cette fonction recherche les operations qui ne sont pas des paiements par carte bancaire. elle prend en compte dans son critere: le mois et l'annee
	 * auxquels ont eu lieu les transactions, ainsi que le compte lié a celles-ci <br/>
	 * Cette recherche est paginee
	 * 
	 * @param c
	 *            le compte lié a l'operation
	 * @param annee
	 *            l'annee definissant le critere de recherche
	 * @param mois
	 *            le mois definissant le critere de recherche
	 * @param page
	 *            le numero de la page recherchee
	 * @param nbResultats
	 *            le nombre de resultats par page
	 * @return la liste des operations hormis par carte bancaire
	 */
	List<Operation> getOperationsNoCBByMoisAnnee(Compte c, int annee, int mois, int page, int nbResultats);

	/**
	 * Cette fonction renvoie le nombre d'operations non liees a une carte bancaire. elle prend en compte dans son critere: le mois et l'annee auxquels ont eu
	 * lieu les transactions, ainsi que le compte lié a celles-ci
	 * 
	 * @param c
	 *            le compte lié a l'operation
	 * @param annee
	 *            l'annee definissant le critere de recherche
	 * @param mois
	 *            le mois definissant le critere de recherche
	 * @return le nombre d'operations hormis par carte bancaire
	 */
	long getNbOperationsNoCBByMoisAnnee(Compte c, int annee, int mois);

	/**
	 * Cette fonction recherche les operations effectuees par carte bancaire
	 * 
	 * @param c
	 *            le compte lie aux operations recherchees
	 * @param annee
	 *            l'annee definissant le critere de recherche
	 * @param mois
	 *            le mois definissant le critere de recherche
	 * @return la liste des operations carte bancaires
	 */
	List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois);

	/**
	 * Cette fonction recherche les operations de type virement interne effectues par un utilisateur donne dans les trente-six derniers mois
	 * 
	 * @param u
	 *            l'utilisateur concerne
	 * @return la liste des virements internes
	 */
	List<OperationVirementInterne> getVirementsInternes(Utilisateur u);

	/**
	 * Cette fonction cree un virement entre deux comptes, et d'un certain montant
	 * 
	 * @param idCompteEmetteur
	 *            l'identifiant du compte qui emet le virement (compte debiteur)
	 * @param idCompteRecepteur
	 *            l'identifiant du compte qui recoit le virement (compte crediteur)
	 * @param montant
	 *            le montant du virement a creer
	 * @throws SoldeInsuffisantException
	 *             Cette exception est levee si le solde du compte debiteur est insuffisant pour autoriser ce virement
	 * @throws PermissionRefuseeException
	 *             Cette exception est levee si les deux comptes n'appartiennent pas au meme utilisateur
	 */
	/*
	 * Cette fonction plante tout si les ids ne correspondent a rien.
	 */

	void createVirement(int idCompteEmetteur, int idCompteRecepteur, double montant) throws IdCompteNotFoundException, SoldeInsuffisantException,
			PermissionRefuseeException,MontantNegatifException;
}
