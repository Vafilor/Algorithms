package com.vafilor.search;

/**
 *
 * Solution of a sliding block puzzle has the blank in the bottom right corner. E.g. (0 is blank)
 * 1 2 3
 * 4 5 6
 * 7 8 0
 *
 * Created by Andrey Melnikov on 6/25/2015.
 */
public class ZeroLastSlidingBlockGoal implements ISlidingBlockGoal {

    @Override
    public boolean isGoal(int[][] slidingBlockState) {

        for(int row = 0; row < slidingBlockState.length; row++) {
            for(int column = 0; column < slidingBlockState[row].length; column++) {

                if((row == slidingBlockState.length - 1) && (column == slidingBlockState[row].length - 1) && slidingBlockState[row][column] == 0 ) {
                    return true;
                } else {

                    if (slidingBlockState[row][column] != (slidingBlockState.length * row + column + 1)) {
                        return false;

                    }
                }
            }
        }

        return true;
    }
}
