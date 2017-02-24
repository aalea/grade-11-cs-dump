/**
 * 
 */
package homeStuff;
import java.util.Scanner;

/**
 * @author Owner
 *
 */
public class NegPosZero {
	public static void main (String[] args){
		Scanner input = new Scanner(System.in);
		int num1, num2, num3, num4, num5;
		int numOfZeroes, numOfNeg, numOfPos;
		numOfZeroes = 0;
		numOfNeg = 0;
		numOfPos = 0;
		
		System.out.println("Please enter 5 numbers: ");
		num1 = input.nextInt();
		num2 = input.nextInt();
		num3 = input.nextInt();
		num4 = input.nextInt();
		num5 = input.nextInt();
		
		if (num1 > 0)
			numOfPos = numOfPos + 1;
		else if (num1 < 0)
			numOfNeg = numOfNeg + 1;
		else if (num1 == 0)
			numOfZeroes = numOfZeroes + 1;
		
		if (num2 > 0)
			numOfPos = numOfPos + 1;
		else if (num2 < 0)
			numOfNeg = numOfNeg + 1;
		else if (num2 == 0)
			numOfZeroes = numOfZeroes + 1;
		
		if (num3 > 0)
			numOfPos = numOfPos + 1;
		else if (num3 < 0)
			numOfNeg = numOfNeg + 1;
		else if (num3 == 0)
			numOfZeroes = numOfZeroes + 1;
		
		if (num4 > 0)
			numOfPos = numOfPos + 1;
		else if (num4 < 0)
			numOfNeg = numOfNeg + 1;
		else if (num4 == 0)
			numOfZeroes = numOfZeroes + 1;
		
		if (num5 > 0)
			numOfPos = numOfPos + 1;
		else if (num5 < 0)
			numOfNeg = numOfNeg + 1;
		else if (num5 == 0)
			numOfZeroes = numOfZeroes + 1;
		
		System.out.printf("Number of Positive Numbers: %d\nNumber of Negative Numbers: %d\nNumber of Zeroes: %d\n", numOfPos, numOfNeg, numOfZeroes);
		
		
	}

}
