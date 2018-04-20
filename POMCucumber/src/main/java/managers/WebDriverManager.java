package managers;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import dataProviders.ConfigFileReader;
import enums.DriverType;

public class WebDriverManager {
	public ConfigFileReader prop;
	private WebDriver driver;
	
	private static DriverType driverType;
	
	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		prop = new ConfigFileReader();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	private WebDriver createDriver() {
		 switch (driverType) {	    
	        case FIREFOX : 
	        	System.setProperty(prop.CONFIG.getProperty("firefoxwebdriver"), FileReaderManager.getInstance().getConfigReader().getDriverPath(DriverType.FIREFOX));	     
	        	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	    		capabilities.setCapability("marionette", true);
	        	driver = new FirefoxDriver(capabilities);
		    	break;
	        case CHROME : 
	        	System.setProperty(prop.CONFIG.getProperty("chromewebdriver"), FileReaderManager.getInstance().getConfigReader().getDriverPath(DriverType.CHROME));
	        	ChromeOptions options = new ChromeOptions();
	      	  	options.addArguments("--disable-infobars"); // used to disable the info bar ‘Chrome is being controlled by automated test software’ 
	      	  	options.addArguments("--disable-extensions"); // used to disable - 'Disable Developer Mode Extension' pop up in right corner	  	      	
	        	driver = new ChromeDriver(options);
	    		break;
	        case INTERNETEXPLORER : 
	        	System.setProperty(prop.CONFIG.getProperty("iewebdriver"), FileReaderManager.getInstance().getConfigReader().getDriverPath(DriverType.INTERNETEXPLORER));
	        	driver = new InternetExplorerDriver();
	    		break;
	        }

	        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
			return driver;
	}
	
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
}
