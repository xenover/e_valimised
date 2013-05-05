package com.appspot.xenover.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.html5.RemoteLocalStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class KandidatuurTest {
	private Selenium selenium;
	WebDriver driver = new FirefoxDriver();

	@Before
	public void setUp() throws Exception {
		
		String baseUrl = "http://xenover.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testUntitled() throws Exception {
		selenium.open("/setls.html");
		Thread.sleep(3000);
		selenium.open("/");
		Thread.sleep(2000);
		selenium.waitForPageToLoad("20000");
		// Kandideerimine ja login
		selenium.selectWindow("title=E-valimised: avaleht");
		Thread.sleep(5000);
		selenium.click("id=kandideeri");
		Thread.sleep(2000);
		selenium.type("id=telefon", "asda");
		selenium.type("id=telefon", "53900999");
		selenium.type("id=email", "kertp@hotmail.com");
		selenium.select("id=p_seis", "label=Vallaline");
		selenium.select("id=haridus", "label=Keskharidus");
		selenium.type("id=amet", "Muidusööja");
		selenium.type("id=asutus", "Mahv");
		selenium.select("id=erakond", "label=Eesti Venelaspartei");
		Thread.sleep(1000);
		selenium.click("id=submitCandidature");
		assertEquals("Andmed salvestatud", selenium.getAlert());
		selenium.click("id=haaletamine");
		Thread.sleep(5000);
		selenium.type("id=eesnimi", "");
		selenium.type("id=perenimi", "");
		selenium.click("id=showTable");
		Thread.sleep(8000);
		assertTrue(selenium.isTextPresent("Eesti Venelaspartei"));
		assertTrue(selenium.isTextPresent("Peeter"));
		selenium.click("id=6");
		assertTrue(selenium.getConfirmation().matches("^Kas oled kindel, et soovid anda oma hääle valitud kandidaadile[\\s\\S]$"));
		Thread.sleep(5000);
		assertTrue(selenium.isTextPresent("Oled andnud oma hääle juba kandidaadile Peeter Paan. Uue hääle andmisel eelmine hääl tühistatakse."));
		selenium.click("id=showTable");
		Thread.sleep(10000);
		selenium.click("id=3");
		assertTrue(selenium.getConfirmation().matches("^Kas oled kindel, et soovid anda oma hääle valitud kandidaadile[\\s\\S]$"));
		Thread.sleep(3000);
		assertTrue(selenium.isTextPresent("Oled andnud oma hääle juba kandidaadile ASDF qwerty. Uue hääle andmisel eelmine hääl tühistatakse."));
		Thread.sleep(6000);
		selenium.click("id=deleteVote");
		assertTrue(selenium.getConfirmation().matches("^Kas oled kindel, et soovid hääle tühistada[\\s\\S]$"));
		Thread.sleep(6000);
		assertTrue(selenium.isTextPresent("Sa pole veel kellelegi oma häält andnud."));
		Thread.sleep(6000);
		selenium.open("/clearls.html");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
