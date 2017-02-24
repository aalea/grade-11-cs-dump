/*
 * Math.floor may be used to round a number to a specific decimal place. The statement 
 * y = Math.floor( x * 10 + 0.5 ) / 10; rounds x to the tenths position (i.e., the first 
 * position to the right of the decimal point). The statement 
 * y = Math.floor( x * 100 + 0.5 ) / 100; rounds x to the hundredths position (i.e., the 
 * second position to the right of the decimal point).
 * 
 * Write an application that defines four methods for rounding a number x in various ways:

a) roundToInteger( number )

b) roundToTenths( number )

c) roundToHundredths( number )

d) roundToThousandths( number )

For each value read, your program should display the original value, the number rounded to 
the nearest integer, the number rounded to the nearest tenth, the number rounded to the 
nearest hundredth and the number rounded to the nearest thousandth.
 */

import java.util.Scanner; 

public class RounderPositions {
	
	public static void main (String[] args) {
		
		Scanner input = new Scanner (System.in);
		char choice;
		double x;
		
		System.out.println("What position do you wish to round to?");
		System.out.println("1) Whole number 2) Tenths 3) Hundredths 4) Thousandths");
		choice = input.next().charAt(0);
		
		System.out.println("Please enter value");
		x = input.nextDouble();
		
		switch (Character.getNumericValue(choice)) {
		
		case 1: {
			System.out.println(calculateInteger(x));
			break;
		}
		case 2: {
			System.out.println(calculateTenths(x));
			break;
		}
		case 3: {
			System.out.println(calculateHundredths(x));
			break;
		}
		case 4: {
			System.out.println(calculateThousandths(x));
			break;
		}
		}
		
		
	}
	
	private static double calculateInteger(double x) {
		return Math.floor(x + 0.5); 
	}
	
	private static double calculateTenths(double x) {
		return Math.floor( x * 10 + 0.5 ) / 10; 
	}
	
	private static double calculateHundredths(double x) {
		return Math.floor( x * 100 + 0.5 ) / 100 
	}
	
	private static double calculateThousandths(double x) {
		return Math.floor( x * 1000 + 0.5 ) / 1000 
	}

}
