package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

@Entity(name = "operationcartebancaire")
@DiscriminatorValue("OPERATIONCARTEBANCAIRE")
public class OperationCarteBancaire extends Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 959175591783754961L;
	private DateTime dateEffective;

	public OperationCarteBancaire() {

	}

	@Column(name = "dateeffective", nullable = false)
	public DateTime getDateEffective() {
		return dateEffective;
	}

	public void setDateEffective(DateTime dateEffective) {
		this.dateEffective = dateEffective;
	}

}
