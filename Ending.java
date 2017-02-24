package testing;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Ending extends JFrame implements ActionListener {
	
	/**
	 * this value holds the instance of the protagonist in this class
	 */
	Protagonist protagonist;
	
	/**
	 * this value holds the instance of the protagonist in this class
	 */
	FinalBoss boss;
	
	/**
	 * this label holds the image of the protagonist
	 */
	JLabel protagonistLabel;
	
	/**
	 * this label holds the image of the boss
	 */
	JLabel bossLabel;

	/**
	 * this timer determines when to continue the animation, stopping right after the
	 * "it was just a dream" dialog pops up. It sends a signal every 2 seconds.
	 */
	Timer animationTimer = new Timer(2000, this);

	/**
	 * this timer determines the moments when the protagonist moves off the screen.
	 * It is enabled after the animationTimer is turned off, and sends a signal every 1 second.
	 */
	Timer leavingScreenTimer = new Timer(1000, this);

	/**
	 * this value is checked everytime the animationTimer sends a signal, if it is enabled.
	 * It determines the part of the animation that should be played.
	 */
	int animationCounter = 0;

	/**
	 * this value is checked everytime the leavingScreenTimer sends a signal. 
	 * It determines the distance the protagonist should be moving to the right, until he is offscreen.
	 */
	int leavingCounter = 314;

	/**
	 * 
	 * @param the instance of the protagonist from the final boss battle
	 * @param the instance of the boss from the final boss battle
	 */
	public Ending(Protagonist protagonist, FinalBoss boss) {

		this.protagonist = protagonist; //sets the instance of the protagonist from the final boss battle to the instance in this class
		this.boss = boss; //sets the instance of the boss from the final boss battle to the instance in this class

		RPGGameTest.frame.dispose(); //gets rid of the screen with the room
		
		//setup frame
		setLayout(null); //do not assign a layout manager to this frame
		setSize(1000, 500); //set the size of this frame
		setTitle("YOU WON"); //set the title of this frame
		getContentPane().setBackground(Color.WHITE); //change background of frame to white
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //give the user the ability to close the frame by clicking X

		resizeImages(); //resize the imageicons so they're the appropriate size for the ending frame
		
		//setup labels
		
		//add imageicons to labels
		protagonistLabel = new JLabel(protagonist.IMAGE[1][1]); //add protagonist standing right to label
		bossLabel = new JLabel(boss.IMAGE[0][0]); //add boss standing right to label
		
		//make it so that it's possible for the labels to have background colours by allowing the background of the label to be solid
		protagonistLabel.setOpaque(true);
		bossLabel.setOpaque(true);
		
		//set the background of the labels to white
		protagonistLabel.setBackground(Color.WHITE);
		bossLabel.setBackground(Color.WHITE);
		
		//position the labels
		protagonistLabel.setBounds(10, 40, 311, 320);
		bossLabel.setBounds(700, 10, 198, 352);
		
		//add the labels onto the ending frame
		add(protagonistLabel);
		add(bossLabel);
		
		setVisible(true); //make the frame visible
		
		animationTimer.start(); //start the animation
		
	}


	/**
	 * this method is triggered everytime the animationTimer or leavingScreenTimer is triggered.
	 * All the animation movements are in here.
	 */
	@Override
	public void actionPerformed(ActionEvent timer) {

		if (timer.getSource()==animationTimer) { //if a signal is sent from the animation timer
			
			if (animationCounter==0) { //if no animation has been done
				
				bossLabel.setIcon(new ImageIcon("images/poof.png")); //make boss disappear in a cloud of smoke
				
			}
			else if (animationCounter==1) { //if it is step 1 in animation
				
				bossLabel.setIcon(protagonist.IMAGE[0][1]); //set the boss' label to the protagonist standing left
				bossLabel.setBounds(680, 40, 311, 320); //reset the position of the boss so it looks parallel with the real protagonist
				
				playSound("poof", "sounds/poof.wav"); //play a "poof" sound
				
				JOptionPane.showMessageDialog(this, "Mario was you all along!"); //display message
				
			}
			else if (animationCounter==2) { //if it is step 2 in animation
				
				JOptionPane.showMessageDialog(this, "But who are you?"); //display message
				bossLabel.setVisible(false); //make boss label disappear
				
			}
			else if (animationCounter==3) { //if it is step 3 in animation
				
				getContentPane().setBackground(Color.DARK_GRAY); //reset background of frame to dark gray
				
				protagonistLabel.setBackground(Color.DARK_GRAY); //reset background the the protagonist's label to dark gray
				protagonistLabel.setBounds(314, 280, 371, 220); //change to location of the protagonist so it looks like it's sleeping 
															   //on the floor
				
				protagonistLabel.setIcon(protagonist.IMAGE[6][1]); //set the protagonist's label to protagonist on the floor
				JOptionPane.showMessageDialog(this, "ZZzzz..."); //display message, signifying that the protagonist is sleeping
				
			}
			else if (animationCounter==5) { //if it is step 5 in animation
				
				protagonistLabel.setBounds(314, 40, 311, 320); //reset position of protagonist as he just woke up
				protagonistLabel.setIcon(protagonist.IMAGE[1][1]); //change protagonist label to protagonist standing right

				JOptionPane.showMessageDialog(this, "It was just a dream..."); //display message
				
				leavingScreenTimer.start(); //start the leaving screen animation
				animationTimer.stop(); //stop the other animation timer
				animationCounter = 0; //reset the animation counter for the next sequence of animation
				
			}
			
		}
		else if (timer.getSource()==leavingScreenTimer) { //if a signal is sent from the leaving screen timer
			
			if (animationCounter==8) { //if it is step 8 in the second sequence of animation
				
				JOptionPane.showMessageDialog(this, "And from that day forth, "
						+ "he learned the the most important lesson of all, "); //display message
				JOptionPane.showMessageDialog(this, "No matter how threatening your friends become, keep them, "
						+ "they may be useful in the future."); //display message
				
				System.exit(0); //close the application
			}
			
			else { //if step 8 in the second sequence of animation has not been reached yet
				
				protagonistLabel.setBounds(leavingCounter, 40, 311, 320); //move the protagonist to the right
				leavingCounter = leavingCounter * 2; //multiply the protagonist's next movement to the right by itself, 
													//so he moves further each timer
				
			}
			
		}
		
		animationCounter++; //add 1 to animation counter signifying the next step in aniamtion

	}
	
	/**
	 * 
	 * this method will play the ending music using AudioInputStream
	 * 
	 * @param the sound that will be played
	 * @param location of the sound file
	 */
	static void playSound(String name, String destination) {
	
		try {
			
			//get the audio file and put it in the input stream
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(destination).getAbsoluteFile());
			//get the clip of the audio from the input stream
			Clip clip = AudioSystem.getClip();
			//open the clip in the audio input stream
			clip.open(audioInputStream);
			//start playing the clip
			clip.start();

		} catch(Exception ex) { //if any error happens with the above
			
			System.out.println(ex); //print the error that occureed
			
		}
		
	}
	
	/**
	 * this method resizes the imageicons so they're the appropriate size for the ending frame
	 */
	private void resizeImages() {
		
		//resize imageicon of protagonist standing right
		Image image = protagonist.IMAGE[1][1].getImage(); //convert imageicon to image
		Image fixedImage = image.getScaledInstance(311, 320, java.awt.Image.SCALE_SMOOTH); //scale image
		protagonist.IMAGE[1][1] = new ImageIcon(fixedImage); //convert new image back to imageicon and assign to protagonist standing right
		
		//resize imageicon of protagonist standing left
		image = protagonist.IMAGE[0][1].getImage(); //convert imageicon to image
		fixedImage = image.getScaledInstance(311, 320, java.awt.Image.SCALE_SMOOTH); //scale image
		protagonist.IMAGE[0][1] = new ImageIcon(fixedImage); //convert new image back to imageicon and assign to protagonist standing left
		
		//resize imageicon of boss standing left
		image = boss.IMAGE[0][0].getImage(); //convert imageicon to image
		fixedImage = image.getScaledInstance(198, 352, java.awt.Image.SCALE_SMOOTH); //scale image
		boss.IMAGE[0][0] = new ImageIcon(fixedImage); //convert new image back to imageicon and assign to boss standing left
		
	}



}
