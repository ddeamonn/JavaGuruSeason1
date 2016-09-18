import java.util.Scanner;

/**
 * Created by Amir on 19.07.2016..
 */

public class NumberOperations {

    private static int SUM_OPERTAION = 1;
    private static int SUBSTR_OPERATION = 2;
    private static int MULT_OPERATION = 3;
    private static int DEV_OPERATION = 4;

    public static void main (String[] args){

        int firstNumber = getNumberFromUser();
        int secondNumber = getNumberFromUser();

        printResult(summingNumbers(firstNumber, secondNumber), SUM_OPERTAION);
        printResult(subtructionNumbers(firstNumber, secondNumber), SUBSTR_OPERATION);
        printResult(multiplyNumbers(firstNumber, secondNumber), MULT_OPERATION);
        printResult(divideNumbers(firstNumber, secondNumber), DEV_OPERATION);
    }


    private static int getNumberFromUser(){
        System.out.print("Enter integer number: ");
        Scanner consoleInput = new Scanner(System.in);

        return consoleInput.nextInt();
    }

    private static int summingNumbers(int firstNumber, int secondNumber) {
        return (firstNumber + secondNumber);
    }

    private static int subtructionNumbers(int firstNumber, int secondNumber) {
        return (firstNumber - secondNumber);
    }

    private static int multiplyNumbers(int firstNumber, int secondNumber) {
        return (firstNumber * secondNumber);
    }

    private static float divideNumbers(int firstNumber, int secondNumber) {
        return ((float) firstNumber / (float) secondNumber);
    }

    private static void printResult(float result, int operation) {
        if (operation == SUM_OPERTAION ) {
            System.out.println("Summing: " + (int) result);
        } else if (operation == SUBSTR_OPERATION) {
            System.out.println("Subtraction: " + (int)result);
        } else if (operation == MULT_OPERATION) {
            System.out.println("Multiplication: " + (int)result);
        } else if (operation == DEV_OPERATION) {
            System.out.println("Division: " + result);
        }
    }
}
