package com.hybrid.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

public class Keywords {
	
	WebDriver driver;
	Properties p1;//This is to read the OR.properties file
	Properties p2; //This is to read the respective environment based property file
	public int x;
	
	
	public Keywords()  {
		try {
			p1 = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\src\\com\\hybrid\\config\\OR.properties");
			p1.load(fis);
			String environment = p1.getProperty("env");//UAT
			p2 = new Properties();
			fis = new FileInputStream("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\src\\com\\hybrid\\config\\"+environment+".properties");
			p2.load(fis);
		
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	
	public void openBrowser(String browser) {
		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
			//Maximising the window
			driver.manage().window().maximize();
		}else if(browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else if(browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
	}
	
	public void navigate() {
		//driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.get(p2.getProperty("url"));
	}
	
	//Implicit wait
	public void pauseImplicit() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//Validation for text present in any control
	public void verifyText(String expectedAttributeValue, String attributeType, String msg, String locator) {
		String expectedAttributeValueLogo = expectedAttributeValue;
		//String expectedAttributeValueLogo = "rediffmail-logo";
		//String actualAttributeValueLogo = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/div")).getAttribute("class");
		String actualAttributeValueLogo = driver.findElement(By.xpath(p1.getProperty(locator))).getAttribute(attributeType);
		//Assert.assertTrue(actualAttributeValueLogo.equals(expectedAttributeValueLogo), "Validation failed. Not in the landing page");
		Assert.assertTrue(actualAttributeValueLogo.equals(expectedAttributeValueLogo), msg);
	}
	
	//Validation for static text - Overloading
	public void verifyText(String expectedText, String msg, String locator) {
		String expected = expectedText;
		String actual = driver.findElement(By.xpath(p1.getProperty(locator))).getText();
		Assert.assertTrue(actual.equals(expected), msg);
	}
	
	//Typing - Username
	public void writeInInputUserName(String Locator, String uname) {
		driver.findElement(By.xpath(p1.getProperty(Locator))).sendKeys(p2.getProperty(uname));
	}
	
	//Typing Password
	public void writeInInputPassword(String Locator, String pwd) {
		driver.findElement(By.xpath(p1.getProperty(Locator))).sendKeys(p2.getProperty(pwd));
	}
	
	//Button - Submit
	public void clickButtonSubmit(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).submit();
	}
	
	//Button - Click
	public void clickButtonClick(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).click();;
	}
	
	//Link - Click
	public void clickLink(String Locator) {
		driver.findElement(By.xpath(p1.getProperty(Locator))).click();
	}
	
	//Typing - All Edit boxes
	public void writeInInput(String locator, String data) {
		driver.findElement(By.xpath(p1.getProperty(locator))).click();
		driver.findElement(By.xpath(p1.getProperty(locator))).sendKeys(data);
	}
	
	//Key Enumerator - TAB button
	public void enumerateKeys(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).sendKeys(Keys.TAB);
	}
	
	//Switch to Frame from main page
	public void frameSwitch(String locator) {
		WebElement w1 = driver.findElement(By.xpath(p1.getProperty(locator)));
		driver.switchTo().frame(w1);
	}
	
	//Swtich from frame to main page
	public void frameGetOut() {
		driver.switchTo().defaultContent();
	}
	
	//Selection of specific Radio button
	public void selectRadio(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).click();
	}
	
	//TO check if a specific radio button is selected or not
	public void isRadioButtonSelected(String locator) {
		boolean value = driver.findElement(By.xpath(p1.getProperty(locator))).isSelected();
		if(value==true) {
			System.out.println("Radio button is selected");
		}else {
			System.out.println("Radio button is not selected");
		}
	}
	
	//Selection of specific Checkbox
	public void checkCheckBox(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).click();
	}
	
	//Selection of specific Checkbox
	public void uncheckCheckBox(String locator) {
		driver.findElement(By.xpath(p1.getProperty(locator))).click();
	}
	
	//To check if a specific checkbox is selected or not
	public void isCheckboxSelected(String locator) {
		boolean value = driver.findElement(By.xpath(p1.getProperty(locator))).isSelected();
		if(value==true) {
			System.out.println("Checkbox is selected");
		}else {
			System.out.println("Checkbox is not selected");
		}
	}
	
	//Application independent
	//TO check if a webelement is present or not - If present then perform Selenium based action
	public boolean isElementPresent(String element, String locatorValue, String locatorType) {
		//locatorType - xpath, cssSelectoe, id, name, className tagname, linkText, partialLinkText
		//locatorValue - value of xpath, cssSelector,id, name, className tagname, linkText, partialLinkText
		//int x;
		if(locatorType.equals("xpath")) {
			x = driver.findElements(By.xpath(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("cssSelector")) {
			x = driver.findElements(By.cssSelector(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("id")) {
			x = driver.findElements(By.id(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("className")) {
			x = driver.findElements(By.className(p1.getProperty(locatorValue))).size();
			
		}else if (locatorType.equals("name")) {
			x = driver.findElements(By.name(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("linkText")) {
			x = driver.findElements(By.linkText(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("partialLinkText")) {
			x = driver.findElements(By.partialLinkText(p1.getProperty(locatorValue))).size();
			
		}else if(locatorType.equals("tagName")) {
			x = driver.findElements(By.tagName(p1.getProperty(locatorValue))).size();
			
		}
		
		if(x==0) {
			System.out.println("The "+element+" is not present");
			return false;
		}else {
			System.out.println("The "+element+" is present");
			return true;
		}
	
		
	}
	
	//Application independent  - Stepwise ScreenShot
	public void stepWiseScreenshot(String Stepname) {
		try {
			TakesScreenshot t = (TakesScreenshot)driver;
			File tempLocation = t.getScreenshotAs(OutputType.FILE);
			File permLocation = new File("C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\StepWieseScreenshot\\"+Stepname+".jpg");
			FileUtils.copyFile(tempLocation, permLocation);
		
		}catch(Exception e) {
			String x = e.getMessage();
			System.out.println(x);
		}
	}
	
	//Application Independent  - Failed Screenshot
	public String FailedScreenshot(String name) {
		try {
			TakesScreenshot scrStepwise = (TakesScreenshot)driver;
			File tempLocation = scrStepwise.getScreenshotAs(OutputType.FILE);
			String path = "C:\\Users\\acer\\Desktop\\Workspace_July2017\\HybridFramework\\FailedScreenshot\\"+name+".jpg";
			File permanentLocation = new File(path);
			FileUtils.copyFile(tempLocation, permanentLocation);
			return path;
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot "+e.getMessage());
			return e.getMessage();
		}
		
	}
	
	
	

}
