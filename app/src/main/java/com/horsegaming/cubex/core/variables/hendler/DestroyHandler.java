package com.horsegaming.cubex.core.variables.hendler;

import android.util.Log;

import com.horsegaming.cubex.core.variables.base.BoxColorType;
import com.horsegaming.cubex.core.variables.gameobjects.MatrixGameObject;

/**
 * Created by Horse on 30.07.2015.
 */
public class DestroyHandler
{
    static int countMark;

    public static int lastDestroyCount;

    private static void recursionMatrixDestroy(MatrixGameObject matrixGameObject, int x, int y, boolean isMarkedToDestroy, BoxColorType typeBox) {



        if(matrixGameObject.getSubElement(x,y) == null) return;

        if (isMarkedToDestroy) {
            if (matrixGameObject.getSubElement(x,y).isMarked) return;
        }

        if (!matrixGameObject.getSubElement(x,y).isDestroy&&  matrixGameObject.getSubElement(x,y).Type == typeBox )
        {
            matrixGameObject.getSubElement(x,y).isMarked = true;

            if(matrixGameObject.getSubElement(x,y).isMarked)
                countMark++;

            if (!isMarkedToDestroy) matrixGameObject.getSubElement(x,y).isDestroy = true;

            if (countMark >= 3) {
                isMarkedToDestroy = false;
            }

            for (int i = -1; i <= 1; i += 2)
            {
                if (x + i >= 0 && x + i < matrixGameObject.getLength())
                    recursionMatrixDestroy(matrixGameObject, x + i, y, isMarkedToDestroy, typeBox);

            }

            for (int j = -1; j <= 1; j += 2)
            {
                if (y + j >= 0 && y + j < matrixGameObject.getLength())
                    recursionMatrixDestroy(matrixGameObject, x, y + j, isMarkedToDestroy, typeBox);

            }
        } else {
            return;
        }

    }

    public static int destroy(MatrixGameObject matrixGameObject, int x, int y) {

        lastDestroyCount = 0;

        if (matrixGameObject.getSubElement(x, y) == null)
            return 0;


        recursionMatrixDestroy(matrixGameObject, x, y, true, matrixGameObject.getSubElement(x, y).Type);

        for (int i = 0; i < matrixGameObject.getLength(); i++) {
            for (int j = 0; j < matrixGameObject.getLength(); j++) {
                if (matrixGameObject.getSubElement(i, j) == null) continue;
                if (!matrixGameObject.getSubElement(i, j).isDestroy && matrixGameObject.getSubElement(x, y).isMarked)
                    matrixGameObject.getSubElement(i, j).isMarked = false;
                if (matrixGameObject.getSubElement(i, j).isDestroy) {
                  //  Log.d("I cheked", i + " " + j );
                    matrixGameObject.destroySubElement(i, j);
                    lastDestroyCount++;
                }
            }
        }
        countMark = 0;

        //Log.d("Point", tmpInt +"");
        return lastDestroyCount;
    }
}
