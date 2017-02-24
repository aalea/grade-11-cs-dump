package boring;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
//import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class IntroScreen extends JPanel implements ActionListener {
	
	private JButton startButton = new JButton("Start");
	private JButton instructionButton = new JButton("Instructions");
	private ImageIcon logoImage = new ImageIcon("images/Logo.gif");
	private JLabel logoLabel = new JLabel(logoImage);
	private JButton chooseModeButton = new JButton("Choose Mode");
	private JButton chooseThemeButton = new JButton("Choose Theme");
	private final String[] MODES = {"Easy", "Normal", "Hard"};
	private final String[] THEMES = {"New", "Classic", "Ms. PacMan", "Christmas", "Extreme 80s"};
	static String chosenMode = "Easy";
	static String chosenTheme = "New";
	
	public IntroScreen() {
		

		
		//set layout and background
		setLayout(null);
		setBackground(Color.black);
		
		//position logo
		logoLabel.setBounds(50, 100, 500, 227);
		add(logoLabel);
		
		//position and add buttons
		startButton.setBounds(160, 450, 100, 100);
		instructionButton.setBounds(280, 450, 150, 100);
		chooseModeButton.setBounds(130, 560, 155, 100);
		chooseThemeButton.setBounds(300, 560, 155, 100);
		add(startButton);
		//startButton.setFocusable(false);
		add(instructionButton);
		//instructionButton.setFocusable(false);
		add(chooseModeButton);
		//chooseModeButton.setFocusable(false);
		add(chooseThemeButton);
		
		
		//add action listeners to buttons
		startButton.addActionListener(this);
		instructionButton.addActionListener(this);
		chooseModeButton.addActionListener(this);
		chooseThemeButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==startButton) {
			//JOptionPane.showMessageDialog(this, "Are you ready?");
			PacManGUI.introScreen.setVisible(false);
			
			//PacManGUI.scoreBoardPanel.setVisible(true);
			//PacManGUI.scoreBoardPanel.setFocusable(false);
			//PacManGUI.highScorePanel.setFocusable(false);
			//PacManGUI.introScreen.dispose();
			//remove(PacManGUI.introScreen);
			//PacManGUI.introScreen.invalidate();
			//PacManGUI.board.validate();
			addKeyListener(PacManGUI.board);
			PacManGUI.board.setBounds(0, 100, 600, 600);
			add(PacManGUI.board);
			//board.setVisible(false);
			//PacManGUI.board.setVisible(true);
			
			PacManGUI.board.setVisible(true);
			//PacManGUI.board.setFocusable(true);
			//PacManGUI.board.requestFocus();
			//addKeyListener(PacManGUI.board);
			//this.close();
		}
		else if (e.getSource()==instructionButton) {
			JOptionPane.showMessageDialog(this, "Up arrow to move up, "
					+ "Down arrow to move down, Right arrow to move right,"
					+ " Left arrow to move left");
		}
		else if (e.getSource()==chooseModeButton) {
			chosenMode = (String) JOptionPane.showInputDialog(this, "What mode would you like to play in?", 
					"Difficulty Selection", JOptionPane.QUESTION_MESSAGE, null, MODES, MODES[1]);
			System.out.println(chosenMode);
		}
		else if (e.getSource()==chooseThemeButton) {
			chosenTheme = (String) JOptionPane.showInputDialog(this, "What theme would you like to use?", 
					"Theme Selection", JOptionPane.QUESTION_MESSAGE, null, THEMES, THEMES[0]);
			System.out.println(chosenTheme);
		}
	}

}
