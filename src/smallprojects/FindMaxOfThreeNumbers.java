/**
 * Created by Amir on 19.07.2016..
 */

import java.util.Scanner;

public class FindMaxOfThreeNumbers {

    public static void main(String[] args) {
        int firstNumber = 0, secondNumber = 0, thirdNumber = 0;

        System.out.println("Enter three numbers: ");

        Scanner input = new Scanner(System.in);
        firstNumber = input.nextInt();
        secondNumber = input.nextInt();
        thirdNumber = input.nextInt();

        if ((firstNumber >= secondNumber) && (firstNumber > thirdNumber)){
            System.out.println("First number is greatest: " + firstNumber);
        } else if ((firstNumber >= secondNumber) && (firstNumber < thirdNumber)){
            System.out.println("Third number is greatest: " + thirdNumber);
        } else if ((secondNumber >= firstNumber) && (secondNumber > thirdNumber)){
            System.out.println("Second number is greatest: " + secondNumber);
        }  else if ((secondNumber >= firstNumber) && (secondNumber < thirdNumber)){
            System.out.println("Third number is greatest: " + thirdNumber);
        } else if ((thirdNumber >= firstNumber) && (thirdNumber < secondNumber)){
            System.out.println("Third number is greatest: " + thirdNumber);
        } else {
            System.out.println("Numbers are equals");
        }
    }
}
