package dataProviders;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import enums.DriverType;

public class ConfigFileReader {

	public Properties CONFIG=null;
	private final String propertyFilePath= "\\configs\\Configuration.properties";
	
	FileInputStream objfile;
	String locatorType, locatorValue , driverPath ;
	By locator;
	
//	public ConfigFileReader(){
		
		//className = new Exception().getStackTrace()[1].getClassName();
		//fileName = className.split("\\.")[1];
		
//			CLASS=new Properties();	
//			try
//			{
//				 objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\ObjectRepository\\"+fileName+".properties");
//				 CLASS.load(objfile1);
//			}
//			catch (IOException e) {			
//				e.printStackTrace();
//			 	}
//		
//	}
	
	public ConfigFileReader(){
	
		CONFIG=new Properties();	
		try
		{
			 objfile = new FileInputStream(System.getProperty("user.dir")+propertyFilePath);
			 CONFIG.load(objfile);
		}
		catch (Exception e) {			
			e.printStackTrace();
		 	}
	}
	
	public String getDriverPath(DriverType drivertype){		
		 switch (drivertype) {	    
	        case FIREFOX : 
	        	 driverPath = CONFIG.getProperty("firefoxdriverpath");				
	        break;
	        case CHROME : 
				 driverPath = CONFIG.getProperty("chromedriverpath");
				//else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
			break;
	        case INTERNETEXPLORER:
	        	 driverPath = CONFIG.getProperty("iedriverpath");
		 }
		 return driverPath;
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = CONFIG.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = CONFIG.getProperty("appUrl");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public DriverType getBrowser() {
		String browserName = CONFIG.getProperty("browser");
		if(browserName == null || browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equalsIgnoreCase("internetexplorer")) return DriverType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}


	public Boolean getBrowserWindowSize() {
		String windowSize = CONFIG.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
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
	

	public String getTestDataResourcePath(){
		String testDataResourcePath = CONFIG.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) return testDataResourcePath;
		else throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");		
	}

	@SuppressWarnings("unused")
	public String getReportConfigPath(){
		String reportconfigpath = System.getProperty("user.dir") + CONFIG.getProperty("reportConfigPath");
		if(reportconfigpath!= null) return reportconfigpath;
		else throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");			
	}
	
	
}
