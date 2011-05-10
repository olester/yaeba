package com.excilys.formation.yaeba.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

@Entity(name = "operationcartebancaire")
@DiscriminatorValue("OPERATIONCARTEBANCAIRE")
public class OperationCarteBancaire extends Operation {

	private DateTime dateEffective;

	public OperationCarteBancaire() {

	}

	public OperationCarteBancaire(String libelle, double montant, DateTime dateCreation, DateTime dateEffective) {
		super(libelle, montant, dateCreation);
		this.dateEffective = dateEffective;
	}

	@Column(name = "dateeffective", nullable = false)
	public DateTime getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(DateTime dateEffective) {
		this.dateEffective = dateEffective;
	}

}
