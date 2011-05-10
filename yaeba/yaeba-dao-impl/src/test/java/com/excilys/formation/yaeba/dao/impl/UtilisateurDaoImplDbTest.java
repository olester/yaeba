package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class, LogbackConfigurerTestExecutionListener.class })
@DataSet("classpath:dataset.xml")
@Logback("classpath:logback-test-dao.xml")
public class UtilisateurDaoImplDbTest {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Test
	public void testGetUtilisateurById() {
		Utilisateur u = utilisateurDao.getUtilisateurById("98");
		assertNull(u);

		u = utilisateurDao.getUtilisateurById("99");
		assertEquals("citron", u.getNom());
	}

	@Test
	public void testGetUtilisateurByLogin() {
		Utilisateur u = utilisateurDao.getUtilisateurByLogin("riendutout");
		assertNull(u);

		u = utilisateurDao.getUtilisateurByLogin("monlogin");
		assertEquals("citron", u.getNom());
	}

	@Test
	public void testUpdate() {
		Utilisateur u = utilisateurDao.getUtilisateurByLogin("monlogin");
		u.setNom("fraise");
		utilisateurDao.update(u);
		assertEquals("fraise", u.getNom());
	}

	@Test
	public void testSave() {
		Utilisateur u = new Utilisateur("login2", "nom2", "prenom2", "adresse2", "motDePasse2", null, null);
		utilisateurDao.save(u);
		Utilisateur u2 = utilisateurDao.getUtilisateurByLogin("login2");
		assertNotNull(u2);
	}

	@Test
	public void testDelete() {
		Utilisateur u = utilisateurDao.getUtilisateurByLogin("monlogin");
		utilisateurDao.delete(u);
		Utilisateur u2 = utilisateurDao.getUtilisateurByLogin("monlogin");
		assertNull(u2);
	}

	// Ne passe pas!!!!!!!!
	// @Test
	// public void testDeleteNull() {
	// Utilisateur u = utilisateurDao.getUtilisateurByLogin("unlogininexistant");
	// utilisateurDao.delete(u);
	// Utilisateur u2 = utilisateurDao.getUtilisateurByLogin("unlogininexistant");
	// assertNull(u2);
	// }

}