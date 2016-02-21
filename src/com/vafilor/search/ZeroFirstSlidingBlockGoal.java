package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/25/2015.
 *
 * Solution of a sliding block puzzle has the blank in the top left corner. E.g. (0 is blank)
 * 0 1 2
 * 3 4 5
 * 6 7 8
 *
 */
public class ZeroFirstSlidingBlockGoal implements ISlidingBlockGoal {

    @Override
    public boolean isGoal(SlidingBlockState state) {
        int[][] slidingBlockState = state.state;

        for(int row = 0; row < slidingBlockState.length; row++) {
            for(int column = 0; column < slidingBlockState[row].length; column++) {
                if(slidingBlockState[row][column] != (slidingBlockState.length * row + column) ) {
                    return false;
                }
            }
        }

        return true;
    }
}
