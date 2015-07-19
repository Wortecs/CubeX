package com.horsegaming.cubex.core.interfaces;

/**
 * Created by Horse on 04.07.2015.
 */
public interface ILimiter
{
    void tick ( int value);
    double getLess();
    void setStartParam(int value);
}
