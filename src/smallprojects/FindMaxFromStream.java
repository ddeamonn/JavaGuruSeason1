/**
 * Created by Amir on 19.07.2016..
 */

import java.util.Scanner;

public class FindMaxFromStream {
    public static void main (String[] args){

        int current, max = 0;

        System.out.println("Enter numbers pressing <enter> after number input. To exit enter 0 and <enter>");
        Scanner input = new Scanner(System.in);

        do{
            current = input.nextInt();

            if (current > max) {
                max = current;
            }

        } while(current != 0);
        System.out.println("Maximal number was: " + max);
    }
}
