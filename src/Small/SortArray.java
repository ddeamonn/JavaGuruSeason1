import java.util.Random;

/**
 * Created by Amir on 26.07.2016..
 */
public class SortArray {
    private static final int ARRAY_SIZE = 16;
    private static final int ELEMENTS_IN_LINE = 4;
    private static final int BUBBLE_SORT = 1;
    private static final int MERGE_SORT = 2;

    public static void main(String[] args) {
        int[] arrayToSort1 = initArray();
        int[] arrayToSort2 = initArray();

        bubbleSort(arrayToSort1);
        printResult(arrayToSort1, BUBBLE_SORT);

        mergeSort(arrayToSort2);
        printResult(arrayToSort2, MERGE_SORT);
    }

    private static int[] initArray() {
        Random randomGenerator = new Random(Integer.MAX_VALUE - 1);
        int[] array = new int[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = randomGenerator.nextInt();
        }
        return array;
    }

    private static void bubbleSort(int[] arrayToSort) {

        boolean numbersIsSwapped;
        int temp;

        do {
            numbersIsSwapped = false;
            for (int i = 1; i < ARRAY_SIZE; i++) {
                if (arrayToSort[i] < arrayToSort[i - 1]) {
                    temp = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i - 1];
                    arrayToSort[i - 1] = temp;
                    numbersIsSwapped = true;
                }
            }
        } while (numbersIsSwapped);
    }

    private static void mergeSort(int[] arrayToSort) {

        int[] mergeHelpArray = new int[ARRAY_SIZE];

        mergeSortSplitArray(arrayToSort, 0, ARRAY_SIZE, mergeHelpArray);

    }

    private static void mergeSortSplitArray(int[] arrayToSort, int startSortRange, int endSortRange, int[] mergeHelpArray) {

        int mediana = (startSortRange + endSortRange) / 2;

        if ( (endSortRange - startSortRange) < 2 )
            return;

        mergeSortSplitArray(arrayToSort, startSortRange, mediana, mergeHelpArray);
        mergeSortSplitArray(arrayToSort, mediana, endSortRange, mergeHelpArray);
        mergeSortMergeSplitted(arrayToSort, startSortRange, mediana, endSortRange, mergeHelpArray);
        mergeSortCopyArrays(arrayToSort, startSortRange, endSortRange, mergeHelpArray);
    }

    private static void mergeSortMergeSplitted(int[] arrayToSort, int startSortRange, int mediana, int endSortRange, int[] mergeHelpArray) {
        int startPoint = startSortRange;
        int endPoint = mediana;

        for (int i = startSortRange; i < endSortRange; i++) {
            if ( (startPoint < mediana) && ((endPoint >= endSortRange) || (arrayToSort[startPoint] <= arrayToSort[endPoint])) ) {
                mergeHelpArray[i] = arrayToSort[startPoint];
                startPoint++;
            }
            else {
                mergeHelpArray[i] = arrayToSort[endPoint];
                endPoint++;
            }
        }
    }

    private static void mergeSortCopyArrays(int[] arrayToSort, int startSortRange, int endSortRange, int[] mergeHelpArray) {
        for (int i = startSortRange; i < endSortRange; i++) {
            arrayToSort[i] = mergeHelpArray[i];
        }
    }

    private static void printResult(int[] arrayToPrint, int sortType) {

        if ( sortType == BUBBLE_SORT) System.out.println("\n Bubble sort result:");
        else if ( sortType == MERGE_SORT ) System.out.println("\n Merge sort result:");

        for (int i = 1; i <= ARRAY_SIZE; i++) {
            System.out.printf("%15d", arrayToPrint[i  - 1]);
            if ((i % ELEMENTS_IN_LINE) == 0 ) System.out.println();
        }
    }
}