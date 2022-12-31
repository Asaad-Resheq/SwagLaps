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

public class BookingCom {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	
	@BeforeTest
	public void beforeMyTest() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.booking.com/searchresults.html?label=gen173nr-1FCAEoggI46AdIM1gEaHSIAQGYATG4ARfIARXYAQHoAQH4AQKIAgGoAgO4AryC-JwGwAIB0gIkNzhmMTg3ZDItNWE4YS00MDBmLTk1ZTYtMGFmMzg3Yjk0M2Rk2AIF4AIB&sid=422ccde2963d588e4bcf36ce0ee771e0&sb=1&sb_lp=1&src=index&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Findex.html%3Flabel%3Dgen173nr-1FCAEoggI46AdIM1gEaHSIAQGYATG4ARfIARXYAQHoAQH4AQKIAgGoAgO4AryC-JwGwAIB0gIkNzhmMTg3ZDItNWE4YS00MDBmLTk1ZTYtMGFmMzg3Yjk0M2Rk2AIF4AIB%26sid%3D422ccde2963d588e4bcf36ce0ee771e0%26sb_price_type%3Dtotal%26%26&ss=Amman%2C+Amman+Governorate%2C+Jordan&is_ski_area=&checkin_year=2022&checkin_month=12&checkin_monthday=29&checkout_year=2023&checkout_month=1&checkout_monthday=3&efdco=1&group_adults=2&group_children=0&no_rooms=1&b_h4u_keep_filters=&from_sf=1&ss_raw=amm&ac_position=0&ac_langcode=en&ac_click_type=b&ac_meta=GhAxZmE3OTU3MTE5ODgwMzZjIAAoATICZW46A2FtbUAASgBQAA%3D%3D&dest_id=-970362&dest_type=city&iata=AMM&place_id_lat=31.95343&place_id_lon=35.945038&search_pageview_id=1fa795711988036c&search_selected=true&search_pageview_id=1fa795711988036c&ac_suggestion_list_length=5&ac_suggestion_theme_list_length=0");
	}

	@Test()
	public void countHotels() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> hotels= driver.findElements(By.className("b978843432"));
		System.out.println("Number of hotels = "+hotels.size());
//				driver.findElement(By.xpath("//*[@id=\"search_results_table\"]/div[2]/div/div/div/div[5]/div[14]/div[1]/div[2]/div/div[1]/div[1]/div/div[1]/div/h3/a/div[1]")).click();
		for (int i = 0; i < hotels.size(); i++) {
				hotels.get(i).click();
				System.out.println(i);
			  driver.navigate().back();
			}
//		WebElement cl=hotels.get(9);
//		cl.click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@id=\"hprt_nos_select_29246919_355232895_0_2_0\"]")).click();
//		driver.findElement(By.xpath("//*[@id=\"hprt_nos_select_675420202_335549783_0_0_0\"]/option[2]")).click();
//		driver.findElement(By.xpath(a))
		
//		String thePrice=driver.findElement(By.xpath("//*[@id=\"hprt-table\"]/tbody/tr[1]/td[3]")).getText();
		
		
		
//		System.out.println(hotels.get(9).getText());	
//		String price = driver.findElement(By.xpath("//*[@id=\"search_results_table\"]/div[2]/div/div/div/div[5]/div[12]/div[1]/div[2]/div/div[2]/div[2]/div/div[1]/span/div/span[2]")).getText();
//		String usdPrice = price.replace("JOD", "USD");		
//		System.out.println("Price in USD: "+ usdPrice);
//		
		
		
		
	}

	@AfterTest
	public void afterMyTest() {
//		driver.quit();
	}
}
