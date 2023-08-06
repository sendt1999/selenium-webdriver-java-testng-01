package webdriver;

import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PI {
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
		
	}

	@Test
	public void TC_01_() {
		// >=2: Nó sẽ đóng tab window mà nó đang đứng
		// =1: Nó cũng đóng browser
		driver.close();  //*
		
		// Không quan tâm bao nhiêu tab/ window => Browser
		driver.quit(); //**
		
		// -Có thể lưu vào 1 biến để sử dụng cho các step sau => dùng lại nhiều lần
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		
		// Clean Code
		// Làm sao để ccode đúng/ chạy được
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		
		//Bad Code
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123");
		
		// -Có thể sử dụng luôn (không cần tạo biến)
		driver.findElement(By.xpath("//button[@id='Login']")).click();
		
		// Tìm 1 element
	    driver.findElement(By.xpath("//input[@id='email']")); //**
	    
	    // Tìm nhiều element
	    List<WebElement> checkboxs = driver.findElements(By.xpath("")); //*
	    
	    // Mở ra 1 Url nào đó
	    driver.get("https://www.facebook.com/"); //**
	    
	    // Click vào link: Tiếng Việt
	    
	    // Trả về Url của page hiện tại
	    driver.getCurrentUrl();
	    
	    // Có thể lưu nó vào 1 biến để sử dụng cho các step sau
	    String vietnamesePageUrl = driver.getCurrentUrl();
	    Assert.assertEquals(vietnamesePageUrl, "https://vi-vn.facebook.com/");
	    
	    // Có thể sử dụng luôn không cần tạo biến
	    Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
	    
	    // Trả về Source code HTML của page hiện tại
	    // Verify tương đối
	    Assert.assertTrue(driver.getPageSource().contains("dành cho người nổi tiếng, thương hiệu hoặc doanh nghiệp."));
	    Assert.assertTrue(driver.getPageSource().contains("dành cho người nổi tiếng"));
	    Assert.assertTrue(driver.getPageSource().contains("thương hiệu hoặc doanh nghiệp."));
	    
	    // Trả về title của page hiện tại
	    Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
	    
	    // Lấy ra được ID của Window/ Tab mà driver đang đứng (active)
	    String loginWindowID = driver.getWindowHandle(); //*
	    
	    // Lấy ra ID của tất cả Window/ Tab 
	    Set<String> allIDs = driver.getWindowHandles(); //*
	    
	    // Cookie/ Cache
	    Options opt = driver.manage();
	    
	    // Login thành công => Lưu lại
	    opt.getCookies(); //*
	    // Testcase khấc => Set cookie vào lại => không cần phải login nữa
	    
	    opt.logs();
	    
	    Timeouts time = opt.timeouts();
	    
	    // Implicit wait and depend on: FindElement/ FindElements
	    // Khoảng thời gian chờ Element xuất hiện trong vòng x giây
	    time.implicitlyWait(5, TimeUnit.SECONDS); // 5s = 5000 ms = 5000000  μs //**
	    time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
	    time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
	    
	    // Khoảng thời gian chờ page load xong trong vò x giây
	    time.pageLoadTimeout(5, TimeUnit.SECONDS);
	    
	    // WebDriver API - Javascript Executor (JavascriptExecutor libraary)
	    // Khoảng thời gian script được thực thi trong vòng x giây
	    time.setScriptTimeout(5, TimeUnit.SECONDS);
	    
	    Window win = opt.window();
	    win.fullscreen();
	    win.maximize(); //**
	    
	    // Test GUI: Font/ Size/ Color/ Position/ Location/...
	    win.getMousePosition();
	    win.getSize();
	    
	    Navigation nav = driver.navigate();
	    nav.back();
	    nav.refresh();
	    nav.forward();
	    nav.to("https://www.facebook.com/");
	    
	    TargetLocator tar = driver.switchTo();
	    // WebDriver API - Alert/ Authentication Alert (Alert library)
	    tar.alert(); //*
	    
	    // WebDriver API - Frame/ Iframe (Frame library)
	    tar.frame("");
	    
	    // WebDriver API - Windowns/ Tabs
	    tar.window("");
	    
	    
	    
	    
				
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
