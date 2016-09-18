import java.util.Scanner;

/**
 * Created by Amir on 17.07.2016..
 */
public class FindGreatestNumber {
    public static void main(String[] args){
        int firstNumber, secondNumber;

        System.out.println("Enter two numbers:");
        Scanner sc = new Scanner(System.in);
        firstNumber = sc.nextInt(); secondNumber = sc.nextInt();

        if (firstNumber > secondNumber){
            System.out.println("First number is greatest: " + firstNumber);
        }
        else{
            if (firstNumber < secondNumber){
                System.out.println("Second number is greatest: " + secondNumber);
            }
            else{
                System.out.println("Number are equels: " + firstNumber + " and " + secondNumber);
            }
        }
    }
}
