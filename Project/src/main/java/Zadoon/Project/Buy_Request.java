package Zadoon.Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Buy_Request extends Contact 
{	 	
	@Test(priority = 4)
	public void buy_request() throws Exception 
	{		
		WebElement profile_Icon, dashboard_URL, buy_Request_Tab, request_Want_To_Buy, categoryPath, search_Category, manufacturerPath, 
		search_Manufacturer, modelPath, search_Model, submit;	  	     
		
	    profile_Icon = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/button/span[2]"));
		profile_Icon.click();
			
		dashboard_URL = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/ul/li[1]/a"));
		dashboard_URL.click();
			
		buy_Request_Tab = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/div/ul/li[1]/a"));
		buy_Request_Tab.click();

		request_Want_To_Buy = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/div[1]/div/a"));
		request_Want_To_Buy.click();
			
		String CSV = "Buy.csv";
		String line;
		String CSVsplit = ",";
		BufferedReader br = new BufferedReader(new FileReader(CSV));
		
		String header = br.readLine();
		System.out.println(header);
		
		final int testcaseIndex = 0;
		final int categoryIndex = 1;
		final int manufacturerIndex = 2;
		final int modelIndex = 3;
		final int yearMinIndex = 4;
		final int yearMaxIndex = 5;
		final int hourMinIndex = 6;
		final int hourMaxIndex = 7;
		final int budgetMinIndex = 8;
		final int budgetMaxIndex = 9;
		final int phoneIndex = 10;
		final int additionalNotesIndex = 11;
		
		
		while((line=br.readLine())!=null) {
			String [] Index = line.split(CSVsplit);
			
			String testcase = (Index.length>testcaseIndex ? Index[testcaseIndex].trim() : "");
			String category = (Index.length > categoryIndex ? Index[categoryIndex].trim() : "");
			String manufacturer = (Index.length > manufacturerIndex ? Index[manufacturerIndex].trim() : "");
			String model = (Index.length > modelIndex ? Index[modelIndex].trim() : "");
			String year_min = (Index.length > yearMinIndex ? Index[yearMinIndex].trim() : "");
			String year_max = (Index.length > yearMaxIndex ? Index[yearMaxIndex].trim() : "");
			String hour_min = (Index.length > hourMinIndex ? Index[hourMinIndex].trim() : "");
			String hour_max = (Index.length > hourMaxIndex ? Index[hourMaxIndex].trim() : "");
			String budget_min = (Index.length > budgetMinIndex ? Index[budgetMinIndex].trim() : "");
			String budget_max = (Index.length > budgetMaxIndex ? Index[budgetMaxIndex].trim() : "");
			String phone_number = (Index.length > phoneIndex ? Index[phoneIndex].trim() : "");
			String additional_notes = (Index.length > additionalNotesIndex ? Index[additionalNotesIndex].trim() : "");
			

			
			System.out.println(line);
			
		//Category Selection
			
		categoryPath = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[2]/div/div/input"));
		categoryPath.click();
		
		Thread.sleep(3000);
		search_Category = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[2]/div/ul")); 
		
			List<WebElement> Category_options = search_Category.findElements(By.tagName("li"));
			boolean categoryFound = false;

			for (WebElement option : Category_options)	
			{
				    if (option.getText().equals(category))
				    {
				        option.click();
				        categoryFound = true;
				        break;
				    }
			    
			}
			
			if (!categoryFound) 
			{
			    List<WebElement> Category_options_Strong = search_Category.findElements(By.tagName("strong"));
			    
			    for (WebElement option1 : Category_options_Strong) 
			    {
			        if (option1.getText().trim().equalsIgnoreCase(category)) 
			        {
			            option1.click();
			            categoryFound = true;
			            break;
			        }
			    }
			}


		//Manufacturer Selection
		
		manufacturerPath = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[2]/div/div/input"));
		manufacturerPath.click();
		
		Thread.sleep(3000);
		search_Manufacturer = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[2]/div/ul"));
		
			List<WebElement> Manufacturer_values = search_Manufacturer.findElements(By.tagName("li"));
			for(WebElement value : Manufacturer_values) 
			{
				if(value.getText().equals(manufacturer)) 
				{
					value.click();
					break;
				}
			}

		//Model Selection

		modelPath = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[2]/div/div/input"));
		modelPath.click();
		
		Thread.sleep(3000);
		search_Model = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[2]/div/ul"));
		
			List<WebElement> Model_datas = search_Model.findElements(By.tagName("li"));
			for(WebElement data : Model_datas) 
			{
				if(data.getText().equals(model)) 
				{
					data.click();
					break;
					
				}
			}
		
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/input"),year_min);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[1]/div/div[2]/div[3]/input"),year_max);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[2]/div/div[2]/div[1]/input"),hour_min);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[2]/div/div[2]/div[3]/input"),hour_max);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[3]/div/div[2]/div[1]/input"),budget_min);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[3]/div/div[2]/div[3]/input"),budget_max);
		fillPhoneField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[4]/div/div[2]/div/input"),phone_number);
		fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div/textarea"),additional_notes);
		
		submit = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[3]/div/button"));
		submit.click();
		
		Thread.sleep(3000);
		
		ExtentTest test = ExtentManager.getInstance().createTest("Want to Buy Form - " + testcase);
		
		String expectedResult = "When user submit the want to buy form with valid data then user should be redirecting on want to sell inquiry listing page.";
        String actualResult = "User is successfully redirected on want to buy inquiry listing page.";
        String failResult = "Expected result didn't match with actual result.";
		
		String expected_Url = "https://zadoon-frontend.vercel.app/profile/buy-request";
		String current_Url = driver.getCurrentUrl();
		
			if(expected_Url.equals(current_Url)) {
				test.pass("Form is submitted successfully.");
				
				String path = ScreenshotUtil.captureScreenshot(driver, "Want to Buy_Success");
	            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	            
	            test.info("Expected Result : " + expectedResult);
	        	test.info("Actual Result : " + actualResult);
	            
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,-250)");
	
				request_Want_To_Buy = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/div[1]/div/a"));
				request_Want_To_Buy.click(); 
				
	
			}
			else 
			{
				test.fail("Form is showing error message.");	
				
				String path = ScreenshotUtil.captureScreenshot(driver, "Want to Buy_Error");
		        test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		        
		        test.info("Expected Result : " + expectedResult);
	        	test.info("Failed Result : " + failResult);
				
	//			String[][] FormValidation = {
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[3]", "Category field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[3]","Manufacturer field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[3]","Model field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/div","Min year field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[1]/div/div[2]/div[3]/div","Max year field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[2]/div/div[2]/div[1]/div","Min hour field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[2]/div/div[2]/div[3]/div","Max hour field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[3]/div/div[2]/div[1]/div","Min budget field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[3]/div/div[2]/div[3]/div","Max budget field is working proper."},
	//					{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/ul/li[4]/div/div[3]","Phone number field is working proper."},
	//			
	//			};
	//			for(String [] Validation : FormValidation) {
	//				try {
	//				Validation_Message = driver.findElement(By.xpath(Validation[0]));
	//				String Validation_Error = Validation_Message.getText();
	//				System.out.println(RED + Validation_Error + RESET);
	//				}
	//				catch(Exception e) {
	//					System.out.println(GREEN + Validation[1] + RESET);
	//				}
	//			}
				String currentUrl = driver.getCurrentUrl(); 
				String cleanUrl = currentUrl.split("\\?")[0]; // Remove query parameters
				driver.get(cleanUrl);
			}
		}
		br.close();
	}
	
	public void fillInputField(By locator, String Value) 
	{
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(Value);
	}
	public void fillPhoneField(By locator, String Value) 
	{
		WebElement element = driver.findElement(locator);
		String Current_Phone = element.getAttribute("value");
		for(int i=0; i<Current_Phone.length(); i++) 
		{
			element.sendKeys(Keys.BACK_SPACE);
		}
		element.sendKeys(Value);
	}
	
}
