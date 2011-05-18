package com.excilys.formation.yaeba.ws.converters;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.ws.InfoUtilisateur;

public class UtilisateurConverter {

	public static InfoUtilisateur utilisateurToInfoUtilisateur(Utilisateur u) {
		InfoUtilisateur iu = new InfoUtilisateur();
		iu.setId(u.getId());
		iu.setNom(u.getNom());
		iu.setPrenom(u.getPrenom());
		return iu;
	}
}
