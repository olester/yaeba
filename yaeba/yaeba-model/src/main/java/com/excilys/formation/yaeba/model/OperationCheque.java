package com.excilys.formation.yaeba.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

@Entity(name = "operationcheque")
@DiscriminatorValue("OPERATIONCHEQUE")
public class OperationCheque extends Operation {

	private int numeroCheque;

	public OperationCheque() {

	}

	public OperationCheque(String libelle, double montant, DateTime dateCreation, int numeroCheque) {
		super(libelle, montant, dateCreation);
		this.numeroCheque = numeroCheque;
	}

	@Column(name = "numerocheque", nullable = false)
	public int getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(int numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

}
