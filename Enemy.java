package testing;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * This class handles all aspects of the enemy the player encounters in the
 * room and in battle.
 * 
 * @author Aalea Ally
 * 
 */
public class Enemy extends Mover implements ActionListener {

	/**
	 * number (essentially the name). Is the index of the object.
	 */
	public int name;

	/**
	 * when resizing sprites, this is the placeholder of the image that needs to be resized
	 */
	private Image image;

	/**
	 * when resizing sprites, this is the placeholder of the new resized image that needs to be reassigned
	 */
	private Image fixedImage;

	/**
	 * determines movement and importance of enemy
	 */
	private  String enemyType;

	/**
	 * determines the damage the enemy will inflict on 
	 * the protagonist during battle
	 */ 
	private  int enemyAttackPower;

	/**
	 * determines how much of the damage from the protagonist will be 
	 * decremented during the battle when attacked by the protagonist
	 */ 
	private  int enemyDefensePower;

	/**
	 * holds the position of the enemy when it's moving around the room
	 */ 
	public  int[] enemyPosition = new int[2];

	/**
	 * Controls movement of the enemy in the room. Sends a signal every 1 second.
	 */
	private Timer enemyMovement = new Timer(1000, this);

	/**
	 * the steps when moving enemy in it's pattern
	 */
	private int steps = 16;

	/**
	 * subsitute direction the enemy is going
	 */
	private int dir;

	/**
	 * creates an array of ImageIcons representing various states of the enemy
	 */
	public final static ImageIcon[][] IMAGE = {

			//0 - walking left 
			{ new ImageIcon("sprites/GoombaLeftStep1.PNG"), new ImageIcon("sprites/GoombaLeftStep2.PNG"), new ImageIcon("sprites/GoombaLeftStep3.PNG")},
			//1 - walking up
			{ new ImageIcon("sprites/GoombaUpStep1.PNG"), new ImageIcon("sprites/GoombaUpStep2.PNG"), new ImageIcon("sprites/GoombaUpStep3.PNG") },
			//2 - walking right
			{ new ImageIcon("sprites/GoombaRightStep1.PNG"), new ImageIcon("sprites/GoombaRightStep2.PNG"), new ImageIcon("sprites/GoombaRight3.PNG")},
			//3 - walking down
			{ new ImageIcon("sprites/GoombaDownStep1.PNG"), new ImageIcon("sprites/GoombaDownStep2.PNG"), new ImageIcon("sprites/GoombaDownStep3.PNG")},
			//4 - attack
			{ new ImageIcon("sprites/GoombaAttack1.PNG"), new ImageIcon("sprites/GoombaAttack2.PNG")},
			//5 - hurt
			{ new ImageIcon("sprites/GoombaHurt.PNG")},

	};

	/**
	 * Enemy constructor
	 * 
	 * @params index in array of enemies, enemy type
	 */
	public Enemy(int name, String enemyType) {
		System.out.println("in enemy constructor");
		//this is used in adding an enemy that just died from battle. A new enemy object is created to replace the old enemy
		//that does not cause another battle when the protagonist touches it.
		if (enemyType.equals("Dead")) {

			this.setHealthPoints(0);
			
			//make it so that the protagonist cannot touch the dead enemy
			this.setRow(0);
			this.setColumn(0);

			System.out.println("enemy removed");

		}
		else { //if the enemy is not dead

			//assign characteristics
			this.setName(name); //set the name of the enemy to the index received from the room class
			this.enemyType = enemyType; //set the type of the enemy to the type received from the room class
			this.setHealthPoints(12); //set the health points of the enemy

			//resize the standing down imageicon 
			image = IMAGE[3][1].getImage(); //convert imageicon to image 
			fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
			IMAGE[3][1] = new ImageIcon(fixedImage); //set the scaled image back to the imageicon

			this.setIcon(IMAGE[3][1]); //set enemy sprite on screen to standing down when game starts

			//rezise the standing left imageicon
			image = IMAGE[0][1].getImage(); //convert imageicon to image
			fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
			IMAGE[0][1] = new ImageIcon(fixedImage); //set the scaled image back to the imageicon

			//resize the moving up imageicon
			image = IMAGE[1][1].getImage(); //convert imageicon to image
			fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
			IMAGE[1][1] = new ImageIcon(fixedImage); //set the scaled image back to the imageicon

			image = IMAGE[2][1].getImage(); //convert imageicon to image
			fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
			IMAGE[2][1] = new ImageIcon(fixedImage); //set the scaled image back to the imageicon


		}

	}

	/**
	 * Manages the movement of the enemy in the room
	 * 
	 * Enemy types - Thinker, Follower, Friend
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//1. Check if enemy type is "Thinker" (paced square movement clockwise)
		if (enemyType.equals("Thinker")) {

			//1.1. Move enemy in a paced square movement clockwise

			//1.1.1. Move right 4 squares when ActionListener detects timer
			if (steps==16) {
				this.setDirection(2);

				this.move(); //one square per loop

			}
			if (steps==15) {
				this.setDirection(2);

				this.move(); //one square per loop
			}
			if (steps==14) {
				this.setDirection(2);

				this.move(); //one square per loop
			}
			if (steps==13) {
				this.setDirection(2);

				this.move(); //one square per loop
			}
			//1.1.2. Move down 4 squares when ActionListener detects timer
			if (steps==12) {
				this.setDirection(3);

				this.move(); //one square per loop
			}
			if (steps==11) {
				this.setDirection(3);

				this.move(); //one square per loop
			}
			if (steps==10) {
				this.setDirection(3);

				this.move(); //one square per loop
			}
			if (steps==9) {
				this.setDirection(3);

				this.move(); //one square per loop
			}
			//1.1.3. Move left 4 squares when ActionListener detects timer
			if (steps==8) {
				this.setDirection(0);

				this.move(); //one square per loop
			}	
			if (steps==7) {
				this.setDirection(0);

				this.move(); //one square per loop
			}
			if (steps==6) {
				this.setDirection(0);

				this.move(); //one square per loop
			}
			if (steps==5) {
				this.setDirection(0);

				this.move(); //one square per loop
			}	
			//1.1.4. Move up 4 squares when ActionListener detects timer
			if (steps==4) {
				this.setDirection(1);

				this.move(); //one square per loop
			}
			if (steps==3) {
				this.setDirection(1);

				this.move(); //one square per loop
			}
			if (steps==2) {
				this.setDirection(1);

				this.move(); //one square per loop
			}
			if (steps==1) {
				this.setDirection(1);

				this.move(); //one square per loop
			}

			steps--; //decrement step counter so next step can be made
			this.setIcon(IMAGE[this.getDirection()][1]); //reset the icon to match the direction
			
			if (steps < 1){ //when the step counter reaches 0
						   //reset the step counter
				steps = 16;
				
			}

		}
		//2. Check if enemy type is "Follower" (follows player slowly)
		else if (enemyType.equals("Follower")) {
			//2.1. Keep moving enemy towards player
			//if enemy is on same row as protagonist
			if (IntroScreen.protagonist.getRow()==this.getRow()) {
				//if enemy is to left of protagonist
				if (IntroScreen.protagonist.getColumn() > this.getColumn()) {
					
					dir = 2; //right
					this.setDirection(dir);
					
				}
				//if enemy is to right of protagonist
				else if (IntroScreen.protagonist.getColumn() < this.getColumn()) {
					
					dir = 0; //left
					this.setDirection(dir);
					this.setIcon(IMAGE[this.getDirection()][1]); //reset the icon to match the direction
					
				}
				
			}
			//if enemy is on same column as protagonist
			else if (IntroScreen.protagonist.getColumn()==this.getColumn()) {
				
				//if enemy is above protagonist
				if (IntroScreen.protagonist.getRow() > this.getRow()) {
					
					dir = 3; //down
					this.setDirection(dir);
					
				}
				//if enemy is below protagonist
				else if (IntroScreen.protagonist.getRow() < this.getRow()) {
					dir = 1; //up
					this.setDirection(dir);
				}
			}
			else { //if the enemy is not on the same row or column as protagonist
				
				do {
					
					dir = (int)(Math.random()*4); //pick a random direction
					
				} while (Math.abs(this.getDirection() - dir) == 2); //keep looping until it's not the opposite direction
				
			}
			
			this.setDirection(dir);
			
			if (IntroScreen.roomNumber==1 && 
					FrameSetup.room1.room[this.getNextRow()][this.getNextColumn()]!='W') { //if the room number is 1 and 
																						  //there is no wall in way
				this.move();
				
			}
			else if (IntroScreen.roomNumber==2 && 
					Loader.room2.room[this.getNextRow()][this.getNextColumn()]!='W') { //if the room number is 2 and 
				  																	  //there is no wall in way
				this.move();
				
			}
			else if (IntroScreen.roomNumber==3 && 
					Loader.room3.room[this.getNextRow()][this.getNextColumn()]!='W') { //if the room number is 3 and 
				  																	  //there is no wall in way
				this.move();
				
			}
			else { //if there is a wall in the way, set the enemy to move in the opposite direction
				
				if (this.getDirection()==0) { //if the wall is to the left
					
					this.setDirection(2); //set the direction to right
					this.move();
					
				}
				else if (this.getDirection()==1) { //if the wall is above
					
					this.setDirection(3); //set the direction to down
					this.move();
					
				}
				else if (this.getDirection()==2) { //if the wall is to the right
					
					this.setDirection(0); //set the direction to left
					this.move();
					
				}
				else if (this.getDirection()==3) { //if the wall is below
					
					this.setDirection(1); //set the direction to up
					this.move();
					
				}
			}

		}

		//3. Check if enemy type is "Friend" (paced square movement counterclockwise)
		else if (enemyType.equals("Friend")) {
			//3.2. Move enemy in a paced square movement counterclockwise
			//3.2.1. Move left 4 squares when ActionListener detects timer
			if (steps==16) {
				
				this.setDirection(0);
				
				this.move(); //moves one square per loop

			}
			if (steps==15) { 
				
				this.setDirection(0);

				this.move(); //moves one square per loop
			}
			if (steps==14) { 

				this.setDirection(0);

				this.move(); //moves one square per loop
			}
			if (steps==13) { 

				this.setDirection(0);

				this.move(); //moves one square per loop
			}
			//1.1.2. Move down 4 squares when ActionListener detects timer
			if (steps==12) { 

				this.setDirection(3);

				this.move(); //moves one square per loop
			}
			if (steps==11) { 

				this.setDirection(3);

				this.move(); //moves one square per loop
			}
			if (steps==10) { 

				this.setDirection(3);

				this.move(); //moves one square per loop
			}
			if (steps==9) { 

				this.setDirection(3);

				this.move(); //moves one square per loop
			}
			//1.1.3. Move right 4 squares when ActionListener detects timer
			if (steps==8) { 

				this.setDirection(2);

				this.move(); //moves one square per loop
			}	
			if (steps==7) { 

				this.setDirection(2);

				this.move(); //moves one square per loop
			}
			if (steps==6) {

				this.setDirection(2);

				this.move(); //moves one square per loop
			}
			if (steps==5) { 

				this.setDirection(2);

				this.move(); //moves one square per loop
			}	
			//1.1.4. Move up 4 squares when ActionListener detects timer
			if (steps==4) { 

				this.setDirection(1);

				this.move(); //moves one square per loop
			}
			if (steps==3) { 

				this.setDirection(1);

				this.move(); //moves one square per loop
			}
			if (steps==2) { 

				this.setDirection(1);

				this.move(); //moves one square per loop
			}
			if (steps==1) { 

				this.setDirection(1);

				this.move(); //moves one square per loop
			}

			steps--; //decrement the step counter so next step can be made
			this.setIcon(IMAGE[this.getDirection()][1]); //reset the icon to match the direction
			
			if (steps < 1){ //when the step counter reaches 0
				   		   //reset the step counter
				steps = 16;
				
			}

		} 

	}


	public Timer getEnemyMovement() {
		return enemyMovement;
	}

	public void setEnemyMovement(Timer enemyMovement) {
		this.enemyMovement = enemyMovement;
	}

	public void setName(int name) {
		this.name = name;
	}

	public  String getEnemyType() {
		return enemyType;
	}

	public  void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}


	public  int getEnemyAttackPower() {
		return enemyAttackPower;
	}


	public  void setEnemyAttackPower(int enemyAttackPower) {
		this.enemyAttackPower = enemyAttackPower;
	}


	public  int getEnemyDefensePower() {
		return enemyDefensePower;
	}


	public  void setEnemyDefensePower(int enemyDefensePower) {
		this.enemyDefensePower = enemyDefensePower;
	}


	public  int[] getEnemyPosition() {
		return enemyPosition;
	}


	public  void setEnemyPosition(int[] enemyPosition) {
		this.enemyPosition = enemyPosition;
	}



}
