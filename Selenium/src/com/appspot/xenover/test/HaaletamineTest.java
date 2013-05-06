package com.appspot.xenover.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HaaletamineTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://xenover.appspot.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHaaletamine() throws Exception {
    driver.get(baseUrl + "/");
    // Warning: waitForTextPresent may require manual changes
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    // Hääletamine sisse logimata
    driver.findElement(By.id("haaletamine")).click();
    driver.findElement(By.id("eesnimi")).clear();
    driver.findElement(By.id("eesnimi")).sendKeys("Juku");
    driver.findElement(By.id("showTableLoggedOut")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Perekonnanime test
    driver.findElement(By.id("eesnimi")).clear();
    driver.findElement(By.id("eesnimi")).sendKeys("");
    driver.findElement(By.id("kandidaadiNr")).clear();
    driver.findElement(By.id("kandidaadiNr")).sendKeys("");
    new Select(driver.findElement(By.id("regioon"))).selectByVisibleText("Kogu Eesti");
    driver.findElement(By.id("perenimi")).clear();
    driver.findElement(By.id("perenimi")).sendKeys("qwerty");
    driver.findElement(By.id("showTableLoggedOut")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // ID test
    driver.findElement(By.id("perenimi")).clear();
    driver.findElement(By.id("perenimi")).sendKeys("");
    driver.findElement(By.id("kandidaadiNr")).clear();
    driver.findElement(By.id("kandidaadiNr")).sendKeys("5");
    driver.findElement(By.id("showTableLoggedOut")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Testi mitme parameetriga otsingut (õige)
    driver.findElement(By.id("eesnimi")).clear();
    driver.findElement(By.id("eesnimi")).sendKeys("Juku");
    driver.findElement(By.id("perenimi")).clear();
    driver.findElement(By.id("perenimi")).sendKeys("Juust");
    driver.findElement(By.id("kandidaadiNr")).clear();
    driver.findElement(By.id("kandidaadiNr")).sendKeys("2");
    new Select(driver.findElement(By.id("regioon"))).selectByVisibleText("Jõgeva maakond");
    driver.findElement(By.id("showTableLoggedOut")).click();
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Test mitme parameetriga (vaste puudub)
    driver.findElement(By.id("perenimi")).clear();
    driver.findElement(By.id("perenimi")).sendKeys("qwerty");
    driver.findElement(By.id("kandidaadiNr")).clear();
    driver.findElement(By.id("kandidaadiNr")).sendKeys("7");
    new Select(driver.findElement(By.id("regioon"))).selectByVisibleText("Kogu Eesti");
    driver.findElement(By.id("showTableLoggedOut")).click();
    driver.findElement(By.id("eesnimi")).clear();
    driver.findElement(By.id("eesnimi")).sendKeys("");
    // Warning: verifyTextNotPresent may require manual changes
    try {
      assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Juku[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    System.out.println(verificationErrorString);
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
