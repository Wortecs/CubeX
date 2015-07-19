package com.horsegaming.cubex.elements.fields;

import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.elements.fields.enums.BoxType;
import com.horsegaming.cubex.core.interfaces.IReDrawable;

import java.util.Random;

/**
 * Created by Horse on 25.06.2015.
 */
public class BoxRandomGenerator implements IBoxGenerator
{
    Random random = new Random();

    @Override
    public boolean generate(Box[][] matrix, Point basePosition, Point size, Class<? extends IReDrawable> drawer)
    {

        //TODO delette
        Log.d("Base" , basePosition.y + "");

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                try {
                    matrix[i][j] = new Box(basePosition.x + i * size.x, basePosition.y + (matrix.length - j - 1) * size.y,
                            size.x, size.y,
                            String.format("Box{0}{1}",i,j),
                            BoxType.values()[random.nextInt(BoxType.values().length)],
                            true,
                            drawer.newInstance());
                    matrix[i][j].reDraw();
                    //TODO ???
                    matrix[i][j].GameFieldBaseYPosition = basePosition.y;

                    matrix[i][j].MatrixPosition = new Point(i ,matrix.length - j - 1);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
