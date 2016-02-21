package com.vafilor.search;

import java.util.Comparator;

/**
 * Created by Andrey Melnikov on 6/25/2015.
 */
public class EstimatedDistanceComparator implements Comparator<HeuristicNode>
{
    public int compare(HeuristicNode first, HeuristicNode second) {
        return Double.compare(first.getEstimatedCostOfCheapestSolution(), second.getEstimatedCostOfCheapestSolution());
    }
}
