package com.excilys.formation.yaeba.service.api;

import java.util.List;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;

public interface OperationService {

	public Operation getOperationById(String id);

	public void update(Operation o);

	public void save(Operation o);

	public void delete(Operation o);

	public List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois);

	public List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois);

}
