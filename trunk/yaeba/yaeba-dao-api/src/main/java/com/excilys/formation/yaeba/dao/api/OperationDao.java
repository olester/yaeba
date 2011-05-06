package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Operation;

public interface OperationDao {

	public Operation getOperationById(String id);

	public List<Operation> getOperationByLibelle(String libelle);

	public void update(Operation o);

	public void save(Operation o);

	public void delete(Operation o);
}
