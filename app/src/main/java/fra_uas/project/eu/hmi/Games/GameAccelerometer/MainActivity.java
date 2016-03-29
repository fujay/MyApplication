package fra_uas.project.eu.hmi.Games.GameAccelerometer;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;


public class MainActivity extends Activity {

    private SensorManager sensorManager;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        setContentView(new ObjectView(this, sensorManager));

    }

}
