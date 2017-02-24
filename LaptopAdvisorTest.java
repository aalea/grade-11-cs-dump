package lap;

//import Laptop;
//import LaptopAdvisorGUI;

/*
 * Name(s): Sanjana, Aalea, Sehel
 * Date: November 11, 2016
 * Course code: ICS3U1-01/02
 * Purpose: To create an application that...
 * Major skills: File Input, GUI, Arrays, Weighted Decision Matrix...
 * Extra features: 
 * Areas of concern: WON'T READ USB 2.0 VALUE OF THE 24TH LAPTOP ~Aalea
 	* (Update: Doesn't read file at all on my laptop at home???) ~Aalea
 	* (Update: Fixed a problem with commas but now it only reads in the first laptop, creates the second one, yet stops before it assigns any of the fields (Hi 3)) ~Aalea
 	* (Update: I EDITED IT IN SHEETS AND THEY TOOK OUT THE COMMAS AT THE END OF EACH LINE GOD DAMMIt) ~Aalea
 	* (Update: Keeps reading after all the laptops are done???) ~Aalea
 	* (Update: WHYYYYYyYYY) ~Aalea
 	* (Update: suddenly recalls the existence of off-by-one errors (in the laptop array), still keeps counting) ~Aalea
 	* (Update: F I N A L L Y     
 	* 		OKAy, when declaring an array, the length is the actual length (not 0-whatever). When calling a specific variable in the array make it like 0-whatever.
 	* 		Prints with the weird format when you generate the toString, changing it now wish me luck) ~Aalea
 	* (Update: Looks good, now adding weightings and scores) ~Aalea
 	* (Update: So this will be my last update of the night, I updated the spreadsheet to include ratings for storage type and RAM. 
 	* 		The null values will just be averages of the rest of the numbers so they'll have an equal chance of being picked. 
 	* 		This version works, I just have to implement the score now. Please don't touch the spreadsheet now, we'll add adjustments
 	* 		once we're finished everything else. Also try using the task window, it's really helpful and I already put some there.) ~Aalea
 	* (Update: I'll keep line 50 commented out until we're ready to test the GUI)
*/


/*
* This is a test class that runs the application.  It includes an array that stores the laptops.  This 
* class then runs the LaptopAdvisorFileInput class and the LaptopAdvisorGUI class.
*
* author - Sanjana
*/


public class LaptopAdvisorTest {


	static Laptop [] laptop = new Laptop[29];


	public static void main (String[] args) {


		new LaptopAdvisorFileInput(laptop);
		
		for (int x = 0; x < laptop.length; x++) {
			
			System.out.println(laptop[x]);
		}
		
		//new LaptopAdvisorGUI(laptop);


	}


}
