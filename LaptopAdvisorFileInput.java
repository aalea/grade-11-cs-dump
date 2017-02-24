

import java.io.*;
import java.util.*;

import Laptop;

public class LaptopAdvisorFileInput {
	
	//new LaptopAdvisorFileInput(Laptop e);
	//}
	//new Laptop(); 
	//};
	
	public LaptopAdvisorFileInput (Laptop[] laptop) {
		
		try {
			
			Scanner input = new Scanner (new File("laptops.csv"));
			input.useDelimiter(",");
			input.nextLine();
			int index = 0;
			
			//while (input.hasNextLine() == true) {
			while (index <= 28) {
				
				laptop[index] = new Laptop();

				laptop[index].setBrand(input.next());
				
				laptop[index].setType(input.next());
				
				laptop[index].setModel(input.next());
				
				laptop[index].setPrice(input.nextDouble());
				
				laptop[index].setDisplaySize(input.nextDouble());
				
				//laptop[index].getRatings()[0] = input.nextDouble();
				
				laptop[index].setWeight(input.nextDouble());

				laptop[index].setDisplayResolution(input.next());
				
				laptop[index].setProcessorType(input.next());
				
				laptop[index].setProcessorCores(input.nextInt());
				
				laptop[index].setProcessorSpeed(input.nextDouble());
				
				laptop[index].getRatings()[1] = input.nextDouble(); //Processor ratings
				
				laptop[index].setStorage(input.nextDouble()); 

				laptop[index].setStorageType(input.next());
				
				laptop[index].getRatings()[2] = input.nextDouble();

				laptop[index].setRam(input.nextInt());

				laptop[index].setRamType(input.next());
				
				laptop[index].getRatings()[3] = input.nextDouble();
				
				laptop[index].setGraphics(input.next());
				
				laptop[index].getRatings()[4] = input.nextDouble(); //Graphics ratings

				laptop[index].setAudio(input.next());

				laptop[index].setInputOutput(input.next());

				laptop[index].setUsb2(input.nextInt());

				laptop[index].setUsb3(input.nextInt());

				laptop[index].setHdmi(input.nextBoolean());
				
				//laptop[index].getRatings()[5] = input.nextDouble();

				laptop[index].setOtherPorts(input.next()); // TODO get rid of headphone jacks or add to all

				laptop[index].setNetworking(input.next());

				laptop[index].setPower(input.next());

				laptop[index].setBatteryLife(input.next());

				laptop[index].setSoftware(input.next());

				laptop[index].setColour(input.next());

				laptop[index].setWarranty(input.next());

				laptop[index].setOtherFeatures(input.next());

				laptop[index].setHyperlink(input.next());

				//laptop[index].setScore(input.nextInt()); 
				
				index++;
				
			}
			
			input.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("ERROR! File cannot be read");
		}
	
	}
}
