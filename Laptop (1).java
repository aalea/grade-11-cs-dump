package lap;

/*
* This is a template class that defines a laptop object.  It includes a list of fields that define the laptop (i.e. 
* one for each column in the spreadsheet - except the ratings).  Then an array that holds all the ratings 
* (both objective and subjective).  And finally the overall score for the laptop.
*
* author - Sehel
*/

public class Laptop {

	private String brand;
	private String type;
	private String model;
	private double price;
	private double displaySize;
	private double weight;
	private String displayResolution; 
	private String processorType;
	private int processorCores;
	private double processorSpeed;
	private double storage;
	private String storageType;
	private int ram;
	private String ramType;
	private String graphics;
	private String audio;
	private String inputOutput;
	private int usb2, usb3;
	private boolean hdmi;
	private String otherPorts;
	private String networking;
	private String power;
	private String batteryLife;
	private String software;
	private String colour;
	private String warranty;
	private String otherFeatures;
	private String hyperlink;
	
	private double[] ratings = new double[6]; // TODO create if statements when things in database are null
	/*
	 * 0 - Display size
	 * 1 - Processor type TODO also compare speeds
	 * 2 - Storage type TODO also compare amounts
	 * 3 - RAM type TODO also compare amounts
	 * 4 - Graphics
	 * 5 - HDMI TODO if HDMI true, add to score if user wanted
	 */
	private int score;
	static Laptop [] laptop = new Laptop [29]; 

	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(double displaySize) {
		this.displaySize = displaySize;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getProcessorType() {
		return processorType;
	}

	public void setProcessorType(String processorType) {
		this.processorType = processorType;
	}

	public int getProcessorCores() {
		return processorCores;
	}

	public void setProcessorCores(int processorCores) {
		this.processorCores = processorCores;
	}

	public double getProcessorSpeed() {
		return processorSpeed;
	}

	public void setProcessorSpeed(double processorSpeed) {
		this.processorSpeed = processorSpeed;
	}

	public double getStorage() {
		return storage;
	}

	public void setStorage(double storage) {
		this.storage = storage;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getRamType() {
		return ramType;
	}

	public void setRamType(String ramType) {
		this.ramType = ramType;
	}

	public String getGraphics() {
		return graphics;
	}

	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	/**
	 * @return the inputOutput
	 */
	public String getInputOutput() {
		return inputOutput;
	}

	/**
	 * @param inputOutput the inputOutput to set
	 */
	public void setInputOutput(String inputOutput) {
		this.inputOutput = inputOutput;
	}

	/**
	 * @return the usb2
	 */
	public int getUsb2() {
		return usb2;
	}

	/**
	 * @param usb2 the usb2 to set
	 */
	public void setUsb2(int usb2) {
		this.usb2 = usb2;
	}

	/**
	 * @return the usb3
	 */
	public int getUsb3() {
		return usb3;
	}

	/**
	 * @param usb3 the usb3 to set
	 */
	public void setUsb3(int usb3) {
		this.usb3 = usb3;
	}

	/**
	 * @return the hdmi
	 */
	public boolean isHdmi() {
		return hdmi;
	}

	/**
	 * @param hdmi the hdmi to set
	 */
	public void setHdmi(boolean hdmi) {
		this.hdmi = hdmi;
	}

	/**
	 * @return the otherPorts
	 */
	public String getOtherPorts() {
		return otherPorts;
	}

	/**
	 * @param otherPorts the otherPorts to set
	 */
	public void setOtherPorts(String otherPorts) {
		this.otherPorts = otherPorts;
	}
	
	/**
	 * @return the otherPorts
	 */
	public String getDisplayResolution() {
		return displayResolution;
	}
	
	/**
	 * @param displayResolution the displayResolution to set
	 */
	public void setDisplayResolution(String displayResolution) {
		this.displayResolution = displayResolution;
	}

	public String getNetworking() {
		return networking;
	}

	public void setNetworking(String networking) {
		this.networking = networking;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getOtherFeatures() {
		return otherFeatures;
	}

	public void setOtherFeatures(String otherFeatures) {
		this.otherFeatures = otherFeatures;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	

	/**
	 * @return the ratings
	 */
	public double[] getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(double[] ratings) { //TODO Ratings are double from now on OK
		this.ratings = ratings;
	}

	@Override																		//	"\nModel #: "
	public String toString() {
		return brand + " " + model + " " + type + "\nPrice: $" + price + "\nDisplay Size: "
				+ displaySize + " inches" + "\nWeight: " + weight + " pounds" + "\nResolution: " + displayResolution + "\nProcessor Type: "
				+ processorType + "\nProcessor Cores: " + processorCores + "\nProcessor Speed: " + processorSpeed
				+ " GHz" + "\nStorage amount: " + storage + " GB" + "\nStorage Type: " + storageType + "\nRAM amount: " + ram + " GB" + "\nRAM Type: " + ramType
				+ "\nGraphics: " + graphics + "\nAudio: " + audio + "\nInput and Output: " + inputOutput + "\nNumber of USB 2.0 ports: " + usb2
				+ "\nNumber of USB 3.0 ports: " + usb3 + "\nHDMI? " + hdmi + "\nOther ports: " + otherPorts + "\nNetworking: " + networking
				+ "\nBattery: " + power + "\nBattery Life: " + batteryLife + "\nOperating System: " + software + "\nColour: " + colour
				+ "\nWarranty: " + warranty + "\nOther Features: " + otherFeatures + "\nHyperlink: " + hyperlink
				+ "\nScore: " + score + "\n"; 
	}



}
