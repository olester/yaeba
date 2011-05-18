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
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;
import com.excilys.formation.yaeba.ws.InfoCompte;
import com.excilys.formation.yaeba.ws.InfoVirement;
import com.excilys.formation.yaeba.ws.api.YaebaWebService;
import com.excilys.formation.yaeba.ws.converters.CompteConverter;

@Path("/")
public class YaebaWebServiceImpl implements YaebaWebService {

	@Autowired
	CompteService compteService;

	@Autowired
	OperationService operationService;

	@Autowired
	CompteConverter compteConverter;

	Logger log = LoggerFactory.getLogger(YaebaWebServiceImpl.class);

	@Override
	@GET
	@Produces("application/json")
	@Path("/getCompteByNumero/{numero}")
	public InfoCompte getCompteByNumero(@PathParam("numero") String numero) {
		com.excilys.formation.yaeba.model.Compte c = compteService.getCompteByNumeroCompte(numero);
		return c != null ? compteConverter.convert(c) : new InfoCompte();
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/virement/debiteur/{debiteur}/crediteur/{crediteur}/montant/{montant}")
	public InfoVirement createVirement(@PathParam("debiteur") String debiteur, @PathParam("crediteur") String crediteur, @PathParam("montant") double montant) {
		// TODO Auto-generated method stub
		Compte cCrediteur = compteService.getCompteByNumeroCompte(crediteur);
		Compte cDebiteur = compteService.getCompteByNumeroCompte(debiteur);

		try {
			operationService.createVirement(cDebiteur.getId(), cCrediteur.getId(), montant);
		} catch (PermissionRefuseeException e) {
			log.error(e.getMessage());
		} catch (SoldeInsuffisantException e) {
			log.error(e.getMessage());
		} catch (MontantNegatifException e) {
			log.error(e.getMessage());
		} catch (IdCompteNotFoundException e) {
			log.error(e.getMessage());
		}

		InfoVirement virement = new InfoVirement();
		virement.setCrediteur(compteConverter.convert(cCrediteur));
		virement.setDebiteur(compteConverter.convert(cDebiteur));

		return virement;
	}
}