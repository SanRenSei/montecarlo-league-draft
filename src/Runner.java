import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
//        GameState gameState = GameState.getInitialState();
//        while (gameState.numberOfMoves>0) {
//            int randomMove = (int)Math.floor(Math.random()*gameState.numberOfMoves);
//            gameState = gameState.executeMove(randomMove);
//        }
//        System.out.println(gameState);
//        System.out.println(Arrays.toString(GameStateEvaluator.eval(gameState)));
        MonteCarloTreeNode node = MonteCarloTreeNode.createHeadNode();
        for (int i=0;i<1000000;i++) {
            node.runSingleSimulation();
            if (i%100000==0) {
                System.out.println(Arrays.toString(node.totalResult));
            }
        }
        System.out.println("======");
        for (int i=0;i<node.children.length;i++) {
            System.out.println(node.children[i].traversals);
        }
    }

}
