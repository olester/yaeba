package com.excilys.formation.yaeba.service.api;

import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurService {

	public Utilisateur getUtilisateurById(String id);

	public void update(Utilisateur u);

	public void save(Utilisateur u);

	public void delete(Utilisateur u);

	public Utilisateur getUtilisateurByLogin(String login);
}