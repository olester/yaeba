package com.excilys.formation.yaeba.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

public class AdminFrTest {

	static Selenium selenium;

	@BeforeClass
	public static void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
		selenium.start();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}

	@Before
	public void beforeLogin() throws Exception {
		selenium.open("/yaeba-webapp/?lang=fr");

		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void afterLogout() throws Exception {
		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * Login, logout
	 * 
	 * @throws Exception
	 */

	@Test
	public void testLogoutLogin() throws Exception {
		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentification", selenium.getTitle());

		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());
	}

	/**
	 * Navigation
	 * 
	 * @throws Exception
	 */

	@Test
	public void testNavVirements() throws Exception {
		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		assertEquals("Virements", selenium.getTitle());
	}

	@Test
	public void testNavAccueil() throws Exception {
		selenium.click("link=Accueil");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());
	}

	@Test
	public void testNavComptes() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

	@Test
	public void testNavLogo() throws Exception {
		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());
	}

	@Test
	public void testNavAdmin() throws Exception {
		selenium.click("link=Admin");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());
	}

	@Test
	public void testSwitchLng() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");
		selenium.click("//div[@id='footer-content']/span/a[1]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("//div[@id='footer-content']/span/a[2]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

	/**
	 * Erreurs
	 * 
	 * @throws Exception
	 */

	@Test
	public void testErreur404() throws Exception {
		try {
			selenium.open("/yaeba-webapp/nimportequoi");
			selenium.waitForPageToLoad("30000");
		} catch (SeleniumException e) {
			// assertEquals("XHR ERROR: URL = http://localhost:8080/yaeba-webapp/nimportequoi Response_Code = 404 Error_Message = Not Found", e.getMessage());
		}
		assertEquals("Page perdue", selenium.getTitle());
		selenium.click("link=Revenir à l'accueil");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * Navigation details compte
	 * 
	 * @throws Exception
	 */

	@Test
	public void testDetailsCompte() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=666");
		selenium.waitForPageToLoad("30000");
		assertEquals("Détails du compte", selenium.getTitle());
	}

	@Test
	public void testDetailsCompteRetour() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=666");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Retourner à la liste de vos comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

	@Test
	public void testChoixMois() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=666");
		selenium.waitForPageToLoad("30000");

		selenium.select("mois", "label=Avril");
		selenium.waitForPageToLoad("30000");
		assertEquals("4", selenium.getValue("mois"));
	}

	@Test
	public void testChoixAnnee() throws Exception {
		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=666");
		selenium.waitForPageToLoad("30000");

		selenium.select("annee", "label=2010");
		selenium.waitForPageToLoad("30000");
		assertEquals("2010", selenium.getValue("annee"));
	}

	/**
	 * Tests Virements
	 */

	@Test
	public void testVirementMontantInvalide() throws Exception {
		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte jeune");
		selenium.type("stringMontant", "mille euros");
		selenium.click("//input[@value='Confirmer']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Veuillez saisir une valeur chiffrée"));
	}

	@Test
	public void testVirementCompte1NonSelect() throws Exception {
		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteRecepteur", "label=Compte jeune");
		selenium.type("stringMontant", "10.0");
		selenium.click("//input[@value='Confirmer']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Veuillez sélectionner un compte émetteur"));
	}

	@Test
	public void testVirementCompte1et2NonSelect() throws Exception {
		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Confirmer']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Veuillez sélectionner un compte récepteur"));
		assertTrue(selenium.isTextPresent("Veuillez sélectionner un compte émetteur"));
	}

	@Test
	public void testVirementComptesIdentiques() throws Exception {
		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte jeune");
		selenium.click("//input[@value='Confirmer']");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteRecepteur", "label=Compte jeune");
		selenium.click("//input[@value='Confirmer']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Veuillez choisir deux comptes différents"));
	}

}
