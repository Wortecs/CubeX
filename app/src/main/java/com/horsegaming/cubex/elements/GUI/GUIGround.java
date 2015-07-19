package com.horsegaming.cubex.elements.GUI;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.variables.GameObject;

/**
 * Created by Horse on 08.07.2015.
 */
public class GUIGround extends GameObject
{

    public GUIGround(Point position, Point size, String tag) {
        super(position, size, tag);
        this.drawable = new GUIGroundDrawer();
    }

    public GUIGround(int screenWidth, int screenHeight)
    {
        this(new Point(0, screenWidth + 3 * (screenHeight-screenWidth)/4), new Point(screenWidth,(screenHeight-screenWidth)/4),"GUIGround");
    }
}

class GUIGroundDrawer implements IDrawer
{
    Paint paint = new Paint();
    @Override
    public void draw(Canvas canvas, Point position, Point size)
    {
        paint.setColor(Color.rgb(26, 188, 156));
       canvas.drawRect(position.x, position.y, position.x + size.x, position.y + size.y, paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawLine(position.x, position.y,position.x +  size.x, position.y, paint);
        paint.setStrokeWidth(0);
    }
}
