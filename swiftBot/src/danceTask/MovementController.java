
package danceTask;

import swiftbot.SwiftBotAPI;

public class MovementController {
    private SwiftBotAPI swiftBot;
    private HexadecimalConverter converter;
    private PhotoTaker photoTaker;
    public int numberOfPhotos = 0; // Counter to keep track of the number of photos taken

    // Constructor
    public MovementController(SwiftBotAPI swiftBot) {
        this.swiftBot = swiftBot;
        this.converter = new HexadecimalConverter();
        this.photoTaker = new PhotoTaker(swiftBot);
    }

    // Method to determine the LED colour based on the hexadecimal value
    public int[] ledColour(String lastScannedHexValue) throws InterruptedException {
        int decimalNumber = this.converter.hexToDecimal(lastScannedHexValue);
        int[] swiftbotColour = new int[3];

        int redValue = decimalNumber;
        int greenValue = (decimalNumber % 80) * 3;
        int blueValue;
        if (redValue > greenValue) {
            blueValue = redValue;
        } else {
            blueValue = greenValue;
        }
        swiftbotColour[0] = Math.min(redValue, 255);
        swiftbotColour[1] = Math.min(greenValue, 255);
        swiftbotColour[2] = Math.min(blueValue, 255);

        return swiftbotColour;
    }

    // Method to determine the length of movement based on the length of the hexadecimal value
    public int lengthOfMovement(String lastScannedHexValue) throws InterruptedException {
        if (lastScannedHexValue.length() == 1) {
            return 1000;
        } else if (lastScannedHexValue.length() == 2) {
            return 500;
        } else {
            throw new IllegalArgumentException("Invalid hexadecimal number length. Expected 1 or 2 digits.");
        }
    }

    // Method to perform spinning movement based on the speed and length of movement
    public void spin(int speed, String lastScannedHexValue) throws InterruptedException {
        speed = speedCalculation(lastScannedHexValue);
        int leftWheelVelocity = Math.max(Math.min(speed, 100), -100);
        int rightWheelVelocity = Math.max(Math.min(-speed, 100), -100);
        swiftBot.move(leftWheelVelocity, rightWheelVelocity, lengthOfMovement(lastScannedHexValue));
    }

    // Method to perform forward movement based on the speed and length of movement, and take a photo
    public void moveForward(String lastScannedHexValue) throws InterruptedException {
    
        int forwardSpeed = speedCalculation(lastScannedHexValue);
        swiftBot.move(forwardSpeed, forwardSpeed, lengthOfMovement(lastScannedHexValue));
        photoTaker.takeForwardPhoto(lastScannedHexValue);
    }

    // Method to calculate the speed based on the hexadecimal value
    public int speedCalculation(String lastScannedHexValue) throws InterruptedException {
        int octalNumber = converter.decimalToOctal(lastScannedHexValue);
        int velocity;
        if (octalNumber > 50) {
            velocity = octalNumber;
        } else {
            velocity = octalNumber + 50;
        }
        velocity = Math.max(Math.min(velocity, 100), -100);
        return velocity;
    }

    // Method to perform movements based on the binary representation of the hexadecimal value
    public void movements(String lastScannedHexValue) throws InterruptedException {
        swiftBot.fillUnderlights(ledColour(lastScannedHexValue));
        for (int i = 0; i < flipBinaryString(lastScannedHexValue).length(); i++) {
            char digit = flipBinaryString(lastScannedHexValue).charAt(i);
            if (digit == '0') {
                spin(50, lastScannedHexValue);
            } else if (digit == '1') {
                moveForward(lastScannedHexValue);
                numberOfPhotos++;
            } else {
                System.out.println("Invalid digit encountered: " + digit);
            }
        }
        swiftBot.disableUnderlights();
    }

    // Method to flip the binary representation of the hexadecimal value
    public String flipBinaryString(String lastScannedHexValue) throws InterruptedException {
        String binaryString = String.valueOf(converter.decimalToBinary(lastScannedHexValue));
        String reversed = "";
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            reversed += binaryString.substring(i, i + 1);
        }
        return reversed;
    }
}






