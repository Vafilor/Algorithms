package com.vafilor.search;

import java.util.List;

/**
 * Created by Andrey on 2/14/2016.
 *
 * A Transition model contains information on how an action changes a state, as well as the actions
 * possible on a given state.
 *
 * T is an object that represents a State of a problem.
 * U is an object that represents an action applicable to a state of the problem.
 *
 * Note: I put getPossibleActions here so there would be less classes to create to implement a Search Problem, and to make a
 * class implementing ISearchProblem slightly more flexible in handling a State of some type, but with different possible action sets.
 * For example, you could simplify the 8-puzzle problem by creating "short-cut" action sets, such as move a piece twice to the right, or left.
 * An algorithm might solve such problems faster with such shortcuts, and if you can translate the "shortcuts" to regular moves, you can easily
 * get the "atomic" action sequence.
 */
public interface ITransitionModel<T,U>
{
     /**
      * @param state a state of the problem.
      * @param action an action applicable to the state of the problem.
      * @return the state resulting from applying action to state.
      */
     T getResult(T state, U action);

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
}
