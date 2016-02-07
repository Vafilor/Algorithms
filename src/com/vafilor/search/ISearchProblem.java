package com.vafilor.search;

import java.util.List;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */

//TODO look into more type-safe way to generalize a problem
public interface ISearchProblem {
    Object getInitialState();

    /**
     * Node is passed in in case the user wants to limit possible actions based on previous actions.
     * @param node containing current problem state
     * @return a List of objects representing the possible actions for the problem
     */
    List<Object> getPossibleActions(Object node);
    Object getResultingState(Object state, Object action);
    boolean isGoalState(Object state);
    double getStepCost(Object previousState, Object action);

    /**
     * Represents a heuristic function.
     *
     * @param state containing current problem state.
     * @return the estimated cost of the cheapest path from the state to a goal state.
     */
    double getHeuristicCost(Object state);
}
