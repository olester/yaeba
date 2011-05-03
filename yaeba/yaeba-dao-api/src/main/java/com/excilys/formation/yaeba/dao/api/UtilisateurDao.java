package com.excilys.formation.yaeba.dao.api;

import com.excilys.formation.yaeba.model.Utilisateur;

public interface UtilisateurDao {

	public Utilisateur getUtilisateurById(String id);

	public Utilisateur getUtilisateurByLogin(String login);

	public void update(Utilisateur u);

	public void save(Utilisateur u);

	public void delete(Utilisateur u);

}