package com.excilys.formation.yaeba.ws.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.ws.InfoUtilisateur;

@Component
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
