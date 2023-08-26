package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass

	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_JQuery() {
		
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		
//		  // Chọn lần đầu tiên 
//		  // 1. Click vào 1 thẻ bất kỳ để làm sao cho nó xổ ra hết
//		  các item của dropdown
//		  driver.findElement(By.cssSelector("span#speed-button")).click();
//		  
//		  // 2. Chờ cho tất cả các item được load ra thành công 
//		  // Locator phải lấy để đại diện cho tất cả các item 
//		  // Lấy đến thẻ chứa text
//		  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
//		  cssSelector("ul#speed-menu div[role=option]")));
//		  
//		  // Đưa hết tất cả item trong dropdown và 1 list List<WebElement>
//		  speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role=option]"));
//		  
//		  // 3. Tìm item xem đúng cái đang cần hay không for (WebElement tempItem :
//		  speedDropdownItems) { String itemText = tempItem.getText();
//		  System.out.println(itemText);
//		  
//		
//		
//		  // 4. Kiểm tra cái text của item đúng với cái mình mong muốn if
//		  (itemText.equals("Fast")) { // 5. Click vào item đó tempItem.click(); //
//		  Thoát khỏi vòng lặp không xét cho các case còn lại nữa break; }
//		 	  
//		  }
//	
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role=option]", "Medium");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role=option]", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role=option]", "Slow");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role=option]", "Fast");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
	}
//		3.1 Nếu nó nằm trong khoảng nhìn thấy của user không cần phải scroll xuống
//		3.2 Nếu nó không nằm trong khoảng nhìn thấy của user thì cần scroll xuống đến item đó

		// Muốn chọn item cho Speed dropdown

	/*
	 * public void randomNumber() { Random rand = new Random(); rand.nextInt(99999);
	 * }
	 */

	@Test
	public void TC_02_ReactJS() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("i.dropdown.icon", "span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
		
	}
		
	@Test
	public void TC_03_VueJS() {
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
		
	}
	
	 @Test
	public void TC_04_Editable() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterAndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
		selectItemInDropdown("input.search", "span.text", "Anguilla");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Anguilla");
		
		selectItemInDropdown("input.search", "span.text", "Bangladesh");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
		
		
	}
		
		
		
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Tránh lặp lại code nhiều lần thì chỉ cần gọi hàm ra để dùng
	// Đi kèm với tham số
	// Nếu truyền cứng 1 giá trị vào trong hàm thì vô nghĩa
	// Nên define dùng đi dùng lại nhiều lần
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
//	1. Click vào 1 thẻ bất kỳ để làm sao cho nó xổ ra hết các item của dropdown
	driver.findElement(By.cssSelector(parentCss)).click();
	sleepInSecond(1);
	
//	2. Chờ cho tất cả các item được load ra thành công
	// Locator phải lấy để đại diện cho tất cả các item
	// Lấy đến thẻ chứa text
	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
	
	// Đưa hết tất cả item trong dropdown và 1 list
	List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
	
//	3. Tìm item xem đúng cái đang cần hay không
	for (WebElement tempItem : speedDropdownItems) {
		String itemText = tempItem.getText();
		sleepInSecond(1);
		System.out.println(itemText);

//	4. Kiểm tra cái text của item đúng với cái mình mong muốn
		if (itemText.trim().equals(expectedTextItem)) {
//	5. Click vào item đó	
			tempItem.click();
			// Thoát khỏi vòng lặp không xét cho các case còn lại nữa
			break;
		}
	}	
}
	
	public void enterAndSelectItemInDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
//		1. Click vào 1 thẻ bất kỳ để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
//		2. Chờ cho tất cả các item được load ra thành công
		// Locator phải lấy để đại diện cho tất cả các item
		// Lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		// Đưa hết tất cả item trong dropdown và 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		
//		3. Tìm item xem đúng cái đang cần hay không
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			sleepInSecond(1);
			System.out.println(itemText);

//		4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if (itemText.trim().equals(expectedTextItem)) {
//		5. Click vào item đó	
				tempItem.click();
				// Thoát khỏi vòng lặp không xét cho các case còn lại nữa
				break;
			}
		}	
	}
}
