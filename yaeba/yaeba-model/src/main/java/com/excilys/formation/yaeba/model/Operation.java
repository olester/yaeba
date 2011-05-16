package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "operation")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation implements Serializable {

	private static final long serialVersionUID = -2365460360189503621L;

	private Integer id;
	private String libelle;
	private double montant;
	private DateTime dateCreation;
	private Compte compte;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "libelle", nullable = false)
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Column(name = "montant", nullable = false)
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	@Column(name = "datecreation", nullable = false)
	@Type(type = "jodaDateTime")
	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "compte_id")
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compte == null) ? 0 : compte.getId().hashCode());
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(montant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Operation other = (Operation) obj;
		if (compte == null) {
			if (other.compte != null) return false;
		} else if (!compte.getId().equals(other.compte.getId())) return false;
		if (dateCreation == null) {
			if (other.dateCreation != null) return false;
		} else if (!dateCreation.equals(other.dateCreation)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (libelle == null) {
			if (other.libelle != null) return false;
		} else if (!libelle.equals(other.libelle)) return false;
		if (Double.doubleToLongBits(montant) != Double.doubleToLongBits(other.montant)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", libelle=" + libelle + ", montant=" + montant + ", dateCreation=" + dateCreation + ", compte=" + compte.getId() + "]";
	}
}
