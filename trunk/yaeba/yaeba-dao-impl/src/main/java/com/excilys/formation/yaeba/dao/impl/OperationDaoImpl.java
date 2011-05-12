package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;

@Repository
public class OperationDaoImpl extends HibernateDaoSupport implements OperationDao {

	@Autowired
	public OperationDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@Override
	public Operation getOperationById(int id) {
		return getHibernateTemplate().get(Operation.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operation> getOperationsByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate().find("FROM Operation o WHERE o.compte = ? AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC", c, dateDebut,
				dateFin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate()
				.find("FROM Operation o WHERE o.compte = ? AND o.discriminator='OPERATIONCARTEBANCAIRE' AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC",
						c, dateDebut, dateFin);
	}

}
