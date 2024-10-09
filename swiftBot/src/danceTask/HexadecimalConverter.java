package danceTask;

public class HexadecimalConverter {
  
    // Method to convert a hexadecimal string to decimal
    public int hexToDecimal(String lastScannedHexValue) throws InterruptedException {
        int decimalNumber = 0;
        
        // Iterate  each character in the hexadecimal string
        for (int i = 0; i < lastScannedHexValue.length(); i++) {
            char hexChar = lastScannedHexValue.charAt(i);
            
            int digit;
            
            // Convert each hexadecimal digit to its decimal equivalent
            if (hexChar >= '0' && hexChar <= '9') {
                digit = hexChar - '0';
                System.out.println(digit);
            } else if (hexChar >= 'A' && hexChar <= 'F') {
                digit = hexChar - 'A' + 10;
                System.out.println(digit);
            } else if (hexChar >= 'a' && hexChar <= 'f') {
                digit = hexChar - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexChar);
            }       
            // Update the decimal number by multiplying by 16 and adding the digit
            decimalNumber = decimalNumber * 16 + digit;      
        }
        // Return the decimal number
        return decimalNumber;
    }

    // Method to convert a hexadecimal string to octal
    public int decimalToOctal(String lastScannedHexValue ) throws InterruptedException {
        // Convert the hexadecimal string to decimal
        int decimalNumber = hexToDecimal(lastScannedHexValue);
        int octalNumber = 0;
        int power = 1;
        
        // Convert the decimal number to octal
        while (decimalNumber > 0) {
            int octalRemainder = decimalNumber % 8;
            octalNumber = octalNumber + (octalRemainder * power);
            power = power * 10;
            decimalNumber = decimalNumber / 8;
        }
        // Return the octal number
        return octalNumber; 
    }

    // Method to convert a hexadecimal string to binary
    public  long decimalToBinary(String lastScannedHexValue) throws InterruptedException {
        // Convert the hexadecimal string to decimal
        int decimalNumber = hexToDecimal(lastScannedHexValue);
        long binaryNumber = 0;
        long placeValue = 1;

        // Handle the case where the decimal number is 0
        if (decimalNumber == 0) {
            return 0;
        }

        // Convert the decimal number to binary
        while (decimalNumber > 0) {
            int binaryRemainder = decimalNumber % 2;
            binaryNumber = binaryNumber + (binaryRemainder * placeValue);
            placeValue = placeValue * 10;
            decimalNumber = decimalNumber / 2;
        }
        // Return the binary number
        return binaryNumber;
    }
}



