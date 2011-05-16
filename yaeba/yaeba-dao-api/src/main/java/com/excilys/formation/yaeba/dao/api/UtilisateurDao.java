package com.excilys.formation.yaeba.dao.api;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurDao {

	Utilisateur getUtilisateurById(int id);

	Utilisateur getUtilisateurByLoginFetchRoles(String login);

	Utilisateur getOwner(Compte c);

}