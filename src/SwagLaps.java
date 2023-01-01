
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
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
//		driver.quit();
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
		List<WebElement> listOfNames = driver.findElements(By.className("inventory_item_name"));
//		driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).click();
		System.out.println(1);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for (int i = 0; i < listOfNames.size(); i++) {
			System.out.println("i= " + i);
			Thread.sleep(1000);
			try {
				WebElement element = wait
						.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item_name")));
				if (listOfNames.get(i).isDisplayed()) {
					System.out.println(i + " isDisplayed");
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
		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		int numOfAddeedItems = 0;
		for (int i = 0; i < addToCartMainPage.size(); i++) {
			addToCartMainPage.get(i).click();
			numOfAddeedItems++;
		}

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

	@Test(enabled = false)
	public void addSpecificItemsToTheCart(int number) {
		/*
		 * this test adds specific number of items to the cart and then checks if all of
		 * them really have been added by checking the cart icon & the cart page;
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		List<WebElement> addToCartMainPage = driver.findElements(By.className("btn_primary"));
		int numOfAddeedItems = 0;
		for (int i = 0; i < number; i++) {
			addToCartMainPage.get(i).click();
			numOfAddeedItems++;
		}

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

	@Test(enabled = false)
	public void descAndNameCheck() {
		// this test wants to test if the items description is right for the item;

		List<WebElement> theName = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> theDesc = driver.findElements(By.className("inventory_item_desc"));

		for (int i = 0; i < theName.size(); i++) {

		}
	}

	@Test(priority = 1, groups = "sorting", enabled = false)
	public void sorting_A_Z() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[1]")).click();
		List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
		List<String> strings = new ArrayList<>();
		for (WebElement element : names) {
			strings.add(element.getText());
		}

		for (int i = 1; i < strings.size(); i++) {
			softassert.assertTrue(strings.get(i - 1).compareTo(strings.get(i)) < 0);
		}

		softassert.assertAll();

	}

	@Test(priority = 1, groups = "sorting", enabled = false)
	public void sorting_Z_A() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
		List<String> strings = new ArrayList<>();
		for (WebElement element : names) {
			strings.add(element.getText());
		}

		for (int i = 1; i < strings.size(); i++) {
			System.out.println(strings.get(i - 1) + "------" + strings.get(i));
			softassert.assertTrue(strings.get(i - 1).compareTo(strings.get(i)) > 0);
		}

		softassert.assertAll();
	}

	@Test(priority = 1, groups = "sorting", enabled = false)
	public void sorting_low_High() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		List<WebElement> sPrices = driver.findElements(By.className("inventory_item_price"));
		List<Double> prices = new ArrayList<Double>();
		List<Double> samePrices = new ArrayList<Double>();
		for (int i = 0; i < sPrices.size(); i++) {
			prices.add(Double.parseDouble(sPrices.get(i).getText().replace("$", "")));
			samePrices.add(Double.parseDouble(sPrices.get(i).getText().replace("$", "")));
		}
		Collections.sort(prices);
		for (int i = 0; i < sPrices.size(); i++) {
			softassert.assertEquals(samePrices.get(i), prices.get(i));
		}
		softassert.assertAll();
	}

	@Test(priority = 2, groups = "sorting", enabled = false)
	public void sorting_high_low() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();
		List<WebElement> sPrices = driver.findElements(By.className("inventory_item_price"));
		List<Double> prices = new ArrayList<Double>();
		List<Double> samePrices = new ArrayList<Double>();
		for (int i = 0; i < sPrices.size(); i++) {
			prices.add(Double.parseDouble(sPrices.get(i).getText().replace("$", "")));
			samePrices.add(Double.parseDouble(sPrices.get(i).getText().replace("$", "")));
		}
		Collections.sort(prices);
		Collections.reverse(prices);
		for (int i = 0; i < sPrices.size(); i++) {
			softassert.assertEquals(samePrices.get(i), prices.get(i));
		}
		softassert.assertAll();
	}

	@Test(priority = 1)
	public void totalPrice() throws InterruptedException {
		/*
		 * This test sums the total items price and check if its equal the total
		 * presented and then adds the Tax to it and rechecks with the total price
		 */

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		addSpecificItemsToTheCart(6);
		List<WebElement> sPrices = driver.findElements(By.className("inventory_item_price"));
		List<Double> prices = new ArrayList<Double>();
		double cartTotal = 0;
		for (int i = 0; i < sPrices.size(); i++) {
			prices.add(Double.parseDouble(sPrices.get(i).getText().replace("$", "")));
			cartTotal += prices.get(i);
		}
		System.out.println("the total in the cart page is: " + cartTotal);
		driver.findElement(By.name("checkout")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"first-name\"]"))
				.sendKeys("Aswood" + Keys.TAB + "SnowMan" + Keys.TAB + "1999");
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();

		double subTotal = Double
				.parseDouble(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[5]"))
						.getText().replace("Item total: $", ""));

		softassert.assertEquals(subTotal, cartTotal,
				"check the subTotal if equals the true total before adding the tax");

		double withTax = ((subTotal * 0.08) + subTotal);

		BigDecimal bd = new BigDecimal(withTax);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		double totalWithTax = bd.doubleValue();

		double actualTotalWithTax = Double
				.parseDouble(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]"))
						.getText().replace("Total: $", ""));

		softassert.assertEquals(actualTotalWithTax, totalWithTax);

		softassert.assertAll();
	}

}
