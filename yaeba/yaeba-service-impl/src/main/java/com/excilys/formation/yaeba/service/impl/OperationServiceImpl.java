package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.service.api.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDao operationDao;

	@Override
	public Operation getOperationById(String id) {
		return operationDao.getOperationById(id);
	}

	@Override
	public void update(Operation o) {
		operationDao.update(o);
	}

	@Override
	public void save(Operation o) {
		operationDao.save(o);
	}

	@Override
	public void delete(Operation o) {
		operationDao.delete(o);
	}

	@Override
	public List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsByDate(c, dt, dt.dayOfMonth().withMaximumValue());
	}

}
