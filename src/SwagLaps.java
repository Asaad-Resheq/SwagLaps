import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SwagLaps {
	public WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	 

	@BeforeTest
	public void beforeTesting() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	@AfterTest
	public void afterTesting() {
		driver.quit();
	}

	@Test()
	public void login() {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]"))
				.sendKeys("standard_user" + Keys.TAB + "secret_sauce" + Keys.ENTER);
	}

	@Test(enabled = false)
	public void nameCheck() throws InterruptedException {
		/*
		 * this test cheaks the outside and inside name for every item
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		List<WebElement> listOfInsideNames = new ArrayList<WebElement>();
		List<WebElement> listOfNames = driver.findElements(By.className("inventory_item_img"));
//		driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
		System.out.println(1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for (int i = 0; i < listOfNames.size(); i++) {
			System.out.println("i= "+i);
			Thread.sleep(1000);
			try {
				WebElement element = wait
							.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item_name")));
				if (listOfNames.get(i).isDisplayed()) {
					System.out.println(i+" isDisplayed");
					listOfNames.get(i).click();
					
					
					listOfInsideNames.add(driver.findElement(By.className("inventory_details_name")));
					System.out.println(listOfInsideNames.get(i).getText());
					driver.navigate().back();
				}

			} catch (StaleElementReferenceException e) {
				System.out.println(i + "-error");
			}
			System.out.println(3);

			System.out.println("------" + listOfInsideNames.size());

		}


	}

	@Test(priority = 2, enabled = false)
	public void addAllItemsToTheCart() {
		/*
		 * this test adds all items to the cart and then checks if all of them really
		 * have been added by checking the cart icon & the cart page;
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		int numOfAddeedItems = addAllItems();

		System.out.println(numOfAddeedItems + " Items have been added to the cart from the main page.");

		int numberOnCartIcon = Integer
				.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText());

		System.out.println(numberOnCartIcon + " Items are showing on the cart icon.");
		softassert.assertEquals(numberOnCartIcon, numOfAddeedItems,
				"-this checks if the added items equals the number that shows on the cart icon on the top right;");

		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		List<WebElement> itemsInCartPage = driver.findElements(By.className("cart_item"));
		softassert.assertEquals(itemsInCartPage.size(), numOfAddeedItems,
				"-this check if the number of the added items in the cart page equals to the real added items number;");
		System.out.println(itemsInCartPage.size() + " Items have been added to the cart page.");

		softassert.assertAll();
	}

	public int addAllItems() {
		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		for (int i = 1; i <= addToCartMainPage.size(); i++) {
			addToCartMainPage.get(i).click();

		}
		return addToCartMainPage.size();
	}

	public int addSpecificItems(int numberOfItems) {
		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		for (int i = 0; i < numberOfItems; i++) {
			addToCartMainPage.get(i).click();

		}
		return numberOfItems;
	}

	public void removeAll() {
		List<WebElement> removeFromCartMainPage = driver.findElements(By.className("btn_secondary"));
		for (int i = 0; i < removeFromCartMainPage.size(); i++) {
			removeFromCartMainPage.get(i).click();
		}
	}
	
	public String clickI(int i,List<WebElement> names) {
		names.get(i).click();
		String detialName = driver.findElement(By.className("inventory_details_name")).getText();
		driver.navigate().back();
		
		return detialName;
		 
		
	}
	
	@Test()
	public void descAndNameCheck() {
		List<WebElement> theName = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> theDesc = driver.findElements(By.className("inventory_item_desc"));
		
		for(int i=0;i<theName.size();i++) {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
