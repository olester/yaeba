package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.utils.spring.log.logback.test.Logback;
import com.excilys.utils.spring.log.logback.test.LogbackConfigurerTestExecutionListener;
import com.excilys.utils.spring.test.dbunit.DataSet;
import com.excilys.utils.spring.test.dbunit.DataSetTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml", "classpath:/context/test-applicationContext-HSQL.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class, LogbackConfigurerTestExecutionListener.class })
@DataSet("classpath:dataset.xml")
@Logback("classpath:logback-test-dao.xml")
public class CompteDaoImplDBTest {

	@Autowired
	private CompteDao compteDao;
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Test
	public void testGetCompteById() {
		Compte c = compteDao.getCompteById(98);
		assertNull(c);

		c = compteDao.getCompteById(99);
		assertEquals("testcompte", c.getLibelle());
	}

	@Test
	public void testGetCompteByNumeroCompte() {
		Utilisateur u = utilisateurDao.getUtilisateurById(99);
		Compte c = compteDao.getCompteByNumeroCompte(u, "riendutout");
		assertNull(c);

		c = compteDao.getCompteByNumeroCompte(u, "4567");
		assertEquals("testcompte", c.getLibelle());
	}

	@Test
	public void testGetComptes() {
		Utilisateur u = utilisateurDao.getUtilisateurById(99);
		List<Compte> comptes = compteDao.getComptes(u);

		assertEquals(2, comptes.size());
		assertEquals("testcompte", comptes.get(0).getLibelle());
		assertEquals("testcompte2", comptes.get(1).getLibelle());
	}

	@Test
	public void testIsEmpty() {
		Utilisateur u = utilisateurDao.getUtilisateurById(99);
		Compte c = compteDao.getCompteByNumeroCompte(u, "4567");
		assertFalse(compteDao.isEmpty(c));

		c = compteDao.getCompteByNumeroCompte(u, "1234");
		assertTrue(compteDao.isEmpty(c));
	}

}
