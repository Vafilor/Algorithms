package com.vafilor.search;

/**
 * Created by Andrey on 2/12/2016.
 *
 * A LimitNode adds a variable to Node to keep track of additional information for a Node, such as
 * the best alternative Path Cost, or the smallest PathCost of the subtree from this Node.
 *
 * Algorithms can make use of this information to decide whether or not to expand/re-expand a node.
 */
public class LimitNode<T,U> extends HeuristicNode<T,U> implements Comparable<LimitNode<T,U>> {
    protected double limit;

    public LimitNode(T state, LimitNode<T,U> parent, U action, double pathCost, double heuristicValue, double limit) {
        super(state, parent, action, pathCost, heuristicValue);
        this.limit = limit;
    }

    public double getLimit() {
        return this.limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * Limit Nodes are compared based on their limit values, with a Node being "less" if its limit value is less.
     *
     * Uses Double's comparison method.
     * @param that
     * @return + if this.limit > that.limit
     *         0 if this.limit equals that.limit (based on Double's comparison implementation.
     *         - if this.limit < that.limit
     */
    @Override
    public int compareTo(LimitNode<T,U> that)
    {
       return Double.compare(this.limit, that.limit);
    }

    @Override
    public LimitNode<T,U> getParent()
    {
        return (LimitNode<T,U>)this.parent;
    }
}
