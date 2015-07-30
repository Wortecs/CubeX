package com.horsegaming.cubex.core.variables.gameobjects;

/**
 * Created by Horse on 21.07.2015.
 */
interface IComponentable<T extends Component>
{
    T getComponent(Class<T> component);
    T getComponent(String name);

    boolean addComponent(T component);

    void removeComponent(String name);


}
