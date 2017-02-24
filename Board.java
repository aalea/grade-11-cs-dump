package boring;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represent the game board and includes methods to handle keyboard
 * events and game actions
 * 
 * @author Aalea Ally
 *
 */
public class Board extends JPanel implements KeyListener, ActionListener {

	/**
	 * Timer for game movement
	 */
	public Timer gameTimer = new Timer(250, this);
	
	/**
	 * Timer for PacMan animation
	 */
	private Timer animateTimer = new Timer(50, this);
	
	/**
	 * Timer to determine cherry and 1-Up appearance
	 */
	private Timer itemTimer = new Timer (10000, this);
	
	/**
	 * ImageIcon constant for wall
	 */
	
		private static ImageIcon WALL = new ImageIcon("images/StdWall.bmp");
	
	/**
	 * ImageIcon constant for food
	 */
	private static ImageIcon FOOD = new ImageIcon("images/StdFood.bmp");
	
	/**
	 * ImageIcon constant for blank
	 */
	private static final ImageIcon BLANK = new ImageIcon("images/Black.bmp");
	
	/**
	 * ImageIcon constant for door
	 */
	private static final ImageIcon DOOR = new ImageIcon("images/Black.bmp");
	
	/**
	 * ImageIcon constant for skull
	 */
	private static final ImageIcon SKULL = new ImageIcon("images/Skull.bmp");
	
	/**
	 * ImageIcon constant for cherry
	 */
	private static final ImageIcon CHERRY = new ImageIcon("images/Cherry.bmp");
	
	/**
	 * ImageIcon constant for 1up
	 */
	private static final ImageIcon ONEUP = new ImageIcon("images/1up.bmp");
	
	private static boolean blueMode = false;
	
	Timer blueModeTimer = new Timer(100000, this);
	
	/**
	 * array to hold the game board characters from the text file
	 */
	private char[][] maze = new char[25][27];
	
	/**
	 * array to hold the game board images
	 */
	private JLabel[][] cell = new JLabel[25][27]; //parallel array
	
	/**
	 * PacMan object
	 */
	private PacMan pacMan;
	
	/**
	 * Array of Ghost objects
	 */
	private Ghost[] ghost = new Ghost[3];
	
	/**
	 * track amount of food on board
	 */
	private int pellets=0;
	
	/**
	 * steps for animating PacMan's chomp
	 */
	private int pStep;
	
	/**
	 * Construct the game board including the layout, background Pacman and ghosts
	 * and calls the loadBoard method
	 */
	public Board() {
		
		//1. Set the layout (grid), background (black)
		setLayout(new GridLayout(25,27));
		setBackground(Color.BLACK);
		
		//2. Create PacMan and the ghosts 
		pacMan = new PacMan();
		
		ghost[0] = new Ghost(0);
		ghost[1] = new Ghost(1);
		ghost[2] = new Ghost(2);
		
		//Start music
		playSound("sounds/pacmanintro (better quality).wav");
		//3. Load the maze
		loadBoard("maze3.txt");
	}
	
	/**
	 * Loads the maze onto the screen from a text file
	 */
	private void loadBoard(String level) {
		
		if (IntroScreen.chosenTheme.equals("Classic")) {
			WALL = new ImageIcon("images/ClassicWall.bmp");
			FOOD = new ImageIcon("images/StdFood.bmp");
		}
		else if (IntroScreen.chosenTheme.equals("Ms. PacMan")) {
			WALL = new ImageIcon("images/MsStdWall.bmp");
			FOOD = new ImageIcon("images/StdFood.bmp");
		}
		else if (IntroScreen.chosenTheme.equals("Christmas")) {
			WALL = new ImageIcon("images/ChristmasWall.bmp");
			FOOD = new ImageIcon("images/StdFood.bmp");
		}
		else if (IntroScreen.chosenTheme.equals("Extreme 80s")) {
			WALL = new ImageIcon("images/80sWall.bmp");
			FOOD = new ImageIcon("images/80sFood.bmp");
		}
		else {
			WALL = new ImageIcon("images/StdWall.bmp");
			FOOD = new ImageIcon("images/StdFood.bmp");
		}
		
		setVisible(true);
		
		//keeps track of the row number
		int r = 0;
		
		//1. Open the maze text file for input
		Scanner input;
		
		try {
			input = new Scanner(new File(level));
			
			//2. Cycle through all the rows in the maze file reading one row at a time
			while(input.hasNext()) {
				
				//2.1 Read the next line from the maze file
				maze[r] = input.nextLine().toCharArray(); //can you rip apart into char array?
				
				//2.2 For each row cycle through all the columns
				for (int c = 0; c < maze[r].length; c++) {
					
					cell[r][c]=new JLabel(); //create new picture for each cell
					
					if (maze[r][c] == 'W') {
						cell[r][c].setIcon(WALL);
					}
					else if (maze[r][c] == 'F') {
						cell[r][c].setIcon(FOOD);
						pellets++;
					}
					else if (maze[r][c] == 'P') {
						cell[r][c].setIcon(pacMan.getIcon());
						pacMan.setRow(r);
						pacMan.setColumn(c);
						pacMan.setDirection(0); //start left
					}
					else if (maze[r][c]=='0' || maze[r][c]=='1' || maze[r][c]=='2') {
						
						int gNum = Character.getNumericValue(maze[r][c]);
						//int gNum = (int)(maze[r][c])-48; //or use ASCII conversion
						
						cell[r][c].setIcon(ghost[gNum].getIcon());
						ghost[gNum].setRow(r);
						ghost[gNum].setColumn(c);
					
					}
					else if (maze[r][c] =='D') {
						cell[r][c].setIcon(DOOR);
					}
					
					add(cell[r][c]);
				}
				
				r++;
				
			}
			input.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			
		}
		
	}

	/**
	 * Plays the sound file sent when method is called. Only plays WAV files
	 * 
	 * @param audioFile
	 */
	public void playSound(String audioFile) {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        //while (clip.isRunning()==true) {
	        	if (audioFile.equals("sounds/blueMode.wav") && 
	        		blueModeTimer.isRunning() == false) {
	        		clip.stop();
	        	}
	        //}
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	/**
	 * Handles keyboard entries - to start the game and control PacMan's movements
	 */
	public void keyPressed(KeyEvent key) {
		System.out.println("YO");
		//1.	If the game isn't running and pacMan is alive then 
		//		start the game timer
		if (gameTimer.isRunning()==false && pacMan.isDead()==false)
			gameTimer.start();
		//2.	If PacMan is still alive and the game is not over then
		//loads next level if pacman isn't dead and all pellets are gone
		if (pacMan.isDead()==false && pellets <= 0) {
			loadBoard("maze2.txt");
		}
		else if (pacMan.isDead()==false) {
			
			//2.1	Track direction based on the key pressed
			//		- 37 since ASCII codes for cursor keys 
			//		start at 37
			int direction = key.getKeyCode()-37;
			
			//2.2	Change direction of PacMan;
			//		37-left, 38-up, 39-right,40-down
			if (direction==0 && maze[pacMan.getRow()][pacMan.getColumn()-1] != 'W')
				pacMan.setDirection(0);
			else if (direction==1 && maze[pacMan.getRow()-1][pacMan.getColumn()] != 'W')
				pacMan.setDirection(1);
			else if (direction==2 && maze[pacMan.getRow()][pacMan.getColumn()+1] != 'W')
				pacMan.setDirection(2);
			else if (direction==3 && maze[pacMan.getRow()+1][pacMan.getColumn()] != 'W')
				pacMan.setDirection(3);
		}
	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyReleased(KeyEvent key) {
		// Not used
		
	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyTyped(KeyEvent key) {
		// Not used
		
	}
	
	/**
	 * Allows an object to move and updates both on the maze and screen based on:
	 * the object, direction, and change in row and column
	 * 
	 * @param mover either PacMan or a Ghost
	 * 
	 */
	private void performMove(Mover mover) {
		
		//1. If a mover is at a door then teleport to other side
		if (mover.getColumn()==1) {
			mover.setColumn(24);
			cell[12][1].setIcon(DOOR);
		}
		else if (mover.getColumn()==25) {
			mover.setColumn(2);
			cell[12][25].setIcon(DOOR);
		}
		else if (maze[mover.getRow()][mover.getColumn()]=='O') { //invisible wall for ghost's home
			mover.setDirection(1);
		}
		
		else if (mover==pacMan && maze[mover.getRow()][mover.getColumn()]=='U') {
			statusBoard.setScore(statusBoard.getScore() + 50);
			statusBoard.setPacmanLives(statusBoard.getPacmanLives() + 1);
		}
		
		//2. If there is no wall in the direction that 
		//	 the Mover object wants to go then
		if (maze[mover.getNextRow()][mover.getNextColumn()] != 'W') {
			
			//2.1 If the Mover object is PacMan then animate a chomp
			if (mover==pacMan && maze[mover.getNextRow()][mover.getNextColumn()] != 'W')
				animateTimer.start();//
			//2.2 Otherwise the Mover is a ghost
			else {
				
				//2.2.1. If the cell where the Ghost is has food then reset the food
				if (maze[mover.getRow()][mover.getColumn()]=='F')
					cell[mover.getRow()][mover.getColumn()].setIcon(FOOD);
				//2.2.2. Otherwise reset the cell to blank
				else 
					cell[mover.getRow()][mover.getColumn()].setIcon(BLANK);
				
				//2.2.3. Move the ghost's position
				mover.move();
				
				//2.2.4 If a collison has occured then death occurs
				if (collided()==true && blueMode==false)
					death();
				else if (collided()==true && blueMode==true) {
					System.out.println("BLU DABADEE");
					blueMode();
				}
				//2.2.5. Otherwise update the picture on the screen
				else
					cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());
			
			}
		}
	}
	
	private void blueMode() {
		System.out.println("BLU DABADEE");
				if (blueModeCollided()==0) {
					statusBoard.setScore(statusBoard.getScore() + 100);
					ghost[0].setRow(12);
					ghost[0].setRow(12);
				}
				else if (blueModeCollided()==1) {
					statusBoard.setScore(statusBoard.getScore() + 100);
					ghost[1].setRow(12);
					ghost[1].setRow(12);
				}
				else if (blueModeCollided()==2) {
					statusBoard.setScore(statusBoard.getScore() + 100);
					ghost[2].setRow(12);
					ghost[2].setRow(12);
				}
				
		
	}
	/**
	 * Determines if PacMan has collided with a GHost in blue mode
	 * 
	 * @return collided
	 */
	private int blueModeCollided() {
		System.out.println("BLU DABADEE");
		if (ghost[0].getRow()==pacMan.getRow() && ghost[0].getColumn()==pacMan.getColumn())
			return 0;
		else if (ghost[1].getRow()==pacMan.getRow() && ghost[1].getColumn()==pacMan.getColumn())
			return 1;
		else if (ghost[2].getRow()==pacMan.getRow() && ghost[2].getColumn()==pacMan.getColumn())
			return 2;
		else
			return -1;
		
	}

	
	
	/**
	 * Determines if PacMan has collided with a GHost
	 * 
	 * @return collided
	 */
	private boolean collided() {
		
		//1. Cycle through all the ghost to see if anyone has caught PacMan
		for (Ghost g: ghost) { //Enhanced for loop
		//for (int g = 0; g < 3; g++) {
			
			//1.1. If the ghost is in the same location then return that a collison occured
			if (g.getRow()==pacMan.getRow() && g.getColumn()==pacMan.getColumn())
				return true;
		}
		
		//2. If no ghosts were in the same location then return that no collision occurred
		return false;
	}
	
	/**
	 * Stop the game when PacMan and a ghost 'collide'
	 */
	private void death() {
		
		//play sound
		playSound("sounds/killed.wav");
		
		//if all of his lives run out
		if (statusBoard.getPacmanLives()==0) {
			//1. Set pacMan dead
			pacMan.setDead(true);
		
			//2. Stop the game
			stopGame();
		
			//3. Determine the current location of PacMan on the screen
			//   and assign a picture of a skull
			cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);
			
			//calculates high scores then displays the high score board
			statusBoard.calculateHighScores();
			statusBoard.setupHighScoreTable();
		}
		else { 				//reset his location
			statusBoard.setPacmanLives(statusBoard.getPacmanLives() - 1);
			statusBoard.lifeLabel.setText("Lives: " + statusBoard.getPacmanLives());
			animateTimer.stop();
			gameTimer.stop();
			pacMan.setIcon(pacMan.getIcon());
			pacMan.setRow(15);
			pacMan.setColumn(13);
			pacMan.setDirection(0);
			
		}
		
	}
	
	/**
	 * Stops the game timer
	 */
	private void stopGame() {
		
		//1. If PacMan is dead or all the food is eaten then
		//	 stop the timers
		if (pacMan.isDead()) {
			animateTimer.stop();
			gameTimer.stop();
		}
	}
	
	/**
	 * Moves the ghosts in a random pattern, AI added here
	 */
	private void moveGhosts(){ 
		
		//1. Cycle through all the ghosts
		for (Ghost g: ghost) {
			
			int dir=0;
			
			if (IntroScreen.chosenMode.equals("Easy")) {
				//1.1. Keep selecting random directions to avoid
				//	   'back-tracking'
				do {
					dir = (int)(Math.random()*4);
				} while (Math.abs(g.getDirection() - dir) == 2 
						); 
			
				//Keeps generating numbers until the difference is not 2 & ghsot not in way
			}
			//if player chose normal mode
			else if (IntroScreen.chosenMode.equals("Normal")) {
				//if ghost is on same row as pacman
				if (pacMan.getRow()==g.getRow()) {
					//if ghost is to left of pacman
					if (pacMan.getColumn() > g.getColumn()) {
						dir = 2; //right
						g.setDirection(dir);
						//if wall in way
						if (maze[g.getRow()][g.getNextColumn()]=='W') 
							dir = 0; //left
							g.setDirection(dir);

					}
					//if ghost is to right of pacman
					else if (pacMan.getColumn() < g.getColumn()) {
						dir = 0; //left
						g.setDirection(dir);
						//if wall in way
						if (maze[g.getRow()][g.getNextColumn()]=='W') 
							dir = 2; //right
							g.setDirection(dir);
						
					}
				}
				//if ghost is on same column as pacman
				else if (pacMan.getColumn()==g.getColumn()) {
					//if ghost is above pacman
					if (pacMan.getRow() > g.getRow()) {
						dir = 3; //down
						g.setDirection(dir);
						//if wall in way
						if (maze[g.getNextRow()][g.getColumn()]=='W') 
							dir = 1; //up
							g.setDirection(dir);

					}
					//if ghost is below pacman
					else if (pacMan.getRow() < g.getRow()) 
						dir = 1; //up
						g.setDirection(dir);
						if (maze[g.getNextRow()][g.getColumn()]=='W') 
							dir = 3; //down
							g.setDirection(dir);
					
				}
				else {
					do {
						dir = (int)(Math.random()*4);
					} while (Math.abs(g.getDirection() - dir) == 2 
							);
				}
			}
			
			else if (IntroScreen.chosenMode.equals("Hard")) {
				//Corner PacMan
			
			}
			
				//1.2. Set the ghosts' direction
			
			
				g.setDirection(dir);

			
			//1.3. Move the ghost
			performMove(g);

		}
	}
	
	/**
	 * Determines the source of the action as either the game timer
	 * or the animation timer and then performs the corresponding actions
	 */
	public void actionPerformed(ActionEvent e) {
		//1. If the action is the game timer
		if (e.getSource()==gameTimer) {
			
			//1.1. Then move the PacMan and the ghosts
			if (maze[pacMan.getRow()][pacMan.getColumn()]=='C') {
				statusBoard.setScore(statusBoard.getScore() + 50);
				playSound("sounds/bluemode.wav");
				blueMode = true;
				blueModeTimer.restart();
			}
			else {
				
			}
			performMove(pacMan);
			moveGhosts();
		}
		//2. Otherwise, if the action is the animation timer
		else if (e.getSource()==animateTimer) {
			
			//2.1. Animate PacMan through the current step
			animatePacMan();
			
			//2.2. Increment the step number
			pStep++;
			
			//2.3. If the step is the last step then reset the step to 0
			if (pStep==3)
				pStep=0;
		}
		//else if (e.getSource()==itemTimer) {
		//	addItems();
		//}
		else if (e.getSource()==blueModeTimer) {
			System.out.println("BLU DABADEE ENDS");
			blueMode = false;
		}
			
		
	}
	
	private void addCherry() {
		
		int cherryRow = 0;
		int cherryColumn = 0;
		
		do {
			cherryRow = (int) (Math.random() * 25);
			cherryColumn = (int) (Math.random() * 27);
		} while (maze[cherryRow][cherryColumn] != 'F');
		
		maze[cherryRow][cherryColumn] = 'C';
		cell[cherryRow][cherryColumn].setIcon(CHERRY);
		
	}

	/**
	 * Animates PacMan in 3 steps: open mouth, draw black square, move and close mouth
	 */
	private void animatePacMan() {
		
		//1. If it is step 0 of animation
		if (pStep == 0) {
			
			//1.1. Open mouth in current cell
			cell[pacMan.getRow()][pacMan.getColumn()].setIcon
				(PacMan.IMAGE[pacMan.getDirection()][1]);
			
			playSound("sounds/pacchomp.wav");
			
			//1.2. Delay the animation timer
			animateTimer.setDelay(100);
		}
		//2. Otherwise if it is step 1 of snimation
		else if (pStep==1)
			
			//2.1. Blank the current cell
			cell[pacMan.getRow()][pacMan.getColumn()].setIcon(BLANK);
		
		//3. Otherwise if it is step 2 of animation
		else if (pStep == 2) {
			
			//3.1. Move pacMan
			pacMan.move();
			
			//3.2. If there is any food in the new square on 
			//	   the maze and the Mover is PacMAn then
			if (maze[pacMan.getRow()][pacMan.getColumn()]=='F') {
				
				//3.2.1 Increment the score
				increaseScore();
				pellets--;
				
				//3.2.2. Mark the maze at the new position to 'eaten'
				maze[pacMan.getRow()][pacMan.getColumn()]='E';
				
			}
			
			//3.3. Stop the animation timer
			animateTimer.stop();
			
			//3.4. If pacMan is dead then show a skull
			if (pacMan.isDead())
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(SKULL);
			
			//3.5. Otherwise show the appropriate closed pacMan
			//	   based on its direction
			else
				cell[pacMan.getRow()][pacMan.getColumn()].setIcon(PacMan.IMAGE[pacMan.getDirection()][0]);
		}
	}
	
	private void increaseScore() {
		//record the score in the program and display in console
		statusBoard.setScore(statusBoard.getScore() + 1);
		System.out.println("Score: " + statusBoard.getScore());
		
		//display the score on the panel
		statusBoard.scoreLabel.setText("Score: " + statusBoard.getScore());
		
		//add items if player certain amount of points
		if (statusBoard.getScore()==50)
			addCherry();
		else if (statusBoard.getScore()==1000)
			add1UpItem();
		
	}

	private void add1UpItem() {
		
		int oneUpRow = 0;
		int oneUpColumn = 0;
		
		do {
			oneUpRow = (int) (Math.random() * 26);
			oneUpColumn = (int) (Math.random() * 28);
		} while (maze[oneUpRow][oneUpColumn] != 'F');
		
		maze[oneUpRow][oneUpColumn] = 'U';
		cell[oneUpRow][oneUpColumn].setIcon(ONEUP);
		
	}

	public void restartButtonPressed() {

		
		PacManGUI.highScorePanel.setVisible(false);
		PacManGUI.board.setVisible(true);
		
		gameTimer.stop();
		animateTimer.stop();
		
		statusBoard.setScore(0);
		statusBoard.setPacmanLives(3);
		
		pacMan.setIcon(pacMan.getIcon());
		pacMan.setRow(15);
		pacMan.setColumn(13);
		pacMan.setDirection(0);
		
		ghost[0].setRow(12);
		ghost[0].setColumn(11);
		
		ghost[1].setRow(14);
		ghost[1].setColumn(13);
		
		ghost[2].setRow(15);
		ghost[2].setColumn(12);
		
	}
	

}
