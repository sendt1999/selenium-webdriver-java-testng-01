package webdriver;

import java.awt.Dimension;
import java.awt.Point;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PI {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_WebElement() {
		WebElement element = driver.findElement(By.className(""));
		
		// Dùng cho các textbox/ textarea/ dropdown (Editable)
		// Xóa dữ liệu đi trước khi nhập text
		element.clear(); //**
		
		// Dùng cho các textbox/ textarea/ dropdown (Editable)
		// Nhập liệu
		element.sendKeys(""); //**
		
		// Click vào button/ checkbox/ link/ image/ radio/...
		element.click(); //**
		
		String searchAttribute = element.getAttribute("placeholder"); //*
		String emailTextboxAttrbute = element.getAttribute("value"); //*
		// Search store
		
		// GUI: Font/ Size/ Color/ Location/ Position/...
		element.getCssValue("background-color"); //*
		// #4ab2f1
		
		// Vị trí của element so với web (bên ngoài)
		Point point = element.getLocation();
		point.x = 324;
		point.y = 324;
		
		// Kích thước của element bên trong
		Dimension di = element.getSize();
		di.getHeight();
		di.getWidth();
		
		System.out.println(di.height);
		System.out.println(di.width);
		
		// Location + Size
		rectangle rec = element.getRect();
		
		// chụp hình khi testcase fail
		element.getScreenshotAs(OutputType.FILE); //*
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.BASE64);
		
		// Cần lấy ra cái tên thẻ HTML của element đó => Truyền vào cho 1 locator khác
		driver.findElement(By.id("email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxAttrbute + "[@id='email']"));
		
		// Lấy text từ Error message/ Success/ message/ label/ header/...
		element.getText(); //**
		// Please enter your email
		
		// Khi nào dùng getText - getAsstribute
		// Khi cái value mình cần lấy nó nằm bên ngoài => getText
		// Khi cái value mình cần lấy nó nằm bên trong => getAttribute
		
		// Dùng để verify xem 1 element hiển thi hoặc không
		// Phạm vi: Tất cả các element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
 
		// Dùng để verify xem 1 element có thao tác được hay không
		// Phạm vi: Tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Dùng để verify xem 1 element được chọn hay chưa
		// Phạm vi: Checkbox/ radio
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		// Các element nằm trong thẻ form
		// Tương ứng với hành vi End User (Enter)
		element.submit();
		

	    

		
	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
