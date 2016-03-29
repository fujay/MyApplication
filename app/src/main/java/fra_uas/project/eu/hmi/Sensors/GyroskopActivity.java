package fra_uas.project.eu.hmi.Sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import fra_uas.project.eu.hmi.R;


public class GyroskopActivity extends Activity {

    private TextView tvInfo, tvSensorInfo;
    private SensorManager manager;
    private Sensor sensor;
    private SensorEventListener listener;
    private boolean bSensor = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyeoskop_layout);

        tvInfo = (TextView) findViewById(R.id.textviewGyroscope);
        tvSensorInfo = (TextView) findViewById(R.id.textviewSensorInfo);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sensor == null) {
            bSensor = false;
            tvInfo.setText(R.string.noSensor);
        }

        if(bSensor) {
            tvSensorInfo.setText(sensor.toString());
            listener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values.length > 0) {
                        float x = event.values[0];
                        float y = event.values[1];
                        float z = event.values[2];
                        String text1 = Float.toString(x);
                        String text2 = Float.toString(y);
                        String text3 = Float.toString(z);

                        tvInfo.setText(getString(R.string.type_gyro, text1, text2, text3));
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }
            };
            // Listener registrieren
            manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Listener entfernen
        if(bSensor) {
            if (sensor != null) {
                manager.unregisterListener(listener);
            }
        }
    }
}

