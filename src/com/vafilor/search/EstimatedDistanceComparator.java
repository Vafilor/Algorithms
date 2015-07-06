package com.vafilor.search;

import java.util.Comparator;

/**
 * Created by Andrey on 6/25/2015.
 */
public class EstimatedDistanceComparator implements Comparator<Node> {

    //TODO simplify?
    public int compare(Node first, Node second) {
        if(first.getEstimatedCostOfCheapestSolution() < second.getEstimatedCostOfCheapestSolution()) {
            return -1;
        }

        return 1;
    }
}
