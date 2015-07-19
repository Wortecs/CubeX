package com.horsegaming.cubex.view;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.horsegaming.cubex.core.gamethreed.DrawThread;
import com.horsegaming.cubex.core.gamethreed.UpdateThread;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.elements.GUI.GUIFPSMeter;
import com.horsegaming.cubex.elements.GUI.GUIGround;
import com.horsegaming.cubex.elements.GUI.GUILimiter;
import com.horsegaming.cubex.elements.fields.BoxDecoder;
import com.horsegaming.cubex.elements.fields.BoxRandomGenerator;
import com.horsegaming.cubex.elements.fields.GameField;
import com.horsegaming.cubex.elements.fields.components.LimitClickComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horse on 15.06.2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread _drawThread;
    private UpdateThread _updateThread;
    static private List<GameObject> _gameObjects = new ArrayList<>();


    public GameView(Context context ) {
        super(context);
        this.getHolder().addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //TODO DELLETE
        _gameObjects.add(new GUILimiter(this.getWidth(), this.getHeight(), 10));
        _gameObjects.add(new GUIFPSMeter(new Point(this.getWidth()-50,20),new Point(50,50)));
        _gameObjects.add(new GUIGround(this.getWidth(),this.getHeight()));
        _gameObjects.add(new GameField(this.getWidth(), this.getHeight(), "BoxField",  new BoxDecoder(this.getResources(),1), this.getResources(),1));
        _gameObjects.get(3).addComponent(new LimitClickComponent());
        
        //TODO
        this._drawThread = new DrawThread(this.getHolder(), this.getResources(), _gameObjects);
        this._drawThread.start();
        this._drawThread.setRunning(true);
        this._updateThread = new UpdateThread(_gameObjects);
        this._updateThread.start();
        this._updateThread.setRunning(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //TODO CLICK
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Point clickPos = new Point((int) event.getX(), (int) event.getY());
            for (GameObject gameObject : _gameObjects)
                if (gameObject.contains(clickPos))
                    gameObject.click(clickPos);
        }
        return true;
    }

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


}
