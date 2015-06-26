package com.vafilor.search;

import java.util.List;

public class ProblemSolver {

    public static void main(String[] args) {

		if(args.length < 1) {
			System.out.println("Expected argument: filename");
			return;
		}
	
        try {
            ISearchProblem slidingBlock = new SlidingBlockProblem(args[0]);

            System.out.println(slidingBlock.getInitialState());

            List<Object> solution = SearchMethods.graphBreadthFirstSearch(slidingBlock);

//            List<Object> solution = SearchMethods.graphBreadthFirstSearch(slidingBlock);

            if (solution == null) {
                System.out.println("no solution");
            }

            System.out.println(solution);
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

}
