package com.excilys.formation.yaeba.ws.converters;

import org.springframework.core.convert.converter.Converter;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.ws.InfoCompte;

public class CompteConverter implements Converter<Compte, InfoCompte> {

	@Override
	public InfoCompte convert(Compte c) {
		InfoCompte ic = new InfoCompte();
		ic.setId(c.getId());
		ic.setLibelle(c.getLibelle());
		ic.setNumero(c.getNumeroCompte());
		ic.setSolde(c.getSoldeCourant());
		ic.setPrevisional(c.getEncoursCarte());
		ic.setUtilisateur(new UtilisateurConverter().convert(c.getUtilisateur()));
		return ic;
	}
}
