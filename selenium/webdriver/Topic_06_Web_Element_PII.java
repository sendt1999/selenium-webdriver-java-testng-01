package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio  = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckbox = By.cssSelector("#development");
	By LanguaguesJavaCheckbox = By.cssSelector("#java");

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
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Kiểm tra các phần tử hiển thị trên trang
		// Textbox
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium Webdriver");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		// Textarea
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium GRID");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
	
		// Radio
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age Under 18 is displayed");
		} else {
			System.out.println("Age Under 18 is not displayed");
		}

		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(emailTextbox).isDisplayed()) {
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		if (driver.findElement(educationTextArea).isDisplayed()) {
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
	
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			System.out.println("Age Under 18 is displayed");
		} else {
			System.out.println("Age Under 18 is not displayed");
		}
		
		if (driver.findElement(developmentCheckbox).isDisplayed()) {
			System.out.println("development Checkbox is displayed");
		} else {
			System.out.println("development Checkbox is not displayed");
		}
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(LanguaguesJavaCheckbox).click();
		
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(LanguaguesJavaCheckbox).isSelected());
		
//		driver.findElement(ageUnder18Radio).click();
//		driver.findElement(LanguaguesJavaCheckbox).click();
//		
//		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
//		Assert.assertTrue(driver.findElement(LanguaguesJavaCheckbox).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.cssSelector("#email")).sendKeys("Sendt1999@gmail.com");
		
		By passwordTextbox = By.cssSelector("#new_password");
		By singupButton = By.cssSelector("#create-account-enabled");
		
		driver.findElement(passwordTextbox).sendKeys("abc");
		driver.findElement(passwordTextbox).click();
		sleepInSecond(3);
		
		// Verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		sleepInSecond(3);

		// Verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(5);

		// Verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
		sleepInSecond(3);

		// Verify special
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCABCABC");
		sleepInSecond(3);

		// Verify 8 char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Sen080399@");
		sleepInSecond(3);

		// Verify full data
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
		
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
		// driver.quit();
	}
}
