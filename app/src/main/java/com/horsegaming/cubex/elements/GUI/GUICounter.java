package com.horsegaming.cubex.elements.GUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IPointer;
import com.horsegaming.cubex.core.variables.GameObject;

/**
 * Created by Horse on 04.07.2015.
 */
public class GUICounter extends GameObject implements IPointer
{

    private int _value = 0;

    public GUICounter(Point position, Point size, String tag)
    {
        super(position, size, tag);
        this.drawable = new GUICounterDrawer(this);
    }

    public GUICounter(Point position, Point size)
    {
        this(position, size, getStandartTag());
    }

    @Override
    public void addPoint(int point)
    {
        this._value += point;
    }

    public int getPoint()
    {
        return this._value;
    }

    public static String getStandartTag()
    {
        return "Pointer";
    }
}

class GUICounterDrawer implements IDrawer
{
    public GUICounter guiCounter;
    public Paint paint;

    public GUICounterDrawer(GUICounter counter)
    {
        guiCounter = counter;
        paint = new Paint();
        paint.setTextSize(counter.Size.x);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas, Point position, Point size) {
        canvas.drawText(guiCounter.getPoint() +"",position.x,position.y,paint);
    }
}
