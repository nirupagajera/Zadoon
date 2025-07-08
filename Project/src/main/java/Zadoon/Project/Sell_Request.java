package Zadoon.Project;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Sell_Request extends Buy_Request
{

	@Test(priority = 5)
	public void Sell_Form() throws Exception  
	{
		
		WebElement profile_Icon, dashboard_URL, sell_Request_tab, request_Want_to_Sell_Button , 
		category, search_Category, manufacturer, search_Manufacturer , model, search_Model , dropdownButton , 
		dropdownList , scrollable_Element, yearDropdown, yearList, yearScrollable_Element, statebutton, stateList,
		stateScroll, file_Input, submit_Button, want_to_Sell_Button;
		
		profile_Icon = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/button/span[2]"));
		profile_Icon.click();
			
		dashboard_URL = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/header/div[1]/div[2]/div[3]/div[2]/div/div[2]/ul/li[1]/a"));
		dashboard_URL.click();
		
		sell_Request_tab = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[1]/div/ul/li[2]"));
		sell_Request_tab.click();
		
		request_Want_to_Sell_Button = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/div[1]/div/a"));
		request_Want_to_Sell_Button.click();
		
		String CSV = "Sell.csv";
		String line;
		String CSVSplit = ",";
		
		BufferedReader br = new BufferedReader(new FileReader(CSV));
		
		String header = br.readLine();
		System.out.println(header);
		
		final int testcaseIndex = 0;
		final int categoryIndex = 1;
		final int manufacturerIndex = 2;
		final int modelIndex = 3;
		final int yearIndex = 4;
		final int hourIndex = 5;
		final int serialnumberIndex = 6;
		final int countryIndex = 7;
		final int StateIndex = 8;
		final int cityIndex = 9;
		final int image_Uploader_Index = 10;
		final int drive_URL_Index = 11;
		final int price_Index = 12;
		final int description_Index = 13;
		final int openIndex = 14;
		
		while((line = br.readLine())!= null) 
		{
			
			String [] FormData = line.split(CSVSplit);
			
			String testcasePath = (FormData.length> testcaseIndex)? FormData[testcaseIndex].trim() : " ";
			String categoryPath = (FormData.length> categoryIndex)? FormData[categoryIndex].trim() : " ";
			String manufacturerPath = (FormData.length> manufacturerIndex)? FormData[manufacturerIndex].trim() : " ";
			String modelPath = (FormData.length> modelIndex)? FormData[modelIndex].trim() : " ";
			String yearPath = (FormData.length> yearIndex)? FormData[yearIndex].trim() : " ";
			String hoursPath = (FormData.length> hourIndex)? FormData[hourIndex].trim() : " ";
			String serial_Number_Path = (FormData.length> serialnumberIndex)? FormData[serialnumberIndex].trim() : " ";
			String countryPath = (FormData.length> countryIndex)? FormData[countryIndex].trim() : " ";
			String statePath = (FormData.length> StateIndex)? FormData[StateIndex].trim() : " ";
			String cityPath = (FormData.length> cityIndex)? FormData[cityIndex].trim() : " ";
			String image_Uploader_Path = (FormData.length> image_Uploader_Index)? FormData[image_Uploader_Index].trim() : " ";
			String drive_URL_Path = (FormData.length> drive_URL_Index)? FormData[drive_URL_Index].trim() : " ";
			String targeted_Price_Path = (FormData.length> price_Index)? FormData[price_Index].trim() : " ";
			String product_Description_Path = (FormData.length> description_Index)? FormData[description_Index].trim() : " ";
			String are_you_Open_Path = (FormData.length> openIndex)? FormData[openIndex].trim() : " ";
			
			System.out.println(line);
			
			category = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[2]/div/div/input"));
			category.click();
			
			Thread.sleep(3000);
			search_Category = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[2]/div/ul")); 
			
				List<WebElement> Category_options = search_Category.findElements(By.tagName("li"));
				boolean categoryFound = false;

				for (WebElement option : Category_options)	
				{
					    if (option.getText().equalsIgnoreCase(categoryPath))
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
				        if (option1.getText().trim().equalsIgnoreCase(categoryPath)) 
				        {
				            option1.click();
				            categoryFound = true;
				        }
				    }
				}


			//Manufacturer Selection
			
			manufacturer = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[2]/div/div/input"));
			manufacturer.click();
			
			Thread.sleep(3000);
			search_Manufacturer = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[2]/div/ul"));
			
				List<WebElement> Manufacturer_values = search_Manufacturer.findElements(By.tagName("li"));
				for(WebElement value : Manufacturer_values) 
				{
					if(value.getText().equals(manufacturerPath)) 
					{
						value.click();
						break;
					}
				}

			//Model Selection

			model = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[2]/div/div/input"));
			model.click();
			
			Thread.sleep(3000);
			search_Model = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[2]/div/ul"));
			
				List<WebElement> Model_datas = search_Model.findElements(By.tagName("li"));
				for(WebElement data : Model_datas) 
				{
					if(data.getText().equals(modelPath)) 
					{
						data.click();
						break;
						
					}
				}
			
			//Year Selection
			
			yearDropdown = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[4]/div/div[2]/div[2]/button/div"));
			yearDropdown.click();
			
			yearList = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/ul"));
			
			yearScrollable_Element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/ul/div"));
			
			JavascriptExecutor year = (JavascriptExecutor) driver;
			boolean yearFound = false;
			int currentYear = 11;
			
				for(int i=0;i< currentYear;i++) 
				{
					List <WebElement> YearSelect = yearList.findElements(By.tagName("li"));
					for(WebElement yearOption : YearSelect) 
					{
						if(yearOption.getText().equalsIgnoreCase(yearPath))
						{
							yearOption.click();
							yearFound = true;
							break;
						}
					}
					
					 if (yearFound) 
					 {
					        break;
					 }
					 
					year.executeScript("arguments[0].scrollTop += 200;", yearScrollable_Element);
		            Thread.sleep(500);
				}
				if(!yearFound) 
				{
					System.out.println("Year not found in dropdown");
				}

			//Hour Field
			
			fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[5]/div/input"),hoursPath);
			fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[6]/div/input"),serial_Number_Path);
			
			//Country Selection
	
		    dropdownButton = driver.findElement(By.xpath("//*[@id='__next']/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[7]/div/div[2]/div[2]"));
		    dropdownButton.click();
		    Thread.sleep(1000);

		    dropdownList = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/ul"));
		        
			scrollable_Element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/ul/div"));


		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    boolean countryFound = false;
		    int maxScroll = 43;

		        
			    for (int i = 0; i < maxScroll; i++)
			    {
			        List<WebElement> options = dropdownList.findElements(By.tagName("li"));
			            for (WebElement option : options) 
			            {
			                String text = option.getText().trim();	
			                if (text.equalsIgnoreCase(countryPath)) 
			                {
			                    option.click();
			                    countryFound = true;
			                    break;
			                }
			            }
			            if(countryFound) 
			            {
			            	break;
			            }
			            
			            js.executeScript("arguments[0].scrollTop += 200;", scrollable_Element);
			            Thread.sleep(500);
			            		            
			        }
			        if (!countryFound) 
			        {
			            System.out.println("Country not found in the dropdown!");
			            Actions action = new Actions(driver);
			            action.sendKeys(Keys.ESCAPE).perform();		       
			        }
		        
		 //Select State Dropdown
			        
		        try {
		        	
			        statebutton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[8]/div/div[2]/div[2]/button/div"));
			        statebutton.click();
			        
			        stateScroll = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div"));
			        Thread.sleep(1000);
			        stateList = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/ul"));
			        
			        int prevHeight = 0;
			        int newHeight;
			        
			        while (true) 
			        {
				        List<WebElement> Stateoption = stateList.findElements(By.tagName("li"));
				        for(WebElement Option : Stateoption) 
				        {
				        	if(Option.getText().equals(statePath))
				        	{
				        		Option.click();
				        		break;
				        	}
				        }
			        		
				        JavascriptExecutor StateJs = (JavascriptExecutor) driver;
					    StateJs.executeScript("arguments[0].scrollTop += 200;", stateScroll);
					    Thread.sleep(1500);
				            
					        newHeight = Integer.parseInt(StateJs.executeScript("return arguments[0].scrollTop;", stateList).toString());
					             if (prevHeight == newHeight) 
					             {
					                 System.out.println("State not found in the dropdown!");
					                 break;
					             }
					             prevHeight = newHeight;
			        }
		        }
		        catch (Exception e) 
		        {
		        	System.out.println("Error selecting country: " + e.getMessage());
			    }
		        
		        //City Field
		        
		        	fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[9]/div/input"),cityPath);
		        	
		        //File Uploader Field
		        	
		        	file_Input = driver.findElement(By.cssSelector("input[type=file]"));
		        	file_Input.clear();
		        	file_Input.sendKeys(image_Uploader_Path);
		        	
		        	Thread.sleep(1000);
		        	
		       //Drive URL Field
		        	
		        	fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[2]/div/input"),drive_URL_Path);
		        	
		       //Targeted Price Field
		        	
		        	fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[3]/input"),targeted_Price_Path);

		        	
		       //Description Field
		        	
		        	fillInputField(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[4]/textarea"),product_Description_Path);
		        	
		        	
		       //Are you open to Field
		        	List<WebElement> Value = driver.findElements(By.cssSelector("input[type=checkbox]"));
		        	for(WebElement value : Value) 
		        	{
		        		String Name = value.getAccessibleName();
			        		if(Name.equalsIgnoreCase(are_you_Open_Path)) 
			        		{
			        			value.click();
			        			System.out.println("Checkbox is successfully selected.");
			        			
			        				break;
			        		}
		        	}
		        	
		       //Submit Button
		        
		        	submit_Button = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[3]/div/button"));
		        	submit_Button.click();	
		        	
		        	ExtentTest test = ExtentManager.getInstance().createTest("Want to sell Form - " + testcasePath);
		        	
		        	String expectedResult = "When user submit the want to sell form with valid data then user should be redirecting on want to sell inquiry listing page.";
			        String actualResult = "User is successfully redirected on want to sell inquiry listing page.";
			        String failResult = "Expected result didn't match with actual result.";
			        
			        String Expected_URL = "https://zadoon-frontend.vercel.app/profile/sell-request";
			        String Current_URL = driver.getCurrentUrl();
			        if(Expected_URL.equals(Current_URL)) 
			        {
			        	test.pass("Form is submitted Successfully.");
			        	
			        	String path = ScreenshotUtil.captureScreenshot(driver, "Want to sell_Success");
			            test.pass("Test pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			            
			            test.info("Expected Result : " + expectedResult);
			        	test.info("Actual Result : " + actualResult);
			        	
			        	want_to_Sell_Button = driver.findElement(By.xpath("//*[@id=\\\"__next\\\"]/div/div[2]/div/div[2]/div[1]/div/a"));
			        	want_to_Sell_Button.click();
			        }
			        else 
			        {
			        	test.fail("Form is showing error message.");
		        	
			        	String path = ScreenshotUtil.captureScreenshot(driver, "Want to sell_Error");
			        	test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			        	
			        	test.info("Expected Result : " + expectedResult);
			        	test.info("Failed Result : " + failResult);
		        
//		        	String [][] FormValidation = {
//		        			
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[1]/div/div[3]", "Category field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[2]/div/div[3]", "Manufacturer field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[3]/div/div[3]", "Model field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[4]/div/div[3]", "Year field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[5]/div/div[2]", "Hour field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[6]/div/div[2]", "Serial Number Field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[8]/div/div[3]", "State field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[1]/ul/li[9]/div/div[2]","City field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[3]/div", "Targeted price is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[3]/div", "Product Description field is working proper."},
//		        			{"//*[@id=\"__next\"]/div/div[2]/div/div[2]/form/div/div/div[2]/div/div[5]/div[3]","Checkbox field is working proper."}
//		        	};
//		        	
//		        	for(String[] Form : FormValidation ) {
//		        		try {
//		        			Validation_Message = driver.findElement(By.xpath(Form[0]));
//		        			String Validation_Error = Validation_Message.getText();
//		        			System.out.println(RED + Validation_Error + RESET);	        			
//		        		}
//		        		catch(Exception e) {
//		        			System.out.println(GREEN + Form[1] + RESET);
//		        			
//		        		}
//		        	}
		        	
			        	String currentURL = driver.getCurrentUrl();
			        	String CleanURL = currentURL.split("\\?")[0];
			        	driver.get(CleanURL);
		        
			        }
		        
		}
		br.close();
	}

	public void fillInputField(By xpath, String value) 
	{
		WebElement element = driver.findElement(xpath);
		element.clear();
		element.sendKeys(value);
	}
}
		      




