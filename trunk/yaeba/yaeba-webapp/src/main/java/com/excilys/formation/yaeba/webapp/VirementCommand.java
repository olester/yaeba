package com.excilys.formation.yaeba.webapp;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class VirementCommand implements Serializable {

	private static final long serialVersionUID = 1218778930018603952L;

	@NotNull(message = "transfers.error.unchosenTransmitter")
	private Integer compteEmetteur;
	private String compteEmetteurLibelle;

	@NotNull(message = "transfers.error.unchosenReceiver")
	private Integer compteRecepteur;
	private String compteRecepteurLibelle;

	@Digits(integer = 10, fraction = 2, message = "transfers.error.amountDouble")
	private String stringMontant;

	public VirementCommand() {

	}

	public Integer getCompteEmetteur() {
		return compteEmetteur;
	}

	public void setCompteEmetteur(Integer compteEmetteur) {
		this.compteEmetteur = compteEmetteur;
	}

	public Integer getCompteRecepteur() {
		return compteRecepteur;
	}

	public void setCompteRecepteur(Integer compteRecepteur) {
		this.compteRecepteur = compteRecepteur;
	}

	public double getMontant() {
		return Double.parseDouble(stringMontant);
	}

	public String getCompteEmetteurLibelle() {
		return compteEmetteurLibelle;
	}

	public void setCompteEmetteurLibelle(String compteEmetteurLibelle) {
		this.compteEmetteurLibelle = compteEmetteurLibelle;
	}

	public String getCompteRecepteurLibelle() {
		return compteRecepteurLibelle;
	}

	public void setCompteRecepteurLibelle(String compteRecepteurLibelle) {
		this.compteRecepteurLibelle = compteRecepteurLibelle;
	}

	public String getStringMontant() {
		return stringMontant;
	}

	public void setStringMontant(String stringMontant) {
		this.stringMontant = stringMontant;
	}

}
