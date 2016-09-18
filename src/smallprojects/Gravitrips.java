import java.util.Random;
import java.util.Scanner;

/**
 * Created by Amir on 09.08.2016..
 */

public class Gravitrips {

    private static final int FIELD_MAX_ROW = 6;
    private static final int FIELD_MAX_COLUMN = 7;
    private static final String PLAYER1_SYMBOL = "X";
    private static final int PLAYER1_NUMBER = 1;
    private static final String PLAYER2_SYMBOL = "O";
    private static final int PLAYER2_NUMBER = 2;
    private static final String EMPTY_CELL_SYMBOL = ".";
    private static final int EMPTY_CELL_NUMBER = 0;

    private static final int PLACING_FIGURE_RESULT_SUCCESS = 51;
    private static final int PLACING_FIGURE_RESULT_FAILED = 52;
    private static final int PLACING_FIGURE_NO_FREE_CELLS = 50;

    private static final int USER_INPUT_END_GAME_CODE = 0;

    private static final int GAME_STATUS_WIN_GAME = 100;
    private static final int GAME_STATUS_NO_FREE_CELLS = 102;
    private static final int GAME_STATUS_END_GAME = 103;
    private static final int GAME_STATUS_CONTINUE_GAME = 101;
    private static final int GAME_CHECK_WINDOW_SIZE = 4;

    public static void main(String[] args) {
        int gameStatus = GAME_STATUS_CONTINUE_GAME;
        int moveStatus = GAME_STATUS_CONTINUE_GAME;

        int[][] gameField = generateGameField(FIELD_MAX_ROW, FIELD_MAX_COLUMN);
        int currentPlayer = PLAYER2_NUMBER;

         do {
             currentPlayer = changeCurrentUser(currentPlayer);
             renderField(gameField);
             moveStatus = makePlayerMove(currentPlayer, gameField);
             gameStatus = checkToContinueGame(moveStatus, gameField);

         } while ((gameStatus != GAME_STATUS_END_GAME) &&
                  (gameStatus != GAME_STATUS_WIN_GAME) &&
                  (gameStatus != GAME_STATUS_NO_FREE_CELLS));

        renderField(gameField);
        showGameResult(moveStatus, gameStatus, currentPlayer);

    }

    private static void showGameResult (int moveStatus, int gameStatus, int currentPlayer){
        if (moveStatus == USER_INPUT_END_GAME_CODE) showMessage("Exit game selected.");
        else if(gameStatus == GAME_STATUS_NO_FREE_CELLS) showMessage("No free cells to place. Ending game.");
        else if (currentPlayer == PLAYER1_NUMBER) showMessage("Player 1 is winner.");
        else if (currentPlayer == PLAYER2_NUMBER) showMessage("Player 2 is winner.");
    }

    private static int changeCurrentUser(int currentPlayer) {
        if (currentPlayer == PLAYER1_NUMBER) {
            showMessage("Now player No:" + currentPlayer + " turn.");
            return PLAYER2_NUMBER;
        } else {
            showMessage("Now player No:" + currentPlayer + " turn.");
            return PLAYER1_NUMBER;
        }
    }

    private static int[][] generateGameField(int rowSize, int columnSize) {
        return new int[rowSize][columnSize];
    }

    private static int checkWinSituation(int[][] gameField) {
        int CHECK_ROWS = 0;
        int CHECK_COLUMNS = 1;
        int CHECK_DIAGONAL1 = 2;
        int CHECK_DIAGONAL2 = 3;
        int CHECK_TYPE_COUNT = 4;
        boolean WIN_STATUS = false;
        int offsetRow = 0;
        int offsetColumn = 0;

        while ((offsetRow <= FIELD_MAX_ROW - GAME_CHECK_WINDOW_SIZE) && (!WIN_STATUS)) {
            while ((offsetColumn <= FIELD_MAX_COLUMN - GAME_CHECK_WINDOW_SIZE) && (!WIN_STATUS)) {

                int[] figureTypeOneCount = new int[CHECK_TYPE_COUNT];
                int[] figureTypeTwoCount = new int[CHECK_TYPE_COUNT];

                for (int i = 0; i < GAME_CHECK_WINDOW_SIZE; i++) {
                    for (int j = 0; j < GAME_CHECK_WINDOW_SIZE; j++) {

                        if (gameField[offsetRow + i][offsetColumn + j] == PLAYER1_NUMBER)
                            figureTypeOneCount[CHECK_ROWS]++;
                        else if (gameField[offsetRow + i][offsetColumn + j] == PLAYER2_NUMBER)
                            figureTypeTwoCount[CHECK_ROWS]++;

                        if (gameField[offsetRow + j][offsetColumn + i] == PLAYER1_NUMBER)
                            figureTypeOneCount[CHECK_COLUMNS]++;
                        else if (gameField[offsetRow + j][offsetColumn + i] == PLAYER2_NUMBER)
                            figureTypeTwoCount[CHECK_COLUMNS]++;
                    }

                    if (gameField[offsetRow + i][offsetColumn + i] == PLAYER1_NUMBER)
                        figureTypeOneCount[CHECK_DIAGONAL1]++;
                    else if (gameField[offsetRow + i][offsetColumn + i] == PLAYER2_NUMBER)
                        figureTypeTwoCount[CHECK_DIAGONAL1]++;

                    if (gameField[offsetRow + GAME_CHECK_WINDOW_SIZE - i - 1][offsetColumn + i] == PLAYER1_NUMBER)
                        figureTypeOneCount[CHECK_DIAGONAL2]++;
                    else if (gameField[offsetRow + GAME_CHECK_WINDOW_SIZE - i - 1][offsetColumn + i] == PLAYER2_NUMBER)
                        figureTypeTwoCount[CHECK_DIAGONAL2]++;

                    if (figureTypeOneCount[CHECK_COLUMNS] < GAME_CHECK_WINDOW_SIZE)
                        figureTypeOneCount[CHECK_COLUMNS] = 0;
                    else WIN_STATUS = true;
                    if (figureTypeTwoCount[CHECK_COLUMNS] < GAME_CHECK_WINDOW_SIZE)
                        figureTypeTwoCount[CHECK_COLUMNS] = 0;
                    else WIN_STATUS = true;

                    if (figureTypeOneCount[CHECK_ROWS] < GAME_CHECK_WINDOW_SIZE)
                        figureTypeOneCount[CHECK_ROWS] = 0;
                    else WIN_STATUS = true;
                    if (figureTypeTwoCount[CHECK_ROWS] < GAME_CHECK_WINDOW_SIZE)
                        figureTypeTwoCount[CHECK_ROWS] = 0;
                    else WIN_STATUS = true;
                }

                if (figureTypeOneCount[CHECK_DIAGONAL1] < GAME_CHECK_WINDOW_SIZE)
                    figureTypeOneCount[CHECK_DIAGONAL1] = 0;
                else WIN_STATUS = true;
                if (figureTypeTwoCount[CHECK_DIAGONAL1] < GAME_CHECK_WINDOW_SIZE)
                    figureTypeTwoCount[CHECK_DIAGONAL1] = 0;
                else WIN_STATUS = true;
                if (figureTypeOneCount[CHECK_DIAGONAL2] < GAME_CHECK_WINDOW_SIZE)
                    figureTypeOneCount[CHECK_DIAGONAL2] = 0;
                else WIN_STATUS = true;
                if (figureTypeTwoCount[CHECK_DIAGONAL2] < GAME_CHECK_WINDOW_SIZE)
                    figureTypeTwoCount[CHECK_DIAGONAL2] = 0;
                else WIN_STATUS = true;

                offsetColumn++;
            }
            offsetColumn = 0;
            offsetRow++;
        }
        if (WIN_STATUS) return GAME_STATUS_WIN_GAME;
        return GAME_STATUS_CONTINUE_GAME;
    }

    private static int checkToContinueGame(int gameStatus, int[][] gameField) {
        if (gameStatus == USER_INPUT_END_GAME_CODE) return GAME_STATUS_END_GAME;
        else if (findFreeCells(gameField)) return checkWinSituation(gameField);
        else return GAME_STATUS_NO_FREE_CELLS;
    }

    private static boolean findFreeCells(int[][] gameField) {
        for (int i = 0; i < FIELD_MAX_ROW; i++) {
            for (int j = 0; j < FIELD_MAX_COLUMN; j++) {
                if (gameField[i][j] == EMPTY_CELL_NUMBER) return true;
            }
        }
        return false;
    }

    private static void renderField(int[][] gameField) {
        for (int i = 0; i < FIELD_MAX_COLUMN; i++) System.out.printf("%d ", (i + 1));
        System.out.println();
        for (int i = 0; i < FIELD_MAX_ROW; i++) {
            for (int j = 0; j < FIELD_MAX_COLUMN; j++) {
                if (gameField[i][j] == PLAYER1_NUMBER) System.out.print(PLAYER1_SYMBOL + " ");
                else if (gameField[i][j] == PLAYER2_NUMBER) System.out.print(PLAYER2_SYMBOL + " ");
                else if (gameField[i][j] == EMPTY_CELL_NUMBER) System.out.print(EMPTY_CELL_SYMBOL + " ");
            }
            System.out.println();
        }
    }

    private static void showMenu(int currentPlayer) {
        System.out.printf("Player %d please select column number from 1 to %d to place your figure. Press 0 to exit. \n", currentPlayer, FIELD_MAX_COLUMN);
    }

    private static void showMessage(String message) {
        System.out.println(message);
    }

    private static int getUserInput(int currentPlayer) {
        Scanner sc = new Scanner(System.in);
        int userSelection = sc.nextInt();
        while (!((userSelection > 0) && (userSelection <= FIELD_MAX_COLUMN)) && (userSelection != USER_INPUT_END_GAME_CODE)) {
            showMessage("You have entered wrong number. Please check");
            showMenu(currentPlayer);
            userSelection = sc.nextInt();
        }
        return userSelection;
    }

    private static int makePlayerMove(int playerNumber, int[][] gameField) {
        int columnNumber;

        if (playerNumber == PLAYER1_NUMBER) {
            showMenu(playerNumber);
            columnNumber = getUserInput(playerNumber);
            if (columnNumber == USER_INPUT_END_GAME_CODE) return USER_INPUT_END_GAME_CODE;
            while (placeNewFigure(columnNumber - 1, playerNumber, gameField) != PLACING_FIGURE_RESULT_SUCCESS) {
                showMessage("Check if there is possible to place a new figure");
                columnNumber = getUserInput(playerNumber);
            }
        } else if (playerNumber == PLAYER2_NUMBER) {
            columnNumber = getAIMoveRandom();
            while (placeNewFigure(columnNumber - 1, playerNumber, gameField) != PLACING_FIGURE_RESULT_SUCCESS) {
                columnNumber = getAIMoveRandom();
            }
        }

        return PLACING_FIGURE_RESULT_SUCCESS;
    }

    private static int placeNewFigure(int columnNumber, int playerNumber, int[][] gameField) {
        int freeCellInRow;
        freeCellInRow = findTopOfColumn(columnNumber, gameField);
        if (freeCellInRow != PLACING_FIGURE_NO_FREE_CELLS) {
            gameField[freeCellInRow][columnNumber] = playerNumber;
            return PLACING_FIGURE_RESULT_SUCCESS;
        } else {
            return PLACING_FIGURE_RESULT_FAILED;
        }
    }

    private static int findTopOfColumn(int column, int[][] gameFiled) {
        int row = 0;
        while ((gameFiled[row][column] == EMPTY_CELL_NUMBER) && (row < FIELD_MAX_ROW - 1)) {
            row++;
        }
        if (row == 0) return PLACING_FIGURE_NO_FREE_CELLS;
        if ((gameFiled[row][column] == EMPTY_CELL_NUMBER) && (row < FIELD_MAX_ROW)) return row;
        if ((row < FIELD_MAX_ROW) && (gameFiled[row][column] != EMPTY_CELL_NUMBER)) return row - 1;

        return PLACING_FIGURE_NO_FREE_CELLS;
    }

    public static int getAIMoveRandom() {
        int columnToPlaceFigure;
        Random rnd = new Random();
        columnToPlaceFigure = rnd.nextInt(FIELD_MAX_COLUMN) + 1;
        return columnToPlaceFigure;
    }
}