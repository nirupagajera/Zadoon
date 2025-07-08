package Zadoon.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class FreeMachineAfterLogin extends Register 
{
    public String line;
    public String CsvSplit = ",";
    
    @Test(priority = 7)
    public void machine() throws Exception 
    {
        WebElement freeMachine, submit, toastMessage;
        String Icsv = "FreeMachine.csv";
        BufferedReader br = new BufferedReader(new FileReader(Icsv));
        String header = br.readLine();
        System.out.println(header);
        
        final int testcaseIndex = 0;
        final int firstnameIndex = 1;
        final int lastnameIndex = 2;
        final int phoneIndex = 3;
        final int modelIndex = 4;
        final int manufacturerIndex = 5;
        final int hoursIndex = 6;
        final int messageIndex = 7;
        
        Thread.sleep(2000);
        freeMachine = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/div/button"));
        freeMachine.click();
        
        while ((line = br.readLine()) != null) 
        {
            String[] Data = line.split(CsvSplit);
            String testCase = (Data.length>testcaseIndex ? Data[testcaseIndex].trim(): " ");
            String firstName = (Data.length>firstnameIndex ? Data[firstnameIndex].trim(): " ");
            String lastName = (Data.length>lastnameIndex ? Data[lastnameIndex].trim(): " ");
            String phone = (Data.length>phoneIndex ? Data[phoneIndex].trim(): " ");
            String model = (Data.length>modelIndex ? Data[modelIndex].trim(): " ");
            String manufacturer = (Data.length>manufacturerIndex ? Data[manufacturerIndex].trim(): " ");
            String hours = (Data.length>hoursIndex ? Data[hoursIndex].trim(): " ");
            String message = (Data.length>messageIndex ? Data[messageIndex].trim(): " ");
            
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[1]/div[1]/input"), firstName);
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[1]/div[2]/input"), lastName);
            fillPhoneField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[2]/div[2]/div/input"), phone);
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[3]/div[1]/input"), model);
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[3]/div[2]/input"), manufacturer);
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[4]/div/input"), hours);
            fillInputField(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[5]/textarea"), message);

            submit = driver.findElement(By.xpath("/html/body/div[2]/div[2]/section/div[2]/form/div[6]/button"));
            submit.click();
            
            ExtentTest test = ExtentManager.getInstance().createTest("Free Machine Evaluation Form - " + testCase);
            
            String expectedResult = "When user submit the Free machine evaluation form with valid data then user should be received success message.";
	        String actualResult = "User is successfully received the success message.";
	        String failResult = "Expected result didn't match with actual result.";
            
            toastMessage = driver.findElement(By.className("Toastify__toast-body"));
       
            if (toastMessage.getText().contains("Your request has been submitted. A zadoon representative will contact you to follow up.")) 
            {
                test.pass("Form is submitted Successfully.");
                
                String path = ScreenshotUtil.captureScreenshot(driver, "Free Machine Evaluation_Sucess");
	            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	            
	            test.info("Expected Result : " + expectedResult);
	        	test.info("Actual Result : " + actualResult);
                
                freeMachine.click();
            } 
            else 
            {
                test.fail("Form is showing an error message.");
                
                String path = ScreenshotUtil.captureScreenshot(driver, "Free Machine Evaluation_Error");
		        test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		        
		        test.info("Expected Result : " + expectedResult);
	        	test.info("Failed Result : " + failResult);
                
                driver.navigate().refresh();
                
                freeMachine.click();               
            }
        }
        br.close();
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
        String currentPhone = element.getAttribute("value");
        for (int i = 0; i < currentPhone.length(); i++) 
        {
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(value);
    }
}

