package com.horsegaming.cubex.core.variables.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.horsegaming.cubex.core.elements.Box;
import com.horsegaming.cubex.core.interfaces.IDrawable;
import com.horsegaming.cubex.core.variables.GameObject;

/**
 * Created by Horse on 21.06.2015.
 */
public class RectBoxDrawer implements IDrawable {

    private Box _box;
    private Paint _paint;

    public RectBoxDrawer(Box box)
    {
        this._box = box;
        this._paint = new Paint();
        this.setColor();
    }
    //rgb(192, 57, 43)

    private void setColor()
    {
        switch (this._box.getType()) {
            case RED:
                this._paint.setColor(Color.rgb(192, 57, 43));
                break;
            case GREEN:
                this._paint.setColor(Color.rgb(39, 174, 96));
                break;
            case BLUE:
                this._paint.setColor(Color.rgb(52, 152, 219));
                break;
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawRoundRect(new RectF(this._box.Position.x, this._box.Position.y ,this._box.Size.x,this._box.Size.y),this._box.Size.x/10,this._box.Size.y/10,this._paint);
    }

}
