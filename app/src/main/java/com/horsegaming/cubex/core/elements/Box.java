package com.horsegaming.cubex.core.elements;

import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.core.variables.drawer.RectBoxDrawer;

/**
 * Created by Horse on 21.06.2015.
 */
public class Box extends GameObject
{
    private BoxType _type;

    public Box(Point position, Point size, String tag, BoxType type)
    {
        super(position, size, tag);
        this._type = type;
        this.drawable = new RectBoxDrawer(this);
    }

    public Box( int xPos, int yPos, int xSize, int ySize, String tag, BoxType type)
    {
        this(new Point(xPos,yPos), new Point(xSize,ySize),tag,type);
    }

    public BoxType getType()
    {
        return  this._type;
    }

}
