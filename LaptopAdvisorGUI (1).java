import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

/*
 * This class contains a frame with two panels - user and report.  It allows the user to enter their    
* subjective ratings and their weightings for all criteria.  It then allows the user to see a report 
* that produces the top 3 laptops that best meet their criteria (i.e. score highest using the wdm).
*
* authors - Sanjana, Aalea, Sehel
*/

public class LaptopAdvisorGUI extends JFrame implements ActionListener{

	private Laptop[] laptop; //field
	//fields
	//create a new user
	 //frame and frame menu elements
	 //user panel and user panel elements
	 //elements to fill the weightings array - labels and comboboxes
	 //create report button
	 //report panel and report panel elements
	 //list of laptops
	 //list of the index of top 3 laptops
	
	public LaptopAdvisorGUI(Laptop[] laptopArray){
		
		 /*
		  * A constructor method that reads in the full laptop list and sets up the user panel,
		  * report panel and frame
		  *
		  *  author - Aalea
		  */
		
		this.laptop = laptop;
		
		JPanel userPanel = new JPanel();
		userPanel.userPanelSetup();
		
		JPanel reportPanel = new JPanel();
		reportPanel.reportPanelSetup();
		
		JFrame application = new JFrame();
		application.add(userPanel); //add panel to frame TODO make it so after user submits preferences, switch to reportPanel
		application.setSize(250, 250); //set size of frame
		application.setVisible(true); //make frame visible
		LaptopAdvisorGUI.frameSetup();
		
	}
	
	 /*
	  * This method setups up the frame with a menu
	  *
	  * author - Sanjana
	  */
	 private void frameSetup() {
	  
	 }
	 


	 /*
	  * This method setups up the user panel for inputting their subjective ratings and weightings
	  *
	  * author - Sehel
	  */
	 private void userPanelSetup() extends JPanel{


	 }
	 
	 /*
	  * This method sets up the radio buttons
	  *
	  * author - Sanjana
	  */
	 private void setupRadioButtons() {


	 }
	 
	 /*
	  * This method setups up the weightings
	  *
	  * author - Sanjana
	  */
	 private void setupWeightings() {


	 }
	 
	 /*
	  * This method sets up the report panel
	  *
	  * author - Aalea
	  */
	 private void reportPanelSetup() extends JPanel{
		 
		 JButton firstLaptopButton = new JButton("image[].png"); // TODO name laptop picture files properly and create a variable
		 JButton secondLaptopButton = new JButton("image[laptopNum].png"); // TODO or create 'laptop #' column in database
		 JButton thirdLaptopButton = new JButton("image[laptopNum].png");
		 
		 firstLaptopButton.setBounds(20, 20, 100, 70); //position first laptop
		 add(firstLaptopButton);
		 
		 secondLaptopButton.setBounds(200, 20, 100, 70); //position second laptop
		 add(secondLaptopButton);
		 
		 thirdLaptopButton.setBounds(450, 20, 100, 70); //position third laptop
		 add(thirdLaptopButton);
		 
		 firstLaptopButton.ActionListener(this); // TODO fix and connect to openBrowser
		 secondLaptopButton.ActionListener(this); 
		 thirdLaptopButton.ActionListener(this);
		 
		 JLabel firstLaptopLabel = new JLabel(laptop[laptopNum]); //creates labels
		 JLabel secondLaptopLabel = new JLabel(laptop[laptopNum]);
		 JLabel thirdLaptopLabel = new JLabel(laptop[laptopNum]);
		 
		 setLayout(null);
		 
		 //positions labels
		 firstLaptopLabel.setBounds(20, 100, 100, 70); 
		 add(firstLaptopLabel);
		 
		 secondLaptopLabel.setBounds(200, 100, 100, 70);
		 add(secondLaptopLabel);
		 
		 thirdLaptopLabel.setBounds(450, 100, 100, 70);
		 add(thirdLaptopLabel);
		 
		 
	  
	 }
	 


	 /*
	  * This method handles all the actions that may occur on the GUI - menus, radio buttons, combo 
	  * boxes, report button, laptop image buttons
	  *
	  * author - Sanjana
	  */
	 public void actionPerformed(ActionEvent e) {
	  
	 }
	 
	 /*
	  * This method opens the web browser depending on the index of the laptop; it uses the java 
	  * Desktop class
	  *
	  * author - Sehel
	  */
	 private void openWebBrowser(int index) {
	 
	 }
	 
	 /*
	  * This method sets the budget rating (0, 5 or 10) for each laptop depending on the user’s choice 
	  * (budget, medium or high-end)
	  *
	  * author - Sehel
	  */
	 private void setBudgetRating(int choice) {


	 }


	  /*
	  * This method sets the screen size rating (0, 5 or 10) for each laptop depending on the user’s 
	  * choice (small, medium or large)
	  *
	  * author - Sehel
	  */
	 private void setScreenSizeRating(int choice) {


	 }




	 /*
	  * This method creates the report
	  *
	  * author - Sehel
	  */
	 private void createReport() {
	 
	 //remove the user panel from the frame


	  //rank the laptops


	  //add the report panel to the frame and repaint the frame


	 }


	 
	 /*
	  * This method ranks the laptops
	  *
	  * author - Sanjana
	  */
	 private void rankLaptops(){


	  //For each laptop set its score by using a Weighted Decision Matrix


	  //Determine the top 3 scores


	  //Set the contents of the laptop text area and image button


	 }
	 
	 /*
	  * This method returns the total score by using the concept of a Weighted Decision Matrix
	  *
	  * author - Aalea
	  */
	 private int wdm(Laptop currentLaptop, User user) {
		 
		
	 
	 }


	}
	
}
