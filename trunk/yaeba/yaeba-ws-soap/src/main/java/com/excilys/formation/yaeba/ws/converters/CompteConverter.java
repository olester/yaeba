package com.excilys.formation.yaeba.ws.converters;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.ws.InfoCompte;

public class CompteConverter {

	public static InfoCompte compteToInfoCompte(Compte c) {
		InfoCompte ic = new InfoCompte();
		ic.setId(c.getId());
		ic.setLibelle(c.getLibelle());
		ic.setNumero(c.getNumeroCompte());
		ic.setSolde(c.getSoldeCourant());
		ic.setPrevisional(c.getEncoursCarte());
		ic.setUtilisateur(UtilisateurConverter.utilisateurToInfoUtilisateur(c.getUtilisateur()));
		return ic;
	}
}
