package com.excilys.formation.yaeba.dao.impl;

//
//import static org.junit.Assert.assertTrue;
//
//import java.io.File;
//import java.sql.Connection;
//
//import javax.sql.DataSource;
//
//import org.dbunit.database.DatabaseConfig;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSet;
//import org.dbunit.operation.DatabaseOperation;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.DataSourceUtils;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * @author excilys
// * 
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
//// @TestExecutionListeners(LogbackConfigurerTestExecutionListener.class)
//// @Logback("classpath:logback-test-dao.xml")
public class PeuplageBaseTest {

	// @Autowired
	// private DataSource dataSource;
	//
	// private IDatabaseConnection dbUnitCon;
	// private Connection con;
	//
	// public PeuplageBaseTest() {
	// }
	//
	// @Before
	// public void init() throws Exception {
	// // Avant le test on insère le dataSet
	// con = DataSourceUtils.getConnection(dataSource);
	// dbUnitCon = new DatabaseConnection(con, "yaeba");
	//
	// DatabaseConfig config = dbUnitCon.getConfig();
	// config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
	//
	// DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, getDataSet());
	//
	// }
	//
	// @After
	// public void after() throws Exception {
	// DatabaseOperation.NONE.execute(dbUnitCon, getDataSet());
	// con.close();
	// }
	//
	// // Chargement d'une bdd de test
	// private IDataSet getDataSet() throws Exception {
	// FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/peuplation.xml"));
	// return f;
	// }
	//
	// @Test
	// public void peupler() {
	//
	// assertTrue(true);
	//
	// }
}
