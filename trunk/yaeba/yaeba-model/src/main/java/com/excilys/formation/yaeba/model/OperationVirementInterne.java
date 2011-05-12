package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity(name = "operationvirementinterne")
@DiscriminatorValue("OPERATIONVIREMENTINTERNE")
public class OperationVirementInterne extends Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3815970073513291893L;
	private Compte compteRecepteur;
	private Compte compteDebiteur;

	private DateTime dateEffective;

	public OperationVirementInterne() {

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
