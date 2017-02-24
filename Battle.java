package testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * This class manages the turn-based battle aspect of the game for enemies.
 * It acts as a super class for FinalBossBattle, as many similar aspects in this class
 * can be found in the final boss battle.
 * 
 * @author Aalea Ally
 * 
 */
public class Battle extends JFrame {

	/**
	 * signals whose turn it is in the battle
	 * 
	 * @params 0: no battle, 1: protagonist choice, 2: protagonist attack, 3: enemy attack
	 */ 
	protected static int gameState;

	/**
	 * value to hold whether the user has enough battle points for their attack
	 * 
	 * true: enough battle points
	 * false: not enough battle points
	 */
	protected static boolean enoughBattlePoints;

	/**
	 * value to hold the type of attack the user wishes to do.
	 * 
	 * possible types include 
	 * 	 "Punch (no battle points)", 
	 *   "Slingshot (1 battle point)", 
	 *   "Flail (5 battle points)"
	 */
	protected static String attackType;
	
	/**
	 * this value holds the enemy's attack power
	 */
	private static int enemyAttackPower = 0;

	/**
	 * the instance of the protagonist object in this class
	 */
	protected Protagonist protagonist;

	/**
	 * the instance of the enemy object in this class
	 */
	protected Enemy enemy;

	/**
	 * the instance of the Attack class in this class
	 */
	protected static Attack attack;

	/**
	 * the instance of the HitMeter object in this class
	 */
	protected HitMeter hitMeter;

	/**
	 * the random number generator to determine the enemy's attack power
	 */
	Random rand = new Random();

	/**
	 * the labels of the characters in the battle, which will be displayed on
	 * the dialog boxes
	 */
	JLabel protagonistImage;
	JLabel enemyImage;
	JLabel bossImage;

	/**
	 * the possible commands the user can make the protagonist do during
	 * the battle
	 */
	protected static String[] COMMANDS = 
		{"Attack", 
		"Defend", 
		"Run away"};
	
	/**
	 * used as a placeholder to obtain images that need to be resized
	 */
	Image image;
	
	/**
	 * used as a placeholder to hold images that are resized
	 */
	Image fixedImage;

	/**
	 * 
	 * @param the instance of the protagonist sent from the room class
	 * @param the instance of the enemy sent from the room class, if it is null, the battle is a boss battle
	 * @param the instance of the boss sent from the room class, if it is null, the battle is a normal enemy
	 */
	public Battle(Protagonist protagonist, Enemy enemy, FinalBoss boss) {
		//no layout manager used
		setLayout(null);
		//set the size the battle frame will be
		setSize(400, 200);
		
		//make it so that the battle frame is centred
		Dimension windowSize = getSize(); //get the size of the computer's monitor
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); //gets the graphics environment of the screen
		Point centerPoint = ge.getCenterPoint(); //gets the centre point of the screen

		int centreXPoint = centerPoint.x - windowSize.width / 2; //subtracts centerPoint by half of width of the screen to get centre x point
		int centreYPoint = centerPoint.y - windowSize.height / 2;  //subtracts centerPoint by half of height of the screen to get centre y point

		setLocation(centreXPoint, centreYPoint); //set the location of the frame to the centre

		this.protagonist = protagonist; //uses the instance of the protagonist sent from the room to assign to this object's protagonist
		
		//tries to set the instance of the enemy sent from the room to assign to this object's
		//enemy, but if the value is null then this step is skipped as it is a boss battle
		try {
			this.enemy = enemy;
		}
		catch (java.lang.NullPointerException e) {
			System.out.println("This is not an enemy battle");
		}

		resizeSprites(image, fixedImage); //starts method that resizes the sprites to fit in the dialog boxes

		setVisible(true); //set the battle frame to visible
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //make it so that the user can't just click X and exit the battle

		if (enemy != null) { //if this is an enemy battle

			setTitle("Battle"); 
			
			//display dialog with enemy standing as an image
			JOptionPane.showMessageDialog(this, "You have encountered an enemy!", null, JOptionPane.PLAIN_MESSAGE, enemy.IMAGE[3][1]);
			//display dialog with protagonist's health points and a red heart
			JOptionPane.showMessageDialog(this, "Your health points: " + protagonist.getHealthPoints(), null, JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/heart.png"));
			//display dialog with enemy's health points and a blue heart
			JOptionPane.showMessageDialog(this, "The enemy's health points: " + enemy.getHealthPoints(), null, JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/badHeart.png"));
			
			protagonistTurn(); //calls method that handles the protagonist's turn in battle


		}
		else { //if the FinalBossBattle called the constructor of this class, this will occur instead of lines 144-158

			setTitle("Final Battle");
			
			//resizes the boss' standing image
			image = boss.IMAGE[0][0].getImage(); //convert imageicon to image
			fixedImage = image.getScaledInstance(125, 220, java.awt.Image.SCALE_SMOOTH); //scale image
			boss.IMAGE[0][0] = new ImageIcon(fixedImage); //convert the new image to imageicon and assign it back
			
			//display dialog with boss standing as an image
			JOptionPane.showMessageDialog(this, "You have encountered the boss!", null, JOptionPane.PLAIN_MESSAGE, boss.IMAGE[0][0]);
			//display dialog with protagonist's health points and a red heart
			JOptionPane.showMessageDialog(this, "Your health points: " + protagonist.getHealthPoints(), null, JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/heart.png"));
			//display dialog with boss' health points and a blue heart
			JOptionPane.showMessageDialog(this, "The boss' health points: " + IntroScreen.boss.getHealthPoints(), null, JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/badHeart.png"));


		}

	}

	/**
	 * 
	 * this method resizes the sprites so they are an appropriate size for the dialog boxes they're used in
	 * 
	 * @param placeholder for images that need to be resized
	 * @param placeholder for the new image after scaling that need to be converted back to imageicon
	 */
	private void resizeSprites(Image image, Image fixedImage) {
		
		//resize imageicon of the protagonist punching
		image = protagonist.IMAGE[2][1].getImage(); //convert the imageicon to an image
		fixedImage = image.getScaledInstance(200, 240, java.awt.Image.SCALE_SMOOTH); //scale the image
		protagonist.IMAGE[2][1] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the protagonist punching sprite
		
		//resize the imageicon of the protagonist using the slingshot
		image = protagonist.IMAGE[3][1].getImage(); //convert the imageicon to an image
		fixedImage = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); //scale the image
		protagonist.IMAGE[3][2] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the protagonist using the slingshot

		//resize the imageicon of the protagonist using the flail
		image = protagonist.IMAGE[4][1].getImage(); //convert the imageicon to an image
		fixedImage = image.getScaledInstance(223, 387, java.awt.Image.SCALE_SMOOTH); //scale the image
		protagonist.IMAGE[2][2] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the protagonist using the flail

		//attempt to resize the imageicon of the enemy getting hurt and enemy standing, but if the enemy value if null,
		//then this battle is a boss battle
		try {
			image = enemy.IMAGE[5][0].getImage(); //convert the imageicon to an image
			fixedImage = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); //scale the image
			enemy.IMAGE[5][0] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the enemy getting hurt
			
			image = enemy.IMAGE[3][1].getImage(); //convert the imageicon to an image
			fixedImage = image.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH); //scale the image
			enemy.IMAGE[3][1] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the enemy standing
		}
		catch (java.lang.NullPointerException e) { //if the enemy's value sent to this class is null
			System.out.println("This is not an enemy battle");
		}

		//resize the imageicon of the protagonist on the floor (in cases of tripping or death)
		image = protagonist.IMAGE[6][1].getImage(); //convert the imageicon to an image
		fixedImage = image.getScaledInstance(371, 220, java.awt.Image.SCALE_SMOOTH); //scale the image
		protagonist.IMAGE[6][1] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the protagonist on the floor

		//resize the imageicon of the protagonist hurt
		image = protagonist.IMAGE[5][1].getImage(); //convert the imageicon to an image
		fixedImage = image.getScaledInstance(255, 240, java.awt.Image.SCALE_SMOOTH); //scale the image
		protagonist.IMAGE[5][1] = new ImageIcon(fixedImage); //assign the new image to the imageicon of the protagonist hurt

	}
	
	/**
	 * 
	 * this method handles the protagonist's turn in battle
	 */
	public void protagonistTurn() {
		
		gameState = 1; //set the state of the game to protagonist's turn
		int attackPower = 0; //resets attack power
		protagonist.setDefensePower(0); //resets defense power

		String chosenCommand; //holds the command the user wishes to do
		chosenCommand = (String) JOptionPane.showInputDialog(this, "What do you want to do?", "Action Command Selection", 
				JOptionPane.QUESTION_MESSAGE, null, COMMANDS, COMMANDS[0]); //prompts user for their desired command
		
		if (chosenCommand.equals("Attack")) { //if the user wishes to attack

			gameState = 2; //change the state of the game to protagonist's attack

			do {
				attack = new Attack(); //start new instance of attack class
				attackType = attack.attackType(this); //sets the user's attack choice to the return value from the
													 //attack class, using the battle frame as an argument to host 
													//the dialog box on. 
				
				if (attackType.equals("Slingshot (1 battle point)")) { //if the user chose the slingshot attack
					
					if (protagonist.getBattlePoints() < 1) { //if the user has 0 battle points
						
						JOptionPane.showMessageDialog(this, "Not enough battle points :("
								+ " You have " + protagonist.getBattlePoints()); //inform user that they 
																				//don't have enough battle points to do the attack
						
						enoughBattlePoints = false; //this is the sentinel for the loop, false will ensure it keeps going
					}
					else { //if the user has more than 0 battle points
						protagonist.setBattlePoints(protagonist.getBattlePoints() - 1); //subtract the battle points 
																					   //needed for this attack by 1
						
						JOptionPane.showMessageDialog(this, "You just used 1 battle point."
								+ " You now have " + protagonist.getBattlePoints()); //inform user of the battle point they used
						
						enoughBattlePoints = true; //this is the sentinel for the loop, exit the loop when it reaches the end
					}
				}
				else if (attackType.equals("Flail (5 battle points)")) { //if the user chose the flail attack
					
					if (protagonist.getBattlePoints() < 5) { //if the user doesn't have enough battle points for this attack
						
						JOptionPane.showMessageDialog(this, "Not enough battle points :("
								+ " You have " + protagonist.getBattlePoints()); //inform user that they 
																				//don't have enough battle points to do the attack
						
						enoughBattlePoints = false; //this is the sentinel for the loop, false will ensure it keeps going
					}
					else { //if the user has 5 or more battle points
						
						protagonist.setBattlePoints(protagonist.getBattlePoints() - 5); //subtract the battle points 
						   															   //needed for this attack by 5
						
						JOptionPane.showMessageDialog(this, "You just used 5 battle points."
								+ " You now have " + protagonist.getBattlePoints()); //inform user of the battle points they spent
						
						
						enoughBattlePoints = true; //this is the sentinel for the loop, exit the loop when it reaches the end
					}
				}
				else { //if the user chose the punch attack, which doesn't require any battle points

					enoughBattlePoints = true; //this is the sentinel for the loop, exit the loop when it reaches the end
				}

			} while (enoughBattlePoints==false); //keep going unless the user has enough battle points or chooses the punch attack
			
			hitMeter = new HitMeter(); //start a new instance of the hit meter, the timer will run in the background

			if (attackType.equals("Punch (no battle points)")) { //if the user chose the punch attack
																//display a dialog box prompting when to attack (when to stop the timer)
				JOptionPane.showMessageDialog(this, "Click OK when you think you're ready to hit", null, JOptionPane.PLAIN_MESSAGE, protagonist.IMAGE[2][1]);
			}
			else if (attackType.equals("Slingshot (1 battle point)")) { //if the user chose the slingshot attack
																	   //display a dialog box prompting when to attack (when to stop the timer)
				JOptionPane.showMessageDialog(this, "Click OK when you think you're ready to release", null, JOptionPane.PLAIN_MESSAGE, protagonist.IMAGE[3][1]);
			}
			else if (attackType.equals("Flail (5 battle points)")) { //if the user chose the flail attack
																	//display a dialog box prompting when to attack (when to stop the timer)
				JOptionPane.showMessageDialog(this, "Click OK when you think you're ready to swing", null, JOptionPane.PLAIN_MESSAGE, protagonist.IMAGE[4][1]);
			}

			attackPower = hitMeter.getAttackResult(attackType); //once the user hits the dialog box, the timer will stop and the 
															   //attack power will be retrieved from the hit meter. Different attacks
															  //need techniques on calculating the attack power, which is why it's 
															 //in the arguments.

			enemy.setHealthPoints(enemy.getHealthPoints() - attackPower); //subtract the attack power from the 
																				   //enemy's health points

			JOptionPane.showMessageDialog(this, "You inflicted " + attackPower
					+ " damage on the enemy!", null, JOptionPane.PLAIN_MESSAGE, enemy.IMAGE[5][0]); //display message of how much damage
																								   //the protagonist inflicted on the enemy
			
			if (enemy.getHealthPoints() < 1) { //if the enemy died (health points is 0)
				
				protagonist.setWon(true); 
				
				if (enemy.getEnemyType().equals("Friend")) { //if the enemy is actually a friend
															//subtract 1 from a friends alive accumulator
					Loader.friendsAlive--;
					
				}
				
				JOptionPane.showMessageDialog(this, "You defeated the enemy!"); //display message saying that the protagonist won
				
				enemy = new Enemy(enemy.name, "Dead"); //replace enemy with dead enemy
						
				this.dispose(); //get rid of the battle frame and go back to the room
				
			}
			else { //if the enemy didn't die (health points higher than 0)

				enemyTurn(); //call the method that manages the enemy's turn in battle
				
			}
			
		}
		else if (chosenCommand.equals("Defend")) { //if the user wishes to defend
			
			hitMeter = new HitMeter(); //start new instance of the hit meter, the timer will run in the background

			JOptionPane.showMessageDialog(this, "Click OK when you think you're ready to defend"); //display a dialog box prompting the 
																								  //user when they're ready to defend 
																								 //(when to stop the timer)
	
			protagonist.setDefensePower(hitMeter.getDefenseResult()); //once the user hits the dialog box, the timer will stop and the 
																	 //defense power will be retrieved from the hit meter, 
																	//which will be set to the protagonist's defense.
			
			JOptionPane.showMessageDialog(this, "Your defense has increased by " + protagonist.getDefensePower()); //display message 
																												  //showing the increase in 
																												 //defense

			enemyTurn(); //call the method that handles the enemy's turn in battle
		}	

		else if (chosenCommand.equals("Run away")) { //if user wishes to run away

			hitMeter = new HitMeter(); //start new instance of the hit meter, the timer will run in the background

			JOptionPane.showMessageDialog(this, "Click OK if you think you're running fast enough to run away"); //display a dialog box  
			  																									//prompting the user when 
			 																								   //they're ready to run 	
																											  //(when to stop the timer)
			
			if (hitMeter.canRunAway()==true) { //if the hit meter returns true

				gameState = 0; //set the gamestate to "not in battle"
				protagonist.setWon(false); //the protagonist didn't win the battle
				JOptionPane.showMessageDialog(this, "You successfully ran away! (you wimp)"); //display message
				this.dispose(); //get rid of battle frame and go back to room
				
			}
			else if (hitMeter.canRunAway()==false) { //if the hit meter returns false
				
				JOptionPane.showMessageDialog(this, "HAH you tripped while attempting to run away", null, JOptionPane.PLAIN_MESSAGE, 
						protagonist.IMAGE[6][1]); //display message and image of protagonist on floor
				enemyTurn(); //call method that handles the enemy's turn in battle
				
			}	

		}
		
	}
	
	/**
	 * 
	 * this method handles the enemy's turn in battle
	 * 
	 */
	public void enemyTurn() {
		
		gameState = 3; //set the gamestate to indicate that it's the enemy's turn

		enemyAttackPower = rand.nextInt(3) + 1; //decide whether the enemy's attack power will be 1, 2, or 3

		enemyAttackPower = Math.abs(protagonist.getDefensePower() - enemyAttackPower); //subtract the protagonist's 
																					  //defense from the enemy's attack power to get actual
																					 //attack power

		protagonist.setHealthPoints(protagonist.getHealthPoints() - enemyAttackPower); //subtract the actual attack power from the 
																					  //protagonist's health points

		JOptionPane.showMessageDialog(this, "The enemy attacked you! You lost "
				+ enemyAttackPower + " health points. You now have "
				+ protagonist.getHealthPoints() + " health points.", null, 
				JOptionPane.PLAIN_MESSAGE, protagonist.IMAGE[5][1]); //display message indicating the enemy's attack power, 
																	//the protagonist's health points after the attack, 

		if (protagonist.getHealthPoints() < 1) { //if the protagonist died (health points = 0)

			death(); //calls the method that handles the protagonist's death
			
		}
		else { //if the protagonist survived from the enemy's attack
			
			protagonistTurn(); //call back the method that handles the protagonist's turn
			
		}

	}
	
	/**
	 * this method is called when the protagonist's health points reaches 0. It displays a game over message and closes the application
	 */
	public void death() { 

		JOptionPane.showMessageDialog(this, "Uh oh..you died. Have fun in the afterlife!"); //display dialog with game over message
		
		System.exit(0); //closes the application
		
	}

}
