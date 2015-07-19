package com.horsegaming.cubex.elements.fields;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.core.interfaces.IReDrawable;
import com.horsegaming.cubex.elements.GUI.GUILimiter;
import com.horsegaming.cubex.elements.fields.enums.BoxType;
import com.horsegaming.cubex.view.GameView;

/**
 * Created by Horse on 25.06.2015.
 */
public class BoxDecoder implements IBoxGenerator {

    private Resources _resources;
    private int _level;

    public BoxDecoder( Resources resources, int level)
    {
        this._resources = resources;
        this._level = level;
    }
    //TODO DELETE
    @Override
    public boolean generate(Box[][] matrix, Point basePosition, Point size, Class<? extends IReDrawable> drawer)
    {
        int[] array = this._resources.getIntArray(R.array.level01 + _level - 1);

       // matrix = new Box[array[1]][array[1]];

        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix.length ; j++)
            {
                try {

                    matrix[i][j] = new Box(
                            basePosition.x + i * size.x, basePosition.y + (matrix.length - j - 1) * size.y,
                            size.x, size.y,
                            String.format("Box{0}{1}",i,j),
                            BoxType.values()[array[2 + matrix.length * i + j]],//2 + array.length*i + j
                            true,
                            drawer.newInstance()
                    );
                    matrix[i][j].reDraw();
                    //TODO ???
                    matrix[i][j].GameFieldBaseYPosition = basePosition.y;

                    matrix[i][j].MatrixPosition = new Point(i ,matrix.length - j - 1);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Log.d("Iteration " + (i*matrix.length + j) ,"i: " + i + " j: " + j + " " + matrix[i][j].Type.toString());
            }


        }

        GUILimiter limiter = (GUILimiter)GameView.FindGameObjectByTag(GUILimiter.getStandartTag());
        if(limiter != null)
        {
            limiter.setStartParam(array[0]);
        }

        return true;
    }

}
