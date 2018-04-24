package managers;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import pageObjects.LoginPage;



public class PageObjectManager {

	private WebDriver driver;
	private LoginPage loginPage;
	private TestContext context;
	
	public PageObjectManager(WebDriver driver){
		this.driver = driver;
	}
	
	public LoginPage getLoginPage(){
		return (loginPage == null) ? loginPage = new LoginPage(context): loginPage;
	}
}
