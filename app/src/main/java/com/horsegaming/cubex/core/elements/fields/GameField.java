package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IUpdatable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.staticclasses.Droper;

/**
 * Created by Horse on 21.06.2015.
 */
public final class GameField extends GameObject
{
    private Point _elementSize;
    private Box[][] _matrix;
    private IBoxGenerator _generator;

    //TODO
    public GameField(Point position, Point size, String tag, int matrixSize, IBoxGenerator generator)
    {
        super(position, size, tag);
        this._elementSize = new Point(size.x/matrixSize, size.y/matrixSize);
        this.drawable = new GameFieldDrawer();
        this._matrix = new Box[matrixSize][matrixSize];
        this._generator = generator;
        this.updatable = new GameFieldUpdater();

        //TODO DELETE
        _matrix[2][6] = new Box(10,10,50,50,"",BoxType.RED,true);
        _matrix[2][6].size(3);
       // _matrix[0][1] = new Box(60,10,50,50,"",BoxType.BLUE,true);
      //  _matrix[1][1] = new Box(60,60,50,50,"",BoxType.GREEN,true);
    }

    private class GameFieldDrawer implements IDrawer
    {
        @Override
        //TODO
        public void draw(Canvas canvas, Point position, Point size)
        {
            for (int i = 0; i < _matrix.length; i++)
            {
                for (int j = 0; j < _matrix.length; j++)
                {
                    if(_matrix[i][j] != null) {
                        _matrix[i][j].update();
                        _matrix[i][j].draw(canvas);

                    }

                }
            }
        }
    }

    private class GameFieldUpdater implements IUpdatable
    {
        @Override
        public void update()
        {
            Droper.drop(_matrix);
        }
    }
}


