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

    public static void showUserMenu(User user) {
        List<String> methods = UserUIMethods.allowedMethods(user);
        int i = 1;

        ConsoleIO.showMessage("======== User Menu ========");

        for(String method : methods){
            ConsoleIO.showMessage(i + " - " + method);
            i++;
        }

        ConsoleIO.showMessage(i + " - Exit");

    }

    public static String[] showLoginForm(){

        String[] loginForm = new String[2];
        ConsoleIO.showMessage("======== LOGIN FORM ========");
        ConsoleIO.showMessage("Please enter your login: ");
        loginForm[0] = ConsoleIO.getUserInputString();
        ConsoleIO.showMessage("Please enter your password: ");
        loginForm[1] = ConsoleIO.getUserInputString();

        return loginForm;
    }
}
