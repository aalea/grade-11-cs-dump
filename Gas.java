/**
 * 
 */
package gas;
import java.util.Scanner;
/**
 * @author 340795954
 *
 */
public class Gas {
	
	public static void main (String[] args) {
		
		//variables
		Scanner input = new Scanner(System.in);
		int tankCounter = 0;
		int kilometres, litres;
		int kilometresPerLitre;
		int totalKilometres = 0;
		int totalLitres = 0;
		int totalKilometresPerLitre;
		boolean exitLoop;
		exitLoop = false;
		
		//loop
		do {
			tankCounter++; //counter
			
			//Input
			System.out.printf("Please enter kilometres and litres driven for Tank %d\n", tankCounter);
			kilometres = input.nextInt();
			litres = input.nextInt();
			
			//Process
			totalKilometres = totalKilometres + kilometres;
			totalLitres = totalLitres + litres;
			
			kilometresPerLitre = kilometres / litres;
			totalKilometresPerLitre = (totalKilometres / tankCounter) / (totalLitres / tankCounter);
			
			//Output
			System.out.printf("The kilometres per litre for Tank %d is %dkm/litre and the total is %dkm/litre \n", tankCounter,
					kilometresPerLitre,totalKilometresPerLitre);
			
			//Sentinel
			System.out.println("Do you want to enter another tank? (y/n)");
			if (input.next().equals("n")) {
				exitLoop = true;
			}
			
        } while (exitLoop == false);

		input.close();
		System.exit(0);
		
	}

}
