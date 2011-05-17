package com.excilys.formation.yaeba.service.api;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

/**
 * Il s'agit de l'interface de l'ensemble des services concernant les utilisateurs
 * 
 * @author excilys
 * 
 */
public interface UtilisateurService {

	/**
	 * Cette fonction renvoit l'utilisateur correspondant au login donne, en chargeant egalement ses roles
	 * 
	 * @param login
	 *            le login de l'utilisateur
	 * @return l'utilisateur recherche
	 */
	Utilisateur getUtilisateurByLoginFetchRoles(String login);

	/**
	 * Cette fonction renvoit l'utilisateur qui possede le compte
	 * 
	 * @param c
	 *            le compte dont on recherche le proprietaire
	 * @return l'utilisateur qui possede le compte
	 */
	Utilisateur getOwner(Compte c);
}