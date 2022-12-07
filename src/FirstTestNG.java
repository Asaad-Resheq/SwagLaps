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

public class FirstTestNG {

	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@BeforeTest
	public void beforeMyTest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		
	}

	@Test(priority=1)
	public void loginTest() {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce"+Keys.ENTER);
	
	}
	
	@Test(priority =2)
	public void sortHighToLow() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select")).click();
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();
		
		List<WebElement> listOfPrices= driver.findElements(By.className("inventory_item_price"));
		
		double[] itemsPrices= {};
		
		for(int i=0;i<listOfPrices.size();i++) {
			String newPrice=listOfPrices.get(i).getText().replace("$","");
			itemsPrices[i]= Double.valueOf(newPrice);
			System.out.println(itemsPrices[i]);
		}
		
		softassert.assertEquals(listOfPrices.size(),6);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		softassert.assertAll();
	}
	

	
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
