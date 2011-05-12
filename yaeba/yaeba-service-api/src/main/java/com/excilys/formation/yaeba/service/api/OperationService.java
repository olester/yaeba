package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;

public interface OperationService {

	Operation getOperationById(int id);

	List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois);

	List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois);
}
