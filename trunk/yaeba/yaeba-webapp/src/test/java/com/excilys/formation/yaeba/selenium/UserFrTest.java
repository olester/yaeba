package com.excilys.formation.yaeba.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class UserFrTest {

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

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void afterLogout() throws Exception {
		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
	}

	@Test
	public void testLogoutLogin() throws Exception {
		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentification", selenium.getTitle());

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

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
	public void testSwitchLng() throws Exception {
		selenium.click("//div[@id='footer-content']/span/a[1]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("//div[@id='footer-content']/span/a[2]/img");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

	@Test
	@Ignore
	public void testErreur403() throws Exception {
		selenium.open("/yaeba-webapp/admin/untrucadmin");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accès interdit", selenium.getTitle());
	}

	@Test
	@Ignore
	public void testErreur404() throws Exception {
		selenium.open("/yaeba-webapp/user/nimportequoi.html");
		selenium.waitForPageToLoad("30000");
		assertEquals("Page perdue", selenium.getTitle());
	}

	@Test
	public void testDetailsCompte() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");
		assertEquals("Détails du compte", selenium.getTitle());
	}

	@Test
	public void testDetailsCompteRetour() throws Exception {
		selenium.click("link=123777");
		selenium.waitForPageToLoad("30000");

		selenium.click("link=Retourner à la liste de vos comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());
	}

	// @Test
	// @Ignore
	// public void testChoixMois() throws Exception {
	// selenium.click("link=123777");
	// selenium.waitForPageToLoad("30000");
	//
	// selenium.select("mois", "label=Avril");
	// selenium.waitForPageToLoad("30000");
	// }

}
