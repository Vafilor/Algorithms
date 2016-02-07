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

            long start = System.nanoTime();

            List<Object> solution = SearchMethods.graphBreadthFirstSearch(slidingBlock);

            long end = System.nanoTime();

            long firstLength = end - start;

            start = System.nanoTime();
            List<Object> solution2 = SearchMethods.aStarSearch(slidingBlock);
            end = System.nanoTime();

            long secondLength = end - start;

//            List<Object> solution = SearchMethods.graphBreadthFirstSearch(slidingBlock);

            if (solution == null) {
                System.out.println("no solution");
            }

            System.out.println(solution);
            System.out.println(solution2);

            System.out.println("First Took:" + firstLength / Math.pow(10, 9));
            System.out.println("Second Took:" + secondLength / Math.pow(10, 9));

            System.out.println("Solution 1 Length:" + solution.size());
            System.out.println("Solution 2 Length:" + solution2.size());

        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

}
