package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import org.joda.time.DateTime;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface OperationDao {

	Operation getOperationById(int id);

	List<Operation> getOperationsByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	List<Operation> getOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	List<Operation> getOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin, int page, int nbResultats);

	long getNbOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	List<OperationVirementInterne> getVirementsInternes(Utilisateur u, DateTime dateDebut, DateTime dateFin);

	void create(Operation o);
}
