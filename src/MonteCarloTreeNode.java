import static constants.Constants.CURIOSITY;

public class MonteCarloTreeNode {

    GameState gameState = null;
    int traversals = 1;
    int[] totalResult = {0,0,0,0,0,0};
    MonteCarloTreeNode parent;
    MonteCarloTreeNode[] children = null;

    static MonteCarloTreeNode createHeadNode() {
        return new MonteCarloTreeNode(null, GameState.getInitialState());
    }

    MonteCarloTreeNode(MonteCarloTreeNode parent, GameState state) {
        this.parent = parent;
        this.gameState = state;
    }

    void expandNode() {
        if (this.children!=null) {
            return;
        }
        this.children = new MonteCarloTreeNode[this.gameState.numberOfMoves];
        for (int i=0;i<this.gameState.numberOfMoves;i++) {
            this.children[i] = new MonteCarloTreeNode(this, this.gameState.executeMove(i));
        }
    }

    int decideMove() {
        expandNode();
        int decision = 0;
        double bestCuriosity = 0;
        for (int i=0;i<this.children.length;i++) {
            MonteCarloTreeNode child = this.children[i];
            int childValue = child.totalResult[child.gameState.currentTurn-1]/child.traversals;
            int childTraversals = child.traversals;
            double curiosity = 1.0*childValue/childTraversals+CURIOSITY*Math.sqrt(Math.log(1.0*this.traversals/childTraversals));
            if (curiosity>bestCuriosity) {
                bestCuriosity = curiosity;
                decision = i;
            }
        }
        return decision;
    }

    MonteCarloTreeNode findLeaf() {
        expandNode();
        if (this.children.length==0) {
            return this;
        }
        int decision = decideMove();
        return this.children[decision].findLeaf();
    }

    void backpropagateResult(int[] result) {
        for (int i=0;i<6;i++) {
            this.totalResult[i] += result[i];
        }
        this.traversals++;
        if (this.parent!=null) {
            this.parent.backpropagateResult(result);
        }
    }

    void runSingleSimulation() {
        MonteCarloTreeNode leaf = findLeaf();
        int[] result = GameStateEvaluator.eval(leaf.gameState);
        leaf.backpropagateResult(result);
    }

}
