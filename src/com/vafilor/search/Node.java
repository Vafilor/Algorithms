package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class Node {
    private Object state;
    private Node parent;
    private Object action;
    private double pathCost;

    public Node(Object state, Node parent, Object action, double pathCost) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.pathCost = pathCost;
    }

    public Node(Object state) {
        this(state, null, null, 0.0);
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

    @Override
    public boolean equals(Object obj) {
        if(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName())) {
            Node that = (Node)obj;

            return this.state.equals(that.state);
        }

        return false;
    }
}
