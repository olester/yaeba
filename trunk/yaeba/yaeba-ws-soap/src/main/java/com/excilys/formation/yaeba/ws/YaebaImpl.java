package com.excilys.formation.yaeba.ws;

import javax.jws.WebService;

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
import com.excilys.formation.yaeba.ws.converters.CompteConverter;

@WebService(endpointInterface = "com.excilys.formation.yaeba.ws.Yaeba")
public class YaebaImpl implements Yaeba {

	@Autowired
	CompteService compteService;

	@Autowired
	CompteConverter compteConverter;

	@Autowired
	OperationService operationService;

	private final static Logger logger = LoggerFactory.getLogger(YaebaImpl.class);

	@Override
	public InfoCompte getCompteByNumero(String numero) {
		InfoCompte result = null;
		try {
			result = compteConverter.convert(compteService.getCompteByNumeroCompte(numero));
		} catch (NumeroCompteNotFoundException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public InfoVirement passerVirement(String crediteur, String debiteur, double montant) {
		Compte cCrediteur = null;
		Compte cDebiteur = null;
		try {
			cCrediteur = compteService.getCompteByNumeroCompte(crediteur);
			cDebiteur = compteService.getCompteByNumeroCompte(debiteur);
			operationService.createVirement(cDebiteur.getId(), cCrediteur.getId(), montant);
		} catch (NumeroCompteNotFoundException e) {
			logger.error(e.getMessage());
		} catch (PermissionRefuseeException e) {
			logger.error(e.getMessage());
		} catch (SoldeInsuffisantException e) {
			logger.error(e.getMessage());
		} catch (MontantNegatifException e) {
			logger.error(e.getMessage());
		} catch (IdCompteNotFoundException e) {
			logger.error(e.getMessage());
		}

		InfoVirement virement = new InfoVirement();
		virement.setCrediteur(compteConverter.convert(cCrediteur));
		virement.setDebiteur(compteConverter.convert(cDebiteur));

		return virement;
	}

}
