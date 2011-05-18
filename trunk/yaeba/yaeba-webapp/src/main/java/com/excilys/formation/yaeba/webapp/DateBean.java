package com.excilys.formation.yaeba.webapp;

import org.joda.time.DateTime;

public class DateBean {

	private int annee;
	private int mois;

	public DateBean() {
	}
	
	public DateBean(DateTime dt) {
		this.annee = dt.getYear();
		this.mois = dt.getMonthOfYear();
	}
	
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

}
