package fra_uas.project.eu.hmi.Games.GameDots;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

import fra_uas.project.eu.hmi.R;

public class GameDotsActivity extends Activity implements View.OnClickListener, TextToSpeech.OnInitListener {

    public static final String HIGHSCORE = "highscore";
    private int OBJECTID = 1337;
    private final int MAXSSIZE = 30;
    private final int MINSSIZE = 20;

    private MediaPlayer mpBackground, mpPoint;
    private TextView tvFindObject;
    private FrameLayout frameLayout;
    private int points;
    private int round;
    private int countdown;
    private int highscore;
    private Paint paintColor;
    private ImageView object;
    private String stringFindObject;
    private Random random = new Random();
    private Handler handler = new Handler();
    private TextToSpeech tts;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_dots_layout);
        findViewById(R.id.help).setOnClickListener(this);
        tvFindObject = (TextView) findViewById(R.id.findObject);
        frameLayout = (FrameLayout) findViewById(R.id.container);
        mpPoint = MediaPlayer.create(this, R.raw.powerup);
        mpBackground = MediaPlayer.create(this, R.raw.vivacity);
        mpBackground.setLooping(true);
        mpBackground.start();
        tts = new TextToSpeech(this, this);
        showStartFragment();
    }

    private void loadHighscore() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        highscore = preferences.getInt(HIGHSCORE, 0);
    }

    private void saveHighscore(int points) {
        highscore = points;
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HIGHSCORE, highscore);
        editor.commit();
    }


    private void newGame() {
        points = 0;
        round = 1;
        initRound();
    }

    private void initRound() {
        countdown = 10;
        randomBackground();
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.removeAllViews();
        ObjectView objectView = new ObjectView(this, randomPaintColor());
        container.addView(objectView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        objectView.setObjectCount(8 * (10 + round));
        object = new ImageView(this);
        //noinspection ResourceType
        object.setId(OBJECTID);
        randomObject();
        //randomPaintColor();
        setObjectColor();
        randomSize();
        object.setScaleType(ImageView.ScaleType.CENTER);
        float scale = getResources().getDisplayMetrics().density;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Math.round(64 * scale), Math.round(61 * scale));
        lp.gravity = Gravity.TOP + Gravity.LEFT;
        lp.leftMargin = random.nextInt(container.getWidth() - 64);
        lp.topMargin = random.nextInt(container.getHeight() - 61);
        object.setOnClickListener(this);
        container.addView(object, lp);
        update();
        handler.postDelayed(runnable, 1000 - round * 50);
    }

    private void update() {
        fillTextView(R.id.points, Integer.toString(points) + " ");
        loadHighscore();
        fillTextView(R.id.highscore, Integer.toString(highscore));
        fillTextView(R.id.round, " " + Integer.toString(round));
        fillTextView(R.id.countdown, Integer.toString(countdown * 1000) + " ");
    }

    private void fillTextView(int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }

    private void randomBackground() {
        int randomBackground = random.nextInt(7);
        switch (randomBackground) {
            case 0:
                frameLayout.setBackgroundResource(R.drawable.background1);
                break;
            case 1:
                frameLayout.setBackgroundResource(R.drawable.background2);
                break;
            case 2:
                frameLayout.setBackgroundResource(R.drawable.background3);
                break;
            case 3:
                frameLayout.setBackgroundResource(R.drawable.background4);
                break;
            case 4:
                frameLayout.setBackgroundColor(getResources().getColor(R.color.button));
                break;
            case 5:
                frameLayout.setBackgroundColor(getResources().getColor(R.color.black_transparent));
                break;
            case 6:
                frameLayout.setBackgroundColor(getResources().getColor(R.color.ligth));
                break;
        }
    }

    private void showStartFragment() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.fragment_start, null));
        container.findViewById(R.id.start).setOnClickListener(this);
    }

    private void showGameOverFragment() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        container.addView(getLayoutInflater().inflate(R.layout.layout_gameover, null) );
        container.findViewById(R.id.play_again).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start) {
            startGame();
        } else if(view.getId() == R.id.play_again) {
            showStartFragment();
        } else if(view.getId() == OBJECTID) {
            foundObject();
        } else if(view.getId() == R.id.help) {
            showHelp();
        }
    }

    private void foundObject() {
        handler.removeCallbacks(runnable);
        mpPoint.start();
        showToast(R.string.found);
        points += countdown * 1000;
        round++;
        initRound();
    }

    private void showToast(int stringResId) {
        toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView textView = new TextView(this);
        textView.setText(stringResId);
        textView.setTextColor(getResources().getColor(R.color.points));
        textView.setTextSize(48f);
        toast.setView(textView);
        toast.show();
    }

    private void showHelp() {
        final Dialog dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_help);
        dialog.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startGame();
            }
        });
        dialog.show();
    }

    private void startGame() {
        newGame();
    }

    private void countdown() {
        countdown--;
        update();
        if(countdown <= 0) {
            object.setOnClickListener(null);
            if(points > highscore) {
                    tts.speak(getString(R.string.newRecord), TextToSpeech.QUEUE_FLUSH, null);
                saveHighscore(points);
                update();
            }
            tts.speak(getString(R.string.gameover), TextToSpeech.QUEUE_FLUSH, null);
            showGameOverFragment();
        } else {
            handler.postDelayed(runnable, 1000 - round * 50);
        }
    }

    private void randomSize() {
        this.object.setMaxWidth(randomBetween(MAXSSIZE, MINSSIZE));
        this.object.setMaxHeight(randomBetween(MAXSSIZE, MINSSIZE));
    }

    private void randomObject() {
        int randomNumber = random.nextInt(3);
        switch (randomNumber) {
            case 0:
                object.setImageResource(R.drawable.drawable_rect);
                stringFindObject = "Rect";
                break;
            case 1:
                object.setImageResource(R.drawable.drawable_circle);
                stringFindObject = "Circle";
                break;
            case 2:
                object.setImageResource(R.drawable.drawable_ring);
                stringFindObject = "Ring";
                break;
        }
    }

    private Paint randomPaintColor() {
        int randomColor = random.nextInt(4);
        switch (randomColor) {
            case 0:
                paintColor = new Paint();
                paintColor.setColor(getResources().getColor(R.color.red));
                tvFindObject.setText("Red " + stringFindObject);
                return fakeColor(randomColor);
            case 1:
                paintColor = new Paint();
                paintColor.setColor(getResources().getColor(R.color.blue));
                tvFindObject.setText("Blue " + stringFindObject);
                return fakeColor(randomColor);
            case 2:
                paintColor = new Paint();
                paintColor.setColor(getResources().getColor(R.color.green));
                tvFindObject.setText("Green " + stringFindObject);
                return fakeColor(randomColor);
            case 3:
                paintColor = new Paint();
                paintColor.setColor(getResources().getColor(R.color.yellow));
                tvFindObject.setText("Yellow " + stringFindObject);
                return fakeColor(randomColor);
        }
        return fakeColor(randomColor);
    }

    private Paint fakeColor(int index) {
        int randomColor = random.nextInt(3);
        Paint p = new Paint();
        switch (index) {
            case 0: // Find color was red
                if(randomColor == 0) {
                    p.setColor(getResources().getColor(R.color.blue));
                    return p;
                } else if(randomColor == 1) {
                    p.setColor(getResources().getColor(R.color.green));
                    return p;
                } else {
                    p.setColor(getResources().getColor(R.color.yellow));
                    return p;
                }
            case 1: // Find color was blue
                if(randomColor == 0) {
                    p.setColor(getResources().getColor(R.color.red));
                    return p;
                } else if(randomColor == 1) {
                    p.setColor(getResources().getColor(R.color.green));
                    return p;
                } else {
                    p.setColor(getResources().getColor(R.color.yellow));
                    return p;
                }
            case 2: // Find color was green
                if(randomColor == 0) {
                    p.setColor(getResources().getColor(R.color.red));
                    return p;
                } else if(randomColor == 1) {
                    p.setColor(getResources().getColor(R.color.blue));
                    return p;
                } else {
                    p.setColor(getResources().getColor(R.color.yellow));
                    return p;
                }
            case 3: // Find color was yellow
                if(randomColor == 0) {
                    p.setColor(getResources().getColor(R.color.blue));
                    return p;
                } else if(randomColor == 1) {
                    p.setColor(getResources().getColor(R.color.green));
                    return p;
                } else {
                    p.setColor(getResources().getColor(R.color.red));
                    return p;
                }
        }
        return p;
    }

    private void setObjectColor() {
        this.object.setColorFilter(paintColor.getColor());
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            countdown();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if(mpBackground != null && mpBackground.isPlaying()) {
            mpBackground.stop();
            mpBackground.release();
        }
        if(toast != null) toast.cancel();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
        mpPoint.release();
        if(toast != null) toast.cancel();
    }

    @Override
    public void onInit(int status) {
        tts.setLanguage(Locale.ENGLISH);
    }

    public int randomBetween(int big, int small) {
        return random.nextInt((big + 1) - small) + small;
    }
}
