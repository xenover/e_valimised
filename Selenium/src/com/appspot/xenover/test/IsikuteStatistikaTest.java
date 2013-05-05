package com.appspot.xenover.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.regex.Pattern;

public class IsikuteStatistikaTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://xenover.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testIsikute_statistika() throws Exception {
		selenium.open("/");
		Thread.sleep(2000);
		selenium.click("id=statistika");
		Thread.sleep(5000);
		assertTrue(selenium.isVisible("class=statistika"));
		assertFalse(selenium.isVisible("loader"));
		// Perekonnanime test
		selenium.click("id=statistika_isikud");
		Thread.sleep(1000);
		selenium.type("id=eesnimi", "");
		selenium.type("id=kandidaadiNr", "");
		Thread.sleep(1000);
		selenium.select("id=region", "label=Kogu Eesti");
		selenium.type("id=perenimi", "qwerty");
		selenium.click("id=searchCandidates");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent("qwerty"));
		// ID test
		selenium.type("id=perenimi", "");
		selenium.type("id=kandidaadiNr", "5");
		selenium.click("id=searchCandidates");
		Thread.sleep(2000);
		assertTrue(selenium.isTextPresent(""));
		// Testi mitme parameetriga otsingut (õige)
		selenium.type("id=eesnimi", "Juku");
		selenium.type("id=perenimi", "Juust");
		selenium.type("id=kandidaadiNr", "2");
		selenium.select("id=region", "label=Jõgeva maakond");
		selenium.click("id=searchCandidates");
		Thread.sleep(5000);
		assertTrue(selenium.isTextPresent("Juku"));
		assertTrue(selenium.isTextPresent("Juust"));
		assertTrue(selenium.isTextPresent("Maalaste Erakond"));
		assertTrue(selenium.isTextPresent("2"));
		// Test mitme parameetriga (vaste puudub)
		selenium.type("id=perenimi", "qwerty");
		selenium.type("id=kandidaadiNr", "7");
		selenium.select("id=region", "label=Kogu Eesti");
		selenium.type("id=eesnimi", "");
		Thread.sleep(2000);
		selenium.click("id=searchCandidates");
		selenium.type("id=perenimi", "");
		assertFalse(selenium.isTextPresent("qwerty"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
