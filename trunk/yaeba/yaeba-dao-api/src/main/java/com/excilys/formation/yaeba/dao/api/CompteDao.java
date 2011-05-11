package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface CompteDao {

	public Compte getCompteById(String id);

	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);

	public List<Compte> getComptes(Utilisateur u);

	public boolean isEmpty(Compte c);

}
