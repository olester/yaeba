package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

@Repository
public class CompteDaoImpl extends HibernateDaoSupport implements CompteDao {

	@Autowired
	public CompteDaoImpl(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Compte getCompteById(String id) {
		List<Compte> l = getHibernateTemplate().find("from Compte c where id=?", Integer.parseInt(id));
		// TODO utiliser .get
		if (!l.isEmpty()) return l.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte) {
		List<Compte> comptes = getHibernateTemplate().find("SELECT c FROM Utilisateur u INNER JOIN u.comptes c WHERE u = ? AND c.numeroCompte = ?", u,
				numeroCompte);
		if (!comptes.isEmpty()) return comptes.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptes(Utilisateur u) {
		List<Compte> c = getHibernateTemplate().find("select u.comptes from Utilisateur u where u = ?", u);
		return c;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isEmpty(Compte c) {
		List<Compte> compte = getHibernateTemplate().find("from Compte c where c.operations.size > 0 AND c=?", c);
		return !compte.isEmpty();
	}

}
