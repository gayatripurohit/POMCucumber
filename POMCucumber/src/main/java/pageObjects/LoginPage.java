package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataProviders.ConfigFileReader;
import managers.FileReaderManager;
import testDataTypes.Login;

public class LoginPage {
	
	 WebDriver driver;
	 ConfigFileReader prop;
	 
	public LoginPage(WebDriver driver){
		this.driver = driver;
		prop = new ConfigFileReader();
	}
	
	
	public  void navigateTo_LoginPage(){
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	}
	
	// base methods  
	public WebElement returnElement(By by)
	{
		return driver.findElement(by);
	}
	
	// base methods  
	public void sendkeys(By by,String str)
	{
		driver.findElement(by).sendKeys(str);
	}
	
	// base methods  
	public void click(By by){
		try{
		driver.findElement(by).click();
		}catch(NoSuchElementException e){
			e.getMessage();
		}
	}
	
	//base methods 
	public String getTextOfElement(By by)
	{		
		return	driver.findElement(by).getText();	 
	}
	
	
	public  void enter_login_username(String username){	
		 sendkeys(prop.getObjectLocator(prop.CONFIG.getProperty("username")), username);
		//prop.CONFIG.getProperty("unm")
	}
	
	public  void enter_login_password(String password){
		 sendkeys(prop.getObjectLocator(prop.CONFIG.getProperty("password")), password);
		//prop.CONFIG.getProperty("pwd")
	}
	
	public  void click_login_button(){
		click(prop.getObjectLocator(prop.CONFIG.getProperty("loginbtn")));
		
	}
		
	public void fill_logindetails(Login log){
		enter_login_username(log.uname);
		enter_login_password(log.pass);
	}
	
	public String verify_dashboard(){
		
		String title = getTextOfElement(By.xpath(".//*[@id='page-content-wrapper']/div/div[1]/header/h2"));
		return title;
	}
	
}
