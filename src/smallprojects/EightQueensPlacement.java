/**
 * Created by Amir on 02.08.2016..
 */
public class EightQueensPlacement {
    private static final int BOARD_SIZE = 8;
    private static final int QUEEN_COUNT = 8;
    private static final boolean IS_FREE_CELL = false;
    private static final boolean IS_USED_CELL = true;

    public static void main (String[] args) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];
                tryToPlaceQueen(board, i, j, 0);
            }
        }
    }

    private static void tryToPlaceQueen(boolean[][] board, int rowToPlace, int columnToPlace, int queenNumber) {
        boolean[][] tempArray = new boolean[BOARD_SIZE][BOARD_SIZE];
        if ((queenNumber <= QUEEN_COUNT) && (rowToPlace >= 0) && (rowToPlace < BOARD_SIZE) && (columnToPlace >= 0) && (columnToPlace < BOARD_SIZE)) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (isCellFree(board, i, j) == IS_FREE_CELL)
                        board[i][j] = IS_USED_CELL;
                    copyArrays(board, tempArray);
                    tryToPlaceQueen(board, i, j, queenNumber + 1);
                }
            }
       }

        if (queenNumber >= QUEEN_COUNT) {
            renderArrays(board);
            return;
        }

    }

    private static boolean isCellFree(boolean[][] board, int row, int column) {
        int i = row;
        int j = column;
        int checkStep = 0;

        //if (row > column) max = row;
        //else max = column;

        while (checkStep <= BOARD_SIZE) {
            if ( ((row - checkStep) >= 0) && (j < BOARD_SIZE) ) if (board[row - checkStep][j]) return IS_USED_CELL;
            if (j < BOARD_SIZE) if (board[row][j]) return IS_USED_CELL;
            if ((i < BOARD_SIZE) && (j < BOARD_SIZE)) if (board[i][j]) return IS_USED_CELL;

            if (i < BOARD_SIZE) if (board[i][column]) return IS_USED_CELL;
            if ((i < BOARD_SIZE) && ((column - checkStep) >= 0)) if (board[i][column - checkStep]) return IS_USED_CELL;
            if ((column - checkStep) >= 0) if (board[row][j]) return IS_USED_CELL;

            if (((row - checkStep) >= 0) && ((column - checkStep) >= 0)) if (board[row - checkStep][column - checkStep]) return IS_USED_CELL;
            if (((row - checkStep) >= 0)) if (board[row - checkStep][column]) {

                return IS_USED_CELL;
            }

            i++; j++; checkStep++;
        }
        return IS_FREE_CELL;
    }

    private static void copyArrays(boolean[][] sourceArray, boolean[][] targetArray) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                targetArray[i][j] = sourceArray[i][j];
            }
        }
    }

    private static void renderArrays(boolean[][] board, int queenNumber, int row, int column) {
        System.out.printf("Queen Number: %d. Row: %d and Column: %d ", queenNumber, row, column);
        for(int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((j % BOARD_SIZE) == 0) System.out.println();
                if (board[i][j]) System.out.printf(" U ");
                else System.out.printf(" F ");
            }
        }

        System.out.println();
    }

    private static void renderArrays(boolean[][] board) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("|");
                if (board[i][j]) System.out.print("U");
                else System.out.print(" ");
            }
            System.out.println();
            for(int j = 0; j < BOARD_SIZE; j++) {
               System.out.print("- ");
            }
            System.out.println();
        }

        System.out.println();
    }
}

