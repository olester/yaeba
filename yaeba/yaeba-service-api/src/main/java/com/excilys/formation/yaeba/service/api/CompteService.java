package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;

public interface CompteService {

	public Compte getCompteById(String id);

	public List<Compte> getComptesByUtilisateur(Utilisateur u);

	public boolean isEmpty(Compte c);

	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);
}
