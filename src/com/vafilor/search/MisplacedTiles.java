package com.vafilor.search;

/**
 * Evaluates cost to get to goal for a sliding block puzzle based on the number of misplaced tiles.
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
 * Created by Andrey Melnikov on 6/25/2015.
 */
public class MisplacedTiles implements IHeuristic {

    /**
     * Assumes the object passed in is an int[][]
     * @param slidingBlockState
     * @return
     */
    @Override
    //TODO for now this assumes zeroLast. Add zeroFirst version
    public double getHeuristicCost(Object slidingBlockState) {
        int[][] state = (int[][])slidingBlockState;
        double misplaced = 0;

        //TODO simplify
        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {

                if((row == state.length - 1) && (column == state[row].length - 1)) {
                    if(state[row][column] != 0) {
                        misplaced++;
                    }
                } else {

                    if (state[row][column] != (state.length * row + column + 1)) {
                        misplaced++;
                    }
                }
            }
        }

        return misplaced;
    }
}
