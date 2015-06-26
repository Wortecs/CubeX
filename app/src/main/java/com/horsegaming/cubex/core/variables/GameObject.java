package com.horsegaming.cubex.core.variables;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.interfaces.IDrawable;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;

/**
 * Created by Horse on 20.06.2015.
 */
public abstract class GameObject implements IDrawable, ISizable, IMovable, IUpdatable
{
    protected IDrawer drawable = new DoNothing();
    protected ISizable sizable = new DoNothing();
    protected IUpdatable updatable = new DoNothing();
    protected IMovable movable = new DoNothing();

    public String Tag;
    public Point Position;
    public Point Size;

    public GameObject(Point position, Point size, String tag)
    {
        this.Position = position;
        this.Size = size;
        this.Tag = tag;
    }

    public GameObject( int xPos, int yPos, int xSize, int ySize, String tag, BoxType type)
    {
        this(new Point(xPos,yPos), new Point(xSize,ySize),tag);
    }

    public void setDrawer(IDrawer drawer) {
        this.drawable = drawer;
    }

    @Override
    public void draw(Canvas canvas)
    {
        this.drawable.draw(canvas, this.Position, this.Size);
    }

    @Override
    public void move(Point newPosition) {
        this.movable.move(newPosition);
    }

    @Override
    public void size(Point newSize) {
        this.sizable.size(newSize);
    }

    @Override
    public void size(int scale) {
        this.sizable.size(scale);
    }

    @Override
    public void update() {
        this.updatable.update();
    }


}
