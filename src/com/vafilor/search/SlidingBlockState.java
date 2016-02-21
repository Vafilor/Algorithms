package com.vafilor.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Andrey on 6/7/2015.
 *
 * Represents a state of a Sliding Block Puzzle.
 */
public class SlidingBlockState {
    protected int[][] state;
    /**
     * The length of a side of the "board" of the sliding block puzzle. An 8-puzzle has a length of 3. 3 x 3 board.
     */
    protected int length;
    protected Point blankSpotPosition;
    /**
     * Creates a block state given a filename.
     * Filename format: text file. First line, length of grid, then length * length numbers separated by whitepace and newline.
     * E.g.
     * 3
     * 1 2 3
     * 4 5 6
     * 7 8 0
     * @param fileName
     */
    //TODO clean up - separate methods out and name properly
    //TODO -update to use new file methods
    public SlidingBlockState(String fileName) throws FileNotFoundException{
        File sourceFile = new File(fileName);

        Scanner fileScanner = new Scanner(sourceFile);

        this.length = fileScanner.nextInt();

        this.state = new int[length][length];

        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {
                state[row][column] = fileScanner.nextInt();

                if(state[row][column] == 0) {
                    this.blankSpotPosition = new Point(column, row);
                }
            }
        }

        if(this.blankSpotPosition == null) {
            throw new IllegalArgumentException("Error - no 0 in supplied file");
        }
    }

    /**
     * Creates a sliding block state with the passed in length as its length.
     * Sets the state contents using {@link #fillInDefaultState(int[][])}.
     *
     * @param length
     */
    public SlidingBlockState(int length) {
        this.length = length;

        this.state = new int[length][length];
        this.fillInDefaultState(this.state);
        this.blankSpotPosition = new Point(this.length - 1, this.length - 1);
    }

    /**
     * Copy Constructor
     * @param original The original object to copy.
     */
    public SlidingBlockState(SlidingBlockState original)
    {
        this.length = original.length;
        this.blankSpotPosition = new Point(original.blankSpotPosition);
        this.state = copyInternalState(original.state);
    }

    //TODO test - particularly if newblank is the same as old blank pos

    /**
     * @param newBlankPosition
     * @return a new object where the contents of the current blank position and the passed in position are swapped,
     *         and everything else is the same.
     */
    public SlidingBlockState swapBlank(Point newBlankPosition) {
        SlidingBlockState copy = new SlidingBlockState(this);

        copy.state[this.blankSpotPosition.getRow()][this.blankSpotPosition.getColumn()] = this.state[newBlankPosition.getRow()][newBlankPosition.getColumn()];
        copy.state[newBlankPosition.getRow()][newBlankPosition.getColumn()] = 0;

        copy.blankSpotPosition = new Point(newBlankPosition);

        return copy;
    }

    /**
     * Fills in the passed in 2D int array with values from (array length)^2 - 1 to 0, starting from 0, 0,
     * moving right and down.
     *
     * E.g.
     *
     * 8 7 6
     * 5 4 3
     * 2 1 0
     *
     * @param state
     */
    private void fillInDefaultState(int[][] state) {
        int value = state.length * state.length - 1;

        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {
                state[row][column] = value;
                value--;
            }
        }
    }

    /**
     * @return a string of this state where each entry is separated by a space, and each row is separated by a newline.
     *
     * E.g.
     *
     * 1 2 3
     * 4 5 6
     * 7 8 0
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < this.state.length; i++) {
            for(int j = 0; j < this.state[i].length; j++) {
                result.append(this.state[i][j]);
                result.append(" ");
            }

            result.append( System.lineSeparator() );
        }

        return result.toString();
    }

    /**
     * Two SlidingBlockStates are equal if they have the same length and the same contents in every position.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass().getSimpleName().equals(obj.getClass().getSimpleName())) {
            SlidingBlockState that = (SlidingBlockState)obj;

            if(this.length != that.length) {
                return false;
            }

            for(int i = 0; i < this.state.length; i++) {
                for(int j = 0; j < this.state[i].length; j++) {
                    if(this.state[i][j] != that.state[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }

    public int getLength() {
        return this.length;
    }

    public Point getBlankSpotPosition() {
        return new Point(this.blankSpotPosition);
    }


    private static int[][] copyInternalState(int[][] original)
    {
        int[][] copy = new int[original.length][];

        for(int i = 0; i < original.length; i++)
        {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    /**
     * @return true if the blank is at its minimum possible row, false otherwise.
     */
    public boolean blankAtMinRow()
    {
        return this.blankSpotPosition.getRow() == this.minRow();
    }

    /**
     * @return true if the blank is at its maximum possible row, false otherwise.
     */
    public boolean blankAtMaxRow() {
        return this.blankSpotPosition.getRow() == this.maxRow();
    }

    /**
     * @return true if the blank is at its minimum possible column, false otherwise.
     */
    public boolean blankAtMinColumn() {
        return this.blankSpotPosition.getColumn() == this.minColumn();
    }

    /**
     * @return true if the blank is at its maxmium possible column, false otherwise.
     */
    public boolean blankAtMaxColumn() {
        return this.blankSpotPosition.getColumn() == this.maxColumn();
    }

    /**
     * @return the minimum possible row of this state.
     */
    public int minRow() {
        return 0;
    }

    /**
     * @return the maxmimum possible row of this state.
     */
    public int maxRow() {
        return this.length - 1;
    }

    /**
     * @return the minimum possible column of this state.
     */
    public int minColumn() {
        return 0;
    }

    /**
     * @return the maxmimum possible column of this state.
     */
    public int maxColumn() {
        return this.length - 1;
    }
}
