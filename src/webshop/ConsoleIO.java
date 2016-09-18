package webshop;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Amir i Masha on 2016.09.18..
 */

public class ConsoleIO {

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static int getUserInputInt() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                int number = sc.nextInt();
                return number;
            } catch (InputMismatchException e) {
                ConsoleIO.showMessage("Check your input. Please try again");
            }
        }

    }

    public static String getUserInputString() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String number = sc.nextLine();
                return number;
            } catch (InputMismatchException e) {
                ConsoleIO.showMessage("Check your input. Please try again");
            }
        }

    }
}
