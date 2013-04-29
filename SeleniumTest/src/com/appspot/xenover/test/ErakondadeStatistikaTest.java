package com.appspot.xenover.test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class ErakondadeStatistikaTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://xenover.appspot.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testErakondade_statistika() throws Exception {
		selenium.open("/");
		selenium.click("id=statistika");
		Thread.sleep(2000);
		assertFalse(selenium.isVisible("class=statistika"));
		assertTrue(selenium.isVisible("loader"));
		Thread.sleep(4000);
		assertTrue(selenium.isVisible("class=statistika"));
		assertFalse(selenium.isVisible("loader"));
		selenium.click("css=#statistika_erakonnad > input[type=\"submit\"]");
		Thread.sleep(2000);
		selenium.select("id=regioon", "label=Jõgeva maakond");
		selenium.click("id=searchParties");
		Thread.sleep(2000);
		// XPATHCOUNT
		assertTrue(selenium.isTextPresent("Eestimaa Sinised"));
		assertTrue(selenium.isTextPresent("Maalaste Erakond"));
		assertTrue(selenium.isTextPresent("Töömeeste Erakond"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
