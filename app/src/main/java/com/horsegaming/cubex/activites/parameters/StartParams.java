package com.horsegaming.cubex.activites.parameters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Horse on 14.06.2015.
 */
public class StartParams implements Parcelable
{
    public int GameType;


    public StartParams( int gameType)
    {
        this.GameType = gameType;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<StartParams> CREATOR = new Parcelable.Creator<StartParams>() {
        public StartParams createFromParcel(Parcel in) {
            return new StartParams( in );
        }

        public StartParams[] newArray(int size) {
            return new StartParams[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(GameType);

    }

    private StartParams(Parcel in)
    {
        this.GameType = in.readInt();

    }
}
