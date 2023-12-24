package webApp;

import org.testng.annotations.Test;

public class Administrator {
	// CRUD: Creat Read Update Delete
	// API: Post Get Put Delete
	
	@Test(groups = "web")
	public void Admin_01_Create_New_Author() {
		
	}
	
	@Test(groups = "web", dependsOnMethods = "Admin_01_Create_New_Author")
	public void Admin_02_View_Author() {
		
	}
	
	@Test(groups = "web", dependsOnMethods = {"Admin_01_Create_New_Author", "Admin_02_View_Author"})
	public void Admin_03_Edit_Author() {
		
	}
	
	@Test(groups = "web", dependsOnMethods = "Admin_03_Edit_Author")
	public void Admin_04_Delete_Author() {
		
	}

}
