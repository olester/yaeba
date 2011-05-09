package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface CompteDao {

	public Compte getCompteById(String id);

	public Compte getCompteByNumeroCompte(String numeroCompte);

	public void update(Compte c);

	public void save(Compte c);

	public void delete(Compte c);

	public List<Compte> getComptes(Utilisateur u);

}
