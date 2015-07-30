package com.horsegaming.cubex.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.horsegaming.cubex.core.gamethreed.DrawThread;


/**
 * Created by Horse on 15.06.2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread _drawThread;
    //static private List<GameObject> _gameObjects = new ArrayList<>();


    public GameView(Context context ) {
        super(context);
        this.getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

      /*
        //TODO
        this._drawThread = new DrawThread(this.getHolder(), this.getResources(), _gameObjects);
        this._drawThread.start();
        this._drawThread.setRunning(true);
        this._updateThread = new UpdateThread(_gameObjects);
        this._updateThread.start();
        this._updateThread.setRunning(true);
    */
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        /*
        boolean retry = true;

        this._drawThread.setRunning(false);
        while (retry) {
            try {
                this._drawThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }

        this._updateThread.setRunning(false);
        while (retry) {
            try {
                this._updateThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
        _gameObjects.clear();
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
        //TODO CLICK
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Point clickPos = new Point((int) event.getX(), (int) event.getY());
            for (GameObject gameObject : _gameObjects)
                if (gameObject.contains(clickPos))
                    gameObject.click(clickPos);
        }
        */
        return true;
    }
/*
    public static GameObject FindGameObjectByTag(String tag) {
        for (GameObject gameObject : _gameObjects) {
            if (gameObject.Tag == tag)
                return gameObject;
        }
        return null;
    }

    public static GameObject FindGameObject(Class<? extends GameObject> clazz) {
        for (GameObject gameObject : _gameObjects) {
            if (gameObject.getClass() == clazz)
                return gameObject;
        }
        return null;

    }
*/

}
