package com.vafilor.search;

import java.util.List;

/**
 * Created by Andrey Melnikov on 2/12/2016
 *
 * Represents a Search Problem, which is a problem that can be broken down into states, with an initial state,
 * and a goal state. The solution for a search problem is a sequence of actions that lead you from the initial state
 * to the goal state. An example is the 8 puzzle.
 *
 * T is an object that represents a State of a problem.
 * U is an object that represents an action applicable to a state of the problem.
 */
public interface ISearchProblem<T,U> {
    /**
     * @param state a state of a problem.
     * @return all of the applicable actions in the given state.
     */
    List<U> getPossibleActions(T state);

    /**
     * This method is passed in a Node so that the user can perform pruning of the actions based on previous actions already taken.
     * For example, in the 8 puzzle, it makes no sense to go right if you just went left, this method would prune the right action as it
     * is not productive to consider it.
     *
     * @param node a node containing a state and references to a parent node.
     * @return all of the applicable actions in the given state.
     */
    List<U> getPossibleActions(Node<T,U> node);

    /**
     * @param state a state of the problem.
     * @param action an action applicable to the state of the problem.
     * @return the state resulting from applying action to state.
     */
    T getResultState(T state, U action);

    /**
     * @param state a state of the problem.
     * @return true if the state is a goal state, false otherwise.
     */
    boolean isGoal(T state);

    /**
     * @return the initial state of the problem.
     */
    T getInitialState();

    /**
     * A cost is a measure of "expense"/"badness". The higher the cost of a path to get to a state, the worse the path.
     * @param state a state of the problem.
     * @param action an action applicable to the state of the problem.
     * @return the step cost of performing action in state.
     */
    double getStepCost(T state, U action);
}
