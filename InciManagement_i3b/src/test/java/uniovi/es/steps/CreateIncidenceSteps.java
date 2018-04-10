package uniovi.es.steps;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uniovi.es.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CreateIncidenceSteps {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "https://www.katalon.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @When(value = "the user log in session")
	  public void testUntitledTestCase() throws Exception {
		  driver.get("http://localhost:8080/logIn");
		    driver.findElement(By.name("login")).clear();
		    driver.findElement(By.name("login")).sendKeys("");
		    driver.findElement(By.name("login")).click();
		    driver.findElement(By.name("login")).clear();
		    driver.findElement(By.name("login")).sendKeys("11111111A");
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys("123456");
		    driver.findElement(By.name("kind")).clear();
		    driver.findElement(By.name("kind")).sendKeys("1");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();  
		  
	    driver.get("http://localhost:8080/logIn");
	    driver.findElement(By.name("login")).click();
	    driver.findElement(By.name("login")).clear();
	    driver.findElement(By.name("login")).sendKeys("11111111A");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("kind")).clear();
	    driver.findElement(By.name("kind")).sendKeys("1");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.name("login")).click();
	    driver.findElement(By.name("login")).click();
	    driver.findElement(By.name("login")).clear();
	    driver.findElement(By.name("login")).sendKeys("PersonName");
	    driver.findElement(By.name("login")).click();
	    driver.findElement(By.name("login")).clear();
	    driver.findElement(By.name("login")).sendKeys("11111111A");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("kind")).clear();
	    driver.findElement(By.name("kind")).sendKeys("1");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.id("key")).click();
	    driver.findElement(By.id("key")).clear();
	    driver.findElement(By.id("key")).sendKeys("f1");
	    driver.findElement(By.id("value")).clear();
	    driver.findElement(By.id("value")).sendKeys("f2");
	    driver.findElement(By.id("addButton")).click();
	    driver.findElement(By.xpath("//div[@id='incident-form']/div/form[2]/div")).click();
	    driver.findElement(By.xpath("//div[@id='incident-form']/div/form[2]/div")).click();
	    driver.findElement(By.id("title")).click();
	    driver.findElement(By.id("title")).clear();
	    driver.findElement(By.id("title")).sendKeys("titulo");
	    driver.findElement(By.id("message")).clear();
	    driver.findElement(By.id("message")).sendKeys("desc");
	    driver.findElement(By.id("aditionalInfo")).clear();
	    driver.findElement(By.id("aditionalInfo")).sendKeys("adinfo");
	    driver.findElement(By.id("tags")).clear();
	    driver.findElement(By.id("tags")).sendKeys("tag,tags,tagggs");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	  }
	  
	  @Then(value = "see a page to create incidences")
	  public void testUntitledTestCase2() throws Exception {}
	  
	  @Then(value = "fill the text fields and clicks Notify Incidence")
	  public void testUntitledTestCase3() throws Exception {}

	  @Then(value = "an incidence is created (and redirected to a list view of them)")
	  public void testUntitledTestCase4() throws Exception {}

	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }

	  }
}