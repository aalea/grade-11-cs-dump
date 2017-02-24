package homeStuff;
import java.util.Scanner;

public class Question3 {
	
	public static void main( String[]args ){
		Scanner input = new Scanner(System.in);
		int Number1, Number2, Number3, Product, Sum, Smallest, Largest;
		Smallest = 0;
		Largest = 0;
		double Average;
		
		System.out.println("Please enter the 1st number: ");
		Number1 = input.nextInt();
		System.out.println("Please enter the 2nd number: ");
		Number2 = input.nextInt();
		System.out.println("Please enter the 3rd number: ");
		Number3 = input.nextInt();
		
		Product = Number1 * Number2 * Number3;
		Sum = Number1 + Number2 + Number3;
		Average = Sum / 3;
		
		if (Number1 > Number2 & Number1 > Number3)
			Largest = Number1;
		else if (Number2 > Number1 & Number2 > Number3)
			Largest = Number2;
		else if (Number3 > Number1 & Number3 > Number2)
			Largest = Number3;
		
		if (Number1 < Number2 & Number1 < Number3)
			Smallest = Number1;
		else if (Number2 < Number1 & Number2 < Number3)
			Smallest = Number2;
		else if (Number3 < Number1 & Number3 < Number2)
			Smallest = Number3;
		
		System.out.printf("Product: %d ", Product);
		System.out.printf("Sum: %d ", Sum);
		System.out.printf("Average: %f ", Average);
		System.out.printf("Smallest: %d ", Smallest);
		System.out.printf("Largest: %d ", Largest);
				
}
}