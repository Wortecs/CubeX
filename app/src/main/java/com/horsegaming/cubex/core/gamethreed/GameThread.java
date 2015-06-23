package com.horsegaming.cubex.core.gamethreed;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

import com.horsegaming.cubex.core.elements.Box;
import com.horsegaming.cubex.core.enums.BoxType;
import com.horsegaming.cubex.core.variables.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horse on 15.06.2015.
 */
public final class GameThread extends Thread
{
    private boolean _isRunning = false;
    private SurfaceHolder _holder;
    private Resources _resources;

    private static List<GameObject> _gameObjects;

    public GameThread(SurfaceHolder holder, Resources resources, List<GameObject> gameObjects)
    {
        this(holder,resources);
        if(gameObjects != null)
            this._gameObjects = gameObjects;
        else
            this._gameObjects = new ArrayList<>();
    }

    private GameThread(SurfaceHolder holder, Resources resources)
    {
        this._resources = resources;
        this._holder = holder;
    }

    @Override
    public void run()
    {
        Canvas canvas;
        while (_isRunning) {
            canvas = null;
            try {
                canvas = _holder.lockCanvas(null);
                synchronized (_holder)
                {
                    this.update(canvas);
                }
            }
            finally {
                if (canvas != null) {
                    this._holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private  void update( Canvas canvas )
    {
        if(canvas == null) return;

        canvas.drawColor(Color.rgb(236, 240, 241));
        for (GameObject gameObject : _gameObjects) {
            gameObject.draw(canvas);
            gameObject.update();

        }
    }

    public void setRunning (boolean status)
    {
        this._isRunning = status;
    }

    public static GameObject FindGameObjectByTag(String tag)
    {
        for ( GameObject gameObject : _gameObjects  ) {
            if (gameObject.Tag == tag)
                return gameObject;
        }
        return  null;
    }

}
