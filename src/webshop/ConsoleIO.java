package webshop;

import java.util.*;

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

    public static float getUserInputFloat() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                float number = sc.nextFloat();
                return number;
            } catch (InputMismatchException e) {
                ConsoleIO.showMessage("Check your input. Please try again");
            }
        }
    }

    public static boolean getYesNo() {
        ConsoleIO.showMessage("Please enter 'y' or 'yes', 'n' or 'no'");
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String string = sc.nextLine();
                if ((string.equals("y")) || (string.startsWith("yes"))) {
                    return true;
                }
                if ((string.equals("n")) || (string.startsWith("no"))) {
                    return false;
                }
                ConsoleIO.showMessage("Please enter 'y' or 'yes', 'n' or 'no'");
            } catch (InputMismatchException e) {
                ConsoleIO.showMessage("Check your input. Please enter 'y' or 'yes', 'n' or 'no'");
            }
        }
    }

    public static String getUserInputString() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String string = sc.nextLine();
                return string;
            } catch (InputMismatchException e) {
                ConsoleIO.showMessage("Check your input. Please try again");
            }
        }
    }




}
