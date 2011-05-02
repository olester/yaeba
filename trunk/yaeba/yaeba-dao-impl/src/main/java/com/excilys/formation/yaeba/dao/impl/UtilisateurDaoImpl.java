package com.excilys.formation.yaeba.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Utilisateur;



@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Utilisateur getUtilisateurById(String id) {
		List<Utilisateur> l = hibernateTemplate.find("select c from Utilisateur c where id=?", id);
		//TODO utiliser .get
		if(l.size()>0){
			return l.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public void update(Utilisateur u) {
		hibernateTemplate.update(u);
	}

	@Override
	public void save(Utilisateur u) {
		hibernateTemplate.save(u);
	}

	@Override
	public void delete(Utilisateur u) {
		hibernateTemplate.delete(u);
	}
	
	
}