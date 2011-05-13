package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface OperationService {

	Operation getOperationById(int id);

	List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois);

	List<Operation> getOperationsNoCBByMoisAnnee(Compte c, int annee, int mois);

	List<Operation> getOperationsNoCBByMoisAnnee(Compte c, int annee, int mois, int page, int nbResultats);

	long getNbOperationsNoCBByMoisAnnee(Compte c, int annee, int mois);

	List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois);

	List<OperationVirementInterne> getVirementsInternes(Utilisateur u);

	void create(Operation o);
}
