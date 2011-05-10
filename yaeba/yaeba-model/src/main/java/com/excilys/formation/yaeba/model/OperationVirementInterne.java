package com.excilys.formation.yaeba.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity(name = "operationvirementinterne")
@DiscriminatorValue("OPERATIONVIREMENTINTERNE")
public class OperationVirementInterne extends Operation {

	private Compte compteRecepteur;
	private Compte compteDebiteur;

	private DateTime dateEffective;

	public OperationVirementInterne() {

	}

	public OperationVirementInterne(String libelle, double montant, DateTime dateCreation, int numeroCheque, Compte compteRecepteur, DateTime dateEffective,
			Compte compteDebiteur) {
		super(libelle, montant, dateCreation);
		this.compteRecepteur = compteRecepteur;
		this.dateEffective = dateEffective;
		this.compteDebiteur = compteDebiteur;

	}

	@ManyToOne(targetEntity = Compte.class)
	@JoinColumn(name = "compterecepteur")
	public Compte getCompteRecepteur() {
		return compteRecepteur;
	}

	public void setCompteRecepteur(Compte compteRecepteur) {
		this.compteRecepteur = compteRecepteur;
	}

	@Column(name = "dateeffective", nullable = false)
	public DateTime getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(DateTime dateEffective) {
		this.dateEffective = dateEffective;
	}

	@ManyToOne(targetEntity = Compte.class)
	@JoinColumn(name = "comptedebiteur")
	public Compte getCompteDebiteur() {
		return compteDebiteur;
	}

	public void setCompteDebiteur(Compte compteDebiteur) {
		this.compteDebiteur = compteDebiteur;
	}
}
