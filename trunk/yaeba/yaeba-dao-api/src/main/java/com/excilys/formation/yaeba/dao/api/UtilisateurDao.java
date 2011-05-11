package com.excilys.formation.yaeba.dao.api;

import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurDao {

	public Utilisateur getUtilisateurById(String id);

	public Utilisateur getUtilisateurByLoginFetchRoles(String login);

}