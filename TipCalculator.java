package homeStuff;

import java.util.Scanner;

public class TipCalculator {
	
	public static void main (String[] args){
		
		//
		Scanner input = new Scanner(System.in);
		double bill, percentTip, tip;
		
		//
		System.out.println("Please input bill amount and percentage of tip");
		bill = input.nextDouble();
		percentTip = input.nextDouble();
		
		//
		tip = (double) (percentTip/100) * bill;
		
		//
		System.out.printf("The tip you need to pay is $%.2f", tip);
		
		input.close();
	}//

}//
