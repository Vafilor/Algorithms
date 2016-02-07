package com.vafilor.search;

import java.util.*;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class SearchMethods {

    /**
     * Warning. Use with caution, may consume a lot of memory.
     * @param problem
     * @return null for failure, List of actions otherwise.
     */
    public static List<Object> graphBreadthFirstSearch(ISearchProblem problem) {
        Node node = new Node(problem.getInitialState());

        if(problem.isGoalState(node.getState())) {
            return getSolution(node);
        }

        LinkedList<Node> frontier = new LinkedList<Node>();
        frontier.add(node);

        //Use HashMap to keep track of nodes in frontier, much faster to check using HashMap than LinkedList
        HashMap<String, Node> frontierCopy = new HashMap<String, Node>();
        frontierCopy.put(node.getState().toString(), node);

        HashMap<String, Node> explored = new HashMap<String, Node>();

        Node child = null;

        while(!frontier.isEmpty()) {
            node = frontier.pop();
            frontierCopy.remove(node.getState().toString());

            if(explored.get(node.getState().toString()) == null) {
                explored.put(node.getState().toString(), node);
            }

            for(Object action : problem.getPossibleActions(node) ) {
                child = getChildNode(problem, node, action);

                //Ensure child is not one we have already considered
                if( (frontierCopy.get(child.getState().toString()) == null) && (explored.get(child.getState().toString()) == null) )  {

                    if(problem.isGoalState(child.getState())) {
                        return getSolution(child);
                    }
                    frontier.add(child);
                }
            }
        }

        return null;
    }


    /**
     * Also known as Iterative Deepening Depth First Search.
     * Functions much like Breadth First Search, but using Depth First over and over.
     * This version takes much less memory than Breadth First Search, but does not keep track of visited states.
     *
     * @param problem
     * @return
     */
    public static List<Object> treeDepthFirstSearch(ISearchProblem problem) {
        int depth = 0;

        List<Object> result = null;

        while(true) {
            try {
                result = depthLimitedSearch(problem, depth);
            } catch (IllegalStateException cutoffOccurred) {
                result = null;
            }

            if(result != null) {
                return result;
            }

            depth++;
        }
    }

    public static List<Object> depthLimitedSearch(ISearchProblem problem, int limit) {
        return recursiveDepthLimitedSearch(new Node(problem.getInitialState()), problem, limit);
    }

    //TODO check
    public static List<Object> aStarSearch(ISearchProblem problem) {
        Node node = new Node(problem.getInitialState());

        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new EstimatedDistanceComparator());//LinkedList<Node>();
        frontier.add(node);
        //Use HashMap to keep track of nodes in frontier, much faster to check using HashMap than LinkedList
        HashMap<String, Node> frontierCopy = new HashMap<String, Node>();
        frontierCopy.put(node.getState().toString(), node);

        HashMap<String, Node> explored = new HashMap<String, Node>();

        Node child = null;

        while(!frontier.isEmpty()) {

            node = frontier.poll();
            frontierCopy.remove(node.getState().toString());

            if(problem.isGoalState(node.getState())) {
                return getSolution(node);
            }

            if(explored.get(node.getState().toString()) == null) {
                explored.put(node.getState().toString(), node);
            }

            for(Object action : problem.getPossibleActions(node) ) {
                child = getChildNode(problem, node, action);

                //Ensure child is not one we have already considered
                if( (frontierCopy.get(child.getState().toString()) == null) && (explored.get(child.getState().toString()) == null) )  {
                    frontier.add(child);
                } else if( frontierCopy.get(child.getState().toString()) != null) {
                    Node frontierNode = frontierCopy.get(child.getState().toString());

                    if(frontierNode.getEstimatedCostOfCheapestSolution() > child.getEstimatedCostOfCheapestSolution()) {
                        frontierCopy.put(child.getState().toString(), child);
                        frontier.remove( frontierNode );
                        frontier.add(child);
                    }
                }
            }
        }

        return null;
    }

    private static List<Object> recursiveDepthLimitedSearch(Node node, ISearchProblem problem, int limit) throws IllegalStateException {
        if(problem.isGoalState(node.getState())) {
            return getSolution(node);
        } else if( limit == 0 ) {
            throw new IllegalStateException("Cutoff occurred"); //TODO neater way to do this?
        } else {
            boolean cutoffOccurred = false;

            Node childNode = null;
            List<Object> result = null;

            for(Object action : problem.getPossibleActions(node)) {
                childNode = getChildNode(problem, node, action);

                try {
                    result = recursiveDepthLimitedSearch(childNode, problem, limit - 1);
                } catch (IllegalStateException cutoffOccurredException) {
                    cutoffOccurred = true;
                }

                if(result != null) {
                    return result;
                }
            }


            if(cutoffOccurred) {
                throw new IllegalStateException("Cutoff Occurred");
            }

            return null;
        }
    }

    private static Node getChildNode(ISearchProblem problem, Node parent, Object action) {
        Object newState = problem.getResultingState(parent.getState(), action);
        double pathCost = parent.getPathCost() + problem.getStepCost(parent.getState(), action);
        double estimatedCostOfCheapestSolution = pathCost + problem.getHeuristicCost(newState);

        return new Node(newState, parent, action, pathCost, estimatedCostOfCheapestSolution);
    }

    private static List<Object> getSolution(Node solutionNode) {
        List<Object> actions = new ArrayList<Object>();

        while(solutionNode.getParent() != null) {
            actions.add(0, solutionNode.getAction());
            solutionNode = solutionNode.getParent();
        }

        return actions;
    }
}
