package testing;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Craps extends JFrame {

	public static Random randomNumber1 = new Random();
	public static Random randomNumber2 = new Random();

	public enum Status { CONTINUE, WON, LOST }; 

	private final int SNAKE_EYES = 2; 
	private static final int TREY = 3;
	private static final int SEVEN = 7; 
	private static final int YO_LEVEN = 11; 
	private static final int BOX_CARS = 12; 

	public Craps () {

		Scanner input = new Scanner (System.in); 

		double bankBalance = 1000; 
		double wager = 0;  

		//input wager 

		do { 

			System.out.println("You have $1000 in your balance. How much money would you like to wager?");
			wager = input.nextDouble(); 

			if (bankBalance <= wager)
				System.out.println("Invalid entry! Please enter a lower wager.");

		} while (bankBalance <= wager); 

	}

	JLabel background=new JLabel(new ImageIcon("Table.gif"));
	background.setSize(400,400);
	this.setVisible(true);

	this.setLayout(new BorderLayout());

	add(background);



	public Status play() {

		int myPoint = 0; 
		Status gameStatus; 

		int sumOfDice = 0; 
		sumOfDice = rollDice(); 

		switch ( sumOfDice ) {

		case SEVEN:
		case YO_LEVEN:
			gameStatus = Status.WON; 
			break;
		case SNAKE_EYES:
		case TREY:
		case BOX_CARS:
			gameStatus = Status.LOST;
			break; 
		default:
			gameStatus = Status.CONTINUE;
			myPoint = sumOfDice;
			System.out.printf("Point is %d\n", myPoint);
			break;

		}

		while (gameStatus == Status.CONTINUE) {

			sumOfDice = rollDice();


			if (sumOfDice == myPoint)
				gameStatus = Status.WON;
			else
				if ( sumOfDice == SEVEN)
					gameStatus = Status.LOST;
		}	

		if ( gameStatus == Status.WON ) {
			System.out.println("Player wins");
		} else 
			System.out.println("Player loses");

		return gameStatus; 

	}

	public static int rollDice() {

		int die1 = 1 + randomNumber1.nextInt(6);
		int die2 = 1 + randomNumber2.nextInt(6);

		int sum = die1 + die2;

		System.out.printf("Player rolled %d + %d = %d\n", die1, die2, sum);

		JLabel firstDie = new  JLabel (new ImageIcon());
		firstDie.setBounds (50, 100, 100, 100);


		JLabel secondDie = new  JLabel (new ImageIcon());
		secondDie.setBounds (150, 100, 100, 100);

		if (die1 == 1) {
			firstDie.setIcon(Die1.png);
		}
		else if (die1 == 2) {
			firstDie.setIcon(Die2.png);
		}
		else if (die1 == 3) {
			firstDie.setIcon(Die3.png);
		}
		else if (die1 == 4) {
			firstDie.setIcon(Die4.png);
		}
		else if (die1 == 5) {
			firstDie.setIcon(Die5.png);
		}
		else if (die1 == 6) {
			firstDie.setIcon(Die6.png);
		}

		background.add(firstDie);

		if (die2 == 1) {
			secondDie.Icon(Die1.png);
		}
		else if (die2 == 2) {
			secondDie.Icon(Die2.png);
		}
		else if (die2 == 3) {
			secondDie.Icon(Die3.png);
		}
		else if (die2 == 4) {
			secondDie.Icon(Die4.png);
		}
		else if (die2 == 5) {
			secondDie.Icon(Die5.png);
		}
		else if (die2 == 6) {
			secondDie.Icon(Die6.png);
		}

		background.add(secondDie);






		return sum;

	}

}
