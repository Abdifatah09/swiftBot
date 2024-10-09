// Testing.java
package danceTask;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import swiftbot.*;

public class Testing {
	// Instance variables
	private QRCodeScanner qrCodeScanner;
	private HexadecimalConverter converter;
	private MovementController movementController;
	private PhotoTaker photoTaker;
	private ArrayList<String> scannedHexValues;
	private boolean continueDancing;
	private boolean buttonYEnabled = false; // Add this line
	private boolean buttonXEnabled = false; // Add this line

	public Testing(SwiftBotAPI swiftBot) {
		//Constructors
		this.qrCodeScanner = new QRCodeScanner(swiftBot);
		this.converter = new HexadecimalConverter();
		this.movementController = new MovementController(swiftBot);
		this.photoTaker = new PhotoTaker(swiftBot);
		this.scannedHexValues = new ArrayList<>();

	}
	// Main method to start the dance routine 	
	public void run() {
		//Enabling Buttons for user interaction
		enableButtons();
		//Flag to control the dance loop
		System.out.println("\r\n"
				+ "  _____      _      ____       _  __         _____             ____       _      _   _      ____ U _____ u \r\n"
				+ " |_ \" _| U  /\"\\  u / __\"| u   |\"|/ /        |___ \"|           |  _\"\\  U  /\"\\  u | \\ |\"|  U /\"___|\\| ___\"|/ \r\n"
				+ "   | |    \\/ _ \\/ <\\___ \\/    | ' /            / / U  u      /| | | |  \\/ _ \\/ <|  \\| |> \\| | u   |  _|\"   \r\n"
				+ "  /| |\\   / ___ \\  u___) |  U/| . \\\\u       u// /\\ /___\\     U| |_| |\\ / ___ \\ U| |\\  |u  | |/__  | |___   \r\n"
				+ " u |_|U  /_/   \\_\\ |____/>>   |_|\\_\\         /_/ U|__\"__|     |____/ u/_/   \\_\\ |_| \\_|    \\____| |_____|  \r\n"
				+ " _// \\\\_  \\\\    >>  )(  (__),-,>> \\\\,-.     <<>>_              |||_    \\\\    >> ||   \\\\,-._// \\\\  <<   >>  \r\n"
				+ "(__) (__)(__)  (__)(__)      \\.)   (_/     (__)__)            (__)_)  (__)  (__)(_\")  (_/(__)(__)(__) (__) \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "    _        ____    ____              _____     _       _____      _       _   _                          \r\n"
				+ "U  /\"\\  u U | __\")u |  _\"\\    ___     |\" ___|U  /\"\\  u  |_ \" _| U  /\"\\  u  |'| |'|                         \r\n"
				+ " \\/ _ \\/   \\|  _ \\//| | | |  |_\"_|   U| |_  u \\/ _ \\/     | |    \\/ _ \\/  /| |_| |\\                        \r\n"
				+ " / ___ \\    | |_) |U| |_| |\\  | |    \\|  _|/  / ___ \\    /| |\\   / ___ \\  U|  _  |u                        \r\n"
				+ "/_/   \\_\\   |____/  |____/ uU/| |\\u   |_|    /_/   \\_\\  u |_|U  /_/   \\_\\  |_| |_|                         \r\n"
				+ " \\\\    >>  _|| \\\\_   |||_.-,_|___|_,-.)(\\\\,-  \\\\    >>  _// \\\\_  \\\\    >>  //   \\\\                         \r\n"
				+ "(__)  (__)(__) (__) (__)_)\\_)-' '-(_/(__)(_/ (__)  (__)(__) (__)(__)  (__)(_\") (\"_)                        \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "                                                                                                           \r\n"
				+ "");
		System.out.println("\r\n"
				+ "  ____       _      _   _      ____ U _____ u                                   \r\n"
				+ " |  _\"\\  U  /\"\\  u | \\ |\"|  U /\"___|\\| ___\"|/                                   \r\n"
				+ "/| | | |  \\/ _ \\/ <|  \\| |> \\| | u   |  _|\"                                     \r\n"
				+ "U| |_| |\\ / ___ \\ U| |\\  |u  | |/__  | |___                                     \r\n"
				+ " |____/ u/_/   \\_\\ |_| \\_|    \\____| |_____|                                    \r\n"
				+ "  |||_    \\\\    >> ||   \\\\,-._// \\\\  <<   >>                                    \r\n"
				+ " (__)_)  (__)  (__)(_\")  (_/(__)(__)(__) (__)                                   \r\n"
				+ "  ____     _____      _       ____      _____               _   _     ____      \r\n"
				+ " / __\"| u |_ \" _| U  /\"\\  uU |  _\"\\ u  |_ \" _|     ___     | \\ |\"| U /\"___|u    \r\n"
				+ "<\\___ \\/    | |    \\/ _ \\/  \\| |_) |/    | |      |_\"_|   <|  \\| |>\\| |  _ /    \r\n"
				+ " u___) |   /| |\\   / ___ \\   |  _ <     /| |\\      | |    U| |\\  |u | |_| |     \r\n"
				+ " |____/>> u |_|U  /_/   \\_\\  |_| \\_\\   u |_|U    U/| |\\u   |_| \\_|   \\____|     \r\n"
				+ "  )(  (__)_// \\\\_  \\\\    >>  //   \\\\_  _// \\\\_.-,_|___|_,-.||   \\\\,-._)(|_      \r\n"
				+ " (__)    (__) (__)(__)  (__)(__)  (__)(__) (__)\\_)-' '-(_/ (_\")  (_/(__)__)     \r\n"
				+ "  ____     U  ___ u   U  ___ u  _   _                                           \r\n"
				+ " / __\"| u   \\/\"_ \\/    \\/\"_ \\/ | \\ |\"|                                          \r\n"
				+ "<\\___ \\/    | | | |    | | | |<|  \\| |>                                         \r\n"
				+ " u___) |.-,_| |_| |.-,_| |_| |U| |\\  |u                                         \r\n"
				+ " |____/>>\\_)-\\___/  \\_)-\\___/  |_| \\_|                                          \r\n"
				+ "  )(  (__)    \\\\         \\\\    ||   \\\\,-.                                       \r\n"
				+ " (__)        (__)       (__)   (_\")  (_/                                        \r\n"
				+ "");
		boolean continueDancing = true;
		//Dance Routine loop
		while (continueDancing) {
			try {
				//Scan the QR Code to get the hexadecimal number
				String hexNumber = qrCodeScanner.scanQRCode();
				// Set the last scanned hex value
				String lastScannedHexValue = hexNumber; 

				//Convert the hexadecimal number to different Number Systems
				int decimalNumber = converter.hexToDecimal(lastScannedHexValue);
				int octalNumber = converter.decimalToOctal(lastScannedHexValue);
				long binaryNumber = converter.decimalToBinary(lastScannedHexValue);
				//Calculate Speed and Length of Movement
				int speed = movementController.speedCalculation(lastScannedHexValue);
				int lengthOfMovement = movementController.lengthOfMovement(lastScannedHexValue);
				String flippedBinaryNumber = movementController.flipBinaryString(lastScannedHexValue);
				//Get the LED colour
				int[] ledColour = movementController.ledColour(lastScannedHexValue);
				//Print out all my values
				System.out.println("This is the Hexadecimal Number: " + hexNumber);
				System.out.println("This is the Octal Number: " + octalNumber);
				System.out.println("This is the Decimal Number: " + decimalNumber);
				System.out.println("This is the Binary Number: " + binaryNumber);
				System.out.println("This is the Flipped Binary Number: " + flippedBinaryNumber);
				System.out.println("Speed: " + speed);
				System.out.println("Led Colour: " + "(red " + ledColour[0] + ", green " + ledColour[1] + " ,blue " + ledColour[2] + ")");
				System.out.println("Length: " + lengthOfMovement);
				System.out.println("Length: " + lastScannedHexValue.length());
				Thread.sleep(2000);
				
				System.out.println("This is the Decimal Number: " + decimalNumber);
				//Perform Movements based on the last scanned hex value
				//movementController.movements(lastScannedHexValue);
				writeToFile();
				// Add it to the list
				scannedHexValues.add(lastScannedHexValue); // Add scanned hex value to the list

				// Prompt the user to continue or terminate
				System.out.println("Would you like to enter another hexadecimal?");
				System.out.println("Press Y for yes or press any other key to exit");
				String userInput = System.console().readLine();
				continueDancing = userInput.equalsIgnoreCase("Y");
			} catch (InterruptedException e) {

			}
		}



	}
	//Method to print scanned Hex Value in ascending order
	private void printScannedHexValuesInAscendingOrder() {
		Collections.sort(scannedHexValues);
		System.out.println("Scanned Hex Values in Ascending Order:");
		for (String hexValue : scannedHexValues) {
			System.out.println(hexValue);
		}
	}

	// Method to enable buttons for continuing or exitixng the dance
	public void enableButtons() {
		//Enables Button Y
		if (!buttonYEnabled) {
			MainCode.swiftbot.enableButton(Button.Y, () -> {
				System.out.println("Button Y pressed. Continuing the dance...");
				continueDancing = true;
				run();
			});
			buttonYEnabled = true;
		}
		//Enables Button X
		if (!buttonXEnabled) {
			MainCode.swiftbot.enableButton(Button.X, () -> {
				System.out.println("Button X pressed. Exiting the dance...");
				continueDancing = false;
				printScannedHexValuesInAscendingOrder();
				System.out.println("All Pictures are located in the Pictures folder in the SwiftBot");


				System.exit(0);
			});
			buttonXEnabled = true;
		}
	}
	// Method to write scanned Hex Values to a file added by the number of photos taken
	public void writeToFile() {
		for (String hexValue : scannedHexValues) {
			try (FileWriter writer = new FileWriter("/home/pi/Documents" + hexValue + "_info.txt")) {
				// Write the hexadecimal number
				writer.write("Hexadecimal: " + hexValue + "\n");

				// Write the number of images taken for the current hex number
				int numberOfImages = photoTaker.getPhotoCounter(hexValue);
				writer.write("Number of images taken: " + numberOfImages + "\n");

				// Write any other information you want

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}









