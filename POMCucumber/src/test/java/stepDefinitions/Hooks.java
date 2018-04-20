package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.*;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.*;


public class Hooks {

	TestContext testContext;
	 
	public Hooks(TestContext context) {
		testContext = context;
	}
 
	@Before
	public void BeforeSteps(Scenario scenario) {
		
		Reporter.assignAuthor("Gayatri Purohit Test scenarios");
		
	}
 
	@After(order=0)
	public void AfterSteps() {
		testContext.getWebDriverManager().closeDriver();
	}
	
	@After(order=1)
	public void AfterScenario(Scenario scenario){
		
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				//This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
				
				//Building up the destination path for the screenshot to save
				//Also make sure to create a folder 'screenshots' with in the cucumber-report folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".jpeg");
				
				//Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);   

				//This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			} 
		}
		
	}
}
