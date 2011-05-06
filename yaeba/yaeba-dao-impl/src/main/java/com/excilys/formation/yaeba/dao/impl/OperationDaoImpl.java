package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Operation;

@Repository
public class OperationDaoImpl extends HibernateDaoSupport implements OperationDao {

	@Autowired
	public OperationDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Operation getOperationById(String id) {
		List<Operation> l = getHibernateTemplate().find("select o from Operation o where id=?", Integer.parseInt(id));
		// TODO utiliser .get
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operation> getOperationByLibelle(String libelle) {
		List<Operation> l = getHibernateTemplate().find("select o from Operation o where libelle=?", libelle);
		return l;
	}

	@Override
	public void update(Operation o) {
		getHibernateTemplate().update(o);

	}

	@Override
	public void save(Operation o) {
		getHibernateTemplate().save(o);

	}

	@Override
	public void delete(Operation o) {
		getHibernateTemplate().delete(o);

	}

}
