package com.vafilor.search;

/**
 * Represents a Point in 2 dimensions, with limits on minimum and maximum values for the components.
 * Handy for 2 dimensional arrays (N x N matrices);
 *
 * Created by Andrey Melnikov on 6/7/2015.
 */
public final class Point implements Cloneable {
    private int row;
    private int column;
    private int minRow;
    private int maxRow;
    private int minColumn;
    private int maxColumn;

    public Point(int row, int column, int minRow, int maxRow, int minColumn, int maxColumn) {
        this.validateInput(row, column, minRow, maxRow, minColumn, maxColumn);

        this.row = row;
        this.column = column;
        this.minRow = minRow;
        this.maxRow = maxRow;
        this.minColumn = minColumn;
        this.maxColumn = maxColumn;
    }

    //region getters
    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
    //endregion

    public Point addRow(int change) throws IllegalStateException {
        return new Point(this.row + change, this.column, this.minRow, this.maxRow, this.minColumn, this.maxColumn);
    }

    public Point addColumn(int change) throws IllegalStateException {
       return new Point(this.row, this.column + change, this.minRow, this.maxRow, this.minColumn, this.maxColumn);
    }

    public boolean isAtMinRow() {
        return this.row == this.minRow;
    }

    public boolean isAtMaxRow() {
        return this.row == this.maxRow;
    }

    public boolean isAtMinColumn() {
        return this.column == this.minColumn;
    }

    public boolean isAtMaxColumn() {
        return this.column == this.maxColumn;
    }

    //region validators
    private void validateInput(int row, int column, int minRow, int maxRow, int minColumn, int maxColumn) throws IllegalStateException {
        if(row < minRow || row > maxRow || column < minColumn || column > maxColumn) {
            throw new IllegalStateException("Error, tried to construct Point with invalid row/column");
        }
    }
    //endregion

    @Override
    public Object clone() {
        //TODO super clone?

        return new Point(this.row, this.column, this.minRow, this.maxRow, this.minColumn, this.maxColumn);
    }

}
