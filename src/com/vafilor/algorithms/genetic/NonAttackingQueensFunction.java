package com.vafilor.algorithms.genetic;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 *
 * Evaluates NQueens state based on the number of non-attacking pairs of queens.
 */
public class NonAttackingQueensFunction  implements IFitnessFunction<NQueensState>
{
    private int numberOfQueens;

    //TODO - domain? You can't solve a 2-queens problem.
    public NonAttackingQueensFunction(int numberOfQueens)
    {
        this.numberOfQueens = numberOfQueens;
    }

    /**
     * @param state an NQueens state
     * @return the number of non-attacking pairs of queens.
     */
    public double value(NQueensState state)
    {
        int nonAttackingQueenPairs = 0;

        int queenRow1 = 0;
        int queenRow2 = 0;

        for(int column = 0; column < this.numberOfQueens; column++)
        {
            queenRow1 = state.queenRowForColumn(column);

            for(int column2 = column + 1; column2 < this.numberOfQueens; column2++)
            {
                queenRow2 = state.queenRowForColumn(column2);

                if(!areQueensAttacking(queenRow1, column, queenRow2, column2))
                {
                    nonAttackingQueenPairs++;
                }
            }
        }

        return nonAttackingQueenPairs;
    }

    /**
     * Checks if queens are (row,column) and (row2, column2) are attacking, which means they are
     * either in the same row, or on the same diagonal.
     *
     * @param row
     * @param column
     * @param row2
     * @param column2
     * @return true if queens are attacking, false otherwise.
     */
    private boolean areQueensAttacking(int row, int column, int row2, int column2)
    {
        return  (row == row2) ||
                ((row2 - column2) == (row - column)) ||
                ((row + column) == (row2 + column2));
    }

    /**
     * @return the max number of non-attacking pairs of queens.
     */
    public double maxValue()
    {
        return (this.numberOfQueens * this.numberOfQueens - this.numberOfQueens) / 2.0;
    }
}
