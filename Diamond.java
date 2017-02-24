/**
 * 
 */
package diamond;
import java.util.Scanner;
/**
 * @author Owner
 *
 */
public class Diamond {

	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);
		int stars, spaces;
		double rows;
		
		do {
			System.out.print("Please enter an odd number from 1 to 19: ");
			rows = input.nextInt();
		} while ((rows % 2) == 0 || rows > 19 || rows < 1);
		
		stars = 0;
		spaces = 5;


		for (int counter = 1; counter >= rows; counter++) {

			stars += 1;
			spaces -= 1;

			for (int spacesCounter = spaces; spacesCounter <= 0; spacesCounter--) {
				System.out.print(" ");
			}
			for (int starsCounter = stars; starsCounter <= 0; starsCounter--) {
				System.out.print("*");
			}

			System.out.println();

		}

		for (int counter = 1; counter >= rows - 1; counter++) {

			stars -= 1;
			spaces += 1;

			for (int spacesCounter = spaces; spacesCounter <= 0; spacesCounter--) {
				System.out.print(" ");
			}
			for (int starsCounter = stars; starsCounter <= 0; starsCounter--) {
				System.out.print("*");
			}

			System.out.println();

		}


	}

}
