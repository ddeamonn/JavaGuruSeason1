import java.util.Random;

/**
 * Created by Amir on 23.07.2016..
 */
public class CountOddEvenNumbersIn2Pass {
    private static int ARRAY_SIZE = 100000;

    public static void main (String[] args){
        int[] numbersArray = initArray(ARRAY_SIZE);

        printResult(countOdd(numbersArray), countEven(numbersArray));
    }

    private static int[] initArray(int ARRAY_SIZE) {
        int[] numbersArray = new int[ARRAY_SIZE];
        Random generator = new Random(Integer.MAX_VALUE  - 1);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            numbersArray[i] = generator.nextInt();
        }

        return numbersArray;
    }

    private static int countOdd(int[] numberArray) {
        int oddCount = 0;
        for(int i = 0; i < ARRAY_SIZE; i++) {
            if ((numberArray[i] % 2) == 0) {
                oddCount++;
            }
        }
        return oddCount;
    }

    private static int countEven(int[] numberArray) {
        int evenCount = 0;
        for(int i = 0; i < ARRAY_SIZE; i++) {
            if ((numberArray[i] % 2) != 0) {
                evenCount++;
            }
        }
        return evenCount;
    }

    private static void printResult(int oddCount, int evenCount) {
        System.out.println("Total numbers count: " + ARRAY_SIZE + ". Odd number count: " + oddCount + ". Even number count: " + evenCount);
    }

}
