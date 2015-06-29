package com.horsegaming.cubex.core.elements.fields.drawers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.horsegaming.cubex.core.interfaces.IReDrawable;
import com.horsegaming.cubex.staticclasses.Mediator;

/**
 * Created by Horse on 21.06.2015.
 */
public class RectBoxDrawer implements IReDrawable {

    private Paint _paint;
    private int _frame = 3;

    public RectBoxDrawer(int type)
    {
        this._paint = new Paint();
        this.reDraw(type);

    }

    public RectBoxDrawer()
    {
        this._paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas, Point position, Point size) {
        canvas.drawRoundRect(Mediator.RectFAdapt(position.x + _frame, position.y + _frame,size.x-_frame*2,size.y-_frame*2)
                ,size.x/10,size.y/10,this._paint);

    }

    @Override
    public void reDraw(int type) {
        if(type > 2) throw  new IndexOutOfBoundsException();
        switch (type) {
            case 0:
                this._paint.setColor(Color.rgb(192, 57, 43));
                break;
            case 1:
                this._paint.setColor(Color.rgb(39, 174, 96));
                break;
            case 2:
                this._paint.setColor(Color.rgb(52, 152, 219));
                break;
        }
    }

}
