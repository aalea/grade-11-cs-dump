/*
 * Write a method integerPower( base, exponent ) that returns the value of base exponent For 
 * example, integerPower( 3, 4 ) calculates 3^4 (or 3 * 3 * 3 * 3). Assume that exponent is a 
 * positive, nonzero integer and that base is an integer. Method integerPower should use a
 * for or while statement to control the calculation. Do not use any math library methods. 
 * Incorporate this method into an application that reads integer values for base and exponent 
 * and performs the calculation with the integerPower method.
 */

import java.util.Scanner;

public class ExponentCalculator {
	
	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);
		double base, exponent;
		
		System.out.println("Please enter base and exponent");
		base = input.nextDouble();
		exponent = input.nextDouble();
		
		System.out.println(integerPower(base, exponent));
	}
	
	public static double integerPower(double b, double e) {
		
		double power = b;
		
		for (int counter = 2; counter <= e;) {
			
			power = power * b;
			counter++;
		}
		
		return power;
	}

}
