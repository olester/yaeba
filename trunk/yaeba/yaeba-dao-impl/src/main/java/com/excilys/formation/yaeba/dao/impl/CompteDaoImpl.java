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
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte) {
		System.out.println(u.getNom());
		// Compte compte = (Compte) getHibernateTemplate().find("from Compte where Utilisateur = ? and numeroCompte = ?", u, numeroCompte).get(0);
		List<Compte> comptes = getHibernateTemplate().find("from Compte where numeroCompte=?", numeroCompte);
		System.out.println(numeroCompte);
		// En cours de modif pour la sécurité
		// && u.getComptes().contains(comptes.get(0))
		if (!comptes.isEmpty()) return comptes.get(0);
		return null;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptes(Utilisateur u) {
		List<Compte> c = getHibernateTemplate().find("from Compte where Utilisateur = ?", u);
		return c;
	}

}
