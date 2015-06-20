package com.horsegaming.cubex.core.gamethreed;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

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

    private List<GameObject> _gameObjects = new ArrayList<>();

    public GameThread(SurfaceHolder holder, Resources resources, List<GameObject> gameObjects)
    {
        this(holder,resources);
        this._gameObjects = gameObjects;
    }

    public GameThread(SurfaceHolder holder, Resources resources)
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
                    this.update();
                    this.draw(canvas);
                }
            }
            finally {
                if (canvas != null) {
                    this._holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    //TODO
    private void update()
    {

    }

    //TODO
    private  void draw( Canvas canvas)
    {

    }

    public void setRunning (boolean status)
    {
        this._isRunning = status;
    }


}
