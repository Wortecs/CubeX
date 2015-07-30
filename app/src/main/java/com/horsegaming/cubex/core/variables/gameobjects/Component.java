package com.horsegaming.cubex.core.variables.gameobjects;

import android.graphics.Point;

/**
 * Created by Horse on 21.07.2015.
 */
public interface Component
{
    void OnStart();
    void onUpdate();
    void onClick(Point clickPoss);
    void onDestroy();
}
