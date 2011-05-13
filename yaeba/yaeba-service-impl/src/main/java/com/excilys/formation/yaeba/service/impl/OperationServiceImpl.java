package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.service.api.OperationService;

@Service
@Transactional(readOnly = true)
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationDao operationDao;

	@Override
	public Operation getOperationById(int id) {
		return operationDao.getOperationById(id);
	}

	@Override
	public List<Operation> getOperationsNoCBByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsNoCBByDate(c, dt, dt.dayOfMonth().withMaximumValue());
	}

	@Override
	public List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsCBByDate(c, dt, dt.dayOfMonth().withMaximumValue());
	}

	@Override
	public void create(Operation o) {
		operationDao.create(o);
	}

}
