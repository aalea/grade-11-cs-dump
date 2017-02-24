package homeStuff;
import java.util.Scanner;

public class AddAge {
	public static void main (String[] args){
		Scanner input = new Scanner(System.in);
		String firstName, lastName;
		int age, newAge;
		
		System.out.println("Please enter your first name, last name, and age");
		firstName = input.next();
		lastName = input.next();
		age = input.nextInt();
		
		newAge = age + 1;
		
		System.out.printf("%s %s you will be %d next year", firstName, lastName, newAge);
		
		input.close();
		
	}

}
