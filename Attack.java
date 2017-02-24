package testing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * This class handles the types of attacks for the protagonist, that the user can select from 
 * 
 * @author Aalea Ally
 * 
 */ 

public class Attack extends JButton {
    
    /**
     * String array that holds the types of attacks
     */ 
     private static String[] ATTACKS = 
        {"Punch (no battle points)", 
        "Slingshot (1 battle point)", 
        "Flail (5 battle points)"};
     
    /**
     * 
     * This method is called to display an input dialog to prompt the user for their
     * attack choice, during the battle. It then returns the choice back to the battle,
     * being either the enemy battle or the boss battle
     * 
     * @param the frame to host the input dialog on
     * @return the user's choice on the attack
     */
    public String attackType(JFrame battleFrame) {
        
        //ask users the types of attack they want to use
        return (String) JOptionPane.showInputDialog(battleFrame, "What attack do you want to use?", 
					"Attack", JOptionPane.QUESTION_MESSAGE, null, ATTACKS, ATTACKS[0]);
        
    }

	
	
}
