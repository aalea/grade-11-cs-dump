/*
 * An application of method Math.floor is rounding a value to the nearest integer. 
 * The statement y = Math.floor( x + 0.5 ); will round the number x to the nearest 
 * integer and assign the result to y. Write an application that reads double values 
 * and uses the preceding statement to round each of the numbers to the nearest integer. 
 * For each number processed, display both the original number and the rounded number.
 */

import java.util.Scanner;
public class Rounder {

	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);
		double x, y;
		
		System.out.println("Please enter double value");
		x = input.nextDouble();
		System.out.printf("The rounded number is %.0f", doRound(x));
		
		
	}
	
	private static double doRound(double x) {
		
		double y = Math.floor(x + 0.5);
		return y;
	}
}
