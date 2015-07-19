package com.horsegaming.cubex.elements.fields.components;

import android.util.Log;

import com.horsegaming.cubex.core.interfaces.IComponentable;
import com.horsegaming.cubex.core.variables.GameObject;
import com.horsegaming.cubex.elements.GUI.GUILimiter;
import com.horsegaming.cubex.staticclasses.Destroyer;
import com.horsegaming.cubex.view.GameView;

/**
 * Created by Horse on 04.07.2015.
 */
//TODO
public class LimitClickComponent implements IComponentable {
    @Override
    public void onUpdate()
    {

    }

    @Override
    public void onClick()
    {
        if(Destroyer.lastDestroyCount > 0 )
        {
            Log.d("Last destory", Destroyer.lastDestroyCount + "");
            GameObject gameObject = GameView.FindGameObjectByTag(GUILimiter.getStandartTag());
            if(gameObject != null)
            {
                ((GUILimiter)gameObject).tick(1);
            }
        }
    }
}
