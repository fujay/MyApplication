package fra_uas.project.eu.hmi.Games.GameDots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ObjectView extends View {

    private int imageCount;
    private Random random;
    private long randomSeed = 1;
    private Paint paint = new Paint();
    private AbstractObjects rect, circle;
    private ArrayList<AbstractObjects> objects = new ArrayList<AbstractObjects>();

    public ObjectView(Context context) {
        super(context);
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject());
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject());
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject());
        objects.add(new CircleObject());
        objects.add(new CircleObject());
        objects.add(new CircleObject());
        paint.setAntiAlias(true);
    }

    public ObjectView(Context context, Paint paint) {
        super(context);
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject());
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject(paint));
        objects.add(new fra_uas.project.eu.hmi.Games.GameDots.RectObject());
        objects.add(new CircleObject());
        objects.add(new CircleObject(paint));
        objects.add(new CircleObject());
        paint.setAntiAlias(true);
    }

    public void setObjectCount(int imageCount) {
        this.imageCount = imageCount;
        randomSeed = System.currentTimeMillis();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        random = new Random(randomSeed);
        for(AbstractObjects image : objects) {
            for(int i = 0; i < imageCount / objects.size(); i++) {
                int left = (random.nextInt() * (getWidth() - image.getWidth()));
                int top = (random.nextInt() * (getHeight() - image.getHeight()));
                image.onDraw(canvas, left, top);
            }
        }
    }

}