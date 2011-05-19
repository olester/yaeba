package com.excilys.formation.yaeba.ws.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.ws.InfoCompte;

@Component
public class CompteConverter implements Converter<Compte, InfoCompte> {

	@Autowired
	private UtilisateurConverter utilisateurConverter;

	@Override
	public InfoCompte convert(Compte c) {
		InfoCompte ic = new InfoCompte();
		ic.setId(c.getId());
		ic.setLibelle(c.getLibelle());
		ic.setNumero(c.getNumeroCompte());
		ic.setSolde(c.getSoldeCourant());
		ic.setPrevisional(c.getEncoursCarte());
		ic.setUtilisateur(utilisateurConverter.convert(c.getUtilisateur()));
		return ic;
	}
}
