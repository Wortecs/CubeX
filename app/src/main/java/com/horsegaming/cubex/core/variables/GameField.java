package com.horsegaming.cubex.core.variables;

import android.graphics.Point;

/**
 * Created by Horse on 21.06.2015.
 */
public final class GameField extends GameObject
{
    private Point _elementSize;
    private GameObject[][] _matrix;

    //TODO
    public GameField(Point position, Point size, String tag, int matrixSize )
    {
        super(position, size, tag);
        this._elementSize = new Point(size.x/matrixSize, size.y/matrixSize);

    }

    public GameField(Point position, Point size, String tag, GameObject[][] matrix )
    {
        this(position, size, tag, matrix.length);
        this._matrix = matrix;
    }

}
