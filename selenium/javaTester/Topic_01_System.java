package javaTester;

import java.io.File;

public class Topic_01_System {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		String dalatPhoto = "Da lat.jpg";
		
		String dalatPhotoPath = projectPath + File.separator + "uplaodFiles" + File.separator + dalatPhoto;
		System.out.println(dalatPhotoPath);
		
		String osName = System.getProperty("os.name");
		System.out.println(osName);

	}

}
