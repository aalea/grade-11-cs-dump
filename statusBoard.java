package boring;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class statusBoard {
	
	/**
	 * track game scores (1pt per food item eaten)
	 */
	private static int score=0;
	
	/**
	 * Displays score on scoreboard
	 */
	public static JLabel scoreLabel = new JLabel("Score: 0");
	
	/**
	 * Displays lives on scoreboard
	 */
	public static JLabel lifeLabel = new JLabel("Lives: 3");
	
	/**
	 * track high scores 
	 */
	private static int[] highScores = new int[6];
	
	/**
	 * Holds PacMan's lives
	 */
	private static int pacmanLives=3;
	
	/**
	 * Restarts the game
	 */
	public static JButton restartButton = new JButton("Restart");
	//restartButton.addActionListener(this);
	
	/**
	 * Pauses the game timers to essentially "pause" the game
	 */
	public static JButton pauseButton = new JButton("Pause");
	

	/**
	 * @return the score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public static void setScore(int score) {
		statusBoard.score = score;
	}

	/**
	 * @return the highScores
	 */
	public static int getHighScores(int whichScore) {
		return highScores[whichScore];
	}

	/**
	 * @param highScores the highScores to set
	 */
	public static void setHighScores(int[] highScores, int whichScore) {
		statusBoard.highScores[whichScore] = highScores[whichScore];
	}

	/**
	 * @return the pacmanLives
	 */
	public static int getPacmanLives() {
		return pacmanLives;
	}

	/**
	 * @param pacmanLives the pacmanLives to set
	 */
	public static void setPacmanLives(int pacmanLives) {
		statusBoard.pacmanLives = pacmanLives;
	}
	
	public static void calculateHighScores() {
		if (getScore() > getHighScores(1)) {
			setHighScores(highScores, 1);
		}
		else if (getScore() > getHighScores(2)) {
			setHighScores(highScores, 2);
		}
		else if (getScore() > getHighScores(3)) {
			setHighScores(highScores, 3);
		}
		else if (getScore() > getHighScores(4)) {
			setHighScores(highScores, 4);
		}
		else if (getScore() > getHighScores(5)) {
			setHighScores(highScores, 5);
		}
	}
	
	public static void setupHighScoreTable() {
		PacManGUI.highScorePanel.setBounds(10, 100, 590, 500);
		PacManGUI.highScorePanel.setLayout(new BoxLayout(PacManGUI.highScorePanel, BoxLayout.Y_AXIS));
		
		addALabel("High Scores", PacManGUI.highScorePanel);
        addALabel(Integer.toString(getHighScores(1)), PacManGUI.highScorePanel);
        addALabel(Integer.toString(getHighScores(2)), PacManGUI.highScorePanel);
        addALabel(Integer.toString(getHighScores(3)), PacManGUI.highScorePanel);
        addALabel(Integer.toString(getHighScores(4)), PacManGUI.highScorePanel);
        addALabel(Integer.toString(getHighScores(5)), PacManGUI.highScorePanel);
        
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //PacManGUI.highScorePanel.add(restartButton);
        
        PacManGUI.removeBoard();
    }
 
    private static void addALabel(String text, Container highScorePanel) {
        JLabel highScore = new JLabel(text);
        highScore.setFont(new Font("Serif", Font.PLAIN, 40));
        highScore.setForeground(Color.white);
        highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        PacManGUI.highScorePanel.add(highScore);
        
	}
    
    
    public static void setupScoreBoard() {
    	
    	PacManGUI.scoreBoardPanel.setVisible(true);
    	
    	
    	
    	restartButton.setBounds(5, 5, 80, 40);
    	PacManGUI.scoreBoardPanel.add(restartButton);
    	restartButton.setVisible(true);
    	
    	pauseButton.setBounds(5, 45, 80, 40);
    	PacManGUI.scoreBoardPanel.add(pauseButton);
    	pauseButton.setVisible(true);
    	
    	pauseButton.addActionListener(new ActionListener() { 
    		public void actionPerformed(ActionEvent e) { 
    			//Board.gameTimer.stop();
  		  	}
    	});
    	
    	scoreLabel.setBounds(400, 0, 250, 50);
    	PacManGUI.scoreBoardPanel.add(scoreLabel);
    	scoreLabel.setVisible(true);
    	scoreLabel.setFont(new Font("Impact", Font.BOLD, 35));
    	scoreLabel.setForeground(Color.WHITE);
    	
    	lifeLabel.setBounds(400, 50, 250, 50);
    	PacManGUI.scoreBoardPanel.add(lifeLabel);
    	lifeLabel.setVisible(true);
    	lifeLabel.setFont(new Font("Impact", Font.BOLD, 25));
    	lifeLabel.setForeground(Color.WHITE);
    	
    	PacManGUI.scoreBoardPanel.setFocusable(false);
    	
    	}
    
    
    

	

}
