/**
 * Created by Amir on 20.07.2016..
 */

import java.util.Scanner;


public class GuessNumberQuick {

    public static void main(String[] args) {

        int NUMBER_IS_LARGER = 1;
        int NUMBER_IS_SMALLER = 2;
        int NUMBER_COMPUTER_IS_GUESSED = 3;
        int START_RANGE = 1;
        int END_RANGE = 100;

        int startRange = START_RANGE;
        int endRange = END_RANGE;
        int computerNextGuessNumber;
        int selectedNumber;

        Scanner input = new Scanner(System.in);

        System.out.println("Take number from 1 to 100. Computer will try to guess it.");
        System.out.println("Enter " + NUMBER_IS_LARGER + " - yes if computer number is larger.");
        System.out.println("Enter " + NUMBER_IS_SMALLER + " - no if computer number is smaller.");
        System.out.println("Enter " + NUMBER_COMPUTER_IS_GUESSED + " - if computer has guessed number.");
        System.out.println("Press <enter> after number input.\n");

        do {
            computerNextGuessNumber = (startRange + endRange) / 2;

            System.out.println(NUMBER_IS_LARGER + " - larger, " + NUMBER_IS_SMALLER + " - smaller, " + NUMBER_COMPUTER_IS_GUESSED +
                    "  - guessed. Your inputed number is: " + computerNextGuessNumber + "\n");

            selectedNumber = input.nextInt();
            if (selectedNumber == NUMBER_IS_LARGER) {
                startRange = computerNextGuessNumber;
            } else if (selectedNumber == NUMBER_IS_SMALLER) {
                endRange = computerNextGuessNumber;
            }

        } while (selectedNumber != NUMBER_COMPUTER_IS_GUESSED);
    }
}
