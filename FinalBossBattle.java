package testing;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 * This class manages the turn-based battle aspect of the game for the final boss.
 * It acts as a parent class to Battle.
 * 
 * @author Aalea Ally
 * 
 */
public class FinalBossBattle extends Battle {

	/**
	 * the placeholder for the audio being played
	 */
	static Clip clip;

	/**
	 * @params instance of protagonist sent from room, enemy will be null, instance of boss sent from room 
	 * 
	 */
	public FinalBossBattle(Protagonist protagonist, Enemy enemy, FinalBoss boss) {

		super(protagonist, enemy, boss); //call the constructor from Battle

		gameState = 1; //set the state of the game to protagonist's turn to ensure that several boss battles at a time do not occur

		protagonistTurn(); //calls the method that handles the protagonist's turn in battle

	}

	/**
	 * this method handles the protagonist's turn in battle
	 */
	@Override
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

					if (protagonist.getBattlePoints() < 1) {

						JOptionPane.showMessageDialog(this, "Not enough battle points :("
								+ " You have " + protagonist.getBattlePoints()); //inform user that they 
						//don't have enough battle points to do the attack

						enoughBattlePoints = false; //this is the sentinel for the loop, false will ensure it keeps going

					}
					else {

						protagonist.setBattlePoints(protagonist.getBattlePoints() - 1); //subtract the battle points 
						//needed for this attack by 1

						JOptionPane.showMessageDialog(this, "You just used 1 battle point."
								+ " You now have " + protagonist.getBattlePoints());  //inform user of the battle point they used

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

			IntroScreen.boss.setHealthPoints(IntroScreen.boss.getHealthPoints() 
					- attackPower - IntroScreen.boss.getBossDefensePower()); //subtract the boss' defense points from the attack power, 
			//then subtract that from the 
			//boss' health points

			JOptionPane.showMessageDialog(this, "You inflicted " + attackPower
					+ " damage on the boss!"); //display message of how much damage
			//the protagonist inflicted on the boss

			if (IntroScreen.boss.getHealthPoints() < 1) { //if the boss died (health points is 0)

				protagonist.setWon(true); //the protagonist won the battle

				JOptionPane.showMessageDialog(this, "You defeated the boss!"); //display message saying that the protagonist won

				end(); //call method that handles when the final boss battle ends

			}
			else { //if the boss didn't die

				bossTurn(); //call the method that manages the enemy's turn in battle

			}

		}
		//12. Check if the player clicked on the defend button and the game state is 1
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

			bossTurn(); //call the method that handles the boss' turn in battle
		
		}	
		
		else if (chosenCommand.equals("Run away")) { //if user wishes to run away
			
			//Trick the protagonist into thinking it's the same as the enemy battles,
			JOptionPane.showMessageDialog(this, "Click OK if you think you're running fast enough to run away");
			//however the protagonist will always trip as the hit meter is never activated 
			//for running away during the boss battle. The user will instead waste a turn
			JOptionPane.showMessageDialog(this, "HAH you tripped while attempting to run away", null, JOptionPane.PLAIN_MESSAGE, protagonist.IMAGE[6][1]);
			
			bossTurn();	//call the method that handles the boss' turn in battle

		}
		
	}


	public void bossTurn() {
		
		IntroScreen.boss.setBossDefensePower(0); //reset defense power

		gameState = 3; //change the state of the game to the boss' turn

		int attackChoice = rand.nextInt(10) + 1; //this random number will determine the attack choice
		int bossAttackPower; //this holds the value of the boss' attack power

		if (attackChoice <= 2) { //if the random number is 1 or 2

			bossAttackPower = rand.nextInt(3) + 1; //this random number will determine the attack power

			bossAttackPower = Math.abs(protagonist.getDefensePower() - bossAttackPower); //subtract protagonist's defense from attack power

			protagonist.setHealthPoints(protagonist.getHealthPoints() - bossAttackPower); //subtract attack power from protagonist's health

			JOptionPane.showMessageDialog(this, "The boss punched you! You lost "
					+ bossAttackPower + " health points. You now have "
					+ protagonist.getHealthPoints() + " health points.", null, 
					JOptionPane.PLAIN_MESSAGE, IntroScreen.boss.IMAGE[1][0]); //display boss' attack power and protagonist' health after attack
		
		}

		else if (attackChoice <= 6) { //if the random number is 3, 4, 5, or 6

			bossAttackPower = rand.nextInt(6) + 3; //this random number will determine the attack power

			bossAttackPower = Math.abs(protagonist.getDefensePower() - bossAttackPower); //subtract protagonist's defense from attack power

			protagonist.setHealthPoints(protagonist.getHealthPoints() - bossAttackPower); //subtract attack power from protagonist's health

			JOptionPane.showMessageDialog(this, "The boss jumped on you! You lost "
					+ bossAttackPower + " health points. You now have "
					+ protagonist.getHealthPoints() + " health points.", null, 
					JOptionPane.PLAIN_MESSAGE, IntroScreen.boss.IMAGE[2][0]); //display boss' attack power and protagonist' health after attack
		
		}

		else if (attackChoice == 7) { //if the random number is 7
			
			bossAttackPower = 10; //the power of the fire attack
			
			bossAttackPower = (Math.abs(protagonist.getDefensePower() - bossAttackPower)); //subtract protagonist's defense from attack power

			protagonist.setHealthPoints(protagonist.getHealthPoints() - bossAttackPower); //subtract protagonist's health by attack power

			JOptionPane.showMessageDialog(this, "The boss did his special fire attack on you! You lost "
					+ bossAttackPower + " health points. You now have "
					+ protagonist.getHealthPoints() + " health points.", null, 
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/fireFlower.png")); //display boss' attack power and protagonist' health after attack
		}

		else if (attackChoice <= 9) { //if the random number if 8 or 9

			IntroScreen.boss.setBossDefensePower(3); //set the boss' defense to 3

		}

		if (protagonist.getHealthPoints() < 1) { //if the protagonist died (health points = 0)

			death(); //call the method that handles the protagonist's death
			
		}
		else if (protagonist.getHealthPoints() > 0 && gameState==3) { //if the protagonist survived
			
			friendsTurn(Loader.friendsAlive); //call the method that handles the friends' turn in battle, 
											 //with the number of them in the arguments
			
		}

	}
	
	/**
	 * this method handles the moment in the boss battle when all the friends the protagonist
	 * did not kill help the user in killing the boss
	 * 
	 * @param friends
	 */
	public void friendsTurn(int friendsAmount) {
		
		int friendAttackPower = 0; //holds the value of each friend's attack power
		int friendAttackPowerTotal = 0; //holds the value all the friends' attack power combined

		JOptionPane.showMessageDialog(this, "Your "
				+ friendsAmount + " friends have joined you!", null, 
				JOptionPane.PLAIN_MESSAGE, Enemy.IMAGE[3][1]); //display message showing how many friend 
															  //the protagonist didn't kill with their image

		for (int friendCounter = friendsAmount; friendCounter > 0; friendCounter--) { //continue until all friends have done their attack

			friendAttackPower = 6 + (int)(Math.random() * ((10 - 6) + 1)); //generate a random number between 6 and 10 
																		  //which will be the current friend's attack power

			IntroScreen.boss.setHealthPoints(IntroScreen.boss.getHealthPoints() 
					- friendAttackPower); //subtract attack power from boss' health

			if (friendCounter >= 4) { //if the 4th or higher friend is attacking (can't do same for all as 3 uses 3rd, 2 uses 2nd, 1 uses 1st)
				
				JOptionPane.showMessageDialog(this, "Your "
						+ friendCounter + "th friend inflicted " + friendAttackPower + 
						" damage on the boss!", null, JOptionPane.PLAIN_MESSAGE, 
						IntroScreen.boss.IMAGE[4][0]); //display message of the current friend's damage to the boss and the boss hurt image
			}
			else if (friendCounter==3) { //if the 3rd friend is attacking
				
				JOptionPane.showMessageDialog(this, "Your "
						+ friendCounter + "rd friend inflicted " + friendAttackPower + 
						" damage on the boss!", null, JOptionPane.PLAIN_MESSAGE, 
						IntroScreen.boss.IMAGE[4][0]); //display message of the current friend's damage to the boss and the boss hurt image
			}
			else if (friendCounter==2) { //if the 2nd friend is attacking
				
				JOptionPane.showMessageDialog(this, "Your "
						+ friendCounter + "nd friend inflicted " + friendAttackPower + 
						" damage on the boss!", null, JOptionPane.PLAIN_MESSAGE, 
						IntroScreen.boss.IMAGE[4][0]); //display message of the current friend's damage to the boss and the boss hurt image
			}
			else if (friendCounter==1) { //if the 1st friend is attacking
				JOptionPane.showMessageDialog(this, "Your "
						+ friendCounter + "st friend inflicted " + friendAttackPower + 
						" damage on the boss!", null, JOptionPane.PLAIN_MESSAGE, 
						IntroScreen.boss.IMAGE[4][0]); //display message of the current friend's damage to the boss and the boss hurt image
			}

			friendAttackPowerTotal += friendAttackPower; //add the current friend's attack power to the total

		}

		JOptionPane.showMessageDialog(this, "In total, your friends inflicted "
				+ friendAttackPowerTotal + " damage on the boss! The boss now only has "
				+ IntroScreen.boss.getHealthPoints() + " health points..."); //display total attack power from friends and the boss' health

		if (IntroScreen.boss.getHealthPoints() < 1) { //if the boss died (health points = 0)
			
			protagonist.setWon(true); 
			
			JOptionPane.showMessageDialog(this, "You defeated the boss!"); //display message
			
			end(); //call method that handles when the boss dies
			
		}
		else { //if the boss is still alive
			
			protagonistTurn(); //call method that handles the protagonist's turn
			
		}

	}
	
	/**
	 * this method calls the ending class when the user beats the boss
	 */
	public void end() {
		
		Room.playSound("STOP", ""); //stop playing the boss battle music
		
		playSound("ending", "sounds/music/ending.wav"); //start playing the ending music
		
		new Ending(protagonist, IntroScreen.boss); //start new instance of Ending with this class' instance of protagonist and boss

	}
	
	/**
	 * this method plays and stops the sound being played for the boss battle
	 * 
	 * @param name of sound
	 * @param location of audio file
	 */
	static void playSound(String name, String destination) {
		
		if (name.equals("STOP")) { //stop the music when "STOP" is sent to this method
			
			clip.stop();
			
		}
		else { //otherwise, play the sound
			
			try {
				
				//Define file
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(destination).getAbsoluteFile());
				//Get clip
				clip = AudioSystem.getClip();
				//Open clip in audio input stream
				clip.open(audioInputStream);
				//Start clip
				clip.start();

			} catch(Exception ex) { //if any error occurs
				
				System.out.println(ex); //print the error
				
			}
			
		}
		
	}


}


