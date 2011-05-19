package com.excilys.formation.yaeba.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.exception.NumeroCompteNotFoundException;
import com.excilys.formation.yaeba.ws.converters.CompteConverter;

@WebService(endpointInterface = "com.excilys.formation.yaeba.ws.Compte")
public class CompteImpl implements Compte {

	@Autowired
	CompteService compteService;

	@Autowired
	CompteConverter compteConverter;

	Logger logger = LoggerFactory.getLogger(CompteImpl.class);

	@Override
	@WebMethod
	public InfoCompte getCompteByNumero(String numero) {
		com.excilys.formation.yaeba.model.Compte c = null;
		try {
			c = compteService.getCompteByNumeroCompte(numero);
		} catch (NumeroCompteNotFoundException e) {
			logger.warn(e.getMessage());
		}
		return compteConverter.convert(c);
	}
}
