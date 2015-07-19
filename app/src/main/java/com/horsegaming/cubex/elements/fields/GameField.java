package com.horsegaming.cubex.elements.fields;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.elements.fields.drawers.RectBoxDrawer;
import com.horsegaming.cubex.core.interfaces.IClickable;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IUpdatable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.staticclasses.Destroyer;
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
        this._matrix = new Box[matrixSize][matrixSize];
        this._generator = generator;

        this.drawable = new GameFieldDrawer();
        this.updatable = new GameFieldUpdater();
        this.clicable = new GameFieldClicker();

        this._generator.generate(_matrix, this.Position, _elementSize, RectBoxDrawer.class);

        Log.d("Matrix", _matrix[0][0].Type.toString());

    }

    public GameField(int screenWidth, int screenHeight,  String tag, int matrixSize, IBoxGenerator generator)
    {
        this(new Point(0,(screenHeight - screenWidth)/2),new Point(screenWidth,screenWidth),tag,matrixSize,generator);
        Log.d("Position", this.Position.toString() );
    }

    public GameField(int screenWidth, int screenHeight,  String tag, IBoxGenerator generator, Resources resources, int levelIndex)
    {
        this(new Point(0,(screenHeight - screenWidth)/2),new Point(screenWidth,screenWidth),tag,resources.getIntArray(R.array.level01 + levelIndex - 1)[1],generator);
    }

    private class GameFieldDrawer implements IDrawer
    {
        //TODO delete
      // Paint paint = new Paint();

        @Override
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


            //TODO TEST


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

    private class  GameFieldClicker implements IClickable
    {
        @Override
        public void click(Point clickPos)
        {
            Destroyer.lastDestroyCount = 0;
            for (int i = 0; i < _matrix.length; i++)
            {
                for (int j = 0; j < _matrix.length; j++)
                {
                    //TODO NORMAL CLICK
                    if(_matrix[i][j] != null && _matrix[i][j].contains(clickPos))
                    {

                        Destroyer.destroy(_matrix, i, j);

                    }
                }
            }
        }
    }
}


