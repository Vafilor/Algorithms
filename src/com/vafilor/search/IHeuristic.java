package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/25/2015.
 *
 * Represents an object that calculates a heuristic function for a particular state,
 * which is the estimated cost of the cheapest path from the state to a goal state.
 */
public interface IHeuristic<T> {
    double value(T state);
}
