package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;

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
	public List<Operation> getOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate().find(
				"FROM Operation o WHERE o.compte = ? AND o.class<>OperationCarteBancaire AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC", c,
				dateDebut, dateFin);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Operation> getOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin, int page, int nbResultats) {
		int offset = (page - 1) * nbResultats;
		String queryString = "FROM Operation o WHERE o.compte = ? AND o.class<>OperationCarteBancaire AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC";
		Query q = getSession().createQuery(queryString);
		q.setParameter(0, c);
		q.setParameter(1, dateDebut);
		q.setParameter(2, dateFin);
		q.setMaxResults(nbResultats);
		q.setFirstResult(offset);
		List res = q.list();
		return res;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public long getNbOperationsNoCBByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		List res = getHibernateTemplate().find(
				"SELECT count(*) from Operation o WHERE o.compte = ? AND o.class<>OperationCarteBancaire AND o.dateCreation BETWEEN ? AND ?", c, dateDebut,
				dateFin);
		return ((Long) res.get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationCarteBancaire> getOperationsCBByDate(Compte c, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate().find(
				"FROM OperationCarteBancaire o WHERE o.compte = ? AND o.dateEffective BETWEEN ? AND ? ORDER BY o.dateEffective DESC", c, dateDebut, dateFin);
	}

	@Override
	public void create(Operation o) {
		getHibernateTemplate().save(o);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationVirementInterne> getVirementsInternes(Utilisateur u, DateTime dateDebut, DateTime dateFin) {
		return getHibernateTemplate().find(
				"FROM OperationVirementInterne o WHERE o.compte.utilisateur=? AND o.montant<0 AND o.dateCreation BETWEEN ? AND ? ORDER BY o.dateCreation DESC",
				u, dateDebut, dateFin);
	}

}
