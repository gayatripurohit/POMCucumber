package managers;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import dataProviders.ConfigFileReader;
import enums.DriverType;

public class WebDriverManager {
	public ConfigFileReader configFileReader;
	private WebDriver driver;
	String configPath = "\\configs\\Configuration.properties";
	String locatorType, locatorValue , driverPath ;
	By locator;
	
	private static DriverType driverType;
	
	public WebDriverManager() {
		configFileReader = FileReaderManager.getInstance().getConfigReader(configPath);
		driverType = configFileReader.getBrowser();
		
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	private WebDriver createDriver() {
		 switch (driverType) {	    
	        case FIREFOX : 
	        	System.setProperty(configFileReader.CONFIG.getProperty("firefoxwebdriver"), configFileReader.getDriverPath(DriverType.FIREFOX));	     
	        	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    		capabilities.setCapability("marionette", true);
	        	driver = new FirefoxDriver(capabilities);
		    	break;
	        case CHROME : 
	        	System.setProperty(configFileReader.CONFIG.getProperty("chromewebdriver"), configFileReader.getDriverPath(DriverType.CHROME));
	        	ChromeOptions options = new ChromeOptions();
	      	  	options.addArguments("--disable-infobars"); // used to disable the info bar ‘Chrome is being controlled by automated test software’ 
	      	  	options.addArguments("--disable-extensions"); // used to disable - 'Disable Developer Mode Extension' pop up in right corner	  	      	
	        	driver = new ChromeDriver(options);
	    		break;
	        case INTERNETEXPLORER : 
	        	System.setProperty(configFileReader.CONFIG.getProperty("iewebdriver"), configFileReader.getDriverPath(DriverType.INTERNETEXPLORER));
	        	driver = new InternetExplorerDriver();
	    		break;
	        }

	        if(configFileReader.getBrowserWindowSize()) driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
			return driver;
	}
	
	public  void navigateToLoginPage(){
		driver.get(configFileReader.getPropertyValue("appUrl"));
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
	
	

	public By getObjectLocator(String locatorProperty)
	{
		 locatorType = locatorProperty.split(":")[0];
		 locatorValue = locatorProperty.split(":")[1];

		 locator = null;
		switch(locatorType.toLowerCase())
		{
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;	
		
		}
		return locator;
	}
	
	
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
	
}
