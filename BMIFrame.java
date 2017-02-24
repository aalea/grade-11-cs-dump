package com;

import java.util.Scanner;

import javax.swing.*;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**											
 * @author 340795954
 *
 */

public class BMIFrame extends JFrame implements ActionListener{
	
	//constructor method
	public BMIFrame(){
		
		//setup frame
		setSize(500,500);
		setTitle("BMI Calculator");
		setVisible(true);
		
		//create labels
		JLabel instructionWeightLabel = new JLabel("Weight (pounds)");
		JLabel instructionHeightLabel = new JLabel("Height (inches)");
		JLabel bodyMassIndexLabel = new JLabel();
		
		//create text areas
		JTextArea weightTextArea = new JTextArea();
		JTextArea heightTextArea = new JTextArea();
		
		//create buttons
		JButton calculateButton = new JButton();
		
		//position labels
		setLayout(null);
		
		//position instructions for weight
		instructionWeightLabel.setBounds(50,50,100,50);
		add(instructionWeightLabel);
		
		//position instructions for height
		instructionHeightLabel.setBounds(50,100,100,50);
		add(instructionHeightLabel);
		
		//position text area for weight
		weightTextArea.setBounds(70,50,100,50);
		add(weightTextArea);
		
		//position text area for height
		heightTextArea.setBounds(70,100,100,50);
		add(heightTextArea);
		
		//position button
		calculateButton.setBounds(60,150,100,50);
		add(calculateButton);
		
		
		
		
	}

	public static void actionPerformed(ActionEvent e) {
		
		if (Event.getSource()==calculateButton){
			int weight = Integer.valueOf(weightTextArea.getText());
			int height = Integer.valueOf(heightTextArea.getText());		
			bodyMassIndexLabel.setText( weight / ( height * height ));
			
		}
		
	}
	


}
