package Zadoon.Project;
import java.awt.Window;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Popup;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Web_Form 
{
	public WebDriver driver;

	@Test(priority = 1)	
	public void demoWebForm() throws Exception
	{	
		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
		
		WebElement textInput, password, textArea, dropdown, dropdownList, fileUploader, checkBox,
		radioButton, colorPicker, datePicker, range, disabled_input, readonly_input, submit;
		
		textInput = driver.findElement(By.xpath("//*[@id=\"my-text-id\"]"));
		textInput.sendKeys("Testing");
		
		password = driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[2]/input"));
		password.sendKeys("Nirupa@1234");
		
		textArea = driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[3]/textarea"));
		textArea.sendKeys("lorem ipsum dolor sit amet");
		
		disabled_input = driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[4]/input"));
		if(disabled_input.isEnabled()) 
		{
			disabled_input.sendKeys("Test");
		}
		else
		{
			System.out.println("The input field is disabled.");
		}
		
		readonly_input = driver.findElement(By.xpath("/html/body/main/div/form/div/div[1]/label[5]/input"));
		String text = readonly_input.getAttribute("readonly");
		System.out.println("The field is readonly : " + text);
		
		
		dropdown = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/label[1]/select"));
		List<WebElement> dropdownSelect = dropdown.findElements(By.tagName("option"));
		for(WebElement dropdownValue : dropdownSelect ) 
		{
			String value = dropdownValue.getAccessibleName();
			System.out.println(value);
			if(value.equalsIgnoreCase("two")) 
			{
				dropdownValue.click();
			}
		}
		
		dropdownList = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/label[2]/input"));
		dropdownList.sendKeys("India");
		
		fileUploader = driver.findElement(By.cssSelector("input[type=file]"));
		fileUploader.sendKeys("C:\\Users\\Admin\\Downloads\\image.png");
		
		checkBox = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/div[1]"));
		List<WebElement> list_checkbox = checkBox.findElements(By.tagName("label"));
		for(WebElement list_checkbox_tab : list_checkbox)
		{
			if(!list_checkbox_tab.isSelected())
			{
				list_checkbox_tab.click();
			}
		}
		
		radioButton = driver.findElement(By.xpath("//*[@id=\"my-radio-2\"]"));
		radioButton.click();

		colorPicker = driver.findElement(By.cssSelector("input[type=color]"));
		colorPicker.sendKeys("#000000");
		
		datePicker = driver.findElement(By.xpath("/html/body/main/div/form/div/div[3]/label[2]/input"));
		datePicker.sendKeys("09/29/2001");
		
		range = driver.findElement(By.className("form-range"));
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.dragAndDropBy(range, 90, 0).build();
		action.perform();
		
		System.out.println(range.getAttribute("value"));
		
		submit = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
		submit.click();
		
		Thread.sleep(2000);
	}
	//@Test(priority = 2)
	public void navigation() throws Exception
	{	
		
		WebElement secondPage, nextPage, previousPage;
		
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
		
		secondPage = driver.findElement(By.xpath("/html/body/main/div/div[5]/div/nav/ul/li[3]/a"));
		secondPage.click();
		
		nextPage = driver.findElement(By.xpath("/html/body/main/div/div[5]/div/nav/ul/li[5]/a"));
		nextPage.click();
		
		previousPage = driver.findElement(By.xpath("/html/body/main/div/div[5]/div/nav/ul/li[1]/a"));
		previousPage.click();
		
		Thread.sleep(2000);
	
	}
	//@Test(priority = 3)	
	public void dropdownMenu() throws Exception
	{
		
		WebElement leftDropdown, rightDropdown, doubleClickDropdown;
		
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
		
		//Left click menu
		
		leftDropdown = driver.findElement(By.xpath("//*[@id=\"my-dropdown-1\"]"));
		leftDropdown.click();
		
		//Right click menu
		
		Actions action = new Actions(driver);
		rightDropdown = driver.findElement(By.xpath("//*[@id=\"my-dropdown-2\"]"));
		action.contextClick(rightDropdown).perform();
		
		//Double click menu
		
		doubleClickDropdown = driver.findElement(By.xpath("//*[@id=\"my-dropdown-3\"]"));
		action.doubleClick(doubleClickDropdown).perform();
		
		Thread.sleep(2000);
	}
	
	//Mouse Over effect
	
	//@Test(priority = 4)
	public void mouse_Over() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
		
		WebElement image;
		
		image = driver.findElement(By.xpath("/html/body/main/div/div[4]/div[1]/img"));
		Actions action = new Actions(driver);
		action.moveToElement(image).perform();
		
		Thread.sleep(2000);
	}
	
	//Drag and Drop Effect
	
	//@Test(priority = 5)
	public void dragAndDrop() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
		
		WebElement element;
		
		element = driver.findElement(By.xpath("//*[@id=\"draggable\"]/h5"));
		Actions action = new Actions(driver);
		Action draganddrop = action.dragAndDropBy(element, 660, 0).build();
		draganddrop.perform();
		
		Thread.sleep(2000);
	}
	
	//Drawing Canvas Effect
	
	//@Test(priority = 6)
	public void drawing_canvas() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");
		
		WebElement canvas;
		canvas = driver.findElement(By.xpath("//*[@id=\"my-canvas\"]"));
		Actions action = new Actions(driver);
		action.click(canvas).perform();
		action.moveToElement(canvas).perform();
		action.clickAndHold(canvas).perform();
		action.moveByOffset(150, 50).perform();
		action.moveToElement(canvas).perform();
		action.clickAndHold(canvas).perform();
		action.moveByOffset(100, 50).perform();
		action.moveToElement(canvas).perform();
		
		Thread.sleep(2000);
	}
	
	//Loading Images Effect
	
	//@Test(priority = 7)
	public void loading_images() throws Exception
	{
		
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"spinner\"]"))));
		
		System.out.println("All images are loaded.");
		
	}
	
	//Slow Calculator Effect
	
	//@Test(priority = 8)
	public void slow_calculator() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
		
		WebElement time, value , operator, nextValue, equalButton, result;	
		
		time = driver.findElement(By.xpath("//*[@id=\"delay\"]"));
		time.clear();
		time.sendKeys("5");
		
		
		value = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div[2]/span[2]"));
		value.click();
		
		operator = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div[2]/span[4]"));
		operator.click();
		
		nextValue = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div[2]/span[6]"));
		nextValue.click();
		
		equalButton = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div[2]/span[15]"));
		equalButton.click();
		
		Thread.sleep(5000);
		
		result = driver.findElement(By.xpath("//*[@id=\"calculator\"]/div[1]/div"));
		String resultText = result.getText().trim();
		System.out.println("Your answer is = " + resultText);
		
	}
	
	//Frame Interaction Element
	
	//@Test(priority = 9)
	public void iframe() throws Exception
	{
		driver.get("https://www.krishaweb.com/contact-us/");
		
		WebElement firstname, cookie, lastname, email, submit;
		
		Thread.sleep(5000);
		cookie = driver.findElement(By.xpath("//*[@id=\"hs-eu-confirmation-button\"]"));
		cookie.click();
		
		driver.switchTo().frame(0);

		firstname = driver.findElement(By.name("firstname"));
		firstname.sendKeys("Nirupa");
		
		lastname = driver.findElement(By.name("lastname"));
		lastname.sendKeys("Gajera");
		
		email = driver.findElement(By.name("email"));
		email.sendKeys("fadasdasds");
		
		submit = driver.findElement(By.xpath("//*[@id=\"hsForm_619fcfb6-3c60-4cc0-a92c-b5e470135c1d\"]/div/div[2]/input"));
		submit.click();
	}
	
	//Captcha Automate
	
	//@Test(priority = 10)
	public void recaptcha() throws Exception
	{
//		Site Key : 6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI
//		Secret Key: 6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe
		driver.get("http://44.206.8.68/skybright/contact-us/");
		
		WebElement fullName, email, phoneNumber, submit;
		
		fullName = driver.findElement(By.xpath("//*[@id=\"wpcf7-f6-p631-o1\"]/form/div[2]/div[1]/span/input"));
		fullName.sendKeys("Kian");
		
		email = driver.findElement(By.xpath("//*[@id=\"wpcf7-f6-p631-o1\"]/form/div[2]/div[2]/span/input"));
		email.sendKeys("kianstokes@gmail.com");
		
		phoneNumber = driver.findElement(By.xpath("//*[@id=\"wpcf7-f6-p631-o1\"]/form/div[2]/div[3]/span/input"));
		phoneNumber.sendKeys("0123456789");
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement recaptchaIframe = wait.until(ExpectedConditions.presenceOfElementLocated(
		    By.cssSelector("iframe[src*='recaptcha']")
		));
		driver.switchTo().frame(recaptchaIframe);
		
		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
			    By.cssSelector(".recaptcha-checkbox-border")
			));
		checkbox.click();
			
		driver.switchTo().defaultContent();
		
		Thread.sleep(1000);
		
		submit = driver.findElement(By.xpath("//*[@id=\"wpcf7-f6-p631-o1\"]/form/div[2]/div[7]/input"));
		submit.click();
	}
	
	//PlayStore Slider 
	
	//@Test(priority = 11)
	public void slider() throws Exception
	{
		driver.get("https://play.google.com/store/games");
		
		WebElement mouseOver = driver.findElement(By.xpath("//*[@id='yDmH0d']/c-wiz[2]/div/div/div[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(mouseOver).perform();

		WebElement slideContainer = driver.findElement(By.xpath(
		    "//*[@id='yDmH0d']/c-wiz[2]/div/div/div[1]/c-wiz/div/c-wiz/c-wiz[1]/c-wiz/div/div/div/div[1]"));
		
		List<WebElement> slides = slideContainer.findElements(By.className("VfPpkd-WsjYwc"));
		for(int i=0;i<slides.size();i++)
		{
			WebElement sliderClick = slides.get(0);
			sliderClick.click();
		}
	}
	
	//@Test(priority = 12)
	public void slider_auto_looping() throws Exception
	{
		driver.get("https://stagejco.prodevtest.com/in-house-training/");
		
		WebElement slider;
		
		slider = driver.findElement(By.xpath("//div[contains(@class, 'event-category-slider-wrap swiper event-category-slider swiper-initialized swiper-horizontal swiper-pointer-events')]"));
    	List<WebElement> list_slider = slider.findElements(By.xpath("//div[contains(@class, 'swiper-slide')]"));
    	for(WebElement slider_list_tab : list_slider)
    	{
    		String data_text = slider_list_tab.getText().trim();
    		System.out.println(data_text);
    		
    	}
	}
	
	//Shadow DOM	
	//@Test(priority = 13)
	public void shadow_root() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
		
        WebElement content = driver.findElement(By.id("content"));

        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        String text = textElement.getText().trim();
        System.out.println(text);
	}
	
	//Dialogue Box
	
	//@Test(priority = 14)
	public void launch_alert() throws Exception
	{
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
		
		WebElement launch_alert, launch_confirm, launch_prompt, status, alert_status, 
		launch_modal, modal_status, modal;
		
		//Launch Alert
		
		launch_alert = driver.findElement(By.xpath("//*[@id=\"my-alert\"]"));
		launch_alert.click();
		
		Alert alert_launch = driver.switchTo().alert();
        String alertText = alert_launch.getText();
        System.out.println("Alert data: " + alertText);
        alert_launch.accept();
        
        //Launch Confirm
        launch_confirm = driver.findElement(By.xpath("//*[@id=\"my-confirm\"]"));
		launch_confirm.click();
				
		Alert alert_confirm = driver.switchTo().alert();
		String alttext = alert_confirm.getText();
		System.out.println("Alert Data: " + alttext);
		alert_confirm.dismiss();
		
		status = driver.findElement(By.xpath("//*[@id=\"confirm-text\"]"));
		String text = status.getText().trim();
		System.out.println(text);
		
		Thread.sleep(1000);
		//Launch Prompt
		
		launch_prompt = driver.findElement(By.xpath("//*[@id=\"my-prompt\"]"));
		launch_prompt.click();
		
		Alert alert_prompt = driver.switchTo().alert();
		String alt_prompt = alert_prompt.getText();
		System.out.println(alt_prompt);
		alert_prompt.sendKeys("Nirupa");
		alert_prompt.accept();
		
		alert_status = driver.findElement(By.xpath("//*[@id=\"prompt-text\"]"));
		String alert_text = alert_status.getText().trim();
		System.out.println(alert_text);
		
		//Launch modal
		
		launch_modal = driver.findElement(By.xpath("//button[@id='my-modal']"));
		launch_modal.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));
		
	    modal = driver.findElement(By.className("modal-dialog"));
	            
	    WebElement modal_list = modal.findElement(By.xpath(".//div[@class='modal-body']"));

	    WebElement modalAcceptButton = modal_list.findElement(By.xpath("//div[@class='modal-footer']"));
	    WebElement modal_button = modalAcceptButton	.findElement(By.xpath("//button[starts-with(text(),'Save')]"));
	    modal_button.click();
	   	
		modal_status = driver.findElement(By.xpath("//*[@id=\"modal-text\"]"));
		String modal1 = modal_status.getText().trim();
		System.out.println(modal1);
		
	}	
	
	//Switch Tab
	
	//@Test(priority = 15)
	public void switch_tab() throws Exception
	{
		driver.get("https://krishaweb.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://www.bing.com','_blank');");
		
		Set<String> handles = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(handles);
		
		Thread.sleep(2000);
		
		driver.switchTo().window(tabs.get(0));
		
		Thread.sleep(1000);
		
		driver.switchTo().window(tabs.get(1));
		
	}
	
	//JavaScript Scroll into view
	
	//@Test(priority = 16)
	public void scroll_into_view() throws Exception
	{
		driver.get("https://krishaweb.com/");
		
		WebElement contact = driver.findElement(By.xpath("//*[@id=\"site-content\"]/div[1]/div/div/div/div/div/div/div[1]/div/div[2]/a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView(true);", contact);
	}
	
	//Validate the URL
	//@Test(priority = 17)
	public void validate_link() throws Exception
	{
		driver.get("https://krishaweb.com");
		
		List<WebElement> a_tag = driver.findElements(By.tagName("a"));
		for(WebElement a : a_tag)
		{
			String text = a.getAttribute("href");
			if(text==null || text.isEmpty())
			{
				System.out.println("Url is empty or not configured for element");
				continue;
			}
			try 
			{
				HttpURLConnection conn = (HttpURLConnection) (new URL(text).openConnection());
                conn.setRequestMethod("HEAD");
                conn.connect();
				int responsecode = conn.getResponseCode();
				
				if(responsecode >= 400)
				{
					System.out.println("Broken Links " + text + responsecode);
				}
				else
				{
					System.out.println("Valid link " + text + responsecode);
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception for the url " + text);
			}
		}
			
		}
	
		//Highlight WebElement
		//	@Test(priority = 18)
		public void highlight_element() throws Exception
		{
			driver.get("https://krishaweb.com/");
			
			WebElement contact = driver.findElement(By.linkText("Contact Us"));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2 px solid black;');", contact);
		}
		
		//Handle dynamic table
		//@Test(priority = 19)
		public void dynamic_table() throws Exception
		{
			driver.get("https://www.w3schools.com/html/html_tables.asp");
			
			WebElement table = driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody"));
			List<WebElement> tr = table.findElements(By.tagName("tr"));	
			
			for(int i=0; i<tr.size();i++)
			{
				List<WebElement> td = tr.get(i).findElements(By.tagName("td"));
				
				for(int j=0;j<td.size();j++)
				{
					System.out.println(td.get(j).getText());
				}
			}
		}
		
		//Notification
		//@Test(priority = 20)
		public void notification() throws Exception
		{
			driver.get("https://bonigarcia.dev/selenium-webdriver-java/notifications.html");
			
			WebElement notification_button;
			
			notification_button = driver.findElement(By.xpath("//*[@id=\"notify-me\"]"));
			notification_button.click();
			
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}		
	
		@Test(priority = 21)
		public void shadow_root1() throws Exception
		{
			driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
			
			WebElement content = driver.findElement(By.id("content"));
			
			SearchContext shadow = content.getShadowRoot();
			WebElement gettext = shadow.findElement(By.cssSelector("p"));
			String text = gettext.getText().trim();
			System.out.println(text);
		}
}
 