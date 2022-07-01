import java.util.ArrayList;
import java.util.Arrays;

public class GameState {

    int currentTurn;
    int[] playerAssignments = new int[30];
    int numberOfMoves;

    static GameState getInitialState() {
        GameState toReturn = new GameState();
        toReturn.currentTurn = 1;
        toReturn.numberOfMoves = 24;
        for (int i=0;i<30;i++) {
            toReturn.playerAssignments[i] = 0;
        }
        toReturn.playerAssignments[Player.GAWGEE.index] = 1;
        toReturn.playerAssignments[Player.IWAY.index] = 2;
        toReturn.playerAssignments[Player.PROMINENCEPLAYER.index] = 3;
        toReturn.playerAssignments[Player.CONSUMESAMOSA.index] = 4;
        toReturn.playerAssignments[Player.MYHOLEISSTICKY.index] = 5;
        toReturn.playerAssignments[Player.RANGERR.index] = 6;
        return toReturn;
    }

    GameState executeMove(int move) {
        int index = 0;
        while (move>0 || playerAssignments[index]!=0) {
            if (playerAssignments[index]!=0) {
                index++;
            } else {
                move--;
                index++;
            }
        }
        GameState toReturn = this.copy();
        toReturn.playerAssignments[index] = toReturn.currentTurn;
        toReturn.currentTurn++;
        if (toReturn.currentTurn>=7) {
            toReturn.currentTurn = 1;
        }
        toReturn.numberOfMoves--;
        return toReturn;
    }

    GameState copy() {
        GameState toReturn = new GameState();
        toReturn.numberOfMoves = this.numberOfMoves;
        toReturn.currentTurn = this.currentTurn;
        System.arraycopy(this.playerAssignments, 0, toReturn.playerAssignments, 0, 30);
        return toReturn;
    }

    public String toString() {
        return Arrays.toString(playerAssignments);
    }
}
