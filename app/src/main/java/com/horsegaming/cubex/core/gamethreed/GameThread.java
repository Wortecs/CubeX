package com.horsegaming.cubex.core.gamethreed;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;

import com.horsegaming.cubex.core.elements.fields.BoxRandomGenerator;
import com.horsegaming.cubex.core.elements.fields.GameField;
import com.horsegaming.cubex.core.interfaces.IClicable;
import com.horsegaming.cubex.core.variables.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horse on 15.06.2015.
 */
public final class GameThread extends Thread implements IClicable
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

        //TODO DELLETE
        this._gameObjects.add(new GameField(new Point(0, 0), new Point(700, 700), "BoxField", 6, new BoxRandomGenerator()));


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
                //TODO CHECK
                try {  this.sleep(30); } catch (InterruptedException e) { e.printStackTrace();   }
                canvas = _holder.lockCanvas(null);
                    this.update(canvas);
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


    @Override
    public void click(Point clickPos) {

        for(GameObject gameObject : _gameObjects)
        {
            if(gameObject.contains(clickPos))
            {
                gameObject.click(clickPos);
                Log.d("Click in ", gameObject.getClass().toString());
            }
        }
    }
}
