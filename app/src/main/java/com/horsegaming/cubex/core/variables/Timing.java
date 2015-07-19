package com.horsegaming.cubex.core.variables;

import android.graphics.Color;

/**
 * Created by Horse on 05.07.2015.
 */
public final class Timing
{
    //TODO
    final private static int STANDARD_BACKGROUND_COLOR = Color.rgb(236, 240, 241);
    private static int BackgroundColor = STANDARD_BACKGROUND_COLOR;

    public static void setBackgroundColor(int color)
    {
        BackgroundColor = color;
    }

    public static int getBackgroundColor()
    {
        return BackgroundColor;
    }

    public static void refreshColor()
    {
        BackgroundColor = STANDARD_BACKGROUND_COLOR;
    }



    public static int DeltaTime;
    public static int UpdateTime;

    public static long BlockFPS = 20;


}
