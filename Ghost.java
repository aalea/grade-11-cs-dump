package boring;

import javax.swing.ImageIcon;

/**
 * This class is used to create Ghost objects.
 * It includes a constant ImageIcon array to hold the various ghost pictures
 * and a constructor method that sets the Ghosts' images
 *
 */
public class Ghost extends Mover {
	
	/** 
	 * creates an array of ImageIcons representing various Ghosts
	 */
	public static final ImageIcon[] IMAGE = {
			
			new ImageIcon("images/Ghost0.bmp"),
			new ImageIcon("images/Ghost1.bmp"),
			new ImageIcon("images/Ghost2.bmp"),
			
	};
	
	/**
	 * Ghost constructor
	 * 
	 * @param gNum ghost number - 0, 1 or 2
	 * 
	 */
	public Ghost(int gNum) {
		
		this.setIcon(IMAGE[gNum]);
	}
	

}
