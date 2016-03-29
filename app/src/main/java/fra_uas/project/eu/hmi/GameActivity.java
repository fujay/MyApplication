package fra_uas.project.eu.hmi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fra_uas.project.eu.hmi.Games.GameTiming.GameTimeActivity;

public class GameActivity extends Activity implements View.OnClickListener {

    private Button bGameTiming, bGameDots, bGameAnalog, bGameTouch, bGameAccelerometer, bGameKugel, bGameKoffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        bGameTiming = (Button) findViewById(R.id.buttonGameTiming);
        bGameDots = (Button) findViewById(R.id.buttonGameDots);
        bGameAnalog = (Button) findViewById(R.id.buttonGameObjectAnalog);
        bGameTouch = (Button) findViewById(R.id.buttonGameObjectTouch);
        bGameAccelerometer = (Button) findViewById(R.id.buttonGameObjektAcc);
        bGameKugel = (Button) findViewById(R.id.buttonGameKugel);
        bGameKoffer = (Button) findViewById(R.id.buttonGameKoffer);

        bGameTiming.setOnClickListener(this);
        bGameDots.setOnClickListener(this);
        bGameAnalog.setOnClickListener(this);
        bGameTouch.setOnClickListener(this);
        bGameAccelerometer.setOnClickListener(this);
        bGameKugel.setOnClickListener(this);
        bGameKoffer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGameTiming:
                startActivity(new Intent(GameActivity.this, GameTimeActivity.class));
                break;
            case R.id.buttonGameDots:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.GameDots.LoadActivity.class));
                break;
            case R.id.buttonGameKugel:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.GameKugel.KugelKochActivity.class));
                break;
            case R.id.buttonGameObjectTouch:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.GameTouch.MainActivity.class));
                break;
            case R.id.buttonGameObjectAnalog:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.GameAnalog.MainActivity.class));
                break;
            case R.id.buttonGameObjektAcc:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.GameAccelerometer.MainActivity.class));
                break;
            case R.id.buttonGameKoffer:
                startActivity(new Intent(GameActivity.this, fra_uas.project.eu.hmi.Games.SuitcaseGame.GameActivity.class));
                break;
        }
    }
}
