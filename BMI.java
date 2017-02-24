/**
 * 
 */
package com;

import java.util.Scanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author 340795954
 *
 */
public class BMI {
	
	public static void main (String[] args){
		
		//variables
		Scanner input = new Scanner(System.in);
		double weight, height, bmi;
		bmi = 0;
		String unitsChoice;
		
		//Input
		System.out.println("Do you want to calculate with metric or US units?");
		unitsChoice = input.next();
		
		//Process
		if (unitsChoice.equals("1")){
			System.out.println("Please enter your weight (kilograms) and height (metres)");
			weight = input.nextDouble();
			height = input.nextDouble();
			bmi = CalculateBMIinMetricUnits(weight, height, bmi);
		}
		else if (unitsChoice.equals("2")){
			System.out.println("Please enter your weight (pounds) and height (inches)");
			weight = input.nextDouble();
			height = input.nextDouble();
			bmi = CalculateBMIinUSUnits(weight, height, bmi);
		}
		
		//Output
		System.out.printf("Your BMI is %.2f", bmi);
			
			
	}
	
	//
	public static double CalculateBMIinUSUnits(double weight, double height, double bmi) {
		bmi = (double) ( weight / ( height * height ) ) * 703;
		return bmi;
	}
	
	//
	public static double CalculateBMIinMetricUnits(double weight, double height, double bmi) {
		bmi = (double) ( weight / ( height * height ) );
		return bmi;
	}

}


