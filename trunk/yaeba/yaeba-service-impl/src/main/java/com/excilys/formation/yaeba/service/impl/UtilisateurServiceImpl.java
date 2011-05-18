package com.excilys.formation.yaeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.UtilisateurService;

/**
 * @author excilys
 * 
 */
@Service
@Transactional(readOnly = true)
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurDao utilisateurDao;

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.UtilisateurService#getUtilisateurByLoginFetchRoles(java.lang.String)
	 */
	@Override
	public Utilisateur getUtilisateurByLoginFetchRoles(String login) {
		return utilisateurDao.getUtilisateurByLoginFetchRoles(login);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.UtilisateurService#getOwner(com.excilys.formation.yaeba.model.Compte)
	 */
	@Override
	public Utilisateur getOwner(Compte c) {
		return utilisateurDao.getOwner(c);
	}
}