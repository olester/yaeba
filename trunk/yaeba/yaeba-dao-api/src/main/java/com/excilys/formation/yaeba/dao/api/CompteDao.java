package com.excilys.formation.yaeba.dao.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface CompteDao {

	Compte getCompteById(int id);

	Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);

	Compte getCompteByNumeroCompte(String numeroCompte);

	List<Compte> getComptes(Utilisateur u);

	boolean isEmpty(Compte c);

}
