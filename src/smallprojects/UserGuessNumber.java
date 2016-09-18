/**
 * Created by Amir on 19.07.2016..
 */

import java.util.Random;
import java.util.Scanner;

public class UserGuessNumber {
    public static void main(String[] args){
        int compGenerated, current;
        boolean userIsWon = false;

        Random randomGenerator = new Random();
        compGenerated = randomGenerator.nextInt(100);
        Scanner input = new Scanner(System.in);

        System.out.println("You should guess hidden number from 1 to 100.");
        System.out.println("Enter numbers pressing <enter> after number input.\n\n");

        System.out.print("Enter number: ");

        do {
            current = input.nextInt();
            if (current != compGenerated ) {
                if (current > compGenerated) {
                    System.out.print("Entered number is more then hiddden number. Enter number: ");
                } else if (current < compGenerated) {
                    System.out.print("Entered number is smaller then hidden number. Enter number: ");
                }
            }
            else {
                System.out.println("Success! Number is: " + current);
                userIsWon = true;
            }
        } while(!userIsWon);
    }
}
