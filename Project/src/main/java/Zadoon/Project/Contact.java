package Zadoon.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Contact extends Login 
{
	@Test(priority = 2)	
	public void Contact_UsTest() throws Exception 
	{
			WebElement contactLink, submit, login;
		        
			contactLink = driver.findElement(By.linkText("Contact Us"));
			contactLink.click();
			Thread.sleep(2000);
			System.out.println("Contact Us Page : " + driver.getTitle());
				
	        String inputCsv = "Data.csv";
	        String outputCsv = "Result.csv";
	        String line;
	        String csvSplitBy = ",";
	        
	        BufferedReader br = new BufferedReader(new FileReader(inputCsv));
	        BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsv));
	        
	        final int testcaseIndex = 0;
	        final int subjectIndex = 1;
	        final int firstnameIndex = 2;
	        final int lastnameIndex =3 ;
	        final int emailIndex = 4;
	        final int phoneIndex = 5;
	        final int messageIndex = 6;
        	        
	        String header = br.readLine();
	        
	        if(header != null) 
	        {
	        	bw.write(header + ", Status\n");
	        }
	  	       
	        while ((line = br.readLine()) != null)
	        {
	        	String[] formData = line.split(csvSplitBy);

	        	String testCase = (formData.length>testcaseIndex? formData[testcaseIndex].trim(): "");
	            String subject = (formData.length>subjectIndex ? formData[subjectIndex].trim(): "");
	            String firstName = (formData.length>firstnameIndex ? formData[firstnameIndex].trim(): "");
	            String lastName = (formData.length>lastnameIndex ? formData[lastnameIndex].trim(): "");
	            String email = (formData.length>emailIndex ? formData[emailIndex].trim(): "");
	            String phone = (formData.length>phoneIndex ? formData[phoneIndex].trim(): "");
	            String message = (formData.length>messageIndex ? formData[messageIndex].trim(): "");
	            
	            fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[1]/input"),subject);
	            fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[2]/div[1]/input"),firstName);
	            fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[2]/div[2]/input"),lastName);
	            fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[3]/div[1]/input"),email);
	            fillPhoneField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[3]/div[2]/div/input"),phone);
	            fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[4]/textarea"),message);
	                 
		        submit = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[5]/button"));
		        submit.click();
		        System.out.println(line);
		        
		        ExtentTest test = ExtentManager.getInstance().createTest("Contact Form Test - " + testCase);
		        		       
		        Thread.sleep(5000);
		        String expectedResult = "When user submit the form with valid data then user should be redirecting on thank you page.";
		        String actualResult = "User is successfully redirected on thank you page.";
		        String failResult = "Expected result didn't match with actual result.";
		       
		        String expectedUrl = "https://zadoon-frontend.vercel.app/thank-you";
		        String currentUrl = driver.getCurrentUrl();
		        
		        if (expectedUrl.equals(currentUrl))
		        {
		        	test.pass("Form is submitted successfully");
		        	bw.write(line + ",Pass\n");
		        	
		        	String path = ScreenshotUtil.captureScreenshot(driver, "Contact_Success");
		            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		            test.info("Expected Result : " + expectedResult);
		        	test.info("Actual Result : " + actualResult);
		        	
		        	login = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/div[2]"));
		        	login.click();
		        }
		        else 
		        {
				    test.fail("Form is showing error message");
			
				    String path = ScreenshotUtil.captureScreenshot(driver, "Contact_Error");
			        test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());		
			        			        
			        test.info("Expected Result : " + expectedResult);
		        	test.info("Failed Result : " + failResult);
		        	
//			        String [][] Datavalidation= {
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[1]/div", "Subject field is working proper."},
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[2]/div[1]/div", "FirstName field is working proper."},
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[2]/div[2]/div", "LastName field is working proper."},
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[3]/div[1]/div", "Email field is working proper."},
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[3]/div[2]/div[2]", "Phone field is working proper."},
//			        		{"//*[@id=\"__next\"]/div/div[3]/div/div/div[2]/form/div[4]/div", "Message field is working proper."},
//			        };
//			        
//			        for(String[] Data : Datavalidation) {
//			        	try {
//			        		Validation = driver.findElement(By.xpath(Data[0]));
//			        		String ValidationError = Validation.getText();
//			        		System.out.println(RED + ValidationError +RESET);
//			        	}
//			        	catch(Exception e){
//			        		System.out.println(GREEN + Data[1] + RESET);
//			        	}
//			        }
				   
				    bw.write(line + ",Failed\n");
				    driver.navigate().refresh();
				    Thread.sleep(2000);
				}
	        } 
		        br.close();
		        bw.close();             
	}
	public void fillInputField(By locator, String value) 
	{
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(value);
	}
	public void fillPhoneField(By locator, String value) 
	{
		WebElement element = driver.findElement(locator);
		String phoneValue = element.getAttribute("value");
		for(int i=0;i<phoneValue.length();i++) 
		{
			element.sendKeys(Keys.BACK_SPACE);
		}
		element.sendKeys(value);
	}
}

