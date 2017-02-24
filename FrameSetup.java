package testing;


import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the class that setups the JFrame and all of its 
 * components
 * 
 * @author Aalea Ally
 *
 */
public class FrameSetup extends JFrame implements KeyListener {

	/**
	 * instance of the panel that goes on this frame upon start of the game
	 */
	public IntroScreen introScreen = new IntroScreen();

	/**
	 * instance of the first room
	 */
	public static Room room1 = new Room("Room Maps/Room1.txt");

	/**
	 * placeholder for audio being played
	 */
	private static Clip clip;


	public FrameSetup() {

		addKeyListener(RPGGameTest.frame); //listen for keys being pressed on the frame
		//room1.enemyTimer.stop(); //halt the enemy movement in room 1 until it is added to the frame

		setLayout(null); //do not use a layout manager for this frame
		setSize(1060, 270); //adjust size of frame
		setTitle("Somnium"); //set title of the frame to the name of the game
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the frame so that the application ends upon clicking X

		add(introScreen); //Add the intro screen to the GUI

		setVisible(true); //make frame visible

	}
	
	/**
	 * this method detects when a key is pressed and displays the instructions if the key is 'i', 
	 * or starts the game if any other key is pressed
	 * 
	 * @param the key the user pressed
	 */
	@Override
	public void keyPressed(KeyEvent key) {

		if (key.getKeyCode()==73) { //if the key pressed was 'i'
			
			JOptionPane.showMessageDialog(this, 
					"You don't know how you got here, when, or where you even are. You just woke up.", 
					"Instructions", JOptionPane.PLAIN_MESSAGE, null); //display a tiny bit of story
			JOptionPane.showMessageDialog(this, "To navigate the rooms, "
					+ "Up arrow to move up, "
					+ "Down arrow to move down, "
					+ "Right arrow to move right, "
					+ "Left arrow to move left."); //display instructions
			
		}
		else { //if the key pressed was any other key than 'i'
			
			System.out.println("START");
			removeIntroScreen(); //call the method that handles removing the intro screen
			
		}

	}
	
	/**
	 * unused method mandatory when implementing keylistener
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * unused method mandatory when implementing keylistener
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * this method handles requests for removing the intro screen
	 */
	public void removeIntroScreen() {
		
		//disable intro screen
		remove(introScreen); //remove the intro screen panel from the frame
		RPGGameTest.frame.repaint(); //refreshes the frame
		removeKeyListener(RPGGameTest.frame); //disallow the frame's ability to listen to keys being pressed

		//add room 1
		addKeyListener(room1); //allows room 1 to be able to listen to keys being pressed
		add(room1); //add the room 1 panel to the frame
		
		//change music
		Loader.audioLoader("STOP", ""); //stop the audio being played for the intro
		playSound("enemy rooms", "sounds/music/room.wav"); //play the audio for the rooms
		
		//make the frame visible
		setVisible(true);

	}
	
	/**
	 * this method is called when the protagonist touches a message.
	 * 
	 * @param the location of the paper object in the array
	 * 
	 */
	public static void displayMessage(int indexOfMessage) {

		JOptionPane.showMessageDialog(RPGGameTest.frame, RPGGameTest.paper[indexOfMessage].getMessage()); //display the message in the 
																										 //paper object

	}
	
	/**
	 * this method is called when the protagonist touches an open door
	 */
	public void goThroughDoor() { 

		playSound("STOP", ""); //stop the room music

		System.out.println("Room: " + IntroScreen.roomNumber);
		
		if (IntroScreen.roomNumber==1) { //if the current room is 1
	
			switchRooms(1, 2); //call the method that handles switch rooms, 1 is the current room and 2 is the next
			
		}
		else if (IntroScreen.roomNumber==2) { //if the current room is 2
			
			switchRooms(2, 3); //call the method that handles switch rooms, 2 is the current room and 3 is the next
			
		}
		else if (IntroScreen.roomNumber==3) { //if the current room is 3
			
			switchRooms(3, 4); //call the method that handles switch rooms, 3 is the current room and 4 is the next
			
		}

	}

	/**
	 * this method handles room switching
	 * 
	 * @param the room the protagonist is currently in
	 * @param the room the protagonist will go to
	 */
	public void switchRooms(int currentRoom, int nextRoom) {

		if (nextRoom==2) { //if the room the protagonist will go to is room 2
			
			//remove room 1
			removeKeyListener(room1); //stop listening for key presses in room 1
			RPGGameTest.frame.remove(room1); //remove the room 1 panel from the frame
			
			Loader.room2 = new Room("Room Maps/Room2.txt"); //start new instance of room 2
			
			//add room 2
			addKeyListener(Loader.room2); //start listening for key presses in room 2
			RPGGameTest.frame.add(Loader.room2); //add the room 2 panel to the frame

		}
		else if (nextRoom==3) { //if the room the protagonist will go to is room 3
			
			//remove room 2
			removeKeyListener(Loader.room2); //stop listening for key presses in room 2
			RPGGameTest.frame.remove(Loader.room2); //remove the room 2 panel from the frame

			Loader.room3 = new Room("Room Maps/Room3.txt"); //start new instance of room 3
			
			//add room 3
			addKeyListener(Loader.room3); //start listening for key presses in room 3
			RPGGameTest.frame.add(Loader.room3); //add the room 3 panel to the frame
			
		}
		else { //if the room the protagonist will go to is room 4
			
			//remove room 3
			removeKeyListener(Loader.room3); //stop listening for key presses in room 3
			RPGGameTest.frame.remove(Loader.room3); //remove the room 3 panel from the frame

			Loader.room4 = new Room("Room Maps/Room4.txt"); //start new instance of room 4
			
			//add room 4
			addKeyListener(Loader.room4); //start listening for key presses in room 4
			RPGGameTest.frame.add(Loader.room4); //add the room 4 panel to the frame
			
			Loader.boss = new FinalBoss(); //start new instance of the boss

		}

		RPGGameTest.frame.revalidate(); //reset the layout manager of the frame as new component are added
		RPGGameTest.frame.repaint(); //refresh the frame

		playSound("enemy rooms", "sounds/music/room.wav"); //play the room music again

	}
	
	/**
	 * this method plays and stops sound for the intro and the rooms
	 * 
	 * @param name of sound
	 * @param location of audio file
	 */
	static void playSound(String name, String destination) {
		
		if (name.equals("STOP")) { //stop the music if "STOP" is sent to this method
			
			clip.stop();
			
		}
		
		else { //if "STOP" is not sent
			
			try {
				
				//Define file
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(destination).getAbsoluteFile());
				//Get clip
				clip = AudioSystem.getClip();
				//Open clip in audio input stream
				clip.open(audioInputStream);
				//Start clip
				clip.start();

			} catch(Exception ex) { //if error occurs
				
				System.out.println(ex); //print error
				
			}
			
		}
		
	}


}
