package com.excilys.formation.yaeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public Utilisateur getUtilisateurById(String id) {
		return utilisateurDao.getUtilisateurById(id);
	}

	public void update(Utilisateur u) {
		utilisateurDao.update(u);
	}

	public void save(Utilisateur u) {
		utilisateurDao.save(u);
	}

	public void delete(Utilisateur u) {
		utilisateurDao.delete(u);
	}

	public Utilisateur getUtilisateurByLogin(String login) {
		return utilisateurDao.getUtilisateurByLogin(login);
	}
}