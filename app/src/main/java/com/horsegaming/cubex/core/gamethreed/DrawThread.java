package com.horsegaming.cubex.core.gamethreed;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horse on 15.06.2015.
 */
public final class DrawThread extends Thread
{
    private long _lastFrameTime;
    private boolean _isRunning = false;
    private SurfaceHolder _holder;
    private Resources _resources;

    //private static List<GameObject> _gameObjects;

    public DrawThread(SurfaceHolder holder, Resources resources)
    {
        this._resources = resources;
        this._holder = holder;

    }


    @Override
    public void run()
    {
        /*
        Canvas canvas;
        while (_isRunning) {

            long now = System.currentTimeMillis();
            if(System.currentTimeMillis() - _lastFrameTime > Timing.BlockFPS) {

                canvas = null;
                try {
                    //TODO CHECK
                    canvas = _holder.lockCanvas(null);
                    this.update(canvas);
                } finally {
                    if (canvas != null) {
                        this._holder.unlockCanvasAndPost(canvas);
                    }
                }
                _lastFrameTime = now;
            }
        }
        */
    }

    private  void update( Canvas canvas )
    {
        /*
        if(canvas == null) return;

        canvas.drawColor(Timing.getBackgroundColor());
        for (GameObject gameObject : _gameObjects) {
            gameObject.draw(canvas);
          //  gameObject.update();
        }

        Timing.DeltaTime = (int)(System.currentTimeMillis() - _lastFrameTime);
        */
    }

    public void setRunning (boolean status)
    {
        this._isRunning = status;
    }





}
