package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 2/20/2016.
 *
 * Represents a Node that caches the value of the estimated cost of the shortest distance to the goal state, as determined
 * by a Heuristic function.
 */
public class HeuristicNode<T,U> extends Node<T,U> {
    protected double heuristicValue;

    public HeuristicNode(T state, HeuristicNode<T,U> parent, U action, double pathCost, double heuristicValue)
    {
        super(state, parent, action, pathCost);
        this.heuristicValue = heuristicValue;
    }

    public double getHeuristicValue()
    {
        return this.heuristicValue;
    }

    public void setHeuristicValue(double value)
    {
        this.heuristicValue = value;
    }

    @Override
    public HeuristicNode<T,U> getParent()
    {
        return (HeuristicNode<T,U>)this.parent;
    }

    public double getEstimatedCostOfCheapestSolution() {
        return this.getPathCost() + this.getHeuristicValue();
    }
}
