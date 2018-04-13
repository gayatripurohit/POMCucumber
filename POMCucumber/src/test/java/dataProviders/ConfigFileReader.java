package dataProviders;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class ConfigFileReader {
	
	public Properties CONFIG=null, CLASS=null;
	
	FileInputStream objfile, objfile1;
	
	
	String locatorProperty,locatorType, locatorValue, fileName = null , className;
	By locator;
	
	public ConfigFileReader(String filenm){
	
		CONFIG=new Properties();	
		try
		{
			 objfile = new FileInputStream(System.getProperty("user.dir")+"\\configs\\"+filenm+"");
			 CONFIG.load(objfile);
		}
		catch (Exception e) {			
			e.printStackTrace();
		 	}
	}
	
	public ConfigFileReader(){
		className = new Exception().getStackTrace()[1].getClassName();
		fileName = className.split("\\.")[1];
		
		CLASS=new Properties();	
		try
		{
			 objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\ObjectRepository\\"+fileName+".properties");
			 CLASS.load(objfile1);
		}
		catch (Exception e) {			
			e.printStackTrace();
		 	}
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
}
