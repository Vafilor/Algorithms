package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 *
 * A Node represents an element in the tree and contains a reference to a parent node.
 *
 * T is an object that represents a State of a problem.
 * U is an object that represents an action applicable to a state of the problem.
 */
public class Node<T,U> {
    private T state;
    protected Node<T,U> parent;
    private U action;

    /**
     * The PathCost is the total cost of the path to this point.
     */
    private double pathCost;

    public Node(T state, Node<T,U> parent, U action, double pathCost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
    }


    public T getState() {
        return state;
    }

    public Node<T,U> getParent() {
        return parent;
    }

    public U getAction() {
        return action;
    }

    public double getPathCost() {
        return pathCost;
    }
}
