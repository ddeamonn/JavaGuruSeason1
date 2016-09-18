/**
 * Created by Amir on 19.07.2016..
 */

import java.util.Scanner;


public class FindOddEven {
    public static void main(String[] args){
            int current, odd = 0, even =0;

            System.out.println("Program counts odd and even numbers.");
            System.out.println("Enter numbers pressing <enter> after number input. To exit enter 0 and <enter>");
            Scanner input = new Scanner(System.in);

            do {
                current = input.nextInt();
                if (current != 0 ) {
                    if ((current % 2) == 0) {
                        odd++;
                    } else {
                        even++;
                    }
                }
                else {
                    break;
                }
            } while(true);

            System.out.println("Odd number count: " + odd + " and even number count:" + even);
    }
}
