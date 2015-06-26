package com.horsegaming.cubex.staticclasses;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Parcelable;


/**
 * Created by Horse on 14.06.2015.
 */
public class Mediator
{
    public static void sendParams(Intent intent, String name, Parcelable params )
    {
        if(params != null)
            intent.putExtra(name,params);
    }

    public static RectF RectFAdapt(Point position, Point size)
    {
        return new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
    }
}
