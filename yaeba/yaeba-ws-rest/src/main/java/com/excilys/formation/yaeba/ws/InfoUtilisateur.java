package com.excilys.formation.yaeba.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "utilisateur")
public class InfoUtilisateur {

	private Integer id;
	private String nom;
	private String prenom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
