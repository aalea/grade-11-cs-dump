/**
 * 
 */
package chapter4;
import javax.swing.JFrame;
/**
 * @author 340795954
 *
 */
public class DrawPanelTest {

	public static void main(String[] args) {
		
		DrawPanel panel = new DrawPanel(); //Creates the panel to draw on
		
		JFrame application = new JFrame(); //Creates the frame to hold the panel
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit the frame on close
		
		application.add( panel ); //add panel to frame
		application.setSize(250, 250); //set size of frame
		application.setVisible(true); //make frame visible
		
	}
	
}
