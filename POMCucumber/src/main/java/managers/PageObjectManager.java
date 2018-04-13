package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;



public class PageObjectManager {

	private WebDriver driver;
	private LoginPage login;
	
	public PageObjectManager(WebDriver driver){
		this.driver = driver;
	}
	
	public LoginPage getLoginPage(){
		return (login == null) ? login = new LoginPage(driver): login;
	}
}
