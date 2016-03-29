package fra_uas.project.eu.hmi.Games.GameDots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fra_uas.project.eu.hmi.R;

public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loadscreen);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent menuIntent = new Intent(LoadActivity.this, GameDotsActivity.class);
                    startActivity(menuIntent);
                    finish();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                finally {

                }
            }
        };
        timer.start();
    }
}
