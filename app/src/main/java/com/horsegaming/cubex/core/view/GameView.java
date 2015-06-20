package com.horsegaming.cubex.core.view;

import android.content.Context;
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
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        this._gameThread = new GameThread(this.getHolder(), this.getResources());
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

}
