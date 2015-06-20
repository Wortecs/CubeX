package com.horsegaming.cubex.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.activites.interfaces.ISwitchable;
import com.horsegaming.cubex.activites.parameters.StartParams;
import com.horsegaming.cubex.staticclasses.Mediator;


public class MainActivity extends Activity implements ISwitchable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void toNext(Parcelable sendParameters) {
        Intent loadGameActivity = new Intent(this, GameActivity.class);

        Mediator.sendParams(loadGameActivity, this.getString(R.string.SendToGame), sendParameters);

        this.startActivity(loadGameActivity);
        this.finish();
    }

    @Override
    public void toPrevious(Parcelable sendParameters )
    {
        System.exit(0);
    }

    @Override
    public void onBackPressed()
    {
        this.toPrevious(null);
    }

    public void ClickTest(View v)
    {
        this.toNext(new StartParams(1));
    }

}
