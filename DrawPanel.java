/**
 * 
 */
package chapter4;
import java.awt.*;
import javax.swing.*;
/**
 * @author 340795954
 *
 */
public class DrawPanel extends JPanel{ //Inherits class JPanel

	public void paintComponent( Graphics g) {
		
		super.paintComponent( g ); //Calls paintComponent to make sure it displays correctly
		
		int width = getWidth(); //Gets the width of the window
		int height = getHeight(); //Gets the height of the window
		
		g.drawLine(0, 0, width, height); //draws a line from top left to bottom right
		
		g.drawLine(0, height, width, 0); //draws a line from bottom left to top right
		
	}
}
