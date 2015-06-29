package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Point;

import com.horsegaming.cubex.core.enums.BoxType;
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
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                try {
                    matrix[i][j] = new Box(basePosition.x + i * size.x, basePosition.y + (matrix.length - j - 1) * size.y,
                            size.x, size.y,
                            String.format("Box{0}{1}",i,j),
                            BoxType.values()[random.nextInt(BoxType.values().length)],
                           // BoxType.RED,
                            true,
                            drawer.newInstance());
                    matrix[i][j].reDraw();
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
