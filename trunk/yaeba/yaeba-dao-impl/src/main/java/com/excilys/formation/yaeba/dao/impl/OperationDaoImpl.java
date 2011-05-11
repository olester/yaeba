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

	@SuppressWarnings("unchecked")
	@Override
	public Operation getOperationById(String id) {
		List<Operation> l = getHibernateTemplate().find("from Operation o where id=?", Integer.parseInt(id));
		if (!l.isEmpty()) return l.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operation> getOperationsByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate().find(
				"SELECT o FROM Compte c INNER JOIN c.operations o WHERE c = ? AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC", c, dateDebut,
				dateFin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		// return getHibernateTemplate()
		// .find("SELECT o FROM Compte c INNER JOIN c.operations o WHERE c = ? AND o.class = OperationCarteBancaire AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC",
		// c, dateDebut, dateFin);
		return getHibernateTemplate()
				.find("SELECT o FROM Compte c INNER JOIN c.operations o WHERE c = ? AND o.discriminator='OPERATIONCARTEBANCAIRE' AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC",
						c, dateDebut, dateFin);
	}

}
