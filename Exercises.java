/*
 * 
 */

import java.util.Random;
import java.util.Scanner;
/**
 * @author Owner
 *
 */
public class Exercises {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//salaryTable();
		//diceTable(); //GOOD JOB
		//salesTable();
		airplaneSeats(); //the sentinel is icky but otherwise good job
		//primeSieve();

	}

	//Stops right after discontinuing loop to enter salaries and before printing loop
	public static void salaryTable() {

		Scanner input = new Scanner(System.in);

		final int BASE_SALARY = 200;
		int[] salaryTracker = new int[10];
		int commission;
		boolean goOn = false;

		do {

			System.out.println("What was your commission this week?");
			commission = input.nextInt();

			for (int index = 1; index <= salaryTracker.length; index++) {
				if (commission / 100 * 9 + BASE_SALARY >= 100 + (index * 100) && commission / 100 * 9 + BASE_SALARY <= 199 + (index * 100)) {

					++salaryTracker[index];

				}

				else {
					++salaryTracker[9];
				}

			}

			System.out.println("Would you like to enter another number? (1 or 2)");
			if (input.nextInt() == 2){
				goOn = false;
			}
			else if (input.nextInt() == 1){
				goOn = true;
			}


		} while (goOn == true);

		for (int index = 1; index > salaryTracker.length; index++) {

			System.out.printf("%d to %d\t%d", 100 + index * 100, 199 + index * 100, salaryTracker[index]);

		}

	}

	//do in class with notes
	public static void diceTable() {

		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int [] diceRolls = new int [13];
		int dice1, dice2;

		for (int counter = 1; counter <= 36000; ++counter) {
			dice1 = rand.nextInt(6) + 1;
			dice2 = rand.nextInt(6) + 1;

			++diceRolls[dice1 + dice2];

		}
		for (int index = 2; index <= diceRolls.length; ++index){
			System.out.println(index + "  " + diceRolls[index]);
		}

	}

	//Same for this, but there's something in the bottom right corner that won't load
	public static void salesTable() {

		Scanner input = new Scanner(System.in);

		int[][] salesPerson = new int[5][6];
		int salesPersonIndex;
		int productIndex;
		int totalDollarValue;

		do { //.length = of first dimension?

			System.out.println("Please enter your salesperson number, the product number, and total dollar value for today");
			salesPersonIndex = input.nextInt();
			productIndex = input.nextInt();
			totalDollarValue = input.nextInt();

			salesPerson [salesPersonIndex] [productIndex]  = totalDollarValue;
			//System.out.printf("%d", salesPerson [salesPersonIndex] [productIndex]);

		} while (salesPersonIndex != -1); //horrible but -1 is the sentinel

		System.out.printf("Products \t1\t2\t3\t4\n");

		for (int index = 1; index < salesPerson.length; index++) {

			System.out.printf("Product %d\t%d\t%d\t%d\t%d\n", index, salesPerson[1][index], salesPerson[2][index], salesPerson[3][index], salesPerson[4][index]);

		}

	}

	//replace sentinel
	public static void airplaneSeats() {

		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		int seatingPreference;
		String seating = " ";
		int seat = 0;
		int signal = 0;
		boolean[][] airplaneSeating = new boolean [3][6]; //1-First Class 2-Economy

		do {
			System.out.println("Please type 1 for first class or 2 for economy");
			seatingPreference = input.nextInt();

			if (seatingPreference == 1){
				seating = "First Class";
			}
			else if (seatingPreference == 2){
				seating = "Economy";
			}

			seat = rand.nextInt(5) + 1;

			if (airplaneSeating[seatingPreference][seat] == false) {
				// if seat is in economy, add 5 to it
				System.out.printf("Your seat number is %d in %s. Have a safe flight :)", seat, seating);

			}
			else {
				for (int index = 1; index < airplaneSeating[seatingPreference].length; ++index) {
					if (airplaneSeating[seatingPreference][index] == false) {
						seat = index;
						signal = 1;
						break;
					}
				}

				if (signal == 0) {
					System.out.println("Would you consider moving to a different section? (y/n)");
					if (input.next().equals("n")){
						System.out.println("Next flight leaves in 3 hours");
						System.exit(0);
					}
					else {
						if (seatingPreference == 1) {
							seatingPreference = 2;
						}
						else {
							seatingPreference = 1;
						}
						for (int index = 1; index < airplaneSeating[seatingPreference].length; ++index) {
							if (airplaneSeating[seatingPreference][index] == false) {
								seat = index;
								signal = 1;
								break;
							}
						}
					}
				}
				else {

					if (seatingPreference == 1){
						seating = "First Class";
					}
					else if (seatingPreference == 2){
						seating = "Economy";
					}

					if (airplaneSeating[seatingPreference][seat] == false) {

						System.out.printf("Your seat number is %d in %s. Have a safe flight :)", seat, seating);

					}
				}
			}
			
			System.out.println();
			System.out.println("Would you like to book another seat? (any other # = yes, -1 = no)");
			//signal = input.nextInt();

		} while (input.nextInt() != -1);


	}

	//i giv up
	public static void primeSieve() {

		boolean [] numbers = new boolean [1001];

		for (int index = 0; index < numbers.length; index++) { //sets all elements to true
			numbers[index] = true;
		}

		//int counter = 1;
		//int index = 0;

		for (int index = 2; index < numbers.length; index = index + 1) { //separates non-prime numbers

			if (numbers[index] = true) {
				for (int counter = 1; counter < numbers.length / 2; counter++) {
				
					System.out.println(counter);
					System.out.println(index);
					System.out.println();

					numbers[counter * index] = false;
				}
			} 

		} 
		for (int index = 1; index < numbers.length; index++) { //prints prime numbers
			if (numbers[index] == true) {
				System.out.println(index);
			}
		}



	}

}
