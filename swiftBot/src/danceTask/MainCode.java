package danceTask;

import swiftbot.SwiftBotAPI;

public class MainCode {
    public static SwiftBotAPI swiftbot;
//New Static instance of swiftbot
    static {
        swiftbot = new SwiftBotAPI();
    }
//make a new object called test from the class Testing
    public static void main(String[] args) throws InterruptedException {
        Testing test = new Testing(swiftbot);
        test.run();
    }
}





