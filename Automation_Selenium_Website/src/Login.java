import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	
	WebDriver webDriver;
	
	public Login(WebDriver driver) {
		webDriver = driver;
	}

	TestCaseResult loadLoginPage(HashMap<String, String> data)
	{
		TestCaseStatus caseStatus = TestCaseStatus.FAIL;
		TestCaseResult caseResult = new TestCaseResult();
		
		caseResult.setMethodName(this.getClass().getName()+"/"+"loadLoginPage");
		caseResult.setCaseStatus(caseStatus);
		
		try {
			webDriver.get(data.get("url"));
			if(webDriver.findElement(By.id("user-name")) != null
					&& webDriver.findElement(By.id("password")) != null
					&& webDriver.findElement(By.id("login-button")) != null)
			{
				caseResult.setCaseStatus(TestCaseStatus.SUCCESS);
			}
		}
		catch (Exception exception) {
			caseStatus = TestCaseStatus.FAIL;
		}
		
		return caseResult;
	}

	public TestCaseResult signIntoLoginPage(HashMap<String, String> data) {
		TestCaseStatus caseStatus = TestCaseStatus.FAIL;
		TestCaseResult caseResult = new TestCaseResult();
		
		caseResult.setMethodName(this.getClass().getName()+"/"+"signIntoLoginPage");
		caseResult.setCaseStatus(caseStatus);
		
		try {
			webDriver.get(data.get("url"));
			webDriver.findElement(By.id("user-name")).sendKeys(data.get("email"));
			webDriver.findElement(By.id("password")).sendKeys(data.get("password"));
			webDriver.findElement(By.id("login-button")).click();
			WebDriverWait driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
			driverWait.until(ExpectedConditions.presenceOfElementLocated (By.id("add-to-cart-sauce-labs-backpack")));
		
			if(webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")) != null) {
					caseResult.setCaseStatus(TestCaseStatus.SUCCESS);
					webDriver.close();
			}
		} catch (Exception e) {
			caseStatus = TestCaseStatus.FAIL;
		}
		
		return caseResult;
	}

}
