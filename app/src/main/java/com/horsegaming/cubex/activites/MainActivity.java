package com.horsegaming.cubex.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.horsegaming.cubex.R;
import com.horsegaming.cubex.activites.interfaces.ISwitchable;
import com.horsegaming.cubex.activites.parameters.GameType;
import com.horsegaming.cubex.activites.parameters.StartParams;
import com.horsegaming.cubex.staticclasses.Mediator;


public class MainActivity extends Activity implements ISwitchable {

    GameType _targetType = GameType.ARCADE;
    Button _startButton;
    TextView _info;
    Animation _animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this._startButton = (Button) findViewById(R.id.startButton);
        Log.d("BUTTON", this._startButton.toString() );
        this._info = (TextView) findViewById(R.id.infoText);
        _animation = AnimationUtils.loadAnimation(this, R.anim.start_anim_alpha);
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

    public void OnStartClick(View v)
    {
        this.toNext(new StartParams(_targetType.ordinal()));
    }

    public void  OnArcadeChoose(View v)
    {
        if(this._targetType == GameType.ARCADE) return;

        this._targetType = GameType.ARCADE;

        this._startButton.setBackgroundResource(R.drawable.button_main);
        //this._startButton.setText(R.string.ArcadeStart);

        this._startButton.startAnimation(_animation);
        //TODO
        this._info.setText(R.string.ArcadeInfo);

    }

    public void  OnTimeChoose(View v)
    {
        if(this._targetType == GameType.TIME) return;

        this._targetType = GameType.TIME;
        this._startButton.setBackgroundResource(R.drawable.button_time);
        //this._startButton.setText(R.string.TimeStart);


        this._startButton.startAnimation(_animation);

        //TODO
        this._info.setText(R.string.TimeInfo);
    }

    public void  OnPointChoose(View v)
    {
        if(this._targetType == GameType.POINT) return;

        this._targetType = GameType.POINT;

        this._startButton.setBackgroundResource(R.drawable.button_point);
        //this._startButton.setText(R.string.StepStart);

        this._startButton.startAnimation(_animation);
        //TODO
        this._info.setText(R.string.StepInfo);
    }

}
