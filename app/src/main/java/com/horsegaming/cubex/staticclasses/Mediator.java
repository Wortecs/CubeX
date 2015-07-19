package com.horsegaming.cubex.staticclasses;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Parcelable;

import com.horsegaming.cubex.elements.fields.enums.BoxType;


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

    public static RectF RectFAdapt(int xPos, int yPos, int xSize, int ySize)
    {
        return new RectF(xPos, yPos, xPos + xSize, yPos + ySize);
    }


    public static int BoxTypeToColor(BoxType type)
    {
        //TODO
        switch (type) {
            case RED:
                return Color.rgb(255, 210, 210);
            case GREEN:
                return Color.rgb(210, 255, 210);
            case BLUE:
                return Color.rgb(210, 210, 255);
        }
        return -1;
    }
}
