package fra_uas.project.eu.hmi.Games.GameTiming;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import fra_uas.project.eu.hmi.R;

public class GameTimeActivity extends Activity {
    private static final int DIALOG_ALERT = 1;

    private final int MAX = 10;
    private final int MIN = 0;

    private int time, sec;
    private long startTime = 0L;
    private Handler handler = new Handler();
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedTime = 0L;

    private TextView textviewRandomPeriod, textviewAbsoluteDiff, textviewRelativeDiff;
    private ImageButton buttonStart_Fertig, buttonQuestion;
    private Button buttonRestart;
    private boolean boolStart = true;
    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_time_layout);

        textviewRandomPeriod = (TextView) findViewById(R.id.tvRandomTime);
        textviewAbsoluteDiff = (TextView) findViewById(R.id.tvAbsoluteDiff);
        textviewRelativeDiff = (TextView) findViewById(R.id.tvRelativeDiff);
        buttonStart_Fertig = (ImageButton) findViewById(R.id.bStartStop);
        buttonRestart = (Button) findViewById(R.id.bRestart);
        buttonQuestion = (ImageButton) findViewById(R.id.bQuestion);
        random = new Random();
        time = random.nextInt((MAX + 1) - MIN) + MIN;
        textviewRandomPeriod.setText(String.valueOf(time));

        buttonStart_Fertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boolStart) {

                    startTime = 0L;
                    timeInMilliseconds = 0L;
                    timeSwapBuff = 0L;
                    updatedTime = 0L;

                    startTime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimerThread, 0);
                    buttonStart_Fertig.setImageResource(R.drawable.button_stop);
                    buttonStart_Fertig.setAdjustViewBounds(true);
                    boolStart = false;
                    textviewRelativeDiff.setText("");
                    textviewAbsoluteDiff.setText("");
                    textviewRelativeDiff.setTextColor(Color.WHITE);
                } else {
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimerThread);
                    buttonStart_Fertig.setImageResource(R.drawable.button_start);
                    buttonStart_Fertig.setAdjustViewBounds(true);
                    //buttonStart_Fertig.setMinimumHeight(110);
                    //buttonStart_Fertig.setMinimumWidth(110);
                    boolStart = true;
                    textviewRelativeDiff.setText(String.valueOf(relativeDifference()));
                    textviewRelativeDiff.setTextColor(relativeColor());
                    textviewAbsoluteDiff.setText(String.valueOf(sec));
                }
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        buttonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ALERT);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqGameTiming);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                return builder.create();
            default:
                return super.onCreateDialog(id);
        }
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            sec = (int) (updatedTime / 1000);
            sec = sec % 60;
            handler.postDelayed(this, 0);
        }
    };

    int relativeColor() {
        if(time - sec == 0) {
            return Color.GREEN;
        } else if(time - sec <= 2) {
            return Color.YELLOW;
        } else {
            return Color.RED;
        }
    }

    int relativeDifference() {
        return (time - sec);
    }
}
