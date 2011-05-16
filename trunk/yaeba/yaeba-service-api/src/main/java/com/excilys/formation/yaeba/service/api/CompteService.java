package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;

public interface CompteService {

	Compte getCompteById(int id);

	List<Compte> getComptesByUtilisateur(Utilisateur u);

	boolean isEmpty(Compte c);

	Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte);

	double getEncoursCarte(Compte c) throws NoCardException;

	boolean isApprovisionne(Compte c, double montant);
}
