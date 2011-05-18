package com.excilys.formation.yaeba.ws.converters;

import org.springframework.core.convert.converter.Converter;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.ws.InfoUtilisateur;

public class UtilisateurConverter implements Converter<Utilisateur, InfoUtilisateur> {

	@Override
	public InfoUtilisateur convert(Utilisateur u) {
		InfoUtilisateur iu = new InfoUtilisateur();
		iu.setId(u.getId());
		iu.setNom(u.getNom());
		iu.setPrenom(u.getPrenom());
		return iu;
	}
}
