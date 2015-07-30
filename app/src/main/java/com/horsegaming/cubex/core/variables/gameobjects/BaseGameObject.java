package com.horsegaming.cubex.core.variables.gameobjects;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.horsegaming.cubex.core.variables.base.Mesh;
import com.horsegaming.cubex.core.variables.base.Transform;

import java.util.ArrayList;

/**
 * Created by Horse on 25.07.2015.
 */
abstract class BaseGameObject<T extends Component> implements IComponentable<T>
{
    protected ArrayList<T> _components = new ArrayList<>();

    public Mesh Texture;
    public Transform Parameters;

    public String Tag = "";

    public BaseGameObject(Transform transform, Mesh texture)
    {
        this.Parameters = transform;
        this.Texture = texture;
    }

    public void drawGameObject(Canvas canvas)
    {
        this.Texture.draw(canvas,this.Parameters);
        for(T c : this._components)
            c.onUpdate();
    }
    public void onClickGameObject(Point clickPoss)
    {
        for( T c : this._components )
            c.onClick(clickPoss);
    }

    @Override
    public T getComponent(Class<T> component) {
        for(T c : this._components)
            if(c.getClass() == component)
                return c;
        return null;
    }
    @Override
    public T getComponent(String name) {
        for(T c : this._components)
            if(c.getClass().toString() == name)
                return c;
        return null;
    }
    @Override
    public boolean addComponent(T component) {
        if(!this._components.contains(component))
        {
            this._components.add(component);
            this._components.get(this._components.size()).OnStart();
            return true;
        }else
        {
            Log.d("Missing add", "GameObjject have" + component.getClass().toString());
            return false;
        }
    }
    @Override
    public void removeComponent(String name) {
        for(T c : this._components)
            if(c.getClass().toString() == name) {
                c.onDestroy();
                this._components.remove(c);
                return;
            }
    }

}
