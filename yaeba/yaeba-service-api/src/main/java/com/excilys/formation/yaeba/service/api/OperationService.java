package com.excilys.formation.yaeba.service.api;

import com.excilys.formation.yaeba.model.Operation;

public interface OperationService {

	public Operation getOperationById(String id);

	public Operation getOperationByLibelle(String libelle);

	public void update(Operation o);

	public void save(Operation o);

	public void delete(Operation o);
}