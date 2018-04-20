package stepDefinitions;

import cucumber.TestContext;
import cucumber.api.java.en.*;
import managers.FileReaderManager;
import pageObjects.LoginPage;
import testDataTypes.Login;

import static org.junit.Assert.*;



public class LoginPageSteps {
	LoginPage login;
	TestContext testcontext;
	
	public LoginPageSteps(){
		
	}
	
	public LoginPageSteps(TestContext context) {	
		testcontext = context;
		login = testcontext.getPageObjectManager().getLoginPage();		
	}
	
	@Given("^user is on ums loginpage$")
	public void user_is_on_ums_loginpage(){
		login.navigateTo_LoginPage();	
		
	}

	@When("^user enters \"([^\"]*)\" and password details$")
	public void user_enters_login_details(String username)  {
	    Login log = FileReaderManager.getInstance().getJsonDataReader().getLoginByUserName(username);
	    FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
		login.fill_logindetails(log);	    
		//login.enter_login_username();
		//login.enter_login_password();
	}
	
	@When("^user clicks on Sign in button$")
	public void user_clicks_on_Sign_in_button() {
		FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
		login.click_login_button();
	}
	
	@Then("^user lands on dashboard page$")
	public void user_lands_on_dashboard_page() {
	    System.out.println("On dashboard page");
	    //driver.findElement(By.xpath(".//a[@href='/logout']")).click();
	    
	    String title = login.verify_dashboard();
	    assertEquals("DASHBOARD",title);
	}
	

}
