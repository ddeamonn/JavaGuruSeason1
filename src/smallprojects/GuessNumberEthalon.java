/**
 * Created by Amir on 20.07.2016..
 */

import java.util.Scanner;


public class GuessNumberEthalon {

    static int NUMBER_IS_LARGER = 1;
    static int NUMBER_IS_SMALLER = 2;
    static int NUMBER_COMPUTER_IS_GUESSED = 3;
    static int START_RANGE = 1;
    static int END_RANGE = 100;

    public static void main(String[] args){
        int startRange = START_RANGE;
        int endRange = END_RANGE;
        int computerNextGuessNumber;
        int selectedNumber;

        showGameRuleInfo();

        do {
            computerNextGuessNumber = calculateNextComputerGuessedNumber(startRange, endRange);
            printInputInfo(computerNextGuessNumber);
            selectedNumber = getUserInput();
            startRange = userSelectLargerNumber(selectedNumber, computerNextGuessNumber, startRange);
            endRange = userSelectSmallerNumber(selectedNumber, computerNextGuessNumber, endRange);
        } while (isGameOver(selectedNumber));
    }

    static int userSelectLargerNumber(int selectedNumber, int computerNextGuessNumber, int startRange) {
        if (selectedNumber == NUMBER_IS_LARGER) {
            return computerNextGuessNumber;
        } else {
            return startRange;
        }
    }

    static int userSelectSmallerNumber(int selectedNumber, int computerNextGuessNumber, int endRange) {
        if (selectedNumber == NUMBER_IS_SMALLER) {
            return computerNextGuessNumber;
        } else {
            return endRange;
        }
    }

    private static void showGameRuleInfo() {
        System.out.println("Take number from 1 to 100. Computer will try to guess it.");
        System.out.println("Enter " + NUMBER_IS_LARGER + " - yes if computer number is larger.");
        System.out.println("Enter " + NUMBER_IS_SMALLER + " - no if computer number is smaller.");
        System.out.println("Enter " + NUMBER_COMPUTER_IS_GUESSED + " - if computer has guessed number.");
        System.out.println("Press <enter> after number input.\n");
    }
    private static void printInputInfo(int compNextGuessNumber) {
        System.out.println(NUMBER_IS_LARGER + " - larger, " + NUMBER_IS_SMALLER +" - smaller, " + NUMBER_COMPUTER_IS_GUESSED +
                "  - guessed. Your inputed number is: " + compNextGuessNumber + "\n");
    }

    private static int getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static int calculateNextComputerGuessedNumber(int startRange, int endRange) {
        return (startRange + endRange) / 2;
    }

    private static boolean isGameOver(int selectedNumber) {
        return (selectedNumber != NUMBER_COMPUTER_IS_GUESSED);
    }
}
