package mk.Albanero.Albanero_SignUp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Albanero_Sign_Up {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();  
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Accessing URL
		driver.get("https://demo.albanero.io/");
		
		//Verify SignUp link and page
		driver.findElement(By.xpath("//*[@data-test-id='sign-up']")).click();
		String signUp = driver.findElement(By.xpath("//form/h1")).getText();
		System.out.println("User on " + signUp +" page.");
		Assert.assertEquals(signUp, "Sign Up");

		//Locating and input data in text fields
		String commonPath = "parent::div/parent::div/following-sibling::div//input";
		String rdStr = String.valueOf(System.currentTimeMillis());
		driver.findElement(By.xpath("//*[contains(text(),'First Name')]/"+commonPath)).sendKeys("Albanero");
		driver.findElement(By.xpath("//*[contains(text(),'Last Name')]/"+commonPath)).sendKeys("QA");
		driver.findElement(By.xpath("//*[contains(text(),'Email')]/"+commonPath)).sendKeys("albanqa2106"+rdStr+"@test.com");
		driver.findElement(By.xpath("//*[contains(text(),'Username')]/"+commonPath)).sendKeys("alban_qa2023"+rdStr);

		//Password and Confirm Password
		WebElement passwd = driver.findElement(By.id("passwordtype"));
		passwd.sendKeys("Test@2023");
		driver.findElement(By.xpath("//*[@data-test-id='passwordtype']//span")).click();
		driver.findElement(By.xpath("//*[@id='confirmpasswordtype']")).sendKeys("Test@2023");
		driver.findElement(By.xpath("//*[@data-test-id='confirmpasswordtype']//span")).click();

		//Terms check
		WebElement tnc = driver.findElement(By.xpath("//*[@data-test-id='terms-checkbox']/span"));
		tnc.click();
		
		//Captcha checkbox
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='reCAPTCHA']")));
		driver.findElement(By.xpath("//*[@role='checkbox']")).click();
		Thread.sleep(7000);	// will use Explicit wait...
		String captchaCheck = driver.findElement(By.xpath("//*[@aria-checked='true']")).getAttribute("aria-checked");
		captchaCheck.equalsIgnoreCase("true");
		driver.switchTo().defaultContent();

		//Next button and QR check
		driver.findElement(By.xpath("//*[@datatestid='next-sign-up']")).click();
		String qrValid = driver.findElement(By.xpath("//*[@id='magic-link-title']//*[contains(text(),'Please Scan the QR')]")).getText();
		qrValid.equals("Please Scan the QR");
		
		System.out.println(qrValid);
		
		//Click on Register
		driver.findElement(By.xpath("//*[@datatestid='register-button']")).click();
		
		Thread.sleep(3000);

		driver.quit();
		
	}

}
