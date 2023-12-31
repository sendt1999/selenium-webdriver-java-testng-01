package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_Css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	
	// khởi tạo trình duyệt lên
	
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
	public void TC_01_Empty_Data() {
   
        // ví dụ lấy XPath 1: 
        // http://live.techpanda.org/index.php/mobile.html
        // //a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button
        
        // ví dụ lấy XPath 2: 
        // https://fptshop.com.vn/apple/macbook
        // //a[@title='MacBook Air M2 2023 15 inch']/ancestor::div[@class='product_info']/following-sibling::div/a[text()='MUA NGAY']
    
	   // //td[text()='User ID :']/following-sibling::td
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Ít bị sai cú pháp/ Không đúng mở đóng ngoặc
		// Suugest sode lại: Ctrl + space
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		// Assert.assertTrue -> Kiểm tra 1 điều kiện trả về là đúng
		// Assert.assertFalse -> Kiểm tra 1 điều kiện trả về là sai
		// Assert.assertEqualse -> Kiểm tra thực tế với mong đợi như nhau
		
		// Data Type: WebElement = String
		// Data Type: String = String
		// Data Value: Vui lòng nhập họ tên = Vui lòng nhập họ tên
		
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
			
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Do Sen");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123@123");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@123@123");
		driver.findElement(By.id("txtPassword")).sendKeys("Sen080399@");
		driver.findElement(By.id("txtCPassword")).sendKeys("Sen080399@");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
		
	}

	@Test
	public void TC_03_Incorrect_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Do Sen");
		driver.findElement(By.id("txtEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Sen@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("Sen080399@");
		driver.findElement(By.id("txtCPassword")).sendKeys("Sen080399@");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}

	@Test
	public void TC_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Do Sen");
		driver.findElement(By.id("txtEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");		
	}
	
	@Test
	public void TC_05_Incorrect_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Do Sen");
		driver.findElement(By.id("txtEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123678");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");		
			
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action 1
		driver.findElement(By.id("txtFirstname")).sendKeys("Do Sen");
		driver.findElement(By.id("txtEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("Sen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0123456");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify 1
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");				
		
		// Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("012345678900");
		
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");	
		
		
		// Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0051234567");
				
		driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
				
		// Verify 2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");	
						
		
		
	 }
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
