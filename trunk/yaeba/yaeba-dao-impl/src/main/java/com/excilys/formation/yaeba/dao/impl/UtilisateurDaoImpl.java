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

	@Autowired
	public UtilisateurDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@Override
	public Utilisateur getUtilisateurById(int id) {
		return getHibernateTemplate().get(Utilisateur.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUtilisateurByLoginFetchRoles(String login) {
		List<Utilisateur> l = getHibernateTemplate().find("FROM Utilisateur u JOIN FETCH u.roles WHERE u.login = ?", login);

		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
}