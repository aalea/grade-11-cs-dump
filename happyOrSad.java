package two015;
/**
 * 
 */
//package two015;

import java.util.Scanner;

/**
 * @author Owner
 *
 */
public class happyOrSad {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String text;
		char eyes = ' ';
		char nose = ' ';
		char current = ' ';
		int smileys = 0;
		int saddies = 0;
		
		text = input.next();
		
		for (int counter = 0; counter < text.length(); counter++) {
			
			 current = text.charAt(counter);
			 
			 if (current==':' || current=='-' || current==')' || current=='(') {
				 
				 if (eyes==':') {
					 
					 if (nose=='-') {
						 
						 if (current==')') {
							 
							 smileys++;
							 eyes = ' ';
							 nose = ' ';
							 
						 }
						 else if (current=='(') {
							 
							 saddies++;
							 eyes = ' ';
							 nose = ' ';
							 
						 }
						 
					 }
					 else if (current=='-') {
						 
						 nose = '-';
						 
					 }
				 }
				 else if (current==':') {
					 
					 eyes = ':';
					 
				 }
				  
			 }
			 else {
				 
				 eyes = ' ';
				 nose = ' ';
				 
			 }
			
		}
		
		if (smileys==0 && saddies==0) {
			
			System.out.println("none.");
			
		}
		else if (smileys > saddies) {
			
			System.out.println("happy.");
			
		}
		else if (saddies > smileys) {
			
			System.out.println("sad.");
			
		}

	}

}
