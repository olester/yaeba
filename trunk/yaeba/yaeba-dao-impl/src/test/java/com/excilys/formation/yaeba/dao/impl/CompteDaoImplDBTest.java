package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
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
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
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
		Compte c = compteDao.getCompteById("98");
		assertNull(c);

		c = compteDao.getCompteById("99");
		assertEquals("testcompte", c.getLibelle());
	}

	@Test
	public void testGetCompteByNumeroCompte() {
		Compte c = compteDao.getCompteByNumeroCompte("riendutout");
		assertNull(c);

		c = compteDao.getCompteByNumeroCompte("4567");
		assertEquals("testcompte", c.getLibelle());
	}

	@Test
	public void testUpdate() {
		Compte c = compteDao.getCompteByNumeroCompte("4567");
		c.setNumeroCompte("fraise");
		compteDao.update(c);
		assertEquals("fraise", c.getNumeroCompte());
	}

	@Test
	public void testSave() {
		Utilisateur u = utilisateurDao.getUtilisateurById("99");
		Compte c = new Compte("aaa", "bbb", null, new DateTime(), 10.2f);
		u.addCompte(c);
		compteDao.save(c);
		Compte c2 = compteDao.getCompteByNumeroCompte("aaa");
		assertNotNull(c2);
	}

	@Test
	public void testDelete() {
		Compte c = compteDao.getCompteByNumeroCompte("4567");
		assertNotNull(c);
		compteDao.delete(c);
		Compte c2 = compteDao.getCompteByNumeroCompte("4567");
		assertNull(c2);
	}
}
