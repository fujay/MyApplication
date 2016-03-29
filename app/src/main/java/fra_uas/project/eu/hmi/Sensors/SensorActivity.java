package fra_uas.project.eu.hmi.Sensors;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import fra_uas.project.eu.hmi.MainActivity;
import fra_uas.project.eu.hmi.R;

public class SensorActivity extends Activity implements View.OnClickListener {

    private static final int QUESTION_DIALOG = 1;

    private Button bBeschleunigung, bHelligkeit, bErdmagnet, bSchwerkraft, bGyroskop;
    private SensorManager sensorManager;
    private TextView tvSizeAllSensors, tvAllSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        bBeschleunigung = (Button) findViewById(R.id.buttonBeschleunigung);
        bBeschleunigung.setOnClickListener(this);
        bHelligkeit = (Button) findViewById(R.id.buttonHelligkeit);
        bHelligkeit.setOnClickListener(this);
        bErdmagnet = (Button) findViewById(R.id.buttonErdmagnet);
        bErdmagnet.setOnClickListener(this);
        bSchwerkraft = (Button) findViewById(R.id.buttonSchwerkraft);
        bSchwerkraft.setOnClickListener(this);
        bGyroskop = (Button) findViewById(R.id.buttonGyroskop);
        bGyroskop.setOnClickListener(this);

        tvSizeAllSensors = (TextView) findViewById(R.id.textviewAllSensor);
        tvAllSensors = (TextView) findViewById(R.id.textviewSensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        tvSizeAllSensors.setText(getString(R.string.all_sensors, " " + deviceSensors.size()));
        for(int i = 0; i < deviceSensors.size(); i++) {
            tvAllSensors.append(i + 1 + ". " + deviceSensors.get(i).toString() + "\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemFAQ:
                showDialog(QUESTION_DIALOG);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqSensor);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBeschleunigung:
                startActivity(new Intent(SensorActivity.this, BeschleunigungActivity.class));
                break;
            case R.id.buttonHelligkeit:
                startActivity(new Intent(SensorActivity.this, HelligkeitActivity.class));
                break;
            case R.id.buttonErdmagnet:
                startActivity(new Intent(SensorActivity.this, ErdmagnetActivity.class));
                break;
            case R.id.buttonSchwerkraft:
                startActivity(new Intent(SensorActivity.this, SchwerkraftActivity.class));
                break;
            case R.id.buttonGyroskop:
                startActivity(new Intent(SensorActivity.this, GyroskopActivity.class));
                break;
        }
    }
}
