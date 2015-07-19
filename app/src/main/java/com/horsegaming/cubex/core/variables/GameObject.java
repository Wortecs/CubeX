package com.horsegaming.cubex.core.variables;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.core.interfaces.IComponentable;
import com.horsegaming.cubex.core.interfaces.IClickable;
import com.horsegaming.cubex.core.interfaces.IDrawable;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horse on 20.06.2015.
 */
public abstract class GameObject implements IDrawable, ISizable, IMovable, IUpdatable, IClickable
{

    protected IDrawer drawable = new DoNothing();
    protected ISizable sizable = new DoNothing();
    protected IUpdatable updatable = new DoNothing();
    protected IMovable movable = new DoNothing();
    protected IClickable clicable = new DoNothing();

    protected List<IComponentable> components;

    public String Tag;
    public Point Position;
    public Point Size;


    public GameObject(Point position, Point size, String tag)
    {
        this.Position = position;
        this.Size = size;
        this.Tag = tag;
    }

    @Override
    public void draw(Canvas canvas)
    {
        this.drawable.draw(canvas, this.Position, this.Size);
    }

    @Override
    public boolean move(Point newPosition) {
        return this.movable.move(newPosition);

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

        if(components != null)
        {
            for( IComponentable component : components)
                component.onUpdate();
        }
    }

    @Override
    public void click(Point clickPos) {
        this.clicable.click(clickPos);

        if(components != null)
        {
            for( IComponentable component : components)
                component.onClick();
        }
    }


    public boolean contains(Point position)
    {
        return this.contains(position.x,position.y);
    }
    public boolean contains(int xPos, int yPos)
    {
        if(xPos > this.Position.x && xPos < this.Position.x + this.Size.x &&
            yPos > this.Position.y && yPos < this.Position.y + this.Size.y    )
            return true;
        else
            return false;
    }

    public void setDrawer(IDrawer drawer) {
        this.drawable = drawer;
    }

    public void addComponent(IComponentable component)
    {
        if( components == null)
        {
            components = new ArrayList<>();
        }
        components.add(component);
    }


    public IComponentable getComponent(String name)
    {

        if( this.components != null)
        {
            for (IComponentable component : this.components)
            {
                //TODO DELETE
                Log.d("Component Name: ", component.getClass().toString());

                if(component.getClass().toString() == name)
                    return component;
            }
        }

        return  null;
    }

    protected IComponentable findComponent(Class<? extends  IComponentable> finder)
    {
        Log.d("Find",finder.toString());

        for (IComponentable componentable : components) {

            Log.d("Components",componentable.getClass().toString());
            if (componentable.getClass() == finder)
                return componentable;
        }
        return null;
    }


}
