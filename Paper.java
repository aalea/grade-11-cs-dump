package testing;

import java.util.Arrays;

import javax.swing.JOptionPane;


/**
 * This class uses the Loader class to assign in messages and positions from a database
 * It stores the attributes of each of the pages scattered around the rooms
 * 
 * @author Aalea Ally
 * 
 */
 
public class Paper {
    
    /**
     * holds message of paper
     */ 
    private String message; 
    
    /**
     * holds position of paper
     */
    private int[] position = new int[3]; //position[room number][x position][y position]
     
     /**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	

	/**
	 * @return the position
	 */
	public int[] getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int[] position) {
		this.position = position;
	}

}
