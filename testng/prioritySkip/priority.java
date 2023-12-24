package prioritySkip;

import org.testng.annotations.Test;

public class priority {
	
	@Test(priority = 4)
	public void deleteCustomer() {
		
	}
	
	@Test(priority = 3)
	public void readCustomer() {
		
	}
	
	@Test(priority = 2)
	public void newCustomer() {
		
	}
	
	@Test(priority = 1)
	public void editCustomer() {
		
	}

}
