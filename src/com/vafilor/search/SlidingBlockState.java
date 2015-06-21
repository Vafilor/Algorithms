package com.vafilor.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a state of a Sliding Block Puzzle, and provides methods to interact with it and generate new states.
 *
 * Created by Andrey on 6/7/2015.
 */
public class SlidingBlockState implements Cloneable{
    private int[][] state;
    /**
     * The length of a side of the "board" of the sliding block puzzle. An 8-puzzle has a length of 3. 3 x 3 board.
     */
    private int length;
    private Point blankSpotPosition;

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
    public SlidingBlockState(String fileName) throws FileNotFoundException{
        File sourceFile = new File(fileName);

        Scanner fileScanner = new Scanner(sourceFile);

        this.length = fileScanner.nextInt();

        this.state = new int[length][length];

        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {
                state[row][column] = fileScanner.nextInt();

                if(state[row][column] == 0) {
                    this.blankSpotPosition = new Point(row, column, 0, this.length - 1, 0, this.length - 1);
                }
            }
        }

        if(this.blankSpotPosition == null) {
            throw new IllegalArgumentException("Error - no 0 in supplied file");
        }
    }

    public SlidingBlockState(int length) {
        this.length = length;

        this.state = new int[length][length];
        this.fillInDefaultState(this.state);
        this.blankSpotPosition = new Point(this.length -1, this.length - 1,  0, this.length - 1, 0, this.length - 1);
    }

    private SlidingBlockState swap(Point blankPosition, Point newBlankPosition) {
        SlidingBlockState copy = (SlidingBlockState)this.clone();

        copy.state[blankPosition.getRow()][blankPosition.getColumn()] = this.state[newBlankPosition.getRow()][newBlankPosition.getColumn()];
        copy.state[newBlankPosition.getRow()][newBlankPosition.getColumn()] = this.state[blankPosition.getRow()][blankPosition.getColumn()];
        copy.blankSpotPosition = (Point)newBlankPosition.clone();

        return copy;
    }

    /**
     * Moves the "blank" (0) spot.
     * @param action direction to move 0 spot.
     * @return new SlidingBlockState where the 0 spot has been moved.
     */
    public SlidingBlockState applyAction(SlidingAction action) {
        switch(action) {
            case Up:
                return this.moveUp();
            case Down:
                return this.moveDown();
            case Left:
                return this.moveLeft();
            case Right:
                return this.moveRight();
            default:
                throw new IllegalStateException("Tried to apply illegal action to sliding block:" + action);
        }
    }

    private SlidingBlockState moveUp() {
        if(this.blankSpotPosition.getRow() == 0) { //TODO use min/max
            return this;
        }

        return this.swap(this.blankSpotPosition, this.blankSpotPosition.addRow(-1) );
    }

    private SlidingBlockState moveDown() {
        if(this.blankSpotPosition.getRow() == (this.length - 1) ) {
            return this;
        }

        return this.swap(this.blankSpotPosition, this.blankSpotPosition.addRow(1));
    }

    private SlidingBlockState moveLeft() {
        if(this.blankSpotPosition.getColumn() == 0) {
            return this;
        }

        return this.swap(this.blankSpotPosition, this.blankSpotPosition.addColumn(-1));
    }

    private SlidingBlockState moveRight() {
        if(this.blankSpotPosition.getColumn() == (this.length - 1) ) {
            return this;
        }

        return this.swap(this.blankSpotPosition, this.blankSpotPosition.addColumn(1));
    }

    public boolean isGoalState() {

        //0 1 2
        //3 4 5
        //6 7 8
//        for(int row = 0; row < this.state.length; row++) {
//            for(int column = 0; column < this.state[row].length; column++) {
//                if(this.state[row][column] != (this.length * row + column) ) {
//                    return false;
//                }
//            }
//        }

//        1 2 3
//        4 5 6
//        7 8 0
        for(int row = 0; row < this.state.length; row++) {
            for(int column = 0; column < this.state[row].length; column++) {

                if((row == this.state.length - 1) && (column == this.state[row].length - 1) && this.state[row][column] == 0 ) {
                    return true;
                } else {

                    if (this.state[row][column] != (this.length * row + column + 1)) {
                        return false;

                    }
                }
            }
        }

        return true;
    }

    private void fillInDefaultState(int[][] state) {
        int value = state.length * state.length - 1;

        for(int row = 0; row < state.length; row++) {
            for(int column = 0; column < state[row].length; column++) {
                state[row][column] = value;
                value--;
            }
        }
    }

    @Override
    public Object clone() {
        //TODO super clone?

        SlidingBlockState copy = new SlidingBlockState(this.length);

        //TODO look up array copy method for easier times

        for(int row = 0; row < this.state.length; row++) {
            for(int column = 0; column < this.state[row].length; column++) {
                copy.state[row][column] = this.state[row][column];
            }
        }

        copy.blankSpotPosition = (Point)this.blankSpotPosition.clone();
        copy.length = this.length;

        return copy;
    }

    @Override
    public String toString() {
        String result = "";

        for(int i = 0; i < this.state.length; i++) {
            for(int j = 0; j < this.state[i].length; j++) {
                result += this.state[i][j] + " ";
            }

            result += "\n";
        }

        return result;
    }

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
        return this.blankSpotPosition;
    }
}
