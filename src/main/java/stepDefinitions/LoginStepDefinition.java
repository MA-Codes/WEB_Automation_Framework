package stepDefinitions;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.web.qa.pages.LoginPage;
import com.web.qa.base.TestBase;
import com.web.qa.pages.LandingPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDefinition extends TestBase {

	WebDriver driver;
	LoginPage loginPage;


	public LoginStepDefinition() {
		super();
	}
	
	@Given("^user_enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_and(String id, String password, String language) throws Exception {
		loginPage = new LoginPage();
		LoginPage.signin(id, password, language);
	}
	
	@Then("^login from excel$")
	public void login_from_excel() throws Throwable {
		loginPage = new LoginPage();
		String path = null;
		LoginPage.signin_excel(path);
	}


	@Then("^Validation Header links in home page$")
	public void validation_links() throws InterruptedException, AWTException {
		LandingPage landingpage=new LandingPage();
		landingpage.link_validation();
		}

	
	@Before
	public void setUP() throws IOException {
		initialization();

	}


	@After
	public void tearDown(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
			ConsoleLogs(scenario.getName()); //... to get selenium action -- java console logs
			logBrowserConsoleLogs(scenario.getName()); //... get browser console logs in logs folder
			closeBrowser();
		} else
		{
		closeBrowser();
		}
	}

}
