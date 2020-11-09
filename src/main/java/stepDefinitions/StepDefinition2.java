package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.web.qa.base.TestBase;
import com.web.qa.pages.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinition2 extends TestBase {

	WebDriver driver;
	LoginPage loginPage;

	public StepDefinition2() {
		super();
	}

	@Given("^user_enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_and(String arg1, String arg2) throws Throwable {
	    loginPage=new LoginPage();
	    loginPage.signin(arg1, arg2);
	    
	}
	@Then("^Automation test(\\d+)$")
	public void automation_test(int arg1) throws Throwable {
	   System.out.println(arg1);
	}

}
