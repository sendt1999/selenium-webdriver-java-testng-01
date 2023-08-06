package testng;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args)  {
			String fullName = "Automation Testing";
			
			// Mong đợi một điều kiện trả về là đúng
			Assert.assertTrue(fullName.contains("Automation"));
			
			// Mong đợi một điều kiện trả về là sai
			Assert.assertFalse(fullName.contains("manual"));
			
			// Mong đợi 2 điều kiện bằng nhau
			// Expected Result
			// Actual Result
			Assert.assertEquals(fullName, "Automation Testing");
			
		
		}

}
