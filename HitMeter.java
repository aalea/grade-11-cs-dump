package testing;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class manages the hit meter, which is an object that determines the 
 * effect of an action in battle
 * 
 * @author Aalea Ally
 * 
 */ 

public class HitMeter extends JPanel implements ActionListener {
    
    /**
     * Holds the value of the number the user stopped at
     */ 
     private static int result = 0;
     
     /**
      * Timer for the movement of the meter bar
      */ 
     Timer meterTimer = new Timer(1000, this);
      
      public HitMeter() { 
          
          startMeter(); //call the method that starts the timer for the meter
          
      }
      
      /**
       * this method starts the timer of the hit meter, which will run in 
       * the background of the battle
       */
      public void startMeter() {
          
    	  meterTimer.start(); //starts the timer
          
      }

      /**
       *  this changes the result everytime the timer sends a signal
       */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==meterTimer) { //if the timer sent a signal

			if (result==5) { //if the result variable reached 5

				result = 1; //reset the value to one 

			}
			else { //otherwise

				result++; //add 1 to the result variable

			}

		}
		
	}
	
	/**
	 * this method is called from the battle or final boss battle class, 
	 * which returns the appropriate attack power, determined by the value of
	 * result which was obtained from the timer
	 */
	public int getAttackResult(String attackType) {

		int attackPower = 0; //placeholder for the attack power
		
		if (attackType.equals("Punch (no battle points)")) { //if the user wishes to punch 

			if (result >= 4) { //if the timer stopped at 4 or 5

				attackPower = 3; //give the user 3 attack power

			}
			else if (result==3) { //if the timer stopped at 3

				attackPower = 4; //give the user 4 attack power

			}
			else if (result >= 0) { //if the timer stopped at 0, 1 or 2

				attackPower = 2; //give the user 2 attack power

			}

		}
		else if (attackType.equals("Slingshot (1 battle point)")) { //if the user wishes to do the slingshot attack

			if (result >= 4) { //if the timer stopped at 4 or 5

				attackPower = 5; //give the user 5 attack power
			}
			else if (result==3) { //if the timer stopped at 3

				attackPower = 7; //give the user 7 attack power
			}
			else if (result >= 0) { //if the timer stopped at 0, 1 or 2

				attackPower = 4; //give the user 4 attack power
			}
		}
		else if (attackType.equals("Flail (5 battle points)")) { //if the user wishes to do the flail attack

			if (result >= 4) { //if the timer stopped at 4 or 5

				attackPower = 10; //give the user 10 attack power
			}
			else if (result==3) { //if the timer stopped at 3

				attackPower = 15; //give the user 15 attack power
			}
			else if (result >= 0) { //if the timer stopped at 0, 1 or 2

				attackPower = 5; //give the user 5 attack power
			}

		}
		
		return attackPower; //return the attack power back to the battle

	}
	
	/**
	 * this method is called from the battle or final boss battle class, 
	 * which returns the appropriate defense power, determined by the value of
	 * result which was obtained from the timer
	 */
	public int getDefenseResult() {

		int defensePower = 0; //hold the value of the defense power
		
			if (result >= 4) { //if the timer stopped at 4 or 5

				defensePower = 2; //give the user 2 defense power

			}
			else if (result==3) { //if the timer stopped at 3

				defensePower = 7; //give the user 7 defense power

			}
			else if (result >= 0) { //if the timer stopped at 0, 1 or 2

				defensePower = 5; //give the user 5 defense power

			}
		
		
		return defensePower; //return the determined defense power back to the battle

	}
	
	/**
	 * this method is called from the battle or final boss battle class, 
	 * which returns whether the portagonist is allowed to run away or not
	 */
	public boolean canRunAway() {

		if (result >= 3) { //if the timer stopped at 3, 4 or 5

			return false; //the user can't run away

		}
		else { //if the timer stopped at 0, 1 or 2

			return true; //the user can run away

		}
		
	}
      
      
}
