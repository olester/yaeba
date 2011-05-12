package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface CompteService {

	Compte getCompteById(int id);

	List<Compte> getComptesByUtilisateur(Utilisateur u);

	boolean isEmpty(Compte c);

	Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);
}
