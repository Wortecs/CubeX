package com.horsegaming.cubex.core.gamethreed;

import com.horsegaming.cubex.core.interfaces.IUpdatable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.core.variables.Timing;

import java.util.List;

/**
 * Created by Horse on 29.06.2015.
 */
//TODO!
public class UpdateThread extends Thread
{
    private boolean _isRunning = false;
    private long _lastFrameTime;

    private List<GameObject>  _gameObjects;

    public UpdateThread(List<GameObject> gameObjects)
    {
        this._gameObjects = gameObjects;
    }

    @Override
    public void run()
    {
        while (this._isRunning)
        {
            long now = System.currentTimeMillis();
            if(System.currentTimeMillis() - _lastFrameTime > Timing.BlockFPS)
            {

                for (IUpdatable updatable : _gameObjects) {
                    //TODO SLEEP
                    updatable.update();
                }
                Timing.UpdateTime = (int)(_lastFrameTime - System.currentTimeMillis());
                _lastFrameTime = now;
            }
        }
    }

    public void setRunning (boolean status)
    {
        this._isRunning = status;
    }
}
