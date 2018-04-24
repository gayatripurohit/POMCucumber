package dataProviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import enums.DriverType;

public class ConfigFileReader {

	public Properties CONFIG=null;
//	private final String propertyFilePath= "\\configs\\Configuration.properties";
	
	FileInputStream objfile;
	String locatorType, locatorValue , driverPath ;
	By locator;
	
	public ConfigFileReader(){
		
		String className = new Exception().getStackTrace()[2].getClassName();
		String fileName = className.split("\\.")[1];
		
		CONFIG=new Properties();	
			try
			{
				 objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\ObjectRepository\\"+fileName+".properties");
				 CONFIG.load(objfile);
			}
			catch (IOException e) {			
				e.printStackTrace();
			 	}
		
	}
	
	public ConfigFileReader(String propertyFilePath){
	
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
	
	
	
	public String getPropertyValue(String propertyName) {
		String propertyValue = CONFIG.getProperty(propertyName);
		if(propertyValue != null) return propertyValue;
		else throw new RuntimeException(propertyName + " not specified in the Configuration.properties file.");
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
