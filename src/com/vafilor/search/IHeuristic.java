package com.vafilor.search;

/**
 * Represents an object that calculates a heuristic function,
 * which is the estimated cost of the cheapest path from the state a Node contains to a goal state.
 *
 * Created by Andrey Melnikov on 6/25/2015.
 */
public interface IHeuristic {
    double getHeuristicCost(Object node);
}
