package tests;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LoginBasicTest {

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	private WebDriver driver;

	@BeforeTest
	public void starReporter() {
		extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		//Config to custom report
		extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
		extentSparkReporter.config().setReportName("Test Report");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL,result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, result.getTestName());
		}
		else {
			extentTest.log(Status.SKIP, result.getTestName());
		}
	}

	@BeforeTest
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String browser = System.getProperty("seleniumgrid.browser");
		String huburl = System.getProperty("seleniumgrid.hub.url");
		
		if (huburl!=null) {
			if (browser!=null) {
				switch (browser) {
					case "chrome":
						capabilities = DesiredCapabilities.chrome();
						break;
					case "firefox":
						capabilities = DesiredCapabilities.firefox();
						break;	
					case "edge":
						capabilities = DesiredCapabilities.edge();
						break;
					case "oerpa":
						capabilities = DesiredCapabilities.operaBlink();
						break;
				}
				driver = new RemoteWebDriver(new URL(huburl), capabilities);
			}
		} else {
			//Inicialización local sin paso de parametros
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
		}
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		extentReports.flush();
	}

	@Test
	public void login() {
		System.out.println("0. Start");
		
		
		System.out.println("1. Open target page");
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().setSize(new Dimension(1350, 637));
		
		System.out.println("2. Insert username and password");
		
		System.out.println(" 2.1 Insert username");
		driver.findElement(By.id("login_credentials")).click();
		driver.findElement(By.cssSelector("*[data-test=\"username\"]")).click();
		driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
		
		System.out.println(" 2.1 Insert password");
		driver.findElement(By.cssSelector(".login_password")).click();
		driver.findElement(By.cssSelector("*[data-test=\"password\"]")).click();
		driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
		
		System.out.println("3. Click submit to perform login");
		driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

		System.out.println("4. Verify login has been successfully executed");
		System.out.println(" 4.1 Page title is 'Swag Labs'");
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		extentTest = extentReports.createTest("Test Case 1", "Check Title");

		System.out.println(" 4.2 Page url contains 'inventory'");
		Assert.assertEquals(driver.getCurrentUrl().contains("inventory"), true);
		extentTest = extentReports.createTest("Test Case 2", "Check if contains inventory");
		//Pause the execution for 2 seconds to show the logged in page
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("5. End");
	}
	
}
