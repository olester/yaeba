package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;

@Repository
public class CompteDaoImpl extends HibernateDaoSupport implements CompteDao {

	@Autowired
	public CompteDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Compte getCompteById(String id) {
		List<Compte> l = getHibernateTemplate().find("select c from Compte c where id=?", Integer.parseInt(id));
		// TODO utiliser .get
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Compte getCompteByNumeroCompte(String numeroCompte) {
		List<Compte> l = getHibernateTemplate().find("select c from Compte c where numeroCompte=?", numeroCompte);
		// TODO utiliser .get
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void update(Compte c) {
		getHibernateTemplate().update(c);

	}

	@Override
	public void save(Compte c) {
		getHibernateTemplate().save(c);

	}

	@Override
	public void delete(Compte c) {
		getHibernateTemplate().delete(c);

	}
}
