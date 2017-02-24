/*@author: Aalea Ally
 *@course: ICS 3U1
 *@date: 15/09/2016
 */

package homeStuff;
import java.util.Scanner;

public class Question4 {
	public static void main (String[]args){

		Scanner input = new Scanner(System.in);
		double Radius, Diameter, Circumference, Area;

		System.out.println("Type radius below ");
		Radius = input.nextInt();

		Diameter = Radius * 2;
		Circumference = Math.PI * Radius * 2;
		Area = Math.PI * Radius;
		Area = Area * Area;

		System.out.printf("Diameter: %f ", Diameter);
		System.out.printf("Circumference: %f ", Circumference);
		System.out.printf("Area: %f ", Area);	

	}

}
