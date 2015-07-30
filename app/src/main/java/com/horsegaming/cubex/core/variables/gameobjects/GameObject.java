package com.horsegaming.cubex.core.variables.gameobjects;

import android.graphics.Canvas;

import com.horsegaming.cubex.core.variables.base.Mesh;
import com.horsegaming.cubex.core.variables.base.Transform;

import java.util.ArrayList;

/**
 * Created by Horse on 20.07.2015.
 */
public final class GameObject extends BaseGameObject<Component>
{
    public GameObject(Transform transform, Mesh texture)
    {
        super
                (transform, texture);
    }
}

