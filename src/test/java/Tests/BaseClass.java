package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class BaseClass {
	  private WebDriver driver;
	  @BeforeSuite
	  public void startContainer() throws InterruptedException{
		  commonMethods.runTerminalCommand("docker-compose up","Docker Container starting");
		  Thread.sleep(10000);
	  }
	  @BeforeTest
	  public void setUp() throws MalformedURLException {
		    DesiredCapabilities dc = new DesiredCapabilities(); 
		    dc.setBrowserName("firefox");
		    dc.setPlatform(Platform.LINUX);
	        // Create ChromeDriver instance
	        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		  
	      
	  }
	  @Test
	  public void chooseOption() throws InterruptedException {
		    Thread.sleep (3000);
		    driver.get("https://www.google.com");

	        // Find the search input field and enter "iPhone 14"
	        WebElement searchInput = driver.findElement(By.name("q"));
	        searchInput.sendKeys("iPhone 14");

	        // Submit the search
	        searchInput.submit();
	}
		
	@AfterTest
	  public void tearDown() {
          
	      driver.quit();
	  }
	 @AfterSuite
	  public void stopContainer() throws InterruptedException {
		  commonMethods.runTerminalCommand("docker-compose down","Docker Container is down");
		  Thread.sleep(10000);
	  }
	}

