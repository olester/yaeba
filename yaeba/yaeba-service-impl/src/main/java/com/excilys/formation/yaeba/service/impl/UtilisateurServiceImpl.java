package com.excilys.formation.yaeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.UtilisateurService;

@Service
@Transactional(readOnly = true)
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;

	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public Utilisateur getUtilisateurById(int id) {
		return utilisateurDao.getUtilisateurById(id);
	}

	public Utilisateur getUtilisateurByLoginFetchRoles(String login) {
		return utilisateurDao.getUtilisateurByLoginFetchRoles(login);
	}
}