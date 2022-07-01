public class GameStateEvaluator {

    public static int[] eval(GameState gameState) {
        Player[][] teamLineup = {new Player[5], new Player[5], new Player[5], new Player[5], new Player[5], new Player[5]};
        int[] teamInsertIndexes = {0,0,0,0,0,0};
        int[] results = {0,0,0,0,0,0};
        for (int i=0;i<30;i++) {
            int team = gameState.playerAssignments[i]-1;
            int index = teamInsertIndexes[team];
            teamLineup[team][index] = Player.values()[i];
            teamInsertIndexes[team]++;
        }
        for (int i=0;i<6;i++) {
            for (int j=i+1;j<6;j++) {
                optimizeTeamForMatchup(teamLineup[i], teamLineup[j]);
                optimizeTeamForMatchup(teamLineup[j], teamLineup[i]);
                int result = evalMatchup(teamLineup[i], teamLineup[j]);
                results[i] += result;
                results[j] -= result;
            }
        }
        return results;
    }

    public static void optimizeTeamForMatchup(Player[] team1, Player[] team2) {
        int baseMatchupValue = evalMatchup(team1, team2);
        boolean doSwap = true;
        while (doSwap) {
            doSwap = false;
            SWAP:for (int i=0;i<5;i++) {
                for (int j=i+1;j<5;j++) {
                    Player temp = team1[i];
                    team1[i] = team1[j];
                    team1[j] = temp;
                    int newMatchupValue = evalMatchup(team1, team2);
                    if (newMatchupValue > baseMatchupValue) {
                        baseMatchupValue = newMatchupValue;
                        doSwap = true;
                        break SWAP;
                    } else {
                        temp = team1[i];
                        team1[i] = team1[j];
                        team1[j] = temp;
                    }
                }
            }
        }
    }

    public static int evalMatchup(Player[] team1, Player[] team2) {
        int totalTeamGap = 0;
        for (int i=0;i<5;i++) {
            int baseGap = team1[i].getStrengthInRole(i) - team2[i].getStrengthInRole(i);
            totalTeamGap += baseGap*Math.abs(baseGap);
        }
        return totalTeamGap;
    }

}
