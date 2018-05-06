package uniovi.es.steps;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uniovi.es.Application;
import uniovi.es.steps.utils.CucumberUtils;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Application.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes=Application.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class AddCustomFieldsSteps {

	  private CucumberUtils utils = new CucumberUtils();
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

	  @When(value = "the user logs in session")
	  public void testUntitledTestCasee() throws Exception {
		driver.get("http://165.227.236.206:8080/");
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
	  }
	  
	  @Then(value = "fill the form of a field")
	  public void testUntitledTestCase2() throws Exception 
	  {
		//esta en la pagina de crearla
		  utils.textoPresentePagina(driver, "Create new incident");
		  	
		  driver.findElement(By.id("key")).click();
		  driver.findElement(By.id("key")).clear();
		  driver.findElement(By.id("key")).sendKeys("fieldname");
		  driver.findElement(By.id("value")).clear();
		  driver.findElement(By.id("value")).sendKeys("valueName");
	  }

	  @Then(value = "click Add field")
	  public void testUntitledTestCase3() throws Exception 
	  {
		  driver.findElement(By.id("addButton")).click();
	  }
	  
	  @Then(value = "see the info")
	  public void testUntitledTestCase4() throws Exception 
	  {
		  //sale el nuevo campo
		  utils.textoPresentePagina(driver, "fieldname");
		  utils.textoPresentePagina(driver, "valueName");
	  }
	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

}
