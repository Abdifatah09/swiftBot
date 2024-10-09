package danceTask;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;

/**
 * The PhotoTaker class manages taking photos and tracking the number of photos taken for each hexadecimal number.
 */
public class PhotoTaker {
    private SwiftBotAPI swiftBot;
    private Map<String, Integer> photoCounters; // Map to store photo counters for each hex number

    /**
     * Constructs a new PhotoTaker instance.
     * @param swiftBot The SwiftBotAPI instance used for taking photos
     */
    public PhotoTaker(SwiftBotAPI swiftBot) {
        this.swiftBot = swiftBot;
        this.photoCounters = new HashMap<>(); // Initialize the map
    }

    /**
     * Takes a forward photo and increments the photo counter for the specified hexadecimal number.
     * @param hexNumber The hexadecimal number associated with the photo
     */
    public void takeForwardPhoto(String hexNumber) {
        // Increment the photo counter for the current hex number
        int counter = photoCounters.getOrDefault(hexNumber, 0); // Get the current counter value or 0 if not present
        photoCounters.put(hexNumber, counter + 1); // Increment the counter and put it back into the map

        try {
            // Take the photo
            String folderPath = "/home/pi/Pictures/HexNumber_" + hexNumber; // Use hexNumber parameter instead of lastScannedHexValue
            File directory = new File(folderPath);
            directory.mkdirs(); 
            BufferedImage forwardPhoto = swiftBot.takeStill(ImageSize.SQUARE_480x480);
            String fileName = "forwardPhoto_" + counter + ".jpg"; // Use the counter for the filename
            ImageIO.write(forwardPhoto, "jpg", new File(folderPath, fileName));
            if (forwardPhoto == null) {
                System.out.println("ERROR: Image taken is null");
                System.exit(5);
            }
        } catch (Exception e) {
            System.out.println("Camera not enabled!");
            System.exit(5);
        }
    }

    /**
     * Gets the number of photos taken for the specified hexadecimal number.
     * @param hexNumber The hexadecimal number to get the photo counter for
     * @return The number of photos taken for the specified hexadecimal number
     */
    public int getPhotoCounter(String hexNumber) {
        Integer counter = photoCounters.get(hexNumber);
        return (counter != null) ? counter : 0; // Return 0 if counter is null
    }
}






