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

	@Test
	@Ignore
	public void testErreur403() throws Exception {
		selenium.open("/yaeba-webapp/admin/untrucadmin");
		selenium.waitForPageToLoad("30000");
		assertEquals("Forbidden", selenium.getTitle());
	}

	@Test
	@Ignore
	public void testErreur404() throws Exception {
		selenium.open("/yaeba-webapp/user/nimportequoi.html");
		selenium.waitForPageToLoad("30000");
		assertEquals("Not found", selenium.getTitle());
	}

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

}
