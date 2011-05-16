package com.excilys.formation.yaeba.service.api;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurService {

	Utilisateur getUtilisateurByLoginFetchRoles(String login);

	Utilisateur getOwner(Compte c);
}