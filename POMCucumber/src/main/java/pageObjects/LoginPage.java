package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataProviders.ConfigFileReader;

public class LoginPage {
	
	 WebDriver driver;
	 ConfigFileReader prop;
	 
	public LoginPage(WebDriver driver){
		this.driver = driver;
		
	}
	
	public  void navigateTo_LoginPage(){
		prop = new ConfigFileReader("Configuration.properties");
		driver.get(prop.CONFIG.getProperty("appUrl"));
	}
	
	public  WebElement login_username(WebDriver driver){	
		prop = new ConfigFileReader();
		return driver.findElement(prop.getObjectLocator(prop.CLASS.getProperty("username")));
	}
	
	public  WebElement login_password(WebDriver driver){
		return driver.findElement(prop.getObjectLocator(prop.CLASS.getProperty("password")));
	}
	
	public  WebElement login_button(WebDriver driver){
		return	driver.findElement(prop.getObjectLocator(prop.CLASS.getProperty("loginbtn")));
	}
	
}
