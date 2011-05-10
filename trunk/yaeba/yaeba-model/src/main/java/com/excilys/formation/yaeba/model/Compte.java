package com.excilys.formation.yaeba.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "compte")
public class Compte {

	private Integer id;
	private String numeroCompte;
	private String libelle;
	private Set<Operation> operations;
	private DateTime dateCreation;
	private float soldeCourant;

	public Compte() {
		operations = new HashSet<Operation>();
	}

	public Compte(String numeroCompte, String libelle, Set<Operation> operations, DateTime dateCreation, float soldeCourant) {
		this.numeroCompte = numeroCompte;
		this.operations = operations;
		this.dateCreation = dateCreation;
		this.libelle = libelle;
		this.soldeCourant = soldeCourant;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "numerocompte", unique = true, nullable = false)
	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	@Column(name = "libelle", unique = false, nullable = false)
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "compte_id")
	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	public Set<Operation> getOperationsByDate(int annee, int mois) {
		Set<Operation> operations = new HashSet<Operation>();
		for (Operation o : getOperations())
			if (o.getDateCreation().getYear() + 1900 == annee && o.getDateCreation().getMonthOfYear() + 1 == mois)
				operations.add(o);
		return operations;
	}

	@Column(name = "datecreation", nullable = false)
	@Type(type = "jodaDateTime")
	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column(name = "soldecourant", nullable = false)
	public float getSoldeCourant() {
		return soldeCourant;
	}

	public void setSoldeCourant(float soldeCourant) {
		this.soldeCourant = soldeCourant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroCompte == null) ? 0 : numeroCompte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (numeroCompte == null) {
			if (other.numeroCompte != null)
				return false;
		} else if (!numeroCompte.equals(other.numeroCompte))
			return false;
		return true;
	}

}
