package Zadoon.Project;
import java.time.Duration;
import org.openqa.selenium.By;
import java.io.BufferedReader;
import org.openqa.selenium.Keys;
import java.io.FileReader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Login extends Browser_Open 
{	
	public String csvSplit = ",";
	public String line;
	
    @Test(priority = 3)
    public void login()throws Exception 
    {	
    	WebElement button, email, password, submit;
    	
        button = driver.findElement(By.linkText("Free Machine Evaluation"));
        button.click();
        
        String inputFile = "login.csv";	
    	BufferedReader read = new BufferedReader(new FileReader(inputFile));
        
        Thread.sleep(2000);
        
        String currentURl = driver.getCurrentUrl();
        if (currentURl.contains("login")) 
        {	
        	System.out.println("You need to login first");
        	
        	String header = read.readLine();
        	System.out.println(header);
        	        	
        	final int testcaseIndex = 0;
        	final int emailIndex = 1;
        	final int passwordIndex = 2;
        	
        	while((line=read.readLine())!=null) 
        	{
	        	String[] formData = line.split(csvSplit);
	        	
	        	String testcaseCsv = (formData.length>testcaseIndex ? formData[testcaseIndex].trim(): "");
	        	String emailCsv = (formData.length>emailIndex ? formData[emailIndex].trim(): "");
	        	String passwordCsv = (formData.length>passwordIndex ? formData[passwordIndex].trim(): "");
	        	  	
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	        	email = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[1]/input"));
	        	email.clear();
	        	email.sendKeys(emailCsv);
	        	
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        	
	        	password = driver.findElement(By.name("password"));
	        	password.sendKeys(Keys.CONTROL + "a");
	        	password.sendKeys(Keys.DELETE);
        	
	        	password.sendKeys(passwordCsv);
	        	        	
	        	Thread.sleep(1000);
	
	        	submit = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/div[2]/div/div/form/div[4]/button"));
	        	submit.click();
	        	Thread.sleep(3000);
	        	
	        	ExtentTest test = ExtentManager.getInstance().createTest("Login Form Test - " + testcaseCsv);
	        	
	        	String expectedResult = "When user submit the login form with valid data then user should be successfully redirecting on login Page.";
		        String actualResult = "User is successfully logged in.";
		        String failResult = "Expected result didn't match with actual result.";
	
	        	String expectedUrl = "https://zadoon-frontend.vercel.app/login";
	        	String currentUrl = driver.getCurrentUrl();
        	
	        	if(expectedUrl.equals(currentUrl)) 
	        	{
	        		
	        		test.fail("You are entering invalid username or password.");
	        		
	        		String path = ScreenshotUtil.captureScreenshot(driver, "Login_Error");
			        test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			        
			        test.info("Expected Result : " + expectedResult);
		        	test.info("Failed Result : " + failResult);
        		
        		
//        		String [][] Datavalidation = {
//        				{"Toastify__toast-body", "Email field is working proper."},
//        				{"Toastify__toast-body", "Password field is working proper."}
//        		};
//        		
//        		for(String [] data: Datavalidation) {
//				    try {
//					    Validation = driver.findElement(By.className(data[0]));
//					    String ValidationError = Validation.getText();
//					    System.out.println(ValidationError);
//					    
//					    }
//				    catch(Exception e) {
//				    	System.out.println(data[1]);
//				    }
//        	}    		
	        	}
	        	else 
	        	{	        		
	        		test.pass("You are Successfully login.");
	        		
	        		String path = ScreenshotUtil.captureScreenshot(driver, "Login_Success");
		            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());	
		            
		            test.info("Expected Result : " + expectedResult);
		        	test.info("Actual Result : " + actualResult);
	        	}
	        }        	        	
	        read.close();
        }
	    else
		{
	    	System.out.println("You are already login");   	
		}
	    driver.navigate().refresh();
    }
        
}	
