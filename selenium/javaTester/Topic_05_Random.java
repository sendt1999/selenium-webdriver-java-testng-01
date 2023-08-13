package javaTester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		// utilities = tiện ích
		// Data Type: Class/ Interface/ Collection/ String/ Float...
	
		System.out.println("Automation" + getRandomNumber() + "@gmail.com");
		System.out.println("Automation" + getRandomNumber() + "@gmail.com");
		System.out.println("Automation" + getRandomNumber() + "@gmail.com");
		

	}
	
	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
