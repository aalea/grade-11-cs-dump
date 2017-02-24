package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * This class is called upon whenever an external file needs to be loaded.
 * 
 * @author Aalea Ally
 * 
 */
public class Loader {

	/**
	 * holds clip when the audioLoader is requested
	 */
	protected static Clip clip;
    
    /**
     * holds the instance of the audio file scanner
     */
	protected static AudioInputStream audioInputStream;

	/**
	 * holds the number of friends that are alive
	 */
	public static int friendsAlive = 6;

    /**
     *  instance of room 2
     */
	public static Room room2;
    
    /**
     *  instance of room 3
     */
	public static Room room3;
    
    /**
     *  instance of room 4
     */
	public static Room room4;

    /**
     *  instance of the boss
     */
	public static FinalBoss boss;

    /**
     * @param paper object array
     */
	public Loader(Paper[] paper) {
        
		paperLoader(paper); //call the method that loads the paper messages from the CSV file
        
	}

    /**
     * loads the audio upon first starting the game. Originally intended to be a loader for all classes' audio
     * however there were accessibility issues
     * 
     * @param name of audio, location of audio file
     */
	public static void audioLoader(String name, String destination) {

		if (name.equals("STOP")) { //if STOP is sent to this method
            
			clip.stop(); //stop the audio
            
		}
		else {
            
			try { //attempt to play the audio file
                
				//Define file
				audioInputStream = AudioSystem.getAudioInputStream(new File(destination).getAbsoluteFile());
				//Get clip
				clip = AudioSystem.getClip();
				//Open clip in audio input stream
				clip.open(audioInputStream);
				//Start clip
				clip.start();

			} catch(Exception ex) { //if for any reason the audio could not be played
                
				System.out.println(ex); //print the error
                
			}
            
		}

	}


    /**
     * this method loads the CSV file of the messages into the paper object array
     * 
     * @param 
     */
	public void paperLoader(Paper[] paper) {

		try {

			Scanner input = new Scanner (new File("Messages - Sheet1.csv")); //open messages csv in scanner
			input.useDelimiter(","); //values are separated by commas
			input.nextLine(); //move to next line

			int index = 0; //used to iterate through paper objects

			while (index <= 14) { //repeat until all paper objects are filled

				paper[index] = new Paper(); //add a new paper object
				int[] placeholderArray = new int[3]; //this array will hold input about 
													//the current paper object's location from the csv

				//Read room number of paper
				placeholderArray[0] = Integer.parseInt(input.next().trim());
				//Read x position of paper
				placeholderArray[1] = Integer.parseInt(input.next());
				//Read y position of paper
				placeholderArray[2] = Integer.parseInt(input.next());

				//set the current paper object's position attribute to the input from the csv
				paper[index].setPosition(placeholderArray);

				//Read message for the current paper object and sets it as the message attribute
				paper[index].setMessage(input.next());

				//Move to next line
				index++;

			}

			input.close(); //stops receiving input from the csv

		}

		catch (FileNotFoundException e) { //if the file cannot be found

			System.out.println("ERROR! Messages file cannot be read");

		}

	}

}
