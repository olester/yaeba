package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Utilisateur;

@Repository
public class UtilisateurDaoImpl extends HibernateDaoSupport implements UtilisateurDao {

	// private HibernateTemplate hibernateTemplate;
	//
	// public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	// this.hibernateTemplate = hibernateTemplate;
	// }

	@Autowired
	public UtilisateurDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUtilisateurById(String id) {
		List<Utilisateur> l = getHibernateTemplate().find("select c from Utilisateur c where id=?", id);
		// TODO utiliser .get
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUtilisateurByLogin(String login) {
		List<Utilisateur> l = getHibernateTemplate().find("FROM Utilisateur u WHERE login=?", login);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void update(Utilisateur u) {
		getHibernateTemplate().update(u);
	}

	@Override
	public void save(Utilisateur u) {
		getHibernateTemplate().save(u);
	}

	@Override
	public void delete(Utilisateur u) {
		getHibernateTemplate().delete(u);
	}

}