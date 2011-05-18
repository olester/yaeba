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
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;
import com.excilys.formation.yaeba.ws.converters.CompteConverter;

@WebService(endpointInterface = "com.excilys.formation.yaeba.ws.Virement")
public class VirementImpl implements Virement {

	@Autowired
	CompteService compteService;

	@Autowired
	OperationService operationService;

	Logger log = LoggerFactory.getLogger(VirementImpl.class);

	@Override
	public InfoVirement passerVirement(String compteCrediteur, String compteDebiteur, double montant) {
		Compte cCrediteur = compteService.getCompteByNumeroCompte(compteCrediteur);
		Compte cDebiteur = compteService.getCompteByNumeroCompte(compteDebiteur);

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
		virement.setCrediteur(CompteConverter.compteToInfoCompte(cCrediteur));
		virement.setDebiteur(CompteConverter.compteToInfoCompte(cDebiteur));

		return virement;
	}
}
