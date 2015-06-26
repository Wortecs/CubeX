package com.horsegaming.cubex.core.interfaces;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Horse on 23.06.2015.
 */
public interface IReDrawable extends IDrawer
{
    @Override
    void draw(Canvas canvas, Point position, Point size );

    void reDraw(int type);
}
