package com.horsegaming.cubex.core.variables.base;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Horse on 20.07.2015.
 * Класс в котором храниться параметры игрового объекта
 */
public class Transform
{
    public Point Position;
    public Point Size;

    public Transform(Point position, Point size) {
        this.Position = position;
        this.Size = size;
    }

    public Transform(int posX, int posY, int width, int height) {
        this(new Point(posX, posY), new Point(width, height));
    }

    public void scale(int factor)
    {
        this.Size =
                new Point(this.Size.x*factor,this.Size.y*factor);
    }

    public void move(int distance, Point vector)
    {
        this.Position = new Point(
                this.Position.x + ( distance * vector.x),
                this.Position.y + ( distance * vector.y) );
    }

    public void move(Point vector)
    {
        this.move(1,
                vector);
    }

    public RectF toRectF()
    {
        return new RectF(this.Position.x, this.Position.y,
                this.Position.x + this.Size.x, this.Position.y + this.Size.y);
    }
    public Rect toRect()
    {
        return new Rect(this.Position.x, this.Position.y,
                this.Position.x + this.Size.x, this.Position.y + this.Size.y);
    }

    @Override
    public String toString()
    {
        return String.format("Position: {0}; Size: {1} ",this.Position.toString(),this.Size.toString());
    }
}