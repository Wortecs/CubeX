package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.core.elements.fields.drawers.RectBoxDrawer;
import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.interfaces.IClicable;
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

        this._generator.generate(_matrix,this.Position,_elementSize, RectBoxDrawer.class);

    }

    public GameField(Point screenSize, String tag, int matrixSize, IBoxGenerator generator)
    {
        this(new Point(0,(screenSize.y - screenSize.x)/2),new Point(screenSize.x/2,screenSize.x/2),tag,matrixSize,generator);
    }

    private class GameFieldDrawer implements IDrawer
    {


        //TODO delete
        Paint paint = new Paint();


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

                        paint.setColor(Color.BLACK);
                        paint.setTextSize(20);

                        canvas.drawText(_matrix[i][j].MatrixPosition.toString(),_matrix[i][j].Position.x, _matrix[i][j].Position.y,paint );
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

    private class  GameFieldClicker implements IClicable
    {
        @Override
        public void click(Point clickPos)
        {
            for (int i = 0; i < _matrix.length; i++)
            {
                for (int j = 0; j < _matrix.length; j++)
                {
                    if(_matrix[i][j] != null && _matrix[i][j].contains(clickPos))
                    {
                        Log.d("Click in Box", i + " " + j);
                        Destroyer.destroy(_matrix,i,j);
                    }
                }
            }
        }
    }
}


