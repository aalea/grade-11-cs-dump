package testing;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the class that acts as a container for all the components in the room
 * 
 * @author Aalea Ally
 *
 */
public class Room extends JPanel implements KeyListener, ActionListener {

	/**
	 * These images will load into the data received from the Loader class
	 */
	private static ImageIcon WALL = new ImageIcon("sprites/wall.png");
	public static ImageIcon FLOOR = new ImageIcon("sprites/floor.png");
	public static ImageIcon PAPER = new ImageIcon("sprites/paper.PNG");
	public static ImageIcon DOOR = new ImageIcon("sprites/Black.bmp");
	public static ImageIcon LOCKED_DOOR = new ImageIcon("sprites/lockedDoor.png");

	/**
	 * array to hold the game room characters from the text file
	 */
	char[][] room = new char[60][60];
	
	/**
	 * placeholder for the audio being played
	 */
	static Clip clip;

	/**
	 * array to hold the game room images
	 */
	JLabel[][] cell = new JLabel[60][60]; 

	/**
	 * Array of Enemy objects
	 */
	ArrayList <Enemy> enemies = new ArrayList<Enemy>();

	/**
	 * panel to hold protagonist and enemies
	 */
	JPanel characterPanel = new JPanel();
	
	/**
	 * timer that syncs with enemy movement timer
	 */
	Timer enemyTimer = new Timer(250, this);

	public Room(String map) {
		
		//1. Set bounds, layout
		setLayout(new GridLayout(60, 60)); //sets a 60 by 60 game board, but only the first 11 rows will be visible
		setBounds(0, 0, 1050, 1050); //sets a 1050 by 1050, but the frame will only show 1050 by 270
		setBackground(Color.black); //set the background to black

		//2. Implement visual components

		//resize ImageIcons

		//wall
		Image image = WALL.getImage(); //converts the imageicon to image
		Image fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scales the image
		WALL = new ImageIcon(fixedImage); //converts the scaled image back to an image icon

		//floor
		image = FLOOR.getImage(); //converts the imageicon to image
		fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scales the image
		FLOOR = new ImageIcon(fixedImage); //converts the scaled image back to an image icon

		//paper
		image = PAPER.getImage(); //converts the imageicon to image
		fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scales the image
		PAPER = new ImageIcon(fixedImage); //converts the scaled image back to an image icon

		//door
		image = DOOR.getImage(); //converts the imageicon to image
		fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scales the image
		DOOR = new ImageIcon(fixedImage); //converts the scaled image back to an image icon

		int r = 0; //initialize row iterator for when reading the room map

		Scanner mapInput; //create the scanner that receives input from the room map

		try {

			mapInput = new Scanner(new File(map)); //opens room map in scanner

			//Cycle through all rows 0 to 11 in the maze file reading one row at a time
			while(r < 11) {

				//2.2.1. Read the next line from the room file 
				room[r] = mapInput.nextLine().toCharArray();

				//2.2.2. For each row cycle through all the cells
				for (int c = 0; c < room[r].length; c++) {

					assignRoomIcons(c, r); //assign the current cell to it's icon derived from the room map

				}

				r++; //increment the row iterator

			}
			while(r < 60) { //set the rows that shouldn't be visible to plain black

				room[r] = mapInput.nextLine().toCharArray(); //read the next line in the room file
				
				//For each row cycle through all the cells
				for (int c = 0; c < room[r].length; c++) {

					cell[r][c]=new JLabel(); //create a new label for that location
					cell[r][c].setIcon(DOOR); //set label to a plain black icon
					add(cell[r][c]); //add that label to the room

				}
				
				r++; //increment the row iterator
				
			}

			IntroScreen.roomNumber++; //signifies the change in room, the intro screen is 0, room 1 is 1, etc

			mapInput.close(); //close the scanner for the room maps as the process is finished

		}
		catch (FileNotFoundException e){ //if the file cannot be found

			System.out.println("File not found");

		}
		
		//set the protagonist's position
		
		//appearance in room
		cell[5][30].setIcon(IntroScreen.protagonist.getIcon()); //set the cell icon the protagonist's position to the protagonist's icon
		
		//set the protagonist's row attribute to 5
		IntroScreen.protagonist.setRow(5);

		//set the protagonist's column attribute to 30	
		IntroScreen.protagonist.setColumn(30);

		//set the starting direction of the protagonist
		IntroScreen.protagonist.setDirection(2); //start right

		//make the protagonist visible
		IntroScreen.protagonist.setVisible(true);

		setVisible(true); //make the room visible
		
		enemyLoader(); //call the method that handles loading the enemies

	}
	

	/**
	 * this method handles all the enemies in the room, depending on the room number
	 */
	private void enemyLoader() {
		
		int enemyAmount; //holds number of enemies in current room
		
		if (IntroScreen.roomNumber==1) { //if this is room 1
			
			enemyAmount = 2; //there will be two enemies in room 1
			
			//adding enemies in room 1

			enemies.add(0, new Enemy(0, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(0).setRow(5);
			enemies.get(0).setColumn(6);
			
			System.out.println("Enemy 0 added");
			
			enemies.add(1, new Enemy(1, "Thinker")); //add a new enemy that is a thinker to the enemy arraylist
			enemies.get(1).setRow(5);
			enemies.get(1).setColumn(53);
			
			System.out.println("Enemy 1 added");

		}
		else if (IntroScreen.roomNumber==2) { //if this is room 2

			enemyAmount = 6; //there will be six enemies in room 2
			
			//adding enemies in room 2

			enemies.add(0, new Enemy(0, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(0).setRow(5);
			enemies.get(0).setColumn(6);
			
			System.out.println("Enemy 0 added");
			
			enemies.add(1, new Enemy(1, "Follower")); //add a new enemy that is a follower to the enemy arraylist
			enemies.get(1).setRow(2);
			enemies.get(1).setColumn(2);
			
			System.out.println("Enemy 1 added");
			
			enemies.add(1, new Enemy(2, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(1).setRow(5);
			enemies.get(1).setColumn(56);
			
			System.out.println("Enemy 2 added");
			
			enemies.add(1, new Enemy(3, "Thinker")); //add a new enemy that is a thinker to the enemy arraylist
			enemies.get(1).setRow(5);
			enemies.get(1).setColumn(53);
			
			System.out.println("Enemy 3 added");
			
			enemies.add(1, new Enemy(4, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(1).setRow(5);
			enemies.get(1).setColumn(54);
			
			System.out.println("Enemy 4 added");
			
			enemies.add(1, new Enemy(5, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(1).setRow(3);
			enemies.get(1).setColumn(6);
			
			System.out.println("Enemy 5 added");
			
		}
		else if (IntroScreen.roomNumber==3) { //if this is room 3

			enemies.add(0, new Enemy(0, "Friend")); //add a new enemy that is a friend to the enemy arraylist
			enemies.get(0).setRow(5);
			enemies.get(0).setColumn(55);
			
			System.out.println("Enemy 0 added"); 
			
			enemies.add(1, new Enemy(1, "Thinker")); //add a new enemy that is a thinker to the enemy arraylist
			enemies.get(1).setRow(5);
			enemies.get(1).setColumn(53);
			
			System.out.println("Enemy 1 added");
			
			enemies.add(2, new Enemy(2, "Follower")); //add a new enemy that is a follower to the enemy arraylist
			enemies.get(2).setRow(2);
			enemies.get(2).setColumn(2);
			
			System.out.println("Enemy 2 added");
			
			
		}
		else { //if in room 4
		      //(final boss room)
			enemyTimer.stop(); //stop the timer that updates the position of the enemies, as there will be none in room 4
		}
		
		//iterate through the enemies in the arraylist
		for (Enemy e:  enemies) {
			
			e.getEnemyMovement().start(); //start the movement animation timer for each enemy

		}

		System.out.println("timer started");
		enemyTimer.start(); //start the timer that updates the enemies' positions in the room

	}
	
	/**
	 * this method is called in the constructor to assign each cell in the room
	 *
	 * @param current column in loop, current row in loop
	 */
	public void assignRoomIcons(int c, int r) {

		cell[r][c]=new JLabel(); //create new label for each cell

		if (room[r][c] == 'W') { //if W is read from the room map

			cell[r][c].setIcon(WALL); //set the current cell to the wall sprite

		}
		else if (room[r][c] == 'L') { //if L is read from the room map

			cell[r][c].setIcon(LOCKED_DOOR); //set the current cell to the locked door sprite

		}
		else if (room[r][c] == 'O') { //if O is read from the room map

			cell[r][c].setIcon(FLOOR); //set the current cell to the floor sprite

		}

		else if (room[r][c] =='D') { //if D is read from the room map

			cell[r][c].setIcon(DOOR); //set the current cell to the door sprite

		}
		else if (room[r][c] =='M') { //if M is read from the room map

			cell[r][c].setIcon(PAPER); //set the current cell to the wall sprite

		}
		else if (room[r][c] =='B') { //if B is read from the room map
			
			cell[r][c].setIcon(IntroScreen.boss.IMAGE[0][0]); //set the current cell to the boss sprite
	
			//set the position of the boss
			IntroScreen.boss.setRow(r);
			IntroScreen.boss.setColumn(c);

		}
		
		cell[r][c].setVisible(true); //make the current cell visible
		add(cell[r][c]); //add the current cell to the panel

	}
	
	/**
	 * this method is called when the user hits a key in the room
	 */
	public void keyPressed(KeyEvent key) {
		
		//1.	Track direction based on the key pressed
		//		- 37 since ASCII codes for cursor keys 
		//		start at 37
		int direction = key.getKeyCode()-37;

		//2.	Change direction of protagonist;
		//		37-left, 38-up, 39-right,40-down

		//if there is no wall or locked door in the way of the direction the user wants to move
		if (direction==0 && room[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()-1] != 'W'
				&& room[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()-1] != 'L') {

			IntroScreen.protagonist.setDirection(0); //set the direction to left
			IntroScreen.protagonist.setIcon(IntroScreen.protagonist.IMAGE[0][1]);
			performMove(IntroScreen.protagonist); //move the protagonist by calling the method that handles movement

		}
		//if there is no wall or locked door in the way of the direction the user wants to move
		else if (direction==1 && room[IntroScreen.protagonist.getRow()-1][IntroScreen.protagonist.getColumn()] != 'W'
				&& room[IntroScreen.protagonist.getRow()-1][IntroScreen.protagonist.getColumn()] != 'L') {

			IntroScreen.protagonist.setDirection(1); //set the direction to up
			performMove(IntroScreen.protagonist); //move the protagonist by calling the method that handles movement

		}
		//if there is no wall or locked door in the way of the direction the user wants to move
		else if (direction==2 && room[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()+1] != 'W'
				&& room[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()+1] != 'L') {

			IntroScreen.protagonist.setDirection(2); //set the direction to right
			IntroScreen.protagonist.setIcon(IntroScreen.protagonist.IMAGE[2][0]);
			performMove(IntroScreen.protagonist); //move the protagonist by calling the method that handles movement
		
		}
		//if there is no wall or locked door in the way of the direction the user wants to move
		else if (direction==3 && room[IntroScreen.protagonist.getRow()+1][IntroScreen.protagonist.getColumn()] != 'W'
				&& room[IntroScreen.protagonist.getRow()+1][IntroScreen.protagonist.getColumn()] != 'L') {

			IntroScreen.protagonist.setDirection(3); //set the direction to down
			performMove(IntroScreen.protagonist); //move the protagonist by calling the method that handles movement
			
		}
		
	}
	
	/**
	 * this method handles all movement of movers in the room, with different options 
	 * depending on if the mover is the protagonist, or an enemy
	 *
	 * @param the mover that will be moved
	 */
	public void performMove(Mover mover) {

		//If there is no wall in the direction that 
		//	 the Mover object wants to go then
		if (room[mover.getRow()][mover.getColumn()] != 'W' && room[mover.getRow()][mover.getColumn()] != 'L') { //

			//If the Mover object is the protagonist and the next cell is the floor
			if (mover==IntroScreen.protagonist && room[mover.getNextRow()][mover.getNextColumn()]=='O') {
				
				cell[mover.getRow()][mover.getColumn()].setIcon(FLOOR); //sets the previous space the protagonist was in to the floor
				IntroScreen.protagonist.move(); //change the position of the protagonist
				cell[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()].setIcon(IntroScreen.protagonist.getIcon()); //move the protagonist's icon to its new position
			}	
			//Check if in the same position as one of the sheets of paper					
			if (mover==IntroScreen.protagonist && room[mover.getNextRow()][mover.getNextColumn()]=='M'){
				
				cell[mover.getRow()][mover.getColumn()].setIcon(FLOOR); //makes it seem as if the paper was discarded
				IntroScreen.protagonist.move(); //change the position of the protagonist
				
				//display the message on the paper
				
				int index = 0; //the position in the paper object array

				for (Paper p : RPGGameTest.paper) { //iterate through the paper objects
		
					int[] position = p.getPosition(); //holds the position attributes of the current paper object
		
					if (position[0] == IntroScreen.roomNumber) { //if paper object in same room 
		
						if (position[1] - 1 == IntroScreen.protagonist.getColumn()) { //if paper object has same x position
					
							if (position[2] - 1 == IntroScreen.protagonist.getRow()) { //if paper object has same y position
		
								playSound("paper", "sounds/paper.wav"); //play an opening paper sound effect
								FrameSetup.displayMessage(index); //call the method that handles displaying messages, with the message's index in the arguments
								
							}
					
						}
						
					}
					
					index++; //increase the message being checked's index by 1
					
				}
				
				cell[IntroScreen.protagonist.getRow()][IntroScreen.protagonist.getColumn()].setIcon(IntroScreen.protagonist.getIcon()); //reset the protagonist's icon to it's current position
			}
			else if (mover==IntroScreen.protagonist && room[mover.getNextRow()][mover.getNextColumn()]=='D') { //if the protagonist touched a door
				
				//these try/catch blocks are used because depending on the room, music is played from a different class.
				//If a class is asked to stop playing music if it isn't playing music, then a NullPointerException 
				//will occur and stop the program
				try {
					FrameSetup.playSound("STOP", ""); //stop room music if being played from framesetup class
				}
				catch (java.lang.NullPointerException ex) {
					System.out.println(ex);
				}
				try {
					playSound("STOP", ""); //stop room music if being played from this class
				}
				catch (java.lang.NullPointerException ex) {
					System.out.println(ex);
				}
				
				RPGGameTest.frame.goThroughDoor(); //calls the method that handles the event of going through a door
				
			}
			else if (room[mover.getNextRow()][mover.getNextColumn()]=='B' ||
					room[mover.getRow()][mover.getColumn()]=='B'&& 
					mover==IntroScreen.protagonist &&
					Battle.gameState==0 && FinalBossBattle.gameState==0) { //if the protagonist touched the boss and is not already in a battle
				
				System.out.println("Enemy ahead");
				
				playSound("STOP", ""); //stop room music
				playSound("boss battle", "sounds/music/finalBossBattle.wav"); //play boss battle music
				
				enemyTimer.stop(); //stop the timer that updates the enemies' positions

				new FinalBossBattle(IntroScreen.protagonist, null, IntroScreen.boss); //start the boss battle
				
			}
			
				
			cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon()); //reset icon position to current position of character
			
			
			//Check if protagonist touched an enemy
			
			for (Enemy e : enemies) { //iterate through all enemies

				if (e.getRow()==IntroScreen.protagonist.getNextRow() && 
						e.getColumn()==IntroScreen.protagonist.getNextColumn() ||
						e.getRow()==IntroScreen.protagonist.getRow() && 
						e.getColumn()==IntroScreen.protagonist.getColumn() && 
						Battle.gameState==0 && 
						e.getEnemyType().equals("Dead")==false){ //if protagonist touched an enemy and not already in battle
					
					//change the state of the game to protagonist's turn in battle
					Battle.gameState = 1;
					
					System.out.println("Enemy ahead");
					
					//these try/catch blocks are used because depending on the room, music is played from a different class.
					//If a class is asked to stop playing music if it isn't playing music, then a NullPointerException 
					//will occur and stop the program
					try {
						FrameSetup.playSound("STOP", ""); //stop room music if being played from framesetup class
					}
					catch (java.lang.NullPointerException ex) {
						System.out.println(ex);
					}
					try {
						playSound("STOP", ""); //stop room music if being played from this class
					}
					catch (java.lang.NullPointerException ex) {
						System.out.println(ex);
					}
					
					playSound("battle", "sounds/music/battle.wav"); //play battle music
		
					enemyTimer.stop(); //pause the timer that updates the enemies' positions in the room
					e.getEnemyMovement().stop(); //pause the enemy movement of the current enemy
					
					IntroScreen.protagonist.setWon(false); //the protagonist didn't win the battle yet
					new Battle(IntroScreen.protagonist, e, null); //starts a new battle
					
					if (IntroScreen.protagonist.isWon()==true) { //if the protagonist beat the enemy
						
						enemies.set(e.name, new Enemy(e.name, "Dead")); //replace enemy with dead enemy

						cell[e.getRow()][e.getColumn()].setIcon(FLOOR); //make the enemy disappear
	
						playSound("STOP", ""); //stop the battle music
						playSound("enemy rooms", "sounds/music/room.wav"); //play back room music
						
						//set the position so that the protagonist can't touch the dead enemy
						e.setRow(0);
						e.setColumn(0);
						
						enemyTimer.start(); //start updating enemy movement again
						e.getEnemyMovement().stop(); //stop the enemy's movement since it's dead

					}
					else if (Battle.gameState==0){ //if the protagonist ran away
						
						playSound("STOP", ""); //stop battle music
						playSound("enemy rooms", "sounds/music/room.wav"); //play back room music
						
						//resets the protagonist's location so they don't get caught in battle immediately after they exit
						IntroScreen.protagonist.setRow(5); 
						IntroScreen.protagonist.setColumn(30);
						cell[5][30].setIcon(IntroScreen.protagonist.getIcon());
						
						enemyTimer.start(); //start updating enemy movement again
						e.getEnemyMovement().start(); //turn the movement of the enemy back on
						
						
					}
					
				}
				
			}
			
		}
	}
	
	/**
	 * this method is called, when the timer that signifies when the enemy locations should be updated, sends a signal
	 */
	@Override
	public void actionPerformed(ActionEvent b) {

		if (b.getSource()==enemyTimer) { //if the action event was the enemy timer

			for (Enemy e: enemies) { //iterate through the enemy arraylist
				
				if (e.getPreviousRow() > 0 && e.getPreviousColumn() > 0) { //if they moved
					
					cell[e.getPreviousRow()][e.getPreviousColumn()].setIcon(FLOOR);	//set their last location to the floor 
																				   //so their movement doesn't leave a trail 															  //of enemy sprites

				}
				
				performMove(e); //move the enemy

			}

		}
		
	}
	
	/**
	 * this method is called when there is a request to play or stop sound
	 */
	static void playSound(String name, String destination) {

		if (name.equals("STOP")) { //if STOP is sent to this method

			clip.stop(); //stop the sound

		}
		else {
            //attempt to play audio file
			try {

			    //Define file
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(destination).getAbsoluteFile());
			    //Get clip
				clip = AudioSystem.getClip();
			    //Open clip in audio input stream
				clip.open(audioInputStream);
			    //Start clip
				clip.start();

			} catch(Exception ex) { //if the audio could not be played for any reason
                
				System.out.println(ex); //print the erro that occur
                
			}
            
		}
        
	}
	
	/**
	 * mandatory method when implementing key listener. Not used.
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * mandatory method when implementing key listener. Not used.
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
}



