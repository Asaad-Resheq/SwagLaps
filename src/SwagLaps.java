import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.jq.Main;

public class SwagLaps {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@BeforeTest
	public void beforeTesting() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}
	
	@Test()
	public void login() {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user"+Keys.TAB+"secret_sauce"+Keys.ENTER);
		
	}
	
	@Test()
	public void addAllItems() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.className("btn btn_primary")).click();
	}
	
	
	@AfterTest
	public void afterTesting() { 
		
	}
	

}
