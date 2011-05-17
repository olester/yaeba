package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;

/**
 * Il s'agit de l'interface de l'ensemble des services concernant les comptes
 * 
 * @author excilys
 */
public interface CompteService {

	/**
	 * Cette fonction permet de retrouver un compte grace a son identifiant.
	 * 
	 * @param id
	 *            l'identifiant du compte
	 * @return le compte qu'on cherchait (ou null si la recherche echoue)
	 * @throws IdCompteNotFoundException
	 *             Cette exception est levee si l'id ne correspond a aucun compte
	 */
	Compte getCompteById(int id) throws IdCompteNotFoundException;

	/**
	 * Cette fonction renvoit les comptes appartenant a un utilisateur
	 * 
	 * @param u
	 *            l'utilisateur qui possede les comptes que l'on cherche
	 * @return la liste des comptes
	 */
	List<Compte> getComptesByUtilisateur(Utilisateur u);

	/**
	 * Cette fonction teste si un compte possede des operations
	 * 
	 * @param c
	 *            le compte a tester
	 * @return vrai si le compte ne possede pas d'operations
	 */
	boolean isEmpty(Compte c);

	/**
	 * Cette fonction recherche le compte qui correspond a un utilisateur et a un numero de compte
	 * 
	 * @param u
	 *            l'utilisateur
	 * @param numeroCompte
	 *            le numero du compte
	 * @return le compte correspondant (ou null si l'utilisateur ne possede pas le compte demande, ou si le numero de compte n'existe pas)
	 */
	Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);

	/**
	 * Cette fonction calcule l'encours carte d'un compte
	 * 
	 * @param c
	 *            le compte dont on calcule l'encours carte
	 * @return un double representant la somme des operations carte pas encore debitees
	 * @throws NoCardException
	 *             Cette exception est levee si on cherche a calculer l'encours carte d'un compte pas associe a une CB
	 */
	double getEncoursCarte(Compte c) throws NoCardException;

	/**
	 * Cette fonction verifie si le solde d'un compte est superieur a un certain montant
	 * 
	 * @param c
	 *            le compte que l'on veut tester
	 * @param montant
	 *            le montant auquel comparer le solde du compte
	 * @return vrai si le solde du compte est superieur ou egal au montant specifie
	 */
	boolean isApprovisionne(Compte c, double montant);
}
