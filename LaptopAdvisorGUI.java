/*
 * DOne: Buttons on reportPanel positioned, rankLaptops and setRatings stuff finished, changed x to index (current laptop), 
 * 		 added labels to reportPanel
 * To do: !!!Fix file input!!!, fix images on buttons, add weightings to score, transition topLaptops array to reportPanel, 
 * 		  add background, enable openBrowser, make it so that specs are set in the labels on the reportPanel, do something with the user class
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;


/*
 * This class contains a frame with two panels - user and report.  It allows the user to enter their    
* subjective ratings and their weightings for all criteria.  It then allows the user to see a report 
* that produces the top 3 laptops that best meet their criteria (i.e. score highest using the wdm).
*
* authors - Sanjana, Aalea, Sehel
*/

public class LaptopAdvisorGUI extends JFrame implements ActionListener{

	//start new instance of laptop class
	private Laptop[] laptop; 
	
	public int index; //variable that iterates through laptops

	//main frame
	JFrame applicationFrame = new JFrame("Laptop Advisor");

	//main menu panel
	JPanel mainMenuPanel = new JPanel(); 

	//main menu labels
	JLabel mainTitleLabel = new JLabel("Your Laptop Advisor");
	JLabel enterNameLabel = new JLabel("Enter Your Name");

	//main menu buttons
	JButton startButton = new JButton();
	JButton exitButton1 = new JButton();

	//main menu text area for user to input name
	JTextArea nameTextArea = new JTextArea();


	
	//user panel
	JPanel userPanel = new JPanel(); 

	//title label
	JLabel userTitleLabel = new JLabel("Laptop Advisor Program");
	
	//instruction labels
	JLabel budgetRating = new JLabel("What is your budget?"); 
	JLabel storage = new JLabel("What kind of storage would you like?"); 
	JLabel ram = new JLabel("What kind of RAM would you like?"); 
	JLabel vga = new JLabel("Do you want a VGA port on your computer?");
	JLabel screenSize = new JLabel("What is your preferred screen size?"); 
	JLabel color = new JLabel("What color would you like?");
	JLabel operatingSystem = new JLabel("What kind of operating system do you want?"); 
	JLabel cardReader = new JLabel("Do you want a card reader on your computer?"); 
	JLabel touchScreen = new JLabel("Do you want a touchscreen?"); 
	
	//create array for weighting labels
	JLabel[] weightingLabelArray = new JLabel[4];

	//user panel buttons
	JButton calculateButton = new JButton();
	JButton exitButton2 = new JButton();

	//user panel radio buttons
	JRadioButton[] budgetRadio = new JRadioButton[3]; 
	ButtonGroup budgetButtonGroup = new ButtonGroup(); 

	JRadioButton[] storageRadio = new JRadioButton[2]; 
	ButtonGroup storageButtonGroup = new ButtonGroup(); 

	JRadioButton[] ramRadio = new JRadioButton[2]; 
	ButtonGroup ramButtonGroup = new ButtonGroup(); 

	JRadioButton[] vgaRadio = new JRadioButton[2]; 
	ButtonGroup vgaButtonGroup = new ButtonGroup(); 

	JRadioButton[] screenSizeRadio = new JRadioButton[3]; 
	ButtonGroup screenSizeButtonGroup = new ButtonGroup(); 

	JRadioButton[] colorRadio = new JRadioButton[3]; 
	ButtonGroup colorButtonGroup = new ButtonGroup(); 

	JRadioButton[] operatingSystemRadio = new JRadioButton[3]; 
	ButtonGroup operatingSystemButtonGroup = new ButtonGroup(); 

	JRadioButton[] cardReaderRadio = new JRadioButton[2]; 
	ButtonGroup cardReaderButtonGroup = new ButtonGroup(); 

	JRadioButton[] touchScreenRadio = new JRadioButton[2]; 
	ButtonGroup touchScreenButtonGroup = new ButtonGroup(); 

	//user panel combo boxes
	JComboBox<Integer> weightingComboBox1 = new JComboBox<Integer>();
	JComboBox<Integer> weightingComboBox2 = new JComboBox<Integer>();
	JComboBox<Integer> weightingComboBox3 = new JComboBox<Integer>();
	JComboBox<Integer> weightingComboBox4 = new JComboBox<Integer>();


	
	//report panel
	JPanel reportPanel = new JPanel(); 

	//buttons on report panel that contain images of the top 3 laptops
	JButton laptopRanking1Button = new JButton();
	JButton laptopRanking2Button = new JButton();
	JButton laptopRanking3Button = new JButton();
	
	//labels on report panel that contain specifications on the top 3 laptops
	//TODO matches
	JLabel laptopRanking1Label = new JLabel();
	JLabel laptopRanking2Label = new JLabel();
	JLabel laptopRanking3Label = new JLabel();

	 /*
	  * A constructor method that reads in the full laptop list and sets up the user panel,
	  * report panel and frame
	  *
	  *  author - Aalea
	  */

	public LaptopAdvisorGUI(Laptop[] laptop){


		this.laptop = laptop;


		frameSetup();
		mainMenuSetup();
	}

	/*
	  * This method setups up the frame with a menu
	  *
	  * author - Sanjana
	*/
	
	private void frameSetup() {

		setSize(1366,768);
		setLayout(null);

		add(mainMenuPanel); 
		add(userPanel); 
		add(reportPanel);

		setVisible(true);
		
	}

	 /*
	  * This method setups the start screen where the user can input their name and go to the userPanel
	  *
	  * author - Sanjana
	  */

	private void mainMenuSetup() {

		//set up main menu panel 
		mainMenuPanel.setBounds(50,50,1366,768);
		mainMenuPanel.setLayout(null);
		mainMenuPanel.setVisible(true);

		//set up the labels
		enterNameLabel.setBounds(540,135,250,100);
		enterNameLabel.setFont(new Font("Calibri", Font.PLAIN, 20)); 

		mainTitleLabel.setBounds(315,50,625,65);
		mainTitleLabel.setFont(new Font("Calibri", Font.BOLD, 72));

		//set up the text area
		nameTextArea.setBounds(415,200,400,30);
		nameTextArea.setFont(new Font("Calibri", Font.PLAIN, 20));

		//set up the buttons
		startButton.setBounds(290, 300, 250, 100);
		startButton.setText("Find Your Laptop");
		startButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		startButton.addActionListener(this);

		exitButton1.setBounds(690,300,250,100);
		exitButton1.setText("Exit");
		exitButton1.setFont(new Font("Calibri", Font.PLAIN, 20));
		exitButton1.addActionListener(this);

		//add the components
		mainMenuPanel.add(enterNameLabel);
		mainMenuPanel.add(mainTitleLabel);
		mainMenuPanel.add(startButton);
		mainMenuPanel.add(exitButton1);
		mainMenuPanel.add(nameTextArea);

	}

	 /*
	  * This method setups up the user panel for inputting their subjective ratings and weightings
	  *
	  * author - Aalea
	  */

	private void userPanelSetup() {

		//set up the panel
		userPanel.setBounds(50,50,1366,768);
		userPanel.setLayout(null);

		//set up the buttons
		calculateButton.setBounds(425, 600, 200, 50);
		calculateButton.setText("Calculate");
		calculateButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		calculateButton.addActionListener(this);

		exitButton2.setBounds(675, 600, 200, 50);
		exitButton2.setText("Exit");
		exitButton2.setFont(new Font("Calibri", Font.PLAIN, 20));
		exitButton2.addActionListener(this);

		//set up the title label
		userTitleLabel.setBounds(400,0,700,100);
		userTitleLabel.setFont(new Font("Calibri", Font.BOLD, 50));

		//Set up the labels for each radio button
		budgetRating.setFont(new Font("Calibri", Font.BOLD, 15));
		budgetRating.setBounds(0, 80, 150, 60);

		storage.setFont(new Font("Calibri", Font.BOLD, 15));
		storage.setBounds(175, 80, 250, 60);

		ram.setFont(new Font("Calibri", Font.BOLD, 15));
		ram.setBounds(450, 80, 250, 60);

		vga.setFont(new Font("Calibri", Font.BOLD, 15));
		vga.setBounds(725, 80, 300, 60);

		screenSize.setFont(new Font("Calibri", Font.BOLD, 15));
		screenSize.setBounds(1050, 80, 300, 60);

		color.setFont(new Font("Calibri", Font.BOLD, 15));
		color.setBounds(0, 210, 200, 60);

		operatingSystem.setFont(new Font("Calibri", Font.BOLD, 15));
		operatingSystem.setBounds(300, 210, 300, 60);

		cardReader.setFont(new Font("Calibri", Font.BOLD, 15));
		cardReader.setBounds(700, 210, 300, 60);

		touchScreen.setFont(new Font("Calibri", Font.BOLD, 15));
		touchScreen.setBounds(1050, 210, 200, 60);

		//adds the labels to the panel
		userPanel.add(userTitleLabel);

		userPanel.add(budgetRating);
		userPanel.add(storage);
		userPanel.add(ram);
		userPanel.add(vga);
		userPanel.add(screenSize);
		userPanel.add(color);
		userPanel.add(operatingSystem);
		userPanel.add(cardReader);
		userPanel.add(touchScreen);

		//add the buttons to the panel
		userPanel.add(calculateButton);
		userPanel.add(exitButton2);

		//calls the classes to set up the rest of the userPanel
		setupRadioButtons(); 
		setupWeightings();

		//make userPanel visible
		userPanel.setVisible(true);

	}

	 /*
	  * This method sets up the radio buttons
	  *
	  * author - Sanjana
	  */
	
	private void setupRadioButtons() {

		//The radio buttons
		budgetRadio[0] = new JRadioButton("Less than $500");
		budgetRadio[1] = new JRadioButton("$500 to $1000");
		budgetRadio[2] = new JRadioButton("More than $1000");

		storageRadio[0] = new JRadioButton("HDD");
		storageRadio[1] = new JRadioButton("SDD");

		ramRadio[0] = new JRadioButton("DDR3");
		ramRadio[1] = new JRadioButton("DDR4");

		vgaRadio[0] = new JRadioButton("Yes");
		vgaRadio[1] = new JRadioButton("No");

		screenSizeRadio[0] = new JRadioButton("11.6 inches");
		screenSizeRadio[1] = new JRadioButton("12.5 inches");
		screenSizeRadio[2] = new JRadioButton("15.6 inches");

		colorRadio[0] = new JRadioButton("Black");
		colorRadio[1] = new JRadioButton("White");
		colorRadio[2] = new JRadioButton("Blue");

		operatingSystemRadio[0] = new JRadioButton("Windows");
		operatingSystemRadio[1] = new JRadioButton("MacOS");
		operatingSystemRadio[2] = new JRadioButton("Chrome OS");

		cardReaderRadio[0] = new JRadioButton("Yes");
		cardReaderRadio[1] = new JRadioButton("No");

		touchScreenRadio[0] = new JRadioButton("Yes");
		touchScreenRadio[1] = new JRadioButton("No");

		int y1 = 125; 
		int y2 = 250;

		for (int row = 0; row <= 2; row++) {

			budgetRadio[row].setBounds(0, y1, 150, 30);
			budgetRadio[row].addActionListener(this);
			userPanel.add(budgetRadio[row]);
			budgetButtonGroup.add( budgetRadio[row] ) ;

			screenSizeRadio[row].setBounds(1050, y1, 300, 30);
			screenSizeRadio[row].addActionListener(this);
			userPanel.add(screenSizeRadio[row]);
			screenSizeButtonGroup.add( screenSizeRadio[row] ) ;

			y1 = y1 + 25; 

		}

		y1 = 125;

		for (int row = 0; row <= 1; row++) {
			
			storageRadio[row].setBounds(175, y1, 250, 30);
			storageRadio[row].addActionListener(this);
			userPanel.add(storageRadio[row]);
			storageButtonGroup.add( storageRadio[row] ) ;

			ramRadio[row].setBounds(450, y1, 250, 30);
			ramRadio[row].addActionListener(this);
			userPanel.add(ramRadio[row]);
			ramButtonGroup.add( ramRadio[row] ) ;

			vgaRadio[row].setBounds(725, y1, 300, 30);
			vgaRadio[row].addActionListener(this);
			userPanel.add(vgaRadio[row]);
			vgaButtonGroup.add( vgaRadio[row] ) ;

			y1 = y1 + 25; 

		}

		for (int row = 0; row <= 2; row++) {

			colorRadio[row].setBounds(0, y2, 100, 30);
			colorRadio[row].addActionListener(this);
			userPanel.add(colorRadio[row]);
			colorButtonGroup.add( colorRadio[row] ) ;

			operatingSystemRadio[row].setBounds(300, y2, 300, 30);
			operatingSystemRadio[row].addActionListener(this);
			userPanel.add(operatingSystemRadio[row]);
			operatingSystemButtonGroup.add( operatingSystemRadio[row] ) ;

			y2 = y2 + 25; 
			
		}

		y2 = 250;

		for (int row = 0; row <= 1; row++) {

			cardReaderRadio[row].setBounds(700, y2, 300, 30);
			cardReaderRadio[row].addActionListener(this);
			userPanel.add(cardReaderRadio[row]);
			cardReaderButtonGroup.add( cardReaderRadio[row] ) ;

			touchScreenRadio[row].setBounds(1050, y2, 100, 30);
			touchScreenRadio[row].addActionListener(this);
			userPanel.add(touchScreenRadio[row]);
			touchScreenButtonGroup.add( touchScreenRadio[row] ) ;

			y2 = y2 + 25; 

		}

	}

	 /*
	  * This method setups up the weightings
	  *
	  * author - Sanjana
	  */

	private void setupWeightings() {

		//set ups the labels for each combo box
		weightingLabelArray[0] = new JLabel("Does the processer (CPU) matter to you?");
		weightingLabelArray[1] = new JLabel("Does the storage (size and type) matter to you?");
		weightingLabelArray[2] = new JLabel("Does the RAM matter to you?");
		weightingLabelArray[3] = new JLabel("Do the graphics matter to you?");

		//adds the label to the user panel and gives the label a font
		for (int num = 0; num <= 3; num++) {

			weightingLabelArray[num].setFont(new Font("Calibri", Font.BOLD, 15));
			userPanel.add(weightingLabelArray[num]);

		}

		//sets the bounds for the labels
		weightingLabelArray[0].setBounds(0, 325, 500, 100);
		weightingLabelArray[1].setBounds(325, 325, 500, 100);
		weightingLabelArray[2].setBounds(700, 325, 500, 100);
		weightingLabelArray[3].setBounds(1025, 325, 500, 100);

		//sets the numbers for each combobox
		for (int num1 = 0; num1 <= 10; num1++) {
			weightingComboBox1.addItem(num1);
		}

		for (int num1 = 0; num1 <= 10; num1++) {
			weightingComboBox2.addItem(num1);
		}

		for (int num1 = 0; num1 <= 10; num1++) {
			weightingComboBox3.addItem(num1);
		}

		for (int num1 = 0; num1 <= 10; num1++) {
			weightingComboBox4.addItem(num1);
		}
		
		//adds the comboboxes to the userpanel and sets the bounds 
		userPanel.add(weightingComboBox1);
		weightingComboBox1.addActionListener(this); 
		weightingComboBox1.setBounds(0, 400, 250, 20);
		weightingComboBox1.setVisible(true);

		userPanel.add(weightingComboBox2);
		weightingComboBox2.addActionListener(this); 
		weightingComboBox2.setBounds(325, 400, 250, 20);
		weightingComboBox2.setVisible(true);

		userPanel.add(weightingComboBox3);
		weightingComboBox3.addActionListener(this); 
		weightingComboBox3.setBounds(700, 400, 250, 20);
		weightingComboBox3.setVisible(true);

		userPanel.add(weightingComboBox4);
		weightingComboBox4.addActionListener(this); 
		weightingComboBox4.setBounds(1025, 400, 250, 20);
		weightingComboBox4.setVisible(true);

	}

	 /*
	  * This method sets up the report panel
	  *
	  * author - Aalea
	  */

	private void reportPanelSetup(int[] topLaptops) {

		reportPanel.setBounds(0,0,1366,768);
		reportPanel.setLayout(null);

		laptopRanking1Button.setBounds(0, 10, 450, 400);
		laptopRanking1Button.setIcon(new ImageIcon(new ImageIcon("testPhoto.jpg").getImage().getScaledInstance(450, 400, 0)));
		laptopRanking1Button.addActionListener(this);

		laptopRanking2Button.setBounds(450, 10, 450, 400);
		laptopRanking2Button.setIcon(new ImageIcon(new ImageIcon("testPhoto.jpg").getImage().getScaledInstance(450, 400, 0)));
		laptopRanking2Button.addActionListener(this);

		laptopRanking3Button.setBounds(900, 10, 450, 400);
		laptopRanking3Button.setIcon(new ImageIcon(new ImageIcon("testPhoto.jpg").getImage().getScaledInstance(450, 400, 0)));
		laptopRanking3Button.addActionListener(this);
		
		laptopRanking1Label.setBounds(0,400,450,200);
		laptopRanking1Label.setText("Hello1");
		
		laptopRanking2Label.setBounds(450,400,450,200);
		laptopRanking2Label.setText("Hello2");
		
		laptopRanking3Label.setBounds(900,400,450,200);
		laptopRanking3Label.setText("Hello3");
		
		
		reportPanel.add(laptopRanking1Button);
		reportPanel.add(laptopRanking2Button);
		reportPanel.add(laptopRanking3Button);
		
		reportPanel.add(laptopRanking1Label);
		reportPanel.add(laptopRanking2Label);
		reportPanel.add(laptopRanking3Label);

		reportPanel.setVisible(true);
		
	}

	 /*
	  * This method handles all the actions that may occur on the GUI - menus, radio buttons, combo 
	  * boxes, report button, laptop image buttons
	  *
	  * author - Sanjana
	  */

	public void actionPerformed(ActionEvent e) {

		//exit buttons
		if (e.getSource() == exitButton1 || e.getSource() == exitButton2)
			System.exit(0);

		//start button
		if (e.getSource() == startButton){

			//takes out the main menu panel
			remove(mainMenuPanel); 

			//calls the setup of the user panel
			userPanelSetup();

			//adds the user panel to the frame
			add(userPanel); 

			//refreshes the frame 
			this.repaint();

		}

		//calculate button
		if (e.getSource() == calculateButton){

			createReport();

		}

		//budget radio buttons
		if (e.getSource() == budgetRadio[0])	
			setBudgetRating(0); 
		else if (e.getSource() == budgetRadio[1]) 
			setBudgetRating(1);
		else
			setBudgetRating(2);

		//storage radio buttons
		if (e.getSource() == storageRadio[0])
			setStorageTypeRating(0); 
		else
			setStorageTypeRating(1);

		//ram radio buttons
		if (e.getSource() == ramRadio[0])
			setRamTypeRating(0); 
		else 
			setRamTypeRating(1);

		//vga radio buttons
		if (e.getSource() == vgaRadio[0])
			setVGARating(0); 
		else
			setVGARating(1);

		//screen size radio buttons
		if (e.getSource() == screenSizeRadio[0])
			setBudgetRating(0); 
		else if (e.getSource() == screenSizeRadio[1]) 
			setDisplaySizeRating(1);
		else
			setDisplaySizeRating(2);

		//color radio buttons
		if (e.getSource() == colorRadio[0])
			setColorRating(0); 
		else if (e.getSource() == colorRadio[1]) 
			setColorRating(1);
		else
			setColorRating(2);

		//operating system radio buttons
		if (e.getSource() == operatingSystemRadio[0])
			setOperatingSystemRating(0); 
		else if (e.getSource() == operatingSystemRadio[1]) 
			setOperatingSystemRating(1);
		else
			setOperatingSystemRating(2);

		//card reader radio buttons
		if (e.getSource() == cardReaderRadio[0])
			setCardReaderRating(0); 
		else 
			setCardReaderRating(1);

		//touch screen radio buttons
		if (e.getSource() == touchScreenRadio[0])
			setTouchScreenRating(0); 
		else 
			setTouchScreenRating(1);

	}

	
	 /*
	  * This method opens the web browser depending on the index of the laptop; it uses the java 
	  * Desktop class
	  *
	  * author - Sehel
	  */

	//private void openWebBrowser(int index) {

	//}

	 /*
	  * This method sets the budget rating (0, 5 or 10) for each laptop depending on the user’s choice 
	  * (budget, medium or high-end)
	  *
	  * author - Sehel
	  */
	private int setBudgetRating(int index) {

		int priceRating = 5;
		int score = 0;

		if (budgetRadio[0].isSelected() == true) {

			if (laptop[index].getPrice() < 500)
				score = 10 * priceRating;
			else if (laptop[index].getPrice() >= 500 && laptop[index].getPrice() <= 1000)
				score = 5 * priceRating;
			else
				score = 0;

		} else if (budgetRadio[1].isSelected() == true){

			if (laptop[index].getPrice() < 500.00)
				score = 5 * priceRating;
			else if (laptop[index].getPrice() >= 500.00 && laptop[index].getPrice() <= 1000.00)
				score = 10 * priceRating;
			else if (laptop[index].getPrice() > 1000.00)
				score = 5 * priceRating;

		} else if (budgetRadio[2].isSelected() == true){

			if (laptop[index].getPrice() < 500.00)
				score = 0;
			else if (laptop[index].getPrice() >= 500 && laptop[index].getPrice() <= 1000)
				score = 5 * priceRating;
			else if (laptop[index].getPrice() > 1000)
				score = 10 * priceRating;
			
		} else			
			return 0;

		
		return score;
		
	}


	private int setStorageTypeRating(int index) {

		int storageTypeRating = 1;
		int score = 0;
		
		if (storageRadio[0].isSelected() == true) {

			if (laptop[index].getStorageType().contains("HDD"))
				score = 10 * storageTypeRating;
			else if (laptop[index].getStorageType().contains("SSD"))
				score = 5 * storageTypeRating;
			else
				score = 0;

		} else if (storageRadio[1].isSelected() == true){

			if (laptop[index].getStorageType().contains("HDD"))
				score = 5 * storageTypeRating;
			else if (laptop[index].getStorageType().contains("SSD"))
				score = 10 * storageTypeRating;
			else 
				score = 0;

		}
		
		return score;

	}

	private int setRamTypeRating(int index) {
		
		int ramTypeRating = 1;
		int score = 0;

		if (ramRadio[0].isSelected() == true) {

			if (laptop[index].getRamType().contains("DDR3"))
				score = 10 * ramTypeRating;
			else if (laptop[index].getStorageType().contains("DDR4"))
				score = 5 * ramTypeRating;
			else
				score = 0;

		} else if (ramRadio[1].isSelected() == true){

			if (laptop[index].getRamType().contains("DDR3"))
				score = 5 * ramTypeRating;
			else if (laptop[index].getRamType().contains("DDR4"))
				score = 10 * ramTypeRating;
			else 
				score = 0;

		}
		
		return score;

	}

	private int setVGARating(int index) {

		int vgaTypeRating = 1;
		int score = 0;
		
		if (vgaRadio[0].isSelected() == true) {

			if (laptop[index].getOtherPorts().toUpperCase().contains("VGA"))
				score = 10 * vgaTypeRating;
			else
				score = 5;

		} else if (vgaRadio[1].isSelected() == true){

			if (laptop[index].getOtherPorts().toUpperCase().contains("VGA"))
				score = 5 * vgaTypeRating;
			else 
				score = 10;

		}
		
		return score;
		
	}

	private int setDisplaySizeRating(int index) {

		int displaySizeRating = 1;
		int score = 0;

		if (screenSizeRadio[0].isSelected() == true) {

			if (laptop[index].getDisplaySize() <= 11.6)
				score = 10 * displaySizeRating;
			else if (laptop[index].getDisplaySize() > 11.6 && laptop[index].getDisplaySize() < 12.5)
				score = 5 * displaySizeRating;
			else
				score = 0;

		} else if (screenSizeRadio[1].isSelected() == true){

			if (laptop[index].getDisplaySize() <= 500)
				score = 5 * displaySizeRating;
			else if (laptop[index].getDisplaySize() >= 500 && laptop[index].getDisplaySize() <= 1000)
				score = 10 * displaySizeRating;
			else if (laptop[index].getDisplaySize() > 1000)
				score = 5 * displaySizeRating;

		} else if (screenSizeRadio[2].isSelected() == true){

			if (laptop[index].getDisplaySize() < 500)
				score = 0;
			else if (laptop[index].getDisplaySize() >= 500 && laptop[index].getDisplaySize() <= 1000)
				score = 5 * displaySizeRating;
			else if (laptop[index].getDisplaySize() > 1000)
				score = 10 * displaySizeRating;
			
		} else			
			score = 0;

		
		return score;
	}

	private int setColorRating(int index) {

		int colorRating = 3;
		int score = 0;

		if (colorRadio[0].isSelected() == true) {

			if (laptop[index].getColour().contains("Black"))
				score = 10 * colorRating;
			else
				score = 5;

		} else if (colorRadio[1].isSelected() == true){

			if (laptop[index].getColour().contains("White"))
				score = 10 * colorRating;
			else 
				score = 5;

		} else if (screenSizeRadio[2].isSelected() == true){

			if (laptop[index].getColour().contains("Blue"))
				score = 10 * colorRating;
			else 
				score = 5;
			
		} else			
			score = 0;

		
		return score;

	}

	private int setOperatingSystemRating(int index) {

		int operatingSystemRating = 7;
		int score = 0;

		if (operatingSystemRadio[0].isSelected() == true) {

			if (laptop[index].getSoftware().contains("Windows"))
				score = 10 * operatingSystemRating;
			else
				score = 0;

		} else if (operatingSystemRadio[1].isSelected() == true){

			if (laptop[index].getSoftware().toUpperCase().contains("MACOS") || laptop[index].getSoftware().toUpperCase().contains("MAC OS"))
				score = 10 * operatingSystemRating;
			else 
				score = 0;

		} else if (operatingSystemRadio[2].isSelected() == true){

			if (laptop[index].getSoftware().contains("Chrome OS"))
				score = 10 * operatingSystemRating;
			else 
				score = 0;
			
		} else			
			score = 0;

		
		return score;
		
	}

	private int setCardReaderRating(int index) {
		
		int cardReaderRating = 7;
		int score = 0;
		
		if (cardReaderRadio[0].isSelected() == true) {

			if (laptop[index].getInputOutput().toUpperCase().contains("CARD READER"))
				score = 10 * cardReaderRating;
			else
				score = 0;

		} else if (vgaRadio[1].isSelected() == true){

			if (laptop[index].getInputOutput().toUpperCase().contains("CARD READER"))
				score = 0 * cardReaderRating;
			else 
				score = 10;

		}
		
		return score;

	}

	private int setTouchScreenRating(int index) {
		
		int touchScreenRating = 10;
		int score = 0;
		
		if (touchScreenRadio[0].isSelected() == true) {

			if (laptop[index].getOtherFeatures().toUpperCase().contains("TOUCH SCREEN") || laptop[index].getOtherFeatures().toUpperCase().contains("TOUCHSCREEN"))
				score = 10 * touchScreenRating;
			else
				score = 0;

		} else if (touchScreenRadio[1].isSelected() == true){

			if (laptop[index].getOtherFeatures().toUpperCase().contains("TOUCH SCREEN") || laptop[index].getOtherFeatures().toUpperCase().contains("TOUCHSCREEN"))
				score = 0 * touchScreenRating;
			else 
				score = 10;

		}
		
		return score;
	}

	 /*
	  * This method creates the report
	  *
	  * author - Sehel and Aalea
	  */

	private void createReport() {

		//takes out the user panel
		remove(userPanel); 
		
		for (index = 0; index <= 28 ; index++)
			setBudgetRating(index);
			setStorageTypeRating(index);
			setRamTypeRating(index);
			setVGARating(index);
			setDisplaySizeRating(index);
			setColorRating(index);
			setOperatingSystemRating(index);
			setCardReaderRating(index);
			
			rankLaptops(index);
			
		}

		//calls the setup of the report panel
		reportPanelSetup(topLaptops);

		//adds the report panel to the frame
		add(reportPanel); 

		//refreshes the frame 
		this.repaint();


	}

	 /*
	  * This method ranks the laptops
	  *
	  * author - Aalea
	  */

	private void rankLaptops(int index){
		
		public int topLaptops[] = new int[3];

		wdm(index, user);

		if (laptop[index].getScore() > topLaptops[0]) 
			topLaptops[0] = laptop[index].getScore();
		else if (laptop[index].getScore() > topLaptops[1])
			topLaptops[1] = laptop[index].getScore();
		else if (laptop[index].getScore() > topLaptops[2])
			topLaptops[2] = laptop[index].getScore();

	//Set the contents of the laptop text area and image button


	}


	private int wdm(Laptop currentLaptop, User user) { //TODO do something with the user class


		int totalScore = 0; // the score of the current laptop
		 
		 for (int counter = ratings.length; counter >= 0; counter--) { // loop to iterate through fields that are weighted
			 
			 totalScore = totalScore + rating[counter] * weighting[counter]; // TODO we gotta make something for the combo boxes
			 
		 }
		 
		 return totalScore; // returns the score of the current laptop to rankLaptops?
		 
	}
	
	
}