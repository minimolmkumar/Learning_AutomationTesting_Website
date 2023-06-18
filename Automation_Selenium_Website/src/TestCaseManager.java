import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCaseManager {
	
	private StringBuilder caseReport = new StringBuilder();
	private TestCaseResult caseResult = new TestCaseResult();
	private HashMap<String, String> data = new HashMap<>();
	private boolean isNegativeCase;
	

	public static void main(String[] args) {
		
		TestCaseManager testCaseManager = new TestCaseManager();
		testCaseManager.runTestCases();
		
	}

	private void runTestCases() {
		
		// ***Select browser***
		Browser browser = Browser.CHROME;
		WebDriver driver = getBrowserDriver(browser);
		
		//Get Modules
		//***Login***
		
		//Case 1
		Login login = new Login(driver);
		data.put("url", "https://www.saucedemo.com/");
		caseResult = login.loadLoginPage(data);
		addCaseResultToReport();
		
		//Case 2
		data.put("email", "standard_user");
		data.put("password", "secret_sauce");
		caseResult = login.signIntoLoginPage(data);
		addCaseResultToReport();
		
		//Case 3
		driver = getBrowserDriver(browser);
		login = new Login(driver);
		data.put("email", "standard_user");
		data.put("password", "123");
		isNegativeCase = true;
		caseResult = login.signIntoLoginPage(data);
		handleNegativeCaseResult();
		addCaseResultToReport();
		
		//Close
		/*if(driver != null){
			driver.close();
		}*/
		
		
		//***Output***
		System.out.println(caseReport.toString());
		
	}

	private void handleNegativeCaseResult() {
		
		if(isNegativeCase 
				&& caseResult.getCaseStatus().equals(TestCaseStatus.FAIL)) 
		{
			caseResult.setCaseStatus(TestCaseStatus.SUCCESS);
		}
		
	}

	private void addCaseResultToReport() {
		if(caseResult != null) {
		caseReport.append("\n");
		caseReport.append("******************");
		caseReport.append(caseResult.getMethodName());
		caseReport.append("******************");
		caseReport.append(":");
		caseReport.append("\n");
		caseReport.append("Result: ");
		caseReport.append(caseResult.getCaseStatus());	
		caseReport.append("\n");
		caseReport.append("Negativecase: ");
		caseReport.append(isNegativeCase);
		}
	}

	private static WebDriver getBrowserDriver(Browser browser) {
		
		WebDriver driver = null;
		
		if(browser.equals(Browser.CHROME)){
			driver = new ChromeDriver();
		}
		else if(browser.equals(Browser.EDGE)){
			driver = new EdgeDriver();
		}
		else if(browser.equals(Browser.FIREFOX)){
			driver = new FirefoxDriver();
		}
				
		return driver;
		
	}

}
