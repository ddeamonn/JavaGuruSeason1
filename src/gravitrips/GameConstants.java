package gravitrips;

/**
 * Created by Amir on 31.08.2016..
 */
class GameConstants {

    public static final int PLACING_FIGURE_RESULT_SUCCESS = 51;
    public static final int PLACING_FIGURE_RESULT_FAILED = 52;
    public static final int PLACING_FIGURE_NO_FREE_CELLS = 50;

    public static final int USER_INPUT_END_GAME_CODE = 0;

    public static final int GAME_STATUS_WIN_GAME = 100;
    public static final int GAME_STATUS_NO_FREE_CELLS = 102;
    public static final int GAME_STATUS_END_GAME = 103;
    public static final int GAME_STATUS_CONTINUE_GAME = 101;
    public static final int GAME_CHECK_WINDOW_SIZE = 4;

    public static final int GAME_FIELD_ROW_MIN_SIZE = 6;
    public static final int GAME_FIELD_ROW_MAX_SIZE = 20;
    public static final int GAME_FIELD_COLUMN_MIN_SIZE = 7;
    public static final int GAME_FIELD_COLUMN_MAX_SIZE = 20;

    public static final int GAME_MIN_PLAYERS = 2;
    public static final int GAME_MAX_PLAYERS = 10;

    public static final int PLAYER_TYPE_HUMAN = 1;
    public static final int PLAYER_TYPE_AI = 2;

    public static final String EMPTY_CELL_SYMBOL = ".";
    public static final int EMPTY_CELL_NUMBER = 0;

    public static final int GAME_CHECK_ROWS = 0;
    public static final int GAME_CHECK_COLUMNS = 1;
    public static final int GAME_CHECK_DIAGONAL1 = 2;
    public static final int GAME_CHECK_DIAGONAL2 = 3;
    public static final int GAME_CHECK_TYPE_ROWS_COLUMNS = 1000;
    public static final int GAME_CHECK_TYPE_DIAGONALS = 1001;
    public static final int GAME_CHECK_TYPE_COUNT = 4;
}