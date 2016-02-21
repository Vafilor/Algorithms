package com.vafilor.search;

/**
 * Created by Andrey on 2/20/2016.
 */
public class ManhattanDistance implements IHeuristic<SlidingBlockState> {

    @Override
    public double value(SlidingBlockState state) {
        double manhattanDistance = 0;

        for(int row = 0; row < state.state.length; row++)
        {
            for(int column = 0; column < state.state.length; column++)
            {
                manhattanDistance += this.distanceFromGoalPosition(row, column, state.state[row][column], state.length);
            }
        }

        return manhattanDistance;
    }

    /**
     * Calculates the number of horizontal/vertical movements that the value at (row,column) would need to make to get
     * to its goal position.
     *
     * E.g.
     *
     * 7 2 4
     * 5 0 6
     * 8 5 1
     *
     * The 7 should be in position (2,1) and so needs to move 1 horizontal right, and 2 horizontal down, so this method returns 3,
     * for a 3x3 state.
     *
     * @param row
     * @param column
     * @param value
     * @param stateLength
     * @return The number of horizontal/vertical movements that the value at (row,column) would need to make to get
     * to its goal position.
     */
    private double distanceFromGoalPosition(int row, int column, int value, int stateLength)
    {
        return Math.abs(row - value / stateLength) + Math.abs(column - (value % stateLength));
    }
}
