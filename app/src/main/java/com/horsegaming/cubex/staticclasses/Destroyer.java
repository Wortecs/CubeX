package com.horsegaming.cubex.staticclasses;

import com.horsegaming.cubex.core.interfaces.IDestroyable;

/**
 * Created by Horse on 29.06.2015.
 */
public class Destroyer
{
    static int countMark;
    static boolean inDestroy;

    private static void recursionDestroy(IDestroyable[][] arrayElements, int x, int y, boolean isMarkedToDestroy, int typeBox) {

        if(arrayElements[x][y] == null) return;

        if (isMarkedToDestroy) {
            if (arrayElements[x][y].getIsMarked()) return;
        }

        if (!arrayElements[x][y].getIsDestroy() &&  arrayElements[x][y].convertType() == typeBox )
        {
            arrayElements[x][y].setIsMarked(true);

            if(arrayElements[x][y].getIsMarked())
                countMark++;

            if (!isMarkedToDestroy) arrayElements[x][y].setIsDestroy(true);

            if (countMark >= 3) {
                isMarkedToDestroy = false;
            }

            for (int i = -1; i <= 1; i += 2)
            {
                if (x + i >= 0 && x + i < arrayElements.length)
                    recursionDestroy(arrayElements, x + i, y, isMarkedToDestroy, typeBox);

            }

            for (int j = -1; j <= 1; j += 2)
            {
                if (y + j >= 0 && y + j < arrayElements.length)
                    recursionDestroy(arrayElements, x, y + j, isMarkedToDestroy, typeBox);

            }
        } else {
            return;
        }

    }

    public static int destroy(IDestroyable[][] arrayElements, int x, int y) {

        if(arrayElements[x][y] == null) return 0;
        inDestroy = true;

        recursionDestroy(arrayElements, x, y, true, arrayElements[x][y].convertType());
        int tmpInt = 0;
        for (int i = 0; i < arrayElements.length; i++) {
            for (int j = 0; j < arrayElements[i].length; j++) {
                if(arrayElements[i][j] == null) continue;
                if (!arrayElements[i][j].getIsDestroy() && arrayElements[i][j].getIsMarked())
                    arrayElements[i][j].setIsMarked(false);
                if (arrayElements[i][j].getIsDestroy())
                {
                    arrayElements[i][j] = null;
                    tmpInt++;
                }
            }
        }
        countMark = 0;
        inDestroy = false;
        //Log.d("Point", tmpInt +"");
        return tmpInt;
    }
}
