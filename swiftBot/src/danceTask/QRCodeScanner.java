package danceTask;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import swiftbot.SwiftBotAPI;

public class QRCodeScanner {
	// Instance variables
	public SwiftBotAPI swiftBot;
	private ArrayList<String> scannedHexValues;

	// Constructor
	public QRCodeScanner(SwiftBotAPI swiftBot) {
		// Initialize SwiftBotAPI and ArrayList
		this.swiftBot = swiftBot;
		this.scannedHexValues = new ArrayList<String>();
	}

	// Method to scan a QR code
	public String scanQRCode() throws InterruptedException {
		// Define RGB color values
		int[] white = {255, 255, 255};
		int[] green = {100, 100, 250};
		int[] red = {255, 0, 0};

		// Display message and wait for 5 seconds
		System.out.println("Taking a capture in 5 seconds..");
		Thread.sleep(5000);	

		// Flash underlights for visual feedback
		for (int i = 0; i <= 25; i++) {
			swiftBot.fillUnderlights(white);
			Thread.sleep(100);
			swiftBot.disableUnderlights();
			Thread.sleep(100);
		}

		// Get QR image and decode it
		BufferedImage img = swiftBot.getQRImage();
		String hexNumber = swiftBot.decodeQRImage(img);

		// Provide visual feedback based on the decoded result
		if (hexNumber.isEmpty()) {
			swiftBot.fillUnderlights(red); // If QR code is empty, display red underlights
		} else if (hexNumber.length() > 2) {
			swiftBot.fillUnderlights(red); // If QR code length is more than 2, display red underlights
		} else {
			swiftBot.fillUnderlights(green); // If QR code is valid, display green underlights
		}

		// Set the last scanned hex value and add it to the list of scanned hex values
		LastScannedHexValueHolder.setLastScannedHexValue(hexNumber);
		scannedHexValues.add(hexNumber);

		// Return the scanned hex number
		return hexNumber;
	}
}

