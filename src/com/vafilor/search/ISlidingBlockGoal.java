package com.vafilor.search;

/**
 * Interface for objects that describe what a goal state is for a sliding block puzzle.
 *
 * Created by Andrey Melnikov on 6/25/2015.
 */
public interface ISlidingBlockGoal {
    /**
     * @param slidingBlockState assumes square array, or, the length of each row is the same.
     * @return true if state is a goal, false otherwise.
     */
    boolean isGoal(int[][] slidingBlockState);
}
