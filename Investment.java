/**
 * 
 */
package homeStuff;
import java.util.Scanner;
import static java.lang.System.*;
/**
 * @author Owner
 *
 */
public class Investment {

	public static void main (String[] args){
		
		//
		Scanner input = new Scanner(System.in);
		double p, r, n, fv; //principal, rate, years, future value
		
		//
		out.println("Please input the principal, rate, and years");
		p = input.nextDouble();
		r = input.nextDouble();
		n = input.nextDouble();
		
		//
		fv = (double) Math.pow(p * 1 + r/ 100, n);
		
		//
		out.printf("The future value is %f", fv);
		
		input.close();
		
		
		
	}//
	
}//
