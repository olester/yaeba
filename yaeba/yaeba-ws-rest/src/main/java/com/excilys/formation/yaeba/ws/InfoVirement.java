package com.excilys.formation.yaeba.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "compte")
public class InfoVirement {

	protected InfoCompte debiteur;
	protected InfoCompte crediteur;

	public InfoCompte getDebiteur() {
		return debiteur;
	}

	public void setDebiteur(InfoCompte debiteur) {
		this.debiteur = debiteur;
	}

	public InfoCompte getCrediteur() {
		return crediteur;
	}

	public void setCrediteur(InfoCompte crediteur) {
		this.crediteur = crediteur;
	}
}
