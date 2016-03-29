package fra_uas.project.eu.hmi.Games.GameAnalog;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fra_uas.project.eu.hmi.R;

public class MainActivity extends Activity implements Sprite.OnRespawnListener {

    private static final float SIZE_MAX = 128f;

    private float density;
    private Random random = new Random();
    private Drawable bubbleDrawable;
    private Set<Sprite> sprites = new HashSet<Sprite>();
    private ViewGroup container;
    private Button bUP, bDOWN, bLEFT, bRIGHT;
    private ScheduledExecutorService executor;
    private Runnable moveRunnable = new Runnable() {
        @Override
        public void run() {
            for(final Sprite s : sprites) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s.move(bUP, bDOWN, bLEFT, bRIGHT);
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_analog_layout);

        density = getResources().getDisplayMetrics().density;
        bUP = (Button) findViewById(R.id.buttonUP);
        bDOWN = (Button) findViewById(R.id.buttonDOWN);
        bLEFT = (Button) findViewById(R.id.buttonLEFT);
        bRIGHT = (Button) findViewById(R.id.buttonRIGHT);
        container = (ViewGroup) findViewById(R.id.container);
        bubbleDrawable = getResources().getDrawable(R.drawable.android);
        startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        executor.shutdown();
        sprites.clear();
    }

    protected float scale(float v) {
        return density * v;
    }

    private void startGame() {
        //container.removeAllViews();
        sprites.clear();
            sprites.add(new Sprite((FrameLayout) container, scale(SIZE_MAX), random, bubbleDrawable, this));
        executor = Executors.newSingleThreadScheduledExecutor();
        // 1000 / 50 = 20 Movecycles
        executor.scheduleAtFixedRate(moveRunnable, 0, 50, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onBurst(Sprite sprite) {
        sprites.remove(sprite);
        sprites.add(new Sprite((FrameLayout) container, scale(SIZE_MAX), random, bubbleDrawable, this));
    }
}
