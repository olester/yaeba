package com.excilys.formation.yaeba.ws.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.MontantNegatifException;
import com.excilys.formation.yaeba.service.api.exception.NumeroCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;
import com.excilys.formation.yaeba.ws.InfoCompte;
import com.excilys.formation.yaeba.ws.InfoVirement;
import com.excilys.formation.yaeba.ws.api.YaebaWebService;
import com.excilys.formation.yaeba.ws.converters.CompteConverter;
import com.excilys.formation.yaeba.ws.exception.IntrouvableException;

@Path("/")
public class YaebaWebServiceImpl implements YaebaWebService {

	@Autowired
	CompteService compteService;

	@Autowired
	OperationService operationService;

	@Autowired
	CompteConverter compteConverter;

	Logger logger = LoggerFactory.getLogger(YaebaWebServiceImpl.class);

	@Override
	@GET
	@Produces("application/json")
	@Path("/getCompteByNumero/{numero}")
	public InfoCompte getCompteByNumero(@PathParam("numero") String numero) {
		Compte c;
		try {
			c = compteService.getCompteByNumeroCompte(numero);
		} catch (NumeroCompteNotFoundException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		}
		return compteConverter.convert(c);
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/virement/debiteur/{debiteur}/crediteur/{crediteur}/montant/{montant}")
	public InfoVirement createVirement(@PathParam("debiteur") String debiteur, @PathParam("crediteur") String crediteur, @PathParam("montant") double montant) {

		Compte cCrediteur;
		Compte cDebiteur;
		try {
			cCrediteur = compteService.getCompteByNumeroCompte(crediteur);
			cDebiteur = compteService.getCompteByNumeroCompte(debiteur);
			operationService.createVirement(cDebiteur.getId(), cCrediteur.getId(), montant);
		} catch (PermissionRefuseeException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		} catch (SoldeInsuffisantException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		} catch (MontantNegatifException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		} catch (IdCompteNotFoundException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		} catch (NumeroCompteNotFoundException e) {
			logger.warn(e.getMessage());
			throw new IntrouvableException(e.getMessage());
		}

		InfoVirement virement = new InfoVirement();
		virement.setCrediteur(compteConverter.convert(cCrediteur));
		virement.setDebiteur(compteConverter.convert(cDebiteur));

		return virement;
	}
}