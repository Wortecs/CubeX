package com.horsegaming.cubex.staticclasses;

import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IMovable;
import com.horsegaming.cubex.core.interfaces.ISizable;

/**
 * Created by Horse on 25.06.2015.
 */
public class Droper
{
    public static void drop(IMovable[][] matrix)
    {
        if(matrix == null) return;

        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 1; j < matrix.length; j++)
            {
                if(matrix[i][j-1]  == null && matrix[i][j] != null)
                {
                    if(matrix[i][j].move(new Point(matrix.length - i,matrix.length - j)))
                    {
                         matrix[i][j-1] = matrix[i][j];
                         matrix[i][j] = null;
                    }
                }
            }
        }
    }
}
