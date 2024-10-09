package danceTask;

/**
 * This class is a utility for holding the last scanned hexadecimal value.
 * It provides methods to get and set the last scanned hexadecimal value.
 */
public class LastScannedHexValueHolder {
    private static String lastScannedHexValue; // Static variable to store the last scanned hexadecimal value

    /**
     * Get the last scanned hexadecimal value.
     * @return The last scanned hexadecimal value
     */
    public static String getLastScannedHexValue() {
        return lastScannedHexValue;
    }

    /**
     * Set the last scanned hexadecimal value.
     * @param hexValue The hexadecimal value to set as the last scanned value
     */
    public static void setLastScannedHexValue(String hexValue) {
        lastScannedHexValue = hexValue;
    }
}

