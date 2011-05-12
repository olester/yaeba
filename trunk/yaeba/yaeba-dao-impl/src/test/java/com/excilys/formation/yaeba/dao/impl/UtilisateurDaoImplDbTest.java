package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
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
public class UtilisateurDaoImplDbTest {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Test
	public void testGetUtilisateurById() {
		Utilisateur u = utilisateurDao.getUtilisateurById(98);
		assertNull(u);

		u = utilisateurDao.getUtilisateurById(99);
		assertEquals("citron", u.getNom());
	}

	@Test
	public void testGetUtilisateurByLogin() {
		Utilisateur u = utilisateurDao.getUtilisateurByLoginFetchRoles("riendutout");
		assertNull(u);

		u = utilisateurDao.getUtilisateurByLoginFetchRoles("monlogin");
		assertEquals("citron", u.getNom());
	}

}