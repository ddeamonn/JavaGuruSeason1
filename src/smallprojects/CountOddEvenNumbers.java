import java.util.Random;

/**
 * Created by Amir on 23.07.2016..
 */
public class CountOddEvenNumbers {
    private static int ARRAY_SIZE = 100000;
    private static int ODD_NUMBER = 0;
    private static int EVEN_NUMBER = 1;
    private static int RESULT_ARRAY_SIZE = 2;

    public static void main (String[] args){
        int[] numbersArray;
        int[] oddEvenCountArray;

        int oddCount;
        int evenCount;

        oddEvenCountArray = new int[RESULT_ARRAY_SIZE];
        numbersArray = initArray(ARRAY_SIZE);

        oddCount = countOdd(numbersArray);
        evenCount = countEven(numbersArray);
        printResult(oddCount, evenCount);

        countOddEven(numbersArray, oddEvenCountArray);
        printResult(oddEvenCountArray);

    }

    private static int[] initArray(int ARRAY_SIZE) {
        int[] numbersArray = new int[ARRAY_SIZE];
        Random generator = new Random(Integer.MAX_VALUE  - 1);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            numbersArray[i] = generator.nextInt();
        }

        return numbersArray;
    }

    private static void countOddEven(int[] numberArray, int[] oddEvenCountArray) {
        for(int i =0; i < ARRAY_SIZE; i++) {
            if ((numberArray[i] % 2) == 0) {
                oddEvenCountArray[ODD_NUMBER]++;
            } else {
                oddEvenCountArray[EVEN_NUMBER]++;
            }
        }
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


    private static void printResult(int[] oddEvenCountArray) {
        System.out.println("Total numbers count: " + ARRAY_SIZE + ". Odd number count: " + oddEvenCountArray[ODD_NUMBER] + ". Even number count: "
                + oddEvenCountArray[EVEN_NUMBER]);
    }

    private static void printResult(int oddCount, int evenCount) {
        System.out.println("Total numbers count: " + ARRAY_SIZE + ". Odd number count: " + oddCount + ". Even number count: " + evenCount);
    }

}
