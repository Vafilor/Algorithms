package com.vafilor.search;

/**
 * Created by Andrey Melnikov on 6/25/2015.
 *
 * Evaluates cost to get to goal for a sliding block puzzle based on the number of misplaced tiles.
 * Note: This assumes a 0 is first tile solution.
 *
 * E.g. (where 0 is blank)
 *
 * 7 2 4
 * 5 0 6
 * 8 3 1
 *
 * Goal is:
 *
 * 0 1 2
 * 3 4 5
 * 6 7 8
 *
 * the cost would be: 8, as none of the tiles are in the proper spots.
 *
 *
 */
public class MisplacedTiles implements IHeuristic<SlidingBlockState> {

    @Override
    public double value(SlidingBlockState slidingBlockState) {
        int[][] state = slidingBlockState.state;

        double misplaced = 0;

        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {
                if (state[row][column] != (state.length * row + column )) {
                    misplaced++;
                }
            }
        }

        return misplaced;
    }
}
