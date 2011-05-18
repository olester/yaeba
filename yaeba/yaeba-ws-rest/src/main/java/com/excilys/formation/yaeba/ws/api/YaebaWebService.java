package com.excilys.formation.yaeba.ws.api;

import com.excilys.formation.yaeba.ws.InfoCompte;
import com.excilys.formation.yaeba.ws.InfoVirement;

public interface YaebaWebService {

	InfoCompte getCompteByNumero(String numero);

	InfoVirement createVirement(String debiteur, String crediteur, double montant);
}
