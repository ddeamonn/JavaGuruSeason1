package gravitrips;

import java.util.Random;

/**
 * Created by Amir on 31.08.2016..
 */
class AIPlayer extends Player {

    public AIPlayer(int playerID) {
        super(playerID);
    }

    @Override
    public int makeMove(int gameFieldColumnCount) {
        int columnToPlaceFigure;
        Random rnd = new Random();
        columnToPlaceFigure = rnd.nextInt(gameFieldColumnCount) + 1;
        return columnToPlaceFigure;
    }

}
