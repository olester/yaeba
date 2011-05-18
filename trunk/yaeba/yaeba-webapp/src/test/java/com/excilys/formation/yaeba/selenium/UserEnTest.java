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

public class UserEnTest {

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
		selenium.open("/yaeba-webapp/?lang=en");

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Validate']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void afterLogout() throws Exception {
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * Login, logout
	 * 
	 * @throws Exception
	 */

	@Test
	public void testLogoutLogin() throws Exception {
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentication", selenium.getTitle());

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Validate']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());
	}

	/**
	 * Navigation
	 * 
	 * @throws Exception
	 */

	@Test
	public void testNavVirements() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		assertEquals("Transfers", selenium.getTitle());
	}

	@Test
	public void testNavAccueil() throws Exception {
		selenium.click("link=Home");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());
	}

	@Test
	public void testNavComptes() throws Exception {
		selenium.click("link=Accounts");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());
	}

	@Test
	public void testNavLogo() throws Exception {
		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());
	}

	@Test
	public void testSwitchLng() throws Exception {
		selenium.click("//div[@id='footer-content']/span/a[2]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());

		selenium.click("//div[@id='footer-content']/span/a[1]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());
	}

	/**
	 * Erreurs
	 * 
	 * @throws Exception
	 */

	@Test
	public void testErreur403() throws Exception {
		try {
			selenium.open("/yaeba-webapp/admin/untrucadmin");
			selenium.waitForPageToLoad("30000");
		} catch (SeleniumException e) {
			// assertEquals("XHR ERROR: URL = http://localhost:8080/yaeba-webapp/admin/untrucadmin Response_Code = 403 Error_Message = Forbidden",
			// e.getMessage());
		}
		assertEquals("Forbidden", selenium.getTitle());
		selenium.click("link=Back to home");
		selenium.waitForPageToLoad("30000");
	}

	@Test
	public void testErreur404() throws Exception {
		try {
			selenium.open("/yaeba-webapp/nimportequoi");
			selenium.waitForPageToLoad("30000");
		} catch (SeleniumException e) {
			// assertEquals("XHR ERROR: URL = http://localhost:8080/yaeba-webapp/nimportequoi Response_Code = 404 Error_Message = Not Found", e.getMessage());
		}
		assertEquals("Not found", selenium.getTitle());
		selenium.click("link=Back to home");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * Navigation details compte
	 * 
	 * @throws Exception
	 */

	@Test
	public void testDetailsCompte() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");
		assertEquals("Account details", selenium.getTitle());
	}

	@Test
	public void testDetailsCompteRetour() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Back to your accounts list");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());
	}

	@Test
	public void testChoixMois() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");

		selenium.select("mois", "label=April");
		selenium.waitForPageToLoad("30000");
		assertEquals("4", selenium.getValue("mois"));
	}

	@Test
	public void testChoixAnnee() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");

		selenium.select("annee", "label=2010");
		selenium.waitForPageToLoad("30000");
		assertEquals("2010", selenium.getValue("annee"));
	}

	/**
	 * Transfers tests
	 */

	@Test
	public void testVirementMontantNul() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte courant");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, choose a positive amount"));
	}

	@Test
	public void testVirementMontantNegatif() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte courant");
		selenium.type("stringMontant", "-10.0");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, choose a positive amount"));
	}

	@Test
	public void testVirementMontantInvalide() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte courant");
		selenium.type("stringMontant", "mille euros");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, enter a real number"));
	}

	@Test
	public void testVirementCompte1NonSelect() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteRecepteur", "label=Compte courant");
		selenium.type("stringMontant", "10.0");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, choose an source account"));
	}

	@Test
	public void testVirementCompte1et2NonSelect() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, choose an destination account"));
		assertTrue(selenium.isTextPresent("Please, choose an source account"));
	}

	@Test
	public void testVirementComptesIdentiques() throws Exception {
		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteEmetteur", "label=Compte courant");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		selenium.select("compteRecepteur", "label=Compte courant");
		selenium.click("//input[@value='Confirm']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Please, choose two different accounts"));
	}
}
