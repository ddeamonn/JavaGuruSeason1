package gravitrips;

import java.util.Scanner;

/**
 * Created by Amir on 31.08.2016..
 */
class GameIO {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printObject(Object obj) {
        System.out.println(obj.toString());
    }

    public static int getUserInputInteger() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static int getUserInputInteger(String message) {
        GameIO.printMessage(message);
        return GameIO.getUserInputInteger();
    }

    public static int getUserInputIntegerWithRangeCheck(int startRange, int endRange) {
        boolean successInput = false;
        int userInput = 0;

        GameIO.printMessage("Valid range is from " + startRange + " to " + endRange);
        while (!successInput) {
            userInput = GameIO.getUserInputInteger();
            if ((userInput < startRange) || (userInput > endRange)) {
                GameIO.printMessage("Please check input. Valid range is " + startRange + " to " + endRange);
            }
            else {
                successInput = true;
            }
        }
        return userInput;
    }

    public static int getUserInputIntegerWithRangeCheck(String message, int startRange, int endRange) {
        GameIO.printMessage(message);
        return GameIO.getUserInputIntegerWithRangeCheck(startRange, endRange);
    }

    public static String getUserInputString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static String getUserInputString(String message) {
        GameIO.printMessage(message);
        return GameIO.getUserInputString();
    }

    public static String getUserInputChar() {
        Scanner sc = new Scanner(System.in);
        char userInput = sc.next().charAt(0);
        return Character.toString(userInput);
    }

    public static String getUserInputChar(String message) {
        GameIO.printMessage(message);
        return GameIO.getUserInputChar();
    }
}