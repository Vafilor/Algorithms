package com.vafilor.algorithms.genetic;

import java.util.Random;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 *
 * Represents a positioning of N-Queens on a chessboard, where each queen is in its own column.
 */
public class NQueensState implements IGeneticState<NQueensState> {

    private int[] queensPositions;

    //TODO add a variadic constructor where each entry is a position and the number of queens in the number of entries

    //TODO - only allow a queens number where a solution exits? E.g. exclude 2,3.
    public NQueensState(int numberOfQueens)
    {
        this.queensPositions = new int[numberOfQueens];
    }

    //TODO more testing on this one.
    public NQueensState(int[] partA, int[] partB)
    {
        int newLength = partA.length + partB.length;

        for(int element : partA )
        {
            if( element < -1 || element >= newLength)
            {
                throw new IllegalArgumentException("Element position can't be greater than chessboard size:" + element);
            }
        }

        for(int element : partB )
        {
            if( element < -1 || element >= newLength)
            {
                throw new IllegalArgumentException("Element position can't be greater than chessboard size");
            }
        }

        this.queensPositions = new int[ newLength ];

        System.arraycopy(partA, 0, this.queensPositions, 0, partA.length);
        System.arraycopy(partB, 0, this.queensPositions, partA.length, partB.length);
    }

    /**
     * Changes each queen to a random row.
     */
    public void randomize()
    {
        Random random = new Random();

        for(int i = 0; i < this.queensPositions.length; i++)
        {
            this.queensPositions[i] = random.nextInt(this.queensPositions.length);
        }
    }

    /**
     * @param start
     * @param end
     * @return an NQueensState. Note that the state may actually not be a valid NQueens state. E.g. 4 queens with one having a row of 5.
     */
    @Override
    public NQueensState subSequence(int start, int end)
    {
        NQueensState newState = new NQueensState(end - start);

        System.arraycopy(this.queensPositions, start, newState.queensPositions, 0, newState.queensPositions.length);

        return newState;
    }

    public int size()
    {
        return this.queensPositions.length;
    }

    @Override
    public void mutate(double chanceOfMutation)
    {
        Random random = new Random();

        for(int i = 0; i < this.queensPositions.length; i++)
        {
            if(random.nextDouble() <= chanceOfMutation)
            {
                this.queensPositions[i] = random.nextInt(this.queensPositions.length);
            }
        }
    }

    @Override
    public void mutate()
    {
        this.mutate(0.25);//0.25 is a randomly chosen value
    }

    @Override
    public NQueensState join(NQueensState that)
    {
        return new NQueensState(this.queensPositions, that.queensPositions);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.queensPositions.length; i++)
        {
            result.append(this.queensPositions[i]);
            result.append(" ");
        }

        return result.toString();
    }

    /**
     * @param column
     * @return the queen's Row for a given column.
     */
    public int queenRowForColumn(int column)
    {
        return this.queensPositions[column];
    }
}
