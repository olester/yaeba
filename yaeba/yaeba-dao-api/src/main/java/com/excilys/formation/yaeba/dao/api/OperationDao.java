package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import org.joda.time.DateTime;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;

public interface OperationDao {

	public Operation getOperationById(String id);

	public void update(Operation o);

	public void save(Operation o);

	public void delete(Operation o);

	public List<Operation> getOperationsByDate(Compte c, DateTime dateDebut, DateTime dateFin);

	public List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin);

}
