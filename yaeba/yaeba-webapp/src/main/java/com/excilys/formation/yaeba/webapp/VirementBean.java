package com.excilys.formation.yaeba.webapp;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class VirementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6572986037227011156L;
	private Integer compteEmetteur;
	private Integer compteRecepteur;
	private double montant;

	public VirementBean() {

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
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

}
