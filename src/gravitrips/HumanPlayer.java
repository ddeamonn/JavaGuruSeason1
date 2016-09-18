package gravitrips;

/**
 * Created by Amir on 31.08.2016..
 */
class HumanPlayer extends Player{

    public HumanPlayer(int playerID) {
        super(playerID);
    }

    @Override
    public int makeMove(int gameFieldColumnCount) {
        return GameIO.getUserInputIntegerWithRangeCheck("", 0, gameFieldColumnCount);
    }

}