package com.horsegaming.cubex.elements.GUI;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.core.variables.Timing;

/**
 * Created by Horse on 05.07.2015.
 */
public class GUIFPSMeter extends GameObject
{
    public GUIFPSMeter(Point position, Point size)
    {
        super(position, size, "FPS");
        this.drawable = new FPSMeterDrawer();
    }

}

class FPSMeterDrawer implements IDrawer
{
    Paint paint = new Paint();

    public FPSMeterDrawer()
    {
        paint.setTextSize(20);
    }

    @Override
    public void draw(Canvas canvas, Point position, Point size) {
        canvas.drawText((1000d/(double) Timing.DeltaTime)+"",position.x,position.y,paint);
    }
}
