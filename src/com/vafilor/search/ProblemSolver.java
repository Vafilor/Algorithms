package com.vafilor.search;

import java.util.List;

public class ProblemSolver {

    public static void main(String[] args) {

		if(args.length < 1) {
			System.out.println("Expected argument: filename");
			return;
		}

        List<SlidingAction> result = null;

        try {
            ITransitionModel<SlidingBlockState, SlidingAction> transitionModel = new SlidingBlockTransitionModel();
            IHeuristic<SlidingBlockState> misplacedTiles = new ManhattanDistance();
            ISlidingBlockGoal goalChecker = new ZeroFirstSlidingBlockGoal();
            SlidingBlockState initialState = new SlidingBlockState(args[0]);

            SlidingBlockProblem<SlidingAction> problem = new SlidingBlockProblem<>(transitionModel, initialState, goalChecker);

            long startTime = System.currentTimeMillis();

            result = SearchMethods.recursiveBestFirstSearch(problem, misplacedTiles);

            long endTime = System.currentTimeMillis();

            double timeInSeconds = (endTime-startTime)/1000.0;

            //System.out.format("Result: %s%n", result.toString());
            System.out.format("Took %f seconds%n%s", timeInSeconds, result);

        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }
}
