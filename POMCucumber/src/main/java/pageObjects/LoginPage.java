package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.TestContext;
import dataProviders.ConfigFileReader;
import managers.FileReaderManager;
import managers.WebDriverManager;
import testDataTypes.Login;

public class LoginPage {
	
	 WebDriver driver;
	 ConfigFileReader configFileReader;
	 TestContext testcontext;
	 WebDriverManager webdriver;
	
	 
	 
	public LoginPage(TestContext context){
		testcontext = context;
		configFileReader = FileReaderManager.getInstance().getConfigReader();
		webdriver = testcontext.getWebDriverManager();
	}
	
	public  void enterLoginUsername(String username){	
		webdriver.sendkeys(webdriver.getObjectLocator(configFileReader.CONFIG.getProperty("username")), username);
		
	}
	
	public  void enterLoginPassword(String password){
		webdriver.sendkeys(webdriver.getObjectLocator(configFileReader.CONFIG.getProperty("password")), password);
		
	}
	
	public  void clickLoginButton(){
		webdriver.click(webdriver.getObjectLocator(configFileReader.CONFIG.getProperty("loginbtn")));
		
	}
		
	public void enterLoginDetails(Login log){
		enterLoginUsername(log.uname);
		enterLoginPassword(log.pass);
	}
	
	public String verifyDashboard(){
		
		String title = webdriver.getTextOfElement(By.xpath(".//*[@id='page-content-wrapper']/div/div[1]/header/h2"));
		return title;
	}
	
}
