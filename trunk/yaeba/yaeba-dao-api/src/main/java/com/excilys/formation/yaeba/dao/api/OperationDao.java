package com.excilys.formation.yaeba.dao.api;

import com.excilys.formation.yaeba.model.Operation;

public interface OperationDao {

	public Operation getOperationById(String id);

	public void update(Operation o);

	public void save(Operation o);

	public void delete(Operation o);
}
