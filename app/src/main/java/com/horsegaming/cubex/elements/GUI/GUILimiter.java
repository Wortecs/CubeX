package com.horsegaming.cubex.elements.GUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.ILimiter;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.staticclasses.Mediator;

/**
 * Created by Horse on 04.07.2015.
 */
public class GUILimiter extends GameObject implements ILimiter
{
    private int _value;
    private int _maxValue;

    public GUILimiter(Point position, Point size, int maxValue) {
        this(position, size, getStandartTag(), maxValue);
    }

    public GUILimiter(Point position, Point size, String tag, int maxValue) {
        super(position, size, tag);
        this.drawable = new GUILimiterDrawer(this);
        this._value = maxValue;
        this._maxValue = maxValue;
    }

    public GUILimiter(int screenWidth, int screenHeight, int maxValue)
    {
        this(new Point(0,0), new Point(screenWidth,(screenHeight-screenWidth)/4),maxValue);

    }

    @Override
    public void tick(int value) {
      this._value -= value;

        Log.d("Value", _value + " / " +_maxValue);

       if(this._value < 0)
            this.endGame();
    }

    @Override
    public double getLess() {
        return (double)this._value/(double)this._maxValue;
    }

    @Override
    public void setStartParam(int value) {
        this._value = value;
        this._maxValue = value;
    }

    //TODO
    private void endGame()
    {

    }

    public static String getStandartTag()
    {
        return  "Limiter";
    }
}

class GUILimiterDrawer implements IDrawer
{
    Paint paint;
    GUILimiter limiter;

    public GUILimiterDrawer(GUILimiter link)
    {
        this.limiter = link;
        this.paint = new Paint();
        paint.setTextSize(link.Size.x);
    }

    @Override
    public void draw(Canvas canvas, Point position, Point size)
    {
        paint.setColor(Color.rgb(26, 188, 156));
        canvas.drawRect(position.x, position.y, position.x + size.x, position.y + size.y, paint);

        paint.setColor(Color.rgb(22, 160, 133));

        canvas.drawRoundRect(Mediator.RectFAdapt(position.x + size.x / 40,
                position.y + size.y / 3,
                size.x - size.x / 20,
                size.y / 3)
                , size.x / 2, size.x / 2, paint);

        paint.setColor(Color.rgb(236, 240, 241));
        canvas.drawRoundRect(Mediator.RectFAdapt(
                        position.x + size.x / 40,
                        position.y + size.y / 3,
                        (int) ((size.x - size.x / 20) * limiter.getLess()),
                        size.y / 3),
                size.x / 2, size.x / 2, paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawLine(position.x, position.y + size.y,  position.y + size.x, position.y + size.y, paint);
        paint.setStrokeWidth(0);


    }
}
