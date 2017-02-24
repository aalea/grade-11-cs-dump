package boring;

/**
 * This class create a PacMan GUI that extends the JFrame class. It has a Board (JPanel) and 
 * includes a constructor method that sets up the frame and adds a key listener to the board.
 * 
 * @author Aalea Ally
 * 
 */

import java.awt.Color;

import javax.swing.*;

import java.awt.*;

public class PacManGUI extends JFrame {

	// Board panel

	public static Board board = new Board();
	
	public static IntroScreen introScreen = new IntroScreen();
	
	/**
	 * Container for elements of high score screen
	 */
	public static JPanel highScorePanel = new JPanel();
	/**
	 * Container for elements of scoreboard
	 */
	public static JPanel scoreBoardPanel = new JPanel();


	/**

	 * PacMan GUI constructor

	 */

	public PacManGUI() {

		setLayout(null);

		//1. Setup the GUI
		setSize(600, 750);
		setTitle("PacMan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//2. Listen for events on the intro and board and add the board to the GUI
		introScreen.setBounds(0, 0, 600, 750);
		//add(introScreen);
		
		
		
		highScorePanel.setVisible(true);
		add(highScorePanel);
		statusBoard.setupScoreBoard();
		
		scoreBoardPanel.setLayout(null);
		scoreBoardPanel.setBounds(0, 0, 600, 100);
		scoreBoardPanel.setBackground(Color.BLACK);
		//add(scoreBoardPanel);
		statusBoard.setupScoreBoard();
		//scoreBoardPanel.setVisible(false);
		scoreBoardPanel.setVisible(true);
		
		addKeyListener(board);
		board.setBounds(0, 100, 600, 600);
		add(board);
		//board.setVisible(false);
		board.setVisible(true);
		//3. Make GUI visible
		setVisible(true);
		
		

	}


	public static void removeBoard() {
		
		board.setVisible(false);
		scoreBoardPanel.setVisible(false);
		highScorePanel.setBackground(Color.black);
		highScorePanel.setVisible(true);
		
		
	}
	
	public static void removeHighScorePanel() {
		
	}


	

}


