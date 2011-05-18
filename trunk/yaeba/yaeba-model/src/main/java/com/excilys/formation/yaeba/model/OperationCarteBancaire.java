package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "operationcartebancaire")
public class OperationCarteBancaire extends Operation implements Serializable {

	private static final long serialVersionUID = 959175591783754961L;

	private DateTime dateEffective;

	@Column(name = "dateeffective", nullable = false)
	public DateTime getDateEffective() {
		return dateEffective;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateEffective == null) ? 0 : dateEffective.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		OperationCarteBancaire other = (OperationCarteBancaire) obj;
		if (dateEffective == null) {
			if (other.dateEffective != null) return false;
		} else if (dateEffective.getDayOfYear() != other.dateEffective.getDayOfYear() || dateEffective.getYear() != other.dateEffective.getYear()) return false;
		return true;
	}

	public void setDateEffective(DateTime dateEffective) {
		this.dateEffective = dateEffective;
	}

}
