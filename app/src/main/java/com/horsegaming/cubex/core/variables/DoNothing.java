package com.horsegaming.cubex.core.variables;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IDrawable;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;

/**
 * Created by Horse on 21.06.2015.
 */
//Еласс-болванка
public class DoNothing
    implements IDrawable, IMovable, ISizable, IUpdatable
{
    @Override
    public void draw(Canvas canvas) {


    }
    @Override
    public void move(Point newPosition) {

    }
    @Override
    public void size(Point newSize) {

    }
    @Override
    public void size(int scale) {

    }
    @Override
    public void update() {

    }
}
