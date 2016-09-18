package gravitrips;

import java.util.List;

/**
 * Created by Amir on 31.08.2016..
 */
class GameField {

    int[][] gameField;
    private int playersCount;
    private List<Player> playersList;

    private int FIELD_MAX_ROW;
    private int FIELD_MAX_COLUMN;

    GameField(int fieldMaxRow, int fieldMaxColumn, List<Player> playersList) {
        this.FIELD_MAX_ROW = fieldMaxRow;
        this.FIELD_MAX_COLUMN = fieldMaxColumn;

        gameField = generateGameField();

        this.playersList = playersList;
        this.playersCount = playersList.size();
    }

    private int[][] generateGameField() {
        return new int[this.FIELD_MAX_ROW][this.FIELD_MAX_COLUMN];
    }

    public int placeNewFigure(int columnNumber, int playerNumber) {
        int freeCellInRow;
        freeCellInRow = findTopOfColumn(columnNumber, gameField);
        if (freeCellInRow != GameConstants.PLACING_FIGURE_NO_FREE_CELLS) {
            this.gameField[freeCellInRow][columnNumber] = playerNumber;
            return GameConstants.PLACING_FIGURE_RESULT_SUCCESS;
        } else {
            return GameConstants.PLACING_FIGURE_RESULT_FAILED;
        }
    }

    private int findTopOfColumn(int column, int[][] gameFiled) {
        int row = 0;
        while ((gameFiled[row][column] == GameConstants.EMPTY_CELL_NUMBER) && (row < this.FIELD_MAX_ROW - 1)) {
            row++;
        }
        if (row == 0) return GameConstants.PLACING_FIGURE_NO_FREE_CELLS;
        if ((gameFiled[row][column] == GameConstants.EMPTY_CELL_NUMBER) && (row < this.FIELD_MAX_ROW)) return row;
        if ((row < this.FIELD_MAX_ROW) && (gameFiled[row][column] != GameConstants.EMPTY_CELL_NUMBER)) return row - 1;

        return GameConstants.PLACING_FIGURE_NO_FREE_CELLS;
    }

    private boolean findFreeCells() {
        for (int i = 0; i < this.FIELD_MAX_ROW; i++) {
            for (int j = 0; j < this.FIELD_MAX_COLUMN; j++) {
                if (this.gameField[i][j] == GameConstants.EMPTY_CELL_NUMBER) return true;
            }
        }
        return false;
    }

    public int checkToContinueGame(int gameStatus) {
        if (gameStatus == GameConstants.USER_INPUT_END_GAME_CODE) return GameConstants.GAME_STATUS_END_GAME;
        else if (findFreeCells()) return checkWinSituation();
        else return GameConstants.GAME_STATUS_NO_FREE_CELLS;
    }

    private void countElementsInCheckWindow(int offsetRow, int offsetColumn, int number, int[][] playerFigureTypeCount) {
        for (int j = 0; j < GameConstants.GAME_CHECK_WINDOW_SIZE; j++) {

            for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {
                if (gameField[offsetRow + number][offsetColumn + j] == this.playersList.get(checkPlayer).getPlayerID())
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_ROWS]++;

                if (gameField[offsetRow + j][offsetColumn + number] == this.playersList.get(checkPlayer).getPlayerID())
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_COLUMNS]++;
            }
        }

        for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {

            if (gameField[offsetRow + number][offsetColumn + number] == this.playersList.get(checkPlayer).getPlayerID())
                playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL1]++;

            if (gameField[offsetRow + GameConstants.GAME_CHECK_WINDOW_SIZE - number - 1][offsetColumn + number] == this.playersList.get(checkPlayer).getPlayerID())
                playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL2]++;
        }
    }

    private void cleanCheckValues(int[][] playerFigureTypeCount, int checkType) {
        if (checkType == GameConstants.GAME_CHECK_TYPE_ROWS_COLUMNS) {
            for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_COLUMNS] = 0;
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_ROWS] = 0;
            }
        } else if (checkType == GameConstants.GAME_CHECK_TYPE_DIAGONALS) {
            for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL1] = 0;
                    playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL2] = 0;
            }
        }
    }
    private boolean checkWinSituation(int[][] playerFigureTypeCount, int checkType) {

        if (checkType == GameConstants.GAME_CHECK_TYPE_ROWS_COLUMNS) {
            for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {
                if (playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_COLUMNS] >= GameConstants.GAME_CHECK_WINDOW_SIZE)
                    return true;
                else if (playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_ROWS] >= GameConstants.GAME_CHECK_WINDOW_SIZE)
                    return true;
            }
        } else if (checkType == GameConstants.GAME_CHECK_TYPE_DIAGONALS) {
            for (int checkPlayer = 0; checkPlayer < this.playersCount; checkPlayer++) {

                if (playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL1] >= GameConstants.GAME_CHECK_WINDOW_SIZE)
                    return true;
                else if (playerFigureTypeCount[checkPlayer][GameConstants.GAME_CHECK_DIAGONAL2] >= GameConstants.GAME_CHECK_WINDOW_SIZE)
                    return true;
            }
        }

        return false;
    }

    private int checkWinSituation() {

        int offsetRow = 0;
        int offsetColumn = 0;

        while ((offsetRow <= this.FIELD_MAX_ROW - GameConstants.GAME_CHECK_WINDOW_SIZE)) {
            while ((offsetColumn <= this.FIELD_MAX_COLUMN - GameConstants.GAME_CHECK_WINDOW_SIZE)) {

                int[][] playerFigureTypeCount = new int[this.playersCount][GameConstants.GAME_CHECK_TYPE_COUNT];

                for (int i = 0; i < GameConstants.GAME_CHECK_WINDOW_SIZE; i++) {

                    countElementsInCheckWindow(offsetRow, offsetColumn, i, playerFigureTypeCount);

                    if (checkWinSituation(playerFigureTypeCount, GameConstants.GAME_CHECK_TYPE_ROWS_COLUMNS))
                        return GameConstants.GAME_STATUS_WIN_GAME;
                    else
                        cleanCheckValues(playerFigureTypeCount, GameConstants.GAME_CHECK_TYPE_ROWS_COLUMNS);
                }

                if (checkWinSituation(playerFigureTypeCount, GameConstants.GAME_CHECK_TYPE_DIAGONALS))
                    return GameConstants.GAME_STATUS_WIN_GAME;
                else
                    cleanCheckValues(playerFigureTypeCount, GameConstants.GAME_CHECK_TYPE_DIAGONALS);

                offsetColumn++;
            }

            offsetColumn = 0;
            offsetRow++;
        }
        return GameConstants.GAME_STATUS_CONTINUE_GAME;
    }

    public void renderGameField() {

        System.out.println();

        for (int i = 0; i < this.FIELD_MAX_COLUMN; i++)
            System.out.printf("%-3d ", (i + 1));
        System.out.println();
        for (int i = 0; i < this.FIELD_MAX_ROW; i++) {
            for (int j = 0; j < this.FIELD_MAX_COLUMN; j++) {
                if (this.gameField[i][j] != GameConstants.EMPTY_CELL_NUMBER)
                    for (Player player : this.playersList) {
                        if (player.getPlayerID() == (this.gameField[i][j])) {
                            System.out.printf("%-3s ", player.getPlayerSymbol());
                            break;
                        }
                    }
                else System.out.printf("%-3s ", GameConstants.EMPTY_CELL_SYMBOL);
            }

            System.out.println();
        }
    }

}
