package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.en.*;
import dataProviders.ConfigFileReader;
import managers.PageObjectManager;
import pageObjects.LoginPage;

public class Steps {
	WebDriver driver;
	PageObjectManager pom;
	LoginPage login;
	ConfigFileReader prop;
	
@Given("^user is on ums loginpage$")
public void user_is_on_ums_loginpage(){
	prop = new ConfigFileReader("Configuration.properties") ;
	
	System.setProperty(prop.CONFIG.getProperty("chromewebdriver"),System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	  options.addArguments("--disable-infobars"); // used to disable the info bar -  ‘Chrome is being controlled by automated test software’ 
	  options.addArguments("--disable-extensions"); // used to disable - 'Disable Developer Mode Extension' pop up in right corner	  
	driver=new ChromeDriver(options);	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		
	pom = new PageObjectManager(driver);
	login = pom.getLoginPage(); // using PageObjectManager create login page object
	login.navigateTo_LoginPage();	
}

@When("^user enters login details$")
public void user_enters_login_details()  {
    
	login.login_username(driver).sendKeys(prop.CONFIG.getProperty("unm"));
	login.login_password(driver).sendKeys(prop.CONFIG.getProperty("pwd"));
}

@When("^user clicks on Sign in button$")
public void user_clicks_on_Sign_in_button() {
	login.login_button(driver).click();
}

@Then("^user lands on dashboard page$")
public void user_lands_on_dashboard_page() {
    System.out.println("On dashboard page");

    	driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);	
	
    //driver.findElement(By.xpath(".//a[@href='/logout']")).click();
}

}
