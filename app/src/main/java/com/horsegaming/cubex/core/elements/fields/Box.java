package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.gamethreed.GameThread;
import com.horsegaming.cubex.core.interfaces.IClicable;
import com.horsegaming.cubex.core.interfaces.IDestroyable;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.IReDrawable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.core.elements.fields.drawers.RectBoxDrawer;

/**
 * Created by Horse on 21.06.2015.
*/

final class Box extends GameObject implements IDestroyable
{
    public BoxType Type;
    protected IReDrawable drawable;
    public Point MatrixPosition = new Point();

    private boolean _isMark;
    private boolean _isDestroy;

    public Box(Point position, Point size, String tag, BoxType type, boolean movable)
    {
        super(position, size, tag);
        this.Type = type;
        this.drawable = new RectBoxDrawer(type.ordinal());
        this.sizable = new BoxSizer(this);
        this.updatable = new BoxUpdater(this);
        this.clicable = new BoxClicker();
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

    @Override
    public boolean getIsMarked() {
       return  this._isMark;
    }

    @Override
    public boolean getIsDestroy() {
        return this._isDestroy;
    }

    @Override
    public void setIsDestroy(boolean status) {
        this._isDestroy = status;
    }

    @Override
    public void setIsMarked(boolean status) {
        this._isMark = status;
    }

    @Override
    public int convertType() {
        return this.Type.ordinal();
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
    public boolean move(Point newPosition)
    {
       this.box.MatrixPosition = newPosition;
        return true;
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

//TODO CLICKER
class BoxClicker implements IClicable
{
    @Override
    public void click(Point clickPos)
    {
        GameThread.FindGameObjectByTag("Pointer");
    }
}

