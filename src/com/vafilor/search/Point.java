package com.vafilor.search;

/**
 * Represents a point with integer components. Created so that it can be subclassed and constraints
 * added/adhered to with setter methods.
 *
 * Note: Has set/get Row/Column methods, which try to treat the Point as a point in a Matrix, or 2D array, where the
 * x and y are flipped. X being Column, Y being row. This is okay, except that the constructor uses x/y and not row/column, so
 * you still need to construct it backwards. For safety, you can use the setter methods after creating a point.
 *
 *
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class Point {
    protected int x;
    protected int y;

    public Point(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Copy Constructor.
     * @param point
     */
    public Point(Point point)
    {
        this(point.x, point.y);
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public Point addX(int dx)
    {
        return new Point(this.x + dx, this.y);
    }

    public Point addRow(int dr)
    {
        return new Point(this.x, this.y + dr);
    }

    public int getX()
    {
        return this.x;
    }

    public int getRow()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Point addY(int dy)
    {
        return new Point(this.x, this.y + dy);
    }

    public Point addColumn(int dc)
    {
        return new Point(this.x + dc, this.y);
    }

    public int getY()
    {
        return this.y;
    }

    //TODO - note why you have these
    public int getColumn()
    {
        return this.x;
    }
}
