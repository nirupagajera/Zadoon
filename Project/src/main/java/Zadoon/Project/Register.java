package Zadoon.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Register extends Sell_Request  
{
	@Test(priority = 6)
	public void register() throws Exception 
	{
		WebElement signUp, checkBox, submit, logout, emailPath, passwordPath;
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-250)");
		
		logout = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/ul/li[7]/button"));
		logout.click();
		
		signUp = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/p/a"));
		signUp.click();
		
		String RCSV = "Register.csv";
		String line;
		String csvSplit = ",";
		
		BufferedReader br = new BufferedReader(new FileReader(RCSV));
		String header = br.readLine();
		System.out.println(header);
		
		final int testcaseIndex = 0;
		final int firstnameIndex = 1;
		final int lastnameIndex = 2;
		final int usernameIndex = 3;
		final int emailIndex = 4;
		final int passwordIndex = 5;
		final int companyIndex = 6;
		
		while((line = br.readLine())!= null) 
		{
			String[] Data = line.split(csvSplit);
			
			String testCase = (Data.length>testcaseIndex ? Data[testcaseIndex].trim(): " ");
			String firstName = (Data.length>firstnameIndex ? Data[firstnameIndex].trim(): " ");
			String lastName = (Data.length>lastnameIndex ? Data[lastnameIndex].trim(): " ");
			String userName = (Data.length>usernameIndex ? Data[usernameIndex].trim(): " ");
			String email = (Data.length>emailIndex ? Data[emailIndex].trim(): " ");
			String password = (Data.length>passwordIndex ? Data[passwordIndex].trim(): " ");
			String companyName = (Data.length>companyIndex ? Data[companyIndex].trim(): " ");
			
			System.out.println(line);
			
			fillInputField(By.name("first_name"),firstName);
			fillInputField(By.name("last_name"),lastName);
			fillInputField(By.name("username"),userName);
			fillInputField(By.name("email"),email);
			fillPasswordField(By.name("password"),password);
			fillInputField(By.name("acf.company_name"),companyName);
			
			checkBox = driver.findElement(By.xpath("//*[@id=\"agree\"]"));
			checkBox.click();
			
			submit = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[7]/button"));
			submit.click();
			
			ExtentTest test = ExtentManager.getInstance().createTest("Register Form - " + testCase);
			String expectedUrl = "https://zadoon-frontend.vercel.app/login";
			
			Thread.sleep(5000);
			
			String expectedResult = "When user submit the register form with valid data then user should be successfully register on the website and redirecting on login page.";
	        String actualResult = "User is successfully registered and redirected on login page.";
	        String failResult = "Expected result didn't match with actual result.";
			
			String actualUrl = driver.getCurrentUrl();
			System.out.println(actualUrl);
			if(expectedUrl.equals(actualUrl)) 
			{
				test.pass("Registration Successful.");
				
				String path = ScreenshotUtil.captureScreenshot(driver, "Register_Success");
	            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	            
	            test.info("Expected Result : " + expectedResult);
	        	test.info("Actual Result : " + actualResult);
								
				emailPath = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[1]/input"));
				emailPath.clear();
				emailPath.sendKeys(email);
	        	
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        	
	        	passwordPath = driver.findElement(By.name("password"));
	        	passwordPath.sendKeys(Keys.CONTROL + "a");
	        	passwordPath.sendKeys(Keys.DELETE);
	        	
	        	passwordPath.sendKeys(password);
	        	
	        	submit = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[4]/button"));
	        	submit.click();
	        	Thread.sleep(5000);
				
			}
			else 
			{
				test.fail("Registration form is showing error message.");
				
				String path = ScreenshotUtil.captureScreenshot(driver, "Register_Error");
		        test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		        
		        test.info("Expected Result : " + expectedResult);
	        	test.info("Failed Result : " + failResult);
				
//				String [][] FormValidation = {
//						{"//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[1]/div[1]/div","FirstName field is working proper."},
//						{"//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[1]/div[2]/div","LastName field is working proper."},
//						{"//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[2]/div","UserName field is working proper."},
//						{"//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[3]/div","Email field is working proper."},
//						{"//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[4]/div[2]","Password field is working proper."},
//					};
//				for(String[] Validation : FormValidation) {
//					try {
//						Validation1 = driver.findElement(By.xpath(Validation[0]));
//						String ValidationError = Validation1.getText();
//						System.out.println(RED + ValidationError + RESET);
//					}
//					catch(Exception e) {
//						System.out.println(GREEN + Validation[1] + RESET);
//					}
//				}
				driver.navigate().refresh();
			}
		}
		br.close();
	}
	public void fillInputField(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	public void fillPasswordField(By locator, String value) {
		WebElement element = driver.findElement(locator);
		element.sendKeys(Keys.CONTROL+"a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(value);
	}
}

