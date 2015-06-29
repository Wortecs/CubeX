package com.horsegaming.cubex.core.view;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.horsegaming.cubex.core.gamethreed.GameThread;

/**
 * Created by Horse on 15.06.2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameThread _gameThread;

    public GameView(Context context)
    {
        super(context);
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        //TODO
        this._gameThread = new GameThread(this.getHolder(), this.getResources(), null);
        this._gameThread.start();
        this._gameThread.setRunning(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;

        this._gameThread.setRunning(false);
        while (retry) {
            try {
                this._gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_UP)
        _gameThread.click(new Point((int)event.getX(),(int)event.getY()));
        return true;
    }

}
