package fra_uas.project.eu.hmi.Games.GameDots;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public abstract class AbstractObjects {
    protected final int MAXSSIZE = 60;
    protected final int MINSSIZE = 30;
    protected int width;
    protected int height;
    protected Paint paint;
    protected Random random;

    public AbstractObjects() {
        random = new Random();
    }

    abstract void onDraw(Canvas canvas, int posLeft, int posTop);

    public int randomBetween(int big, int small) {
        return random.nextInt((big + 1) - small) + small;
        //return random.nextInt(big);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}