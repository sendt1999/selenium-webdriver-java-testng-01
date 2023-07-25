package javaTester;

public class Topic_04_And_Or {

	public static void main(String[] args) {
		boolean statusA;
		boolean statusB;
		
		// And - &&
		// Nếu 1 trong 2 điều kiện sai => kết quả sai
		// Nếu 1 trong 2 điều kiện đúng => kết quả sai
		// Cả 2 điều kiện sai => kết quả sai
		// Cả 2 điều kiện đúng => kết quả đúng
		
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = false;
		statusB = true;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = false;
		statusB = false;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = true;
		statusB = true;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		// Or - ||
		// Nếu 1 trong 2 điều kiện sai => kết quả đúng
		// Nếu 1 trong 2 điều kiện đúng => kết quả đúng
		// Cả 2 điều kiện sai => kết quả sai
		// Cả 2 điều kiện đúng => kết quả đúng
				
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));
				
		statusA = false;
		statusB = true;
		System.out.println("Kết quả = " + (statusA || statusB));
				
		statusA = false;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));
				
		statusA = true;
		statusB = true;
		System.out.println("Kết quả = " + (statusA || statusB));

	}

}
