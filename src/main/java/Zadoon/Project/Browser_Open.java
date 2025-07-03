package Zadoon.Project;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser_Open {
	
    protected static final Logger logger = LogManager.getLogger(Browser_Open.class);

	
	public WebDriver driver;
	
	
    @Test(priority = 1)
	public void setUp() 
    {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://zadoon-frontend.vercel.app/");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	System.out.println("Page title is: " + driver.getTitle());
    	
    	ExtentManager.getInstance();
    	
	}
    
    @AfterSuite
    public void flushReport() {
        ExtentManager.getInstance().flush();
    }

}
