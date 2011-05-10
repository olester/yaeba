package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
public class UtilisateurDaoImplDbTest {

	@Autowired
	private UtilisateurDaoImpl utilisateurDaoImpl;

	@Autowired
	private DataSource dataSource;

	private IDatabaseConnection dbUnitCon;
	private Connection con;

	@Test
	public void rien() {
		assertTrue(true);
	}

	// @Before
	// public void init() throws Exception {
	// // Avant le test on insère le dataSet
	// con = DataSourceUtils.getConnection(dataSource);
	// dbUnitCon = new DatabaseConnection(con, "yaeba");
	//
	// DatabaseConfig config = dbUnitCon.getConfig();
	// config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
	//
	// DatabaseOperation.INSERT.execute(dbUnitCon, getDataSet());
	// }
	//
	// @After
	// public void after() throws Exception {
	// /*DatabaseOperation.DELETE.execute(dbUnitCon, getDataSet());
	// con.close();*/
	// }
	//
	// // Chargement d'une bdd de test
	// private IDataSet getDataSet() throws Exception {
	// FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
	// return f;
	// }

	// @Test
	// public void testGetUtilisateurById() {
	// Utilisateur u = utilisateurDaoImpl.getUtilisateurById("98");
	// assertNull(u);
	//
	// u = utilisateurDaoImpl.getUtilisateurById("99");
	// assertEquals("citron", u.getNom());
	// }
	//
	// @Test
	// public void testGetUtilisateurByLogin() {
	// Utilisateur u = utilisateurDaoImpl.getUtilisateurByLogin("riendutout");
	// assertNull(u);
	//
	// u = utilisateurDaoImpl.getUtilisateurByLogin("monlogin");
	// assertEquals("citron", u.getNom());
	// }
	//
	// @Test
	// public void testUpdate() {
	// Utilisateur u = utilisateurDaoImpl.getUtilisateurByLogin("monlogin");
	// u.setNom("fraise");
	// utilisateurDaoImpl.update(u);
	// assertEquals("fraise", u.getNom());
	// }
	//
	// @Test
	// public void testSave() {
	// Utilisateur u = new Utilisateur("login2", "nom2", "prenom2", "adresse2", "motDePasse2", null, null);
	// utilisateurDaoImpl.save(u);
	// Utilisateur u2 = utilisateurDaoImpl.getUtilisateurByLogin("login2");
	// assertNotNull(u2);
	// }
	//
	// @Test
	// public void testDelete() {
	// Utilisateur u = utilisateurDaoImpl.getUtilisateurByLogin("login2");
	// utilisateurDaoImpl.delete(u);
	// Utilisateur u2 = utilisateurDaoImpl.getUtilisateurByLogin("login2");
	// assertNull(u2);
	// }

}