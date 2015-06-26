package com.horsegaming.cubex.activites.interfaces;

import android.os.Parcelable;

/**
 * Created by Horse on 14.06.2015.
 */
public interface ISwitchable
{
    void toNext( Parcelable sendParameters);
    void toPrevious( Parcelable sendParameters );
}
