package fra_uas.project.eu.hmi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fra_uas.project.eu.hmi.InputControls.ButtonActivity;
import fra_uas.project.eu.hmi.InputControls.InputActivity;
import fra_uas.project.eu.hmi.Layouts.LayoutActivity;
import fra_uas.project.eu.hmi.Sensors.SensorActivity;

public class MainActivity extends AppCompatActivity {

    private static final int QUESTION_DIALOG = 1;

    private Button bLayout, bMenu, bDialog, bTaeuschung, bWidget, bVoice, bSensor, bInput, bGame;
    private ImageButton ibFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bLayout = (Button) findViewById(R.id.buttonLayout);
        bLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LayoutActivity.class));
            }
        });
        bMenu = (Button) findViewById(R.id.buttonMenu);
        bMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        bDialog = (Button) findViewById(R.id.buttonDialog);
        bDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });
        bTaeuschung = (Button) findViewById(R.id.buttonTaeschung);
        bTaeuschung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptischeTaeuschungActivity.class));
            }
        });
        bWidget = (Button) findViewById(R.id.buttonWidget);
        bWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
            }
        });
        bVoice = (Button) findViewById(R.id.buttonVoice);
        bVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VoiceActivity.class));
            }
        });
        bSensor = (Button) findViewById(R.id.buttonSensor);
        bSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SensorActivity.class));
            }
        });
        bInput = (Button) findViewById(R.id.buttonInput);
        bInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InputActivity.class));
            }
        });
        bGame = (Button) findViewById(R.id.buttonGames);
        bGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        ibFAQ = (ImageButton) findViewById(R.id.imageButton);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.info);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
