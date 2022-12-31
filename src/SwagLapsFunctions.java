import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLapsFunctions {
	public WebDriver driver;
	

	public int addAllItems() {
		
		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		for (int i = 0; i < addToCartMainPage.size(); i++) {
			addToCartMainPage.get(i).click();
		}
		return addToCartMainPage.size();
	}
	
	public int addSpecificItems(int numberOfItems) {
		driver = new ChromeDriver();

		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		for (int i = 0; i < numberOfItems; i++) {
			addToCartMainPage.get(i).click();
		}
		return numberOfItems;
	}
	
	public void removeAll() {
		driver = new ChromeDriver();
		
		List<WebElement> removeFromCartMainPage = driver.findElements(By.className("btn_secondary"));
		for (int i = 0; i < removeFromCartMainPage.size(); i++) {
			removeFromCartMainPage.get(i).click();
		}
	}
	
}
