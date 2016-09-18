package gravitrips;

/**
 * Created by Amir on 31.08.2016..
 */
abstract class Player {

    private String playerName;
    private String playerSymbol;

    private int playerID = 0;

    public Player(int playerID){

        this.playerName = GameIO.getUserInputString("Enter player name:");
        this.playerSymbol = GameIO.getUserInputChar("Enter player symbol:");

        this.playerID = playerID;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int  getPlayerID() {
        return this.playerID;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public int makeMove(int fieldColumnCount) {
        throw new AbstractMethodError();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!playerName.equals(player.playerName)) return false;
        return playerSymbol.equals(player.playerSymbol);

    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + (playerSymbol != null ? playerSymbol.hashCode() : 0);
        result = 31 * result + playerID;
        return result;
    }

    @Override
    public String toString(){
        String toReturn = String.format("\n Player ID: %d \n Player Name: %s \n Player symbol: %s",
                getPlayerID(), getPlayerName(), getPlayerSymbol());
        return toReturn;
    }

}