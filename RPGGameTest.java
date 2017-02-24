package testing;


/**
 * This is the class that executes first. It calls the necessary classes 
 * to start the game.
 * 
 * @author Aalea Ally
 *
 */
public class RPGGameTest {
	
	/**
	 * the paper object array which holds the attributes of the pages scattered across the rooms
	 */
	static Paper [] paper = new Paper[15]; 

	/**
	 * the first frame that pops up. It holds the intro screen and the rooms.
	 */
	static FrameSetup frame = new FrameSetup();
	
	public static void main (String[] args) {
		
		// starts new instance of the class that setups the frame
		new FrameSetup(); 
		//play intro music in the loader class
		Loader.audioLoader("Intro Music", "sounds/music/intro.wav");
		//start loading attributes of paper object elements in the array
		new Loader(paper);
		
	}

}
