package com.horsegaming.cubex.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Switch;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.activites.interfaces.ISwitchable;
import com.horsegaming.cubex.activites.parameters.GameType;
import com.horsegaming.cubex.activites.parameters.StartParams;
import com.horsegaming.cubex.view.GameView;
import com.horsegaming.cubex.staticclasses.Mediator;

public class GameActivity extends Activity implements ISwitchable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    @Override
    public void toNext( Parcelable sendParameters) {
        Intent loadEndActivity = new Intent(this, EndActivity.class);

        Mediator.sendParams(loadEndActivity, this.getString(R.string.SendToEnd), sendParameters);

        this.startActivity(loadEndActivity);
        this.finish();
    }

    @Override
    public void toPrevious(Parcelable sendParameters ) {
        Intent loadMainActivity = new Intent(this, MainActivity.class);

        Mediator.sendParams(loadMainActivity, this.getString(R.string.SendToMain), sendParameters);

        this.startActivity(loadMainActivity);
        this.finish();
    }

    @Override
    public void onBackPressed()
    {
        this.toPrevious(null);
    }

    private void parameterizeGameType()
    {
        GameType gameType = GameType.values()[((StartParams)this.getIntent().getParcelableExtra
                (this.getString(R.string.GetInGame))).GameType];

        switch (gameType)
        {
            case ARCADE: break;
            case TIME: break;
            case POINT: break;
        }

    }

}
