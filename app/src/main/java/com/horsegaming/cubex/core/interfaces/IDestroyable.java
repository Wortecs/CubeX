package com.horsegaming.cubex.core.interfaces;

/**
 * Created by Horse on 29.06.2015.
 */
public interface IDestroyable
{
    boolean getIsMarked();
    boolean getIsDestroy();

    void setIsDestroy( boolean status);
    void setIsMarked( boolean status);

    int convertType();
}
