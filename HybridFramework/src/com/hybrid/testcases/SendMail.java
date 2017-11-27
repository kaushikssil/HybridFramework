package com.hybrid.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybrid.utils.ExtentManager;
import com.hybrid.utils.Keywords;
import com.hybrid.utils.TestUtils;
import com.hybrid.utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SendMail {
	
	WebDriver driver;
	Keywords k;
	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest logger;
	Xls_Reader xls = new Xls_Reader("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\src\\com\\hybrid\\config\\TestData.xlsx");
	
	@Test(dataProvider = "TestData")
	//public void SendProcedure(String email, String sub, String cArea, String browser, String runMode) throws InterruptedException {
		public void SendProcedure(Hashtable <String, String> h) throws InterruptedException {
		logger = extent.startTest("Send Mail");
		k = new Keywords();//Initialization of the is object contain the loading of OR.properties file and the environment based property file
		
		/*System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
		
		//Maximising the window
		driver.manage().window().maximize();*/
		
		//Implementing the RUNMODE procedure
		/*if(runMode.equals("NO")) {
			throw new SkipException("Runmode is set to NO for "+browser+" browser");
		}*/
		if(h.get("runMode").equals("NO")) {
			throw new SkipException("Runmode is set to NO for "+h.get("browser")+" browser");
		}
		
		k.openBrowser(h.get("browser"));
		logger.log(LogStatus.INFO, "Opening the browser");
		
		//Navigate to google
		//driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		k.navigate();
		logger.log(LogStatus.INFO, "Navigating to Rediffmail application");
		
		//Implicit wait
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		k.pauseImplicit();
		
		
		
		//Single validation to check that the landing page has opening-Checking the static text "rediffmail"
		/*String expectedAttributeValueLogo = "rediffmail-logo";
		String actualAttributeValueLogo = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/div")).getAttribute("class");
		Assert.assertTrue(actualAttributeValueLogo.equals(expectedAttributeValueLogo), "Validation failed. Not in the landing page");*/
		k.verifyText("rediffmail-logo", "class", "Validation failed. Not in the landing page", "XPathRediffmailTextStaticText");
		logger.log(LogStatus.PASS, "Landing page has opened");
		
		//Validation for web element - Username
		/*String expectedUsernameText = "Username";
		String actualUsernameText = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[1]")).getText();
		Assert.assertTrue(actualUsernameText.equals(expectedUsernameText), "Username field not present");*/
		k.verifyText("Username", "Username field not present", "XPathUsernameLabelUsernameField");
		logger.log(LogStatus.PASS, "Username field present");
		
		
		//Type the username
		//driver.findElement(By.xpath("//*[@id=\"login1\"]")).sendKeys("selenium.testmay2017");
		k.writeInInputUserName("XPathUsernameUsernameField", "username");
		logger.log(LogStatus.INFO, "Type in the username");
		
		//Validation for web element - Password
		/*String expectedPasswordText = "Password";
		String actualPasswordText = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[3]/div[1]")).getText();
		Assert.assertTrue(actualPasswordText.equals(expectedPasswordText), "Password field not present");*/
		k.verifyText("Password", "Password field not present", "XPathPasswordLabelPasswordField");
		logger.log(LogStatus.PASS, "Password field present");
		
		
		//Type the password
		//driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("test@1234");
		k.writeInInputPassword("XPathPasswordPasswordField", "password");
		logger.log(LogStatus.INFO, "Type in the password");
		
		//Validation for web element - Go Link
		/*String expectedAttributeValueGo = "Go";
		String actualAttributeValueGo = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[6]/div[1]/input")).getAttribute("value");
		Assert.assertTrue(actualAttributeValueGo.equals(expectedAttributeValueGo), "Go button not present");*/
		k.verifyText("Go", "value", "Go button not present", "XPathGoGoLink");
		logger.log(LogStatus.PASS, "Go button present");
		
		//Click on the Go button
		//driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[6]/div[1]/input")).submit();
		k.clickButtonSubmit("XPathGoLink");
		k.stepWiseScreenshot("ClickGoButton");
		logger.log(LogStatus.INFO, "Click on the Go button");
		
		//Single validation to check that the Inbox page has opened-Checking the text "Inbox link"
		/*String expectedTextInbox = "Inbox";
		String actualTextInbox = driver.findElement(By.xpath("//*[@id=\"boxscroll\"]/li[2]/a")).getText();
		Assert.assertTrue(actualTextInbox.equals(expectedTextInbox), "Validation failed. Not in the inbox page");*/
		k.verifyText("Inbox", "Validation failed. Not in the inbox page", "XPathInboxInboxLink");
		logger.log(LogStatus.PASS, "Inbox page opened");
		
		Thread.sleep(20000L);
		
		
		//Validation for web element - Write mail Link
		/*String expectedTextWriteMail = "Write mail";
		String actualTextWriteMail = driver.findElement(By.xpath("//*[@id=\"boxscroll\"]/li[1]/a")).getText();
		Assert.assertTrue(actualTextWriteMail.equals(expectedTextWriteMail), "Write mail link not present");*/
		k.verifyText("Write mail", "Write mail link not present", "XPathWriteWriteLink");
		logger.log(LogStatus.PASS, "Write mail link present");
		
		//Click on the write mail link
		//driver.findElement(By.xpath("//*[@id=\"boxscroll\"]/li[1]/a/b")).click();
		k.clickLink("XPathWriteLink");
		logger.log(LogStatus.INFO, "Click on the Write mail link");
		
		Thread.sleep(20000L);

		
		//Validation for the web element - To link
		/*String expectedTextTo = "To:";
		String actualTextTo = driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/ul/li[1]/label")).getText();
		System.out.println(actualTextTo);
		Assert.assertTrue(actualTextTo.equals(expectedTextTo), "To link not present");*/
		k.verifyText("To:", "To link not present", "XPathToToLink");
		logger.log(LogStatus.PASS, "To link present");
		
		
		//Type in the To field - Ensure to click on the field so that the cursor blinks and then type
		/*driver.findElement(By.xpath("//*[@id=\"TO_IDcmp2\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"TO_IDcmp2\"]")).sendKeys("selenium.testmay2017@rediffmail.com");
		driver.findElement(By.xpath("//*[@id=\"TO_IDcmp2\"]")).sendKeys(Keys.TAB);*/
		k.writeInInput("XPathToEditBox", h.get("email"));
		k.enumerateKeys("XPathToEditBox");
		logger.log(LogStatus.INFO, "Type in TO field");
		
		
		//Validation for the web element - Subject Field
		/*String expectedTextSubject = "Subject:";
		String actualTextSubject = driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/ul/li[4]/label")).getText();
		Assert.assertTrue(actualTextSubject.equals(expectedTextSubject), "Subject Label not present");*/
		k.verifyText("Subject:", "Subject Label not present", "XPathSubjectSubjectField");
		logger.log(LogStatus.PASS, "Subject Field present");
		
		//Type in the Subject field - Ensure to click on the field so that the cursor blinks and then type
		/*driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/ul/li[4]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/ul/li[4]/input")).sendKeys("Test");*/
		k.writeInInput("XPathSubjectEditBox", h.get("sub"));
		logger.log(LogStatus.INFO, "Type in Subject field");
		
		//Switching to the frame present in the compose area
		/*WebElement w1 = driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe"));
		driver.switchTo().frame(w1);*/
		k.frameSwitch("XPathFrameComposeArea");
		logger.log(LogStatus.INFO, "Swtiching to the frame of the compose are");
		
		//Type in the compose area- Ensure to click on the field so that the cursor blinks and then type
		/*driver.findElement(By.xpath("/html/body")).click();
		driver.findElement(By.xpath("/html/body")).sendKeys("Sending a test mail");*/
		k.writeInInput("XPathComposeAreaEditBox", h.get("cArea"));
		logger.log(LogStatus.INFO, "Typing in the compose area");
		
		
		//Switch back to the main page
		//driver.switchTo().defaultContent();//Move the focus of Selenium from frame to the main page
		k.frameGetOut();
		logger.log(LogStatus.INFO, "Swtiching from frame to the main page");
		
		
		//Validation for the web element - Send Link
		/*String expectedTextSend = "Send";
		String actualTextSend = driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/div[1]/a[1]")).getText();
		Assert.assertTrue(actualTextSend.equals(expectedTextSend), "Send link not present");*/
		k.verifyText("Send", "Send link not present", "XPathSendSendLink");
		logger.log(LogStatus.PASS, "Send link present");
		
		//Click on the Send link
		//driver.findElement(By.xpath("//*[@id=\"rd_compose_cmp2\"]/div[1]/a[1]")).click();
		k.clickLink("XPathSendLink");
		logger.log(LogStatus.INFO, "Click on the Send link");
		
		//Validation for the web element - Logout Link
		/*String expectedTextLogout = "Logout";
		String actualTextLogout = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/a[2]")).getText();
		Assert.assertTrue(actualTextLogout.equals(expectedTextLogout), "Logout link not present");*/
		k.verifyText("Logout", "Logout link not present", "XPathLogoutLogoutLink");
		logger.log(LogStatus.PASS, "Logout link present");
		
		//CLick on the logout link
		//driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/a[2]")).click();
		k.clickLink("XPathLogoutLink");
		logger.log(LogStatus.INFO, "Click on the logout link");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus() ==  ITestResult.FAILURE) {
			k.FailedScreenshot(result.getName());
			String y = k.FailedScreenshot(result.getName());
			String screencast = logger.addScreenCapture(y);
			logger.log(LogStatus.FAIL, screencast);
			
		}
		extent.endTest(logger);
		extent.flush();
	}
	
	@DataProvider
	public Object[][] TestData(){
		//Number of columns is 3 as parameterization would be carried out in TO field, Subject Area and Composition Area
		//Would like to run with browsers like Firefox, Chrome and IE - Thats why three rows are given
		//And since we would like to run with these three browsers, the browser part also needs to be parameterized
		//The fifth column will decide if we want to run the test script on a particular browser
		//Fifth column would be RUNMODE column
		//If the RUNMODE is YES, the test script will run with that browser and if the RUNMODE is NO, the test script will not run with that browser
		
		/*
		Object[][] obj1 = new Object[3][5];
		
		
		//First Row data
		obj1[0][0] = "selenium.testmay2017@rediffmail.com"; //To field
		obj1[0][1] = "Testing"; //Subject Area
		obj1[0][2] = "Sending a Test mail for testing"; //Composition Area
		obj1[0][3] = "Firefox"; //Subject Area
		obj1[0][4] = "YES"; //RUNMODE
		
		//Second Row data
		obj1[1][0] = "selenium.testmay2017@rediffmail.com"; //To field
		obj1[1][1] = "Development"; //Subject Area
		obj1[1][2] = "Sending a Test mail for Development"; //Composition Area
		obj1[1][3] = "Chrome"; //Subject Area
		obj1[1][4] = "YES"; //RUNMODE
		
		//Third Row data
		obj1[2][0] = "selenium.testmay2017@rediffmail.com"; //To field
		obj1[2][1] = "Production"; //Subject Area
		obj1[2][2] = "Sending a Test mail for production"; //Composition Area
		obj1[2][3] = "IE"; //Subject Area
		obj1[2][4] = "NO";//RUNMODE
		
		return obj1;
		*/
		//Implementing the Hashtable concept
		//Number of rows will have a specific hashtable
		//Column fixed at 1
		//Ensure every hashtable in every row should have the same key
		
		Object[][] obj2 = new Object[3][1];
		
		//First row hashtable
		/*Hashtable<String, String> h1 = new Hashtable<String, String>();
		h1.put("email","selenium.testmay2017@rediffmail.com");
		h1.put("sub","Testing");
		h1.put("cArea","Sending a Test mail for Testing");
		h1.put("browser","Firefox");
		h1.put("runMode","YES");
		
		//Second row hashtable
		Hashtable<String, String> h2 = new Hashtable<String, String>();
		h2.put("email","selenium.testmay2017@rediffmail.com");
		h2.put("sub","Development");
		h2.put("cArea","Sending a Test mail for Development");
		h2.put("browser","Chrome");
		h2.put("runMode","NO");
		
		//Third row hashtable
		Hashtable<String, String> h3 = new Hashtable<String, String>();
		h3.put("email","selenium.testmay2017@rediffmail.com");
		h3.put("sub","Production");
		h3.put("cArea","Sending a Test mail for Production");
		h3.put("browser","IE");
		h3.put("runMode","NO");
		
		//Putting the hashtable in respective rows
		obj2[0][0] = h1;
		obj2[1][0] = h2;
		obj2[2][0] = h3;
		
		return obj2;*/
				
		return TestUtils.getData("SendEmail", xls);
	}

}
