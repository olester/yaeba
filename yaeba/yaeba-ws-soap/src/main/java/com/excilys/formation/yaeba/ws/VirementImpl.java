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

@WebService(endpointInterface = "com.excilys.formation.yaeba.ws.Virement")
public class VirementImpl implements Virement {

	@Autowired
	CompteService compteService;

	@Autowired
	OperationService operationService;

	@Autowired
	CompteConverter compteConverter;

	Logger log = LoggerFactory.getLogger(VirementImpl.class);

	@Override
	public InfoVirement passerVirement(String compteCrediteur, String compteDebiteur, double montant) {
		Compte cCrediteur = null;
		Compte cDebiteur = null;

		try {
			cCrediteur = compteService.getCompteByNumeroCompte(compteCrediteur);
			cDebiteur = compteService.getCompteByNumeroCompte(compteDebiteur);
			operationService.createVirement(cDebiteur.getId(), cCrediteur.getId(), montant);
		} catch (PermissionRefuseeException e) {
			log.error(e.getMessage());
		} catch (SoldeInsuffisantException e) {
			log.error(e.getMessage());
		} catch (MontantNegatifException e) {
			log.error(e.getMessage());
		} catch (IdCompteNotFoundException e) {
			log.error(e.getMessage());
		} catch (NumeroCompteNotFoundException e) {
			log.error(e.getMessage());
		}

		InfoVirement virement = new InfoVirement();
		virement.setCrediteur(compteConverter.convert(cCrediteur));
		virement.setDebiteur(compteConverter.convert(cDebiteur));

		return virement;
	}
}
