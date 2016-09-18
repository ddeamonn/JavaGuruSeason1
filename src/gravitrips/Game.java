package gravitrips;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 31.08.2016..
 */
public class Game {

    private int FIELD_MAX_ROW;
    private int FIELD_MAX_COLUMN;
    private int PLAYERS_COUNT = 2;
    private List<Player> players;
    private Player winner;
    private int nextPlayerID;
    GameField gameField;

    private Player currentPlayer;

    Game() {
        this.FIELD_MAX_ROW = GameIO.getUserInputIntegerWithRangeCheck("Please enter game board row size.",
                GameConstants.GAME_FIELD_ROW_MIN_SIZE, GameConstants.GAME_FIELD_ROW_MAX_SIZE);
        this.FIELD_MAX_COLUMN = GameIO.getUserInputIntegerWithRangeCheck("Please enter game board column size.",
                GameConstants.GAME_FIELD_COLUMN_MIN_SIZE, GameConstants.GAME_FIELD_COLUMN_MAX_SIZE);
        this.PLAYERS_COUNT =  GameIO.getUserInputIntegerWithRangeCheck("Please enter players count.",
                GameConstants.GAME_MIN_PLAYERS, GameConstants.GAME_MAX_PLAYERS);

        this.players = new ArrayList<>();
        this.nextPlayerID = 0;
    }

    private void setWinner(Player winner) {
        this.winner = winner;
    }

    private Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }


    private Player getWinner() {
        return this.winner;
    }

    private boolean checkDuplicatePlayersSymbols(Player checkPlayer) {
        for (Player player : this.players) {
            if (player.getPlayerSymbol().equals(checkPlayer.getPlayerSymbol())) {
                return true;
            }
        }
        return false;
    }

    private void addPlayer(Player player) {

    }

    private void initPlayers() {

        int newPlayerId = 0;

        for (int i = 0; i < this.PLAYERS_COUNT; i++) {

            int playerType = GameIO.getUserInputIntegerWithRangeCheck("Please enter player type 1 - Human, 2 - Computer: ", 1, 2);
            boolean success = false;

            newPlayerId++;

            if (playerType == GameConstants.PLAYER_TYPE_HUMAN) {
                HumanPlayer player = new HumanPlayer(newPlayerId);
                while (!success) {
                    if (this.players.contains(player) || this.checkDuplicatePlayersSymbols(player) ){
                        GameIO.printMessage("Player with this name and symbol already exists. Enter new symbol");
                        player = new HumanPlayer(newPlayerId);
                    }
                    else success = true;
                }
                this.players.add(player);
            }

            else if (playerType == GameConstants.PLAYER_TYPE_AI) {
                AIPlayer player = new AIPlayer(newPlayerId);
                while (!success) {
                    if (this.players.contains(player) || this.checkDuplicatePlayersSymbols(player)){
                        GameIO.printMessage("Player with this name and symbol already exists. Enter new symbol");
                        player = new AIPlayer(newPlayerId);
                    }
                    else success = true;
                }
                this.players.add(player);
            }
        }
    }

    private int makePlayerMove() {

        int columnNumber;
        int moveCode = GameConstants.PLACING_FIGURE_RESULT_FAILED;

        while (moveCode != GameConstants.PLACING_FIGURE_RESULT_SUCCESS) {

            GameIO.printMessage("Please make your move. Press 0 to exit.");

            columnNumber = this.currentPlayer.makeMove(this.FIELD_MAX_COLUMN);

            if (columnNumber == GameConstants.USER_INPUT_END_GAME_CODE)
                return GameConstants.USER_INPUT_END_GAME_CODE;

            moveCode = this.gameField.placeNewFigure(columnNumber - 1, this.currentPlayer.getPlayerID());

            if (moveCode == GameConstants.PLACING_FIGURE_RESULT_FAILED) GameIO.printMessage("Check if there is possible to place a new figure");
        }

        return GameConstants.PLACING_FIGURE_RESULT_SUCCESS;
    }

    public void printResults() {
        if (winner != null) {
            GameIO.printMessage("Game winner is player " + this.getWinner().getPlayerName() +
                    " with symbol " + this.getWinner().getPlayerSymbol());
            GameIO.printMessage("Player details: " + this.getWinner().toString());
        }
        else
            GameIO.printMessage("There is no game winners");
    }

    private void changeCurrentPlayer() {
        nextPlayerID = ( nextPlayerID % this.players.size() ) + 1;
        this.setCurrentPlayer(this.players.get(nextPlayerID - 1));
    }

    public void startGame(){

        int gameStatus = GameConstants.GAME_STATUS_CONTINUE_GAME;
        int moveStatus = GameConstants.GAME_STATUS_CONTINUE_GAME;

        this.initPlayers();
        this.currentPlayer = players.get(0);

        this.gameField = new GameField(this.FIELD_MAX_ROW, this.FIELD_MAX_COLUMN, players);

        do {

            this.changeCurrentPlayer();
            this.gameField.renderGameField();
            moveStatus = this.makePlayerMove();
            gameStatus = this.gameField.checkToContinueGame(moveStatus);

        } while ((gameStatus != GameConstants.GAME_STATUS_END_GAME) &&
                (gameStatus != GameConstants.GAME_STATUS_WIN_GAME) &&
                (gameStatus != GameConstants.GAME_STATUS_NO_FREE_CELLS));

        if (gameStatus == GameConstants.GAME_STATUS_WIN_GAME) this.setWinner(currentPlayer);
        this.gameField.renderGameField();
    }

}