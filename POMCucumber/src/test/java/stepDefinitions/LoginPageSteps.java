package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.*;
import managers.FileReaderManager;
import managers.WebDriverManager;
import pageObjects.LoginPage;
import testDataTypes.Login;

import static org.junit.Assert.*;



public class LoginPageSteps {
	TestContext testcontext;
	LoginPage loginPage;
	WebDriverManager webdriver;
	
	public LoginPageSteps(TestContext context) {	
		testcontext = context;
		webdriver = testcontext.getWebDriverManager();
		loginPage = testcontext.getPageObjectManager().getLoginPage();	
		
	}
	
	@Given("^user is on ums loginpage$")
	public void user_is_on_ums_loginpage(){
		webdriver.navigateToLoginPage();	
		
	}

	@When("^user enters \"([^\"]*)\" and password details$")
	public void user_enters_login_details(String username)  {
	    Login log = FileReaderManager.getInstance().getJsonDataReader().getLoginByUserName(username);	   
	    loginPage.enterLoginDetails(log);	    
	}
	
	@When("^user clicks on Sign in button$")
	public void user_clicks_on_Sign_in_button() {
		
		loginPage.clickLoginButton();
	}
	
	@Then("^user lands on dashboard page$")
	public void user_lands_on_dashboard_page() {
	    System.out.println("On dashboard page");
	    //driver.findElement(By.xpath(".//a[@href='/logout']")).click();
	    
	    String title = loginPage.verifyDashboard();
	    assertEquals("DASHBOARD",title);
	}
	

}
