package com.excilys.formation.yaeba.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "compte")
public class InfoCompte {

	private Integer id;
	private String numero;
	private String libelle;
	private double solde;
	protected double previsional;
	private InfoUtilisateur utilisateur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getPrevisional() {
		return previsional;
	}

	public void setPrevisional(double previsional) {
		this.previsional = previsional;
	}

	public InfoUtilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(InfoUtilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
