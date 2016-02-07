package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class Node {
    private Object state;
    private Node parent;
    private Object action;
    private double pathCost;
    private double estimatedCostOfCheapestSolution;

    public Node(Object state, Node parent, Object action, double pathCost, double estimatedCostOfCheapestSolution) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
        this.estimatedCostOfCheapestSolution = estimatedCostOfCheapestSolution;
    }

    public Node(Object state) {
        this(state, null, null, 0.0, Double.MAX_VALUE); //TODO is double max good here?
    }

    public Object getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Object getAction() {
        return action;
    }

    public double getPathCost() {
        return pathCost;
    }

    public double getEstimatedCostOfCheapestSolution() {
        return this.estimatedCostOfCheapestSolution;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName())) {
            Node that = (Node)obj;

            return this.state.equals(that.state);
        }

        return false;
    }
}
