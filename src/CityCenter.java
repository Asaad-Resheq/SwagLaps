import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class CityCenter {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@BeforeTest
	public void beforeMyTest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://citycenter.jo/pc-and-laptops/pc-and-laptops-laptops?sort=p.price&order=ASC&limit=75&bfilter=s0:7;");
	}

	@Test()
	public void cityCenterCount() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//this to set the List type view;
		driver.findElement(By.xpath("//*[@id=\"ProductsSystem_YD9pMDOx\"]/nav/div/div[1]/a[2]")).click();
		//this to set the number of items per page;
		driver.findElement(By.xpath("//*[@id=\"ProductsSystem_YD9pMDOx\"]/nav/div/div[3]/select/option[5]")).click();
		
		List<WebElement> laptops =driver.findElements(By.className("price-regular"));  //40 Items
		List<WebElement> newlaptops =driver.findElements(By.className("price-new"));   //35	Items == 75 items == all items in the page;
		
		String totalAsString = String.valueOf(laptops.size() + newlaptops.size());
		System.out.println("total items in the page: "+totalAsString); //== 75
		
		String price20thItem=(laptops.get(5).getText()).replace("JOD ", ""); //JOD 60900 and then replace it to be 60900;
		double finall=Double.parseDouble(price20thItem);
		System.out.println("price of the 5th item: "+finall/100+" JOD");
		
		String numOfItems = driver.findElement(By.xpath("//*[@id=\"ProductsSystem_YD9pMDOx\"]/div[3]/div")).getText();
		boolean check =false;
		if(numOfItems.contains(totalAsString)) {
			check=true;
		}
		System.out.println("expcted ");
		softassert.assertEquals(check, true);
		
		
		
		
		
		softassert.assertAll();
	}
	@AfterTest
	public void afterMyTest() {
		driver.quit();
	}
}


