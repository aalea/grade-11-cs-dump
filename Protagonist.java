package testing;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class holds all aspects of the protagonist of the game.
 * 
 * It's used to create a protagonist object. It extends
 * the Mover class and includes an array of ImageIcons constants 
 * for the various states of the protagonist.
 * Also, there is a constructor that sets the initial image of the protagonist.
 * 
 * @author Aalea Ally
 * 
 */
public class Protagonist extends Mover {
     
     /**
      * holds health points of protagonist
      */
      private static int defensePower;

	/**
     * holds battle points of protagonist
     */
     private int battlePoints = 10;
     
     /**
     * holds experience points of protagonist
     */
     private static int experiencePoints = 0;
     
     /**
      * signal for when protagonist is dead or not
      */
      private static boolean dead = false;
      
      /**
       * Accumulates the friends not killed
       */ 
      public static int friendsAlive;
      
      /**
       * determine whether the protagonist won a battle or ran away
       */
      public boolean won;
      
    
    /**
	 * creates an array of ImageIcons representing various states of the protagonist
	 */
	public ImageIcon[][] IMAGE = {
			
			//0 - walking left 
			{ new ImageIcon("sprites/shyGuyLeftStep1.PNG"), new ImageIcon("sprites/shyGuyStandingLeft.PNG"), new ImageIcon("sprites/shyGuyLeftStep2.PNG")},
			//1 - walking right
			{ new ImageIcon("sprites/shyGuyRightStep1.PNG"), new ImageIcon("sprites/shyGuyStandingRight.PNG"), new ImageIcon("sprites/shyGuyRightStep2.PNG")},
			//2 - punch
			{ new ImageIcon("sprites/shyGuyStandingRight.PNG"), new ImageIcon("sprites/shyGuyPunch1.PNG"), new ImageIcon("sprites/shyGuyPunch2.PNG") },
			//3 - slingshot
			{ new ImageIcon("sprites/shyGuySlingshot1.PNG"), new ImageIcon("sprites/shyGuySlingshot2.PNG"), new ImageIcon("sprites/shyGuySlingshot3.PNG")},
			//4 - spiny ball weapon
			{ new ImageIcon("sprites/shyGuySpinning1.PNG"), new ImageIcon("sprites/shyGuySpinning2.PNG"), new ImageIcon("sprites/shyGuySpinning3.PNG")},
			//5 - falling
			{ new ImageIcon("sprites/shyGuyHurt1.PNG"), new ImageIcon("sprites/shyGuyHurt2.PNG")},
			//6 - flying away (0) and dead (1)
			{ new ImageIcon("sprites/shyGuyFlying.PNG"), new ImageIcon("sprites/shyGuyDeath.PNG")}
			
	};
	
	/**
	 * Protagonist constructor
	 */
	public Protagonist() {
		
		//sets health points of the protagonist
		this.setHealthPoints(25);
		//sets battle points of the protagonist (used to do heavier attacks)
		this.battlePoints = 35;
		
		//resize image icons

		//protagonist standing left
		Image image = IMAGE[0][1].getImage(); //convert imageicon to image
		Image fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
		IMAGE[0][1] = new ImageIcon(fixedImage); //convert the scaled image back the imageicon
		
		//protagonist standing right
		image = IMAGE[2][0].getImage(); //convert imageicon to image
		fixedImage = image.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH); //scale image
		IMAGE[2][0] = new ImageIcon(fixedImage); //convert the scaled image back the imageicon
		
		this.setIcon(IMAGE[0][1]); //set protagonist to standing right when game starts

	}

	public int getBattlePoints() {
		return battlePoints;
	}

	public void setBattlePoints(int battlePoints) {
		this.battlePoints = battlePoints;
	}

	public static int getExperiencePoints() {
		return experiencePoints;
	}

	public static void setExperiencePoints(int experiencePoints) {
		Protagonist.experiencePoints = experiencePoints;
	}

	public static boolean isDead() {
		return dead;
	}

	public static void setDead(boolean dead) {
		Protagonist.dead = dead;
	}

	public static int getFriendsAlive() {
		return friendsAlive;
	}

	public static void setFriendsAlive(int friendsAlive) {
		Protagonist.friendsAlive = friendsAlive;
	}

	public int getDefensePower() {
		return defensePower;
	}

	public void setDefensePower(int defensePower) {
		Protagonist.defensePower = defensePower;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
	
}
