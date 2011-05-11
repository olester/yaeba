package com.excilys.formation.yaeba.service.api;

import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurService {

	public Utilisateur getUtilisateurById(String id);

	public Utilisateur getUtilisateurByLoginFetchRoles(String login);
}