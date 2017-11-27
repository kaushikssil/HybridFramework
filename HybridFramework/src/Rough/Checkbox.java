package Rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Checkbox {
	
	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_checkbox");
		
		//To switch to the frame
		WebElement w1 = driver.findElement(By.xpath("//*[@id=\"iframeResult\"]"));
		driver.switchTo().frame(w1);
		
		//To get the Text from the checkbox - I have a bike
		String x = driver.findElement(By.xpath("/html/body/form/input[1]")).getText();
		System.out.println(x);
		
		//To check the check box "I have a bike"
		driver.findElement(By.xpath("/html/body/form/input[1]")).click();
		
		//TO check if "I have a bike" checkbox is selected
		boolean y = driver.findElement(By.xpath("/html/body/form/input[1]")).isSelected();
		System.out.println(y);
		
		//TO check if "I have a car" checkbox is selected
		boolean z = driver.findElement(By.xpath("/html/body/form/input[2]")).isSelected();
		System.out.println(z);
		
		//To get the attribute value from the checkbox - I have a bike
		String a = driver.findElement(By.xpath("/html/body/form/input[1]")).getAttribute("value");
		System.out.println(a);
		
	}

}
