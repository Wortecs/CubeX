package com.horsegaming.cubex.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.activites.interfaces.ISwitchable;


public class EndActivity extends Activity implements ISwitchable {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }

    @Override
    public void toNext( Parcelable sendParameters )
    {
        Intent loadMainActivity = new Intent(this, MainActivity.class);
        //Mediator.sendParams(loadMainActivity,this.getString(R.string.SendToMain),sendParameters);
        this.startActivity(loadMainActivity);
        this.finish();
    }

    @Override
    public void toPrevious( Parcelable sendParameters )
    {
        Intent loadGameActivity = new Intent(this, GameActivity.class);
        //Mediator.sendParams(loadGameActivity,this.getString(R.string.SendToGame),sendParameters);
        this.startActivity(loadGameActivity);
        this.finish();
    }


}
