package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class inherits the JPanel class. It gives the player an introduction before
 * diving straight into the game. 
 * 
 * 
 * @author Aalea Ally
 *
 */
public class IntroScreen extends JPanel implements ActionListener {

    	/**
    	 * displays name of game
     	 */ 
	private JLabel titleLabel = new JLabel(new ImageIcon("images/title.png"));
	
	/**
	 * displays the first set of instructions on the intro screen panel
	 */
	private JLabel instructionLabel = new JLabel("press i for instructions, any other key to start");
	
	/**
	 * protagonist object that will be used throughout the game
	 */
	public static Protagonist protagonist = new Protagonist();
	
	/**
	 * final boss object that is stored and used in the boss battle
	 */
	public static FinalBoss boss = new FinalBoss();
	
	/**
	 * timer that controls the blinking function of the instructions
	 */
	private Timer instructionsTimer = new Timer(800, this);
	
	/**
	 * the counter that determines whether the instructions are visible or not 
	 *
	 * 0 - let instructions appear, 1 - let instructions disappear
	 */
	private int instructionsCounter = 0;
	
	/**
	 * stores room number to indicate which file to load
	 */
	public static int roomNumber = 0;
	
	/**
	 * contains font used for instructions label
	 */
	Font instructionFont = new Font("Courier",Font.BOLD,16);


	public IntroScreen() {

		//1. Setup the panel
		setLayout(null);
		setBackground(Color.BLACK);
		setBounds(0, 0, 1050, 300);
		
		//2. Add the components
			//2.1. Add the title label
				//2.1.1. Set bounds
		titleLabel.setBounds(90, 80, 370, 65);
				//2.1.2. Add to panel
		add(titleLabel);
		
		//2. Add the components
		//2.1. Add the instructions label
			//2.1.1. Set bounds
		instructionLabel.setBounds(480, 100, 1000, 50);
			//2.1.2. Add to panel
		add(instructionLabel);
			//2.1.3. Change font
		instructionLabel.setFont(instructionFont);
		instructionLabel.setForeground(Color.WHITE);
		

		instructionsTimer.start(); //start the blinking animation of the instructions
		

	}
	
	/**
	 * this method is called when the timer for the instructions animation sends a signal 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==instructionsTimer) { //if the timer is the timer for the instructions animation
			
			if (instructionsCounter==0) { //if the instructions counter is saying to make the instructions appear
			
				instructionLabel.setVisible(true); //make instructions appear
				instructionsCounter = 1; //make the instructions disappear next time the timer sends a signal
			
			}
			else if (instructionsCounter==1) { //if the instructions counter is saying to make the instructions disappear
				
				instructionLabel.setVisible(false); //make instructions disappear
				instructionsCounter = 0; //make the instructions appear next time the timer sends a signal
				
			}
			
		}
		
	}

}
