package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import org.joda.time.DateTime;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;

public interface OperationDao {

	Operation getOperationById(int id);

	List<Operation> getOperationsByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin);

}
