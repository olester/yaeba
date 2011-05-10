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
public class CompteDaoImplDBTest {

	@Autowired
	private CompteDaoImpl compteDaoImpl;
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
	// DatabaseOperation.DELETE.execute(dbUnitCon, getDataSet());
	// con.close();
	// }
	//
	// // Chargement d'une bdd de test
	// private IDataSet getDataSet() throws Exception {
	// FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
	// return f;
	// }
	//
	// @Test
	// public void testGetCompteById() {
	// Compte c = compteDaoImpl.getCompteById("98");
	// assertNull(c);
	//
	// c = compteDaoImpl.getCompteById("99");
	// assertEquals("testcompte", c.getLibelle());
	// }
	//
	// @Test
	// public void testGetCompteByNumeroCompte() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("riendutout");
	// assertNull(c);
	//
	// c = compteDaoImpl.getCompteByNumeroCompte("4567");
	// assertEquals("testcompte", c.getLibelle());
	// }
	//
	// @Test
	// public void testUpdate() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("4567");
	// c.setNumeroCompte("fraise");
	// compteDaoImpl.update(c);
	// assertEquals("fraise", c.getNumeroCompte());
	// }
	//
	// // @Test
	// // public void testSave() {
	// // Utilisateur u = utilisateurDaoImpl.getUtilisateurById("99");
	// // Compte c = new Compte("aaa", "bbb", null, new Date(), 10.2f);
	// // u.addCompte(c);
	// // compteDaoImpl.save(c);
	// // Compte c2 = compteDaoImpl.getCompteByNumeroCompte(u, "aaa");
	// // assertNotNull(c2);
	// // }
	//
	// @Test
	// public void testDelete() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("4567");
	// assertNotNull(c);
	// compteDaoImpl.delete(c);
	// Compte c2 = compteDaoImpl.getCompteByNumeroCompte("4567");
	// assertNull(c2);
	// }
}
