package com.vafilor.search;

import java.util.*;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class SearchMethods {

//    /**
//     * Warning. Use with caution, may consume a lot of memory.
//     * @param problem
//     * @return null for failure, List of actions otherwise.
//     */
//    public static List<Object> graphBreadthFirstSearch(OLDISearchProblem problem) {
//        Node node = new Node(problem.getInitialState());
//
//        if(problem.isGoalState(node.getState())) {
//            return getSolution(node);
//        }
//
//        LinkedList<Node> frontier = new LinkedList<Node>();
//        frontier.add(node);
//
//        //Use HashMap to keep track of nodes in frontier, much faster to check using HashMap than LinkedList
//        HashMap<String, Node> frontierCopy = new HashMap<String, Node>();
//        frontierCopy.put(node.getState().toString(), node);
//
//        HashMap<String, Node> explored = new HashMap<String, Node>();
//
//        Node child = null;
//
//        while(!frontier.isEmpty()) {
//            node = frontier.pop();
//            frontierCopy.remove(node.getState().toString());
//
//            if(explored.get(node.getState().toString()) == null) {
//                explored.put(node.getState().toString(), node);
//            }
//
//            for(Object action : problem.getPossibleActions(node) ) {
//                child = getChildNode(problem, node, action);
//
//                //Ensure child is not one we have already considered
//                if( (frontierCopy.get(child.getState().toString()) == null) && (explored.get(child.getState().toString()) == null) )  {
//
//                    if(problem.isGoalState(child.getState())) {
//                        return getSolution(child);
//                    }
//                    frontier.add(child);
//                }
//            }
//        }
//
//        return null;
//    }
//
//
//    /**
//     * Also known as Iterative Deepening Depth First Search.
//     * Functions much like Breadth First Search, but using Depth First over and over.
//     * This version takes much less memory than Breadth First Search, but does not keep track of visited states.
//     *
//     * @param problem
//     * @return
//     */
//    public static List<Object> treeDepthFirstSearch(OLDISearchProblem problem) {
//        int depth = 0;
//
//        List<Object> result = null;
//
//        while(true) {
//            try {
//                result = depthLimitedSearch(problem, depth);
//            } catch (IllegalStateException cutoffOccurred) {
//                result = null;
//            }
//
//            if(result != null) {
//                return result;
//            }
//
//            depth++;
//        }
//    }
//
//    public static List<Object> depthLimitedSearch(OLDISearchProblem problem, int limit) {
//        return recursiveDepthLimitedSearch(new Node(problem.getInitialState()), problem, limit);
//    }
//
//    //TODO check
//    public static List<Object> aStarSearch(OLDISearchProblem problem) {
//        Node node = new Node(problem.getInitialState());
//
//        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new EstimatedDistanceComparator());//LinkedList<Node>();
//        frontier.add(node);
//        //Use HashMap to keep track of nodes in frontier, much faster to check using HashMap than LinkedList
//        HashMap<String, Node> frontierCopy = new HashMap<String, Node>();
//        frontierCopy.put(node.getState().toString(), node);
//
//        HashMap<String, Node> explored = new HashMap<String, Node>();
//
//        Node child = null;
//
//        while(!frontier.isEmpty()) {
//
//            node = frontier.poll();
//            frontierCopy.remove(node.getState().toString());
//
//            if(problem.isGoalState(node.getState())) {
//                return getSolution(node);
//            }
//
//            if(explored.get(node.getState().toString()) == null) {
//                explored.put(node.getState().toString(), node);
//            }
//
//            for(Object action : problem.getPossibleActions(node) ) {
//                child = getChildNode(problem, node, action);
//
//                //Ensure child is not one we have already considered
//                if( (frontierCopy.get(child.getState().toString()) == null) && (explored.get(child.getState().toString()) == null) )  {
//                    frontier.add(child);
//                } else if( frontierCopy.get(child.getState().toString()) != null) {
//                    Node frontierNode = frontierCopy.get(child.getState().toString());
//
//                    if(frontierNode.getEstimatedCostOfCheapestSolution() > child.getEstimatedCostOfCheapestSolution()) {
//                        frontierCopy.put(child.getState().toString(), child);
//                        frontier.remove( frontierNode );
//                        frontier.add(child);
//                    }
//                }
//            }
//        }
//
//        return null;
//    }
//
//    private static List<Object> recursiveDepthLimitedSearch(Node node, OLDISearchProblem problem, int limit) throws IllegalStateException {
//        if(problem.isGoalState(node.getState())) {
//            return getSolution(node);
//        } else if( limit == 0 ) {
//            throw new IllegalStateException("Cutoff occurred"); //TODO neater way to do this?
//        } else {
//            boolean cutoffOccurred = false;
//
//            Node childNode = null;
//            List<Object> result = null;
//
//            for(Object action : problem.getPossibleActions(node)) {
//                childNode = getChildNode(problem, node, action);
//
//                try {
//                    result = recursiveDepthLimitedSearch(childNode, problem, limit - 1);
//                } catch (IllegalStateException cutoffOccurredException) {
//                    cutoffOccurred = true;
//                }
//
//                if(result != null) {
//                    return result;
//                }
//            }
//
//
//            if(cutoffOccurred) {
//                throw new IllegalStateException("Cutoff Occurred");
//            }
//
//            return null;
//        }
//    }

    //TODO consistent braces format
    private static class RBFSResult<U>
    {
        private boolean sucessful;
        private double newLimit;
        private List<U> solution;

        public RBFSResult(boolean successful, double newLimit, List<U> solution)
        {
            //TODO - validation? If not successful, can't have solution?
            this.sucessful = successful;
            this.newLimit = newLimit;
            this.solution = solution;
        }

        public boolean isSucessful()
        {
            return this.sucessful;
        }

        public double getNewLimit() {
            return this.newLimit;
        }

        public List<U> getSolution() {
            return this.solution;
        }

    }

	/**
	* Based on the pseudo-code outlined in: Artificial Intelligence: A Modern Approach (3rd Edition) 3rd Edition
    * by Stuart Russell, Peter Norvig
    *
	*/
    public static <T,U> List<U> recursiveBestFirstSearch(ISearchProblem<T,U> problem, IHeuristic<T> heuristicFunction)
    {
        LimitNode<T,U> initialNode = makeLimitNode(problem, heuristicFunction);//TODO can an enum be null?> Err, do we want that?

        RBFSResult<U> result = recursiveBestFirstSearch(problem, initialNode, Double.MAX_VALUE, heuristicFunction);

        //TODO maybe make a wrapper - don't want to check if solution is null all the time. Kinda like RBFSResult
        return result.solution;
    }

    private static <T,U> RBFSResult<U> recursiveBestFirstSearch(ISearchProblem<T,U> problem, LimitNode<T,U> node, double limit, IHeuristic<T> heuristicFunction)
    {
        if(problem.isGoal(node.getState()))
        {
           return new RBFSResult<>(true, limit, getSolution(node) );
        }

        List<LimitNode<T,U>> successors = new ArrayList<>();//TODO is there a better data structure for this?

        for( U action : problem.getPossibleActions(node) )
        {
            successors.add( makeLimitNode(problem, node, action, heuristicFunction) );
        }

        if(successors.isEmpty())
        {
            return new RBFSResult<>(false, Double.MAX_VALUE, null);
        }

        for( LimitNode<T,U> successor: successors)
        {
            successor.setLimit( Math.max( successor.getPathCost() + successor.getHeuristicValue(), node.getLimit() ));
        }

        LimitNode<T,U> bestNode = null;
        LimitNode<T,U> alternative = null;
        RBFSResult<U> result = null;

        while(true)
        {
            bestNode = Collections.min(successors);

            if(bestNode.getLimit() > limit)
            {
                return new RBFSResult<>(false, bestNode.getLimit(), null);
            }

            alternative = getSecondSmallest(successors);

            result = recursiveBestFirstSearch(problem, bestNode, Math.min(limit, alternative.getLimit()), heuristicFunction);
            bestNode.setLimit(result.getNewLimit());

            if(result.isSucessful())
            {
                return result;
            }
        }
    }

    public static <T extends Comparable<T>>  T getSecondSmallest(List<T> items)
    {
        T previousSmallest = items.get(0);
        T smallest = items.get(0);

        for(T item : items)
        {
           if( item.compareTo(smallest) < 0 )
           {
               previousSmallest = smallest;
               smallest = item;
           }
        }

        return previousSmallest;
    }

    private static <T,U> Node<T,U> makeNode(ISearchProblem<T,U> problem, Node<T,U> parent, U action) {
        T newState = problem.getResultState(parent.getState(), action);
        double pathCost = parent.getPathCost() + problem.getStepCost(parent.getState(), action);

        return new Node<>(newState, parent, action, pathCost);
    }

    private static <T,U> LimitNode<T,U> makeLimitNode(ISearchProblem<T,U> problem, LimitNode<T,U> parent, U action, IHeuristic<T> heuristicFunction) {
        T newState = problem.getResultState(parent.getState(), action);
        double pathCost = parent.getPathCost() + problem.getStepCost(parent.getState(), action);

        return new LimitNode<>(newState, parent, action, pathCost, heuristicFunction.value(newState), Double.MIN_VALUE );
    }

    private static <T,U> LimitNode<T,U> makeLimitNode(ISearchProblem<T,U> problem, IHeuristic<T> heuristicFunction)
    {
        T initialState = problem.getInitialState();
        double heuristicValue = heuristicFunction.value(initialState);

        return new LimitNode<>(initialState, null, null, 0.0, heuristicValue, heuristicValue);

    }

    private static <T,U> List<U> getSolution(Node<T,U> solutionNode) {
        List<U> actions = new ArrayList<>();

        while(solutionNode.getParent() != null) {
            actions.add(0, solutionNode.getAction());
            solutionNode = solutionNode.getParent();
        }

        return actions;
    }
}
