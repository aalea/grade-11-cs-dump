package two015;

import java.util.Scanner;

public class specialDay {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int month, day;
		
		month = input.nextInt();
		day = input.nextInt();
		
		if (month > 2 || month==2 && day > 18) {
			System.out.println("After");
		}
		else if (month==1 || month==2 && day < 18) {
			System.out.println("Before");
		}
		else if (month==2 && day==18) {
			System.out.println("Special");
		}
		

	}

}
