package com.horsegaming.cubex.core.variables;

import android.graphics.Canvas;
import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IClicable;
import com.horsegaming.cubex.core.interfaces.IDrawer;
import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.ISizable;
import com.horsegaming.cubex.core.interfaces.IUpdatable;

/**
 * Created by Horse on 21.06.2015.
 */

public class DoNothing
    implements IDrawer, IMovable, ISizable, IUpdatable, IClicable
{

    @Override
    public boolean move(Point newPosition) {
        return false;
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
    @Override
    public void draw(Canvas canvas, Point position, Point size) {

    }

    @Override
    public void click(Point clickPos) {
        return;
    }
}
