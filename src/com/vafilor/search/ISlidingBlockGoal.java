package com.vafilor.search;

/**
 * Describe what a goal state is for a sliding block puzzle.
 *
 * Created by Andrey Melnikov on 6/25/2015.
 */
public interface ISlidingBlockGoal {
    /**
     * @param state
     * @return true if state is a goal, false otherwise.
     */
    boolean isGoal(SlidingBlockState state);
}
