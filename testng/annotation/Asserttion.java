package annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Asserttion {
	boolean status = false;
	String fullName = "Selenium WebDriver";
	
	@Test
	public void TC_01() {
		// Đúng hoặc sai trả về boolean
		// assertTrue / False
		// isXXX: isDisplayed/ isElemented/ isSelected/ isMultiple
		Assert.assertTrue(status);
		
		// Bằng với mong đợi
		// assertEquals
		Assert.assertEquals(fullName, "Selenium 123");
	}

}
