package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.IReDrawable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.core.elements.fields.drawers.RectBoxDrawer;

/**
 * Created by Horse on 21.06.2015.
*/

final class Box extends GameObject
{
    public BoxType Type;
    protected IReDrawable drawable;
    public Point MatrixPosition = new Point();

    public Box(Point position, Point size, String tag, BoxType type, boolean movable)
    {
        super(position, size, tag);
        this.Type = type;
        this.drawable = new RectBoxDrawer(type.ordinal());
        this.sizable = new BoxSizer(this);
        this.updatable = new BoxUpdater(this);
        if(movable)
            this.movable = new BoxMover(this);



    }

    public Box( int xPos, int yPos, int xSize, int ySize, String tag, BoxType type, boolean movable)
    {
        this(new Point(xPos,yPos), new Point(xSize,ySize),tag,type, movable);
    }

    public Box( int xPos, int yPos, int xSize, int ySize, String tag, BoxType type, boolean movable, IReDrawable drawer)
    {
        this(new Point(xPos,yPos), new Point(xSize,ySize),tag,type, movable);
        this.drawable = drawer;
    }

    public void setParameters(Point position, Point size, BoxType type)
    {
        this.Position = position;
        this.Size = size;
        this.Type = type;
    }

    public void setDrawer(IReDrawable drawer)
    {
        this.drawable = drawer;
    }

    @Override
    public void draw(Canvas canvas) {
        this.drawable.draw(canvas, this.Position, this.Size);
    }

    public void reDraw()
    {
        this.drawable.reDraw(this.Type.ordinal());
    }
}

class BoxMover implements IMovable
{
    Box box;

    public BoxMover( Box link)
    {
        this.box = link;
    }

    @Override
    public void move(Point newPosition)
    {
       this.box.MatrixPosition = newPosition;
    }
}

//TODO FOR GAMEOBJECT
class BoxSizer implements ISizable
{
    Box box;
    public BoxSizer( Box link)
    {
        this.box = link;
    }

    @Override
    public void size(Point newSize) {
        this.box.Size = newSize;
    }

    @Override
    public void size(int scale) {
        this.box.Size = new Point(this.box.Size.x * scale, this.box.Size.y * scale);
    }
}

//TODO UPDATER
class BoxUpdater implements IUpdatable
{
    Box box;
    public BoxUpdater( Box link)
    {
        this.box = link;
    }

    @Override
    public void update()
    {
        if( this.box.Position.y < this.box.MatrixPosition.y * this.box.Size.y  )
        {
            this.box.Position.y += this.box.Size.y/5;
        }else if (this.box.Position.y > this.box.MatrixPosition.y * this.box.Size.y)
        {
            this.box.Position.y = this.box.MatrixPosition.y * this.box.Size.y;
        }
    }
}
