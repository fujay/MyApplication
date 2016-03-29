package fra_uas.project.eu.hmi.Games.GameAccelerometer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Random;

public class Sprite implements SensorEventListener {

    private static final int MAXSPEED = 20;
    private static final int MINSPEED = 10;
    private static final int MAXSSIZE = 100;
    private static final int MINSSIZE = 40;

    private int x = 300;
    private int y = 300;
    private int xSpeed;
    private int ySpeed;
    private int width;
    private int height;
    private int id = 0;
    private SensorManager sensorManager;
    private Random random;
    private Bitmap bmp;
    private Rect rect;
    private Paint p;
    private ObjectView objectView;

    public Sprite(ObjectView objectView, Bitmap bmp, SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        random = new Random();
        this.objectView = objectView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        this.xSpeed = random.nextInt(MAXSPEED) - MINSPEED;
        this.ySpeed = random.nextInt(MAXSPEED) - MINSPEED;
    }

    public Sprite(ObjectView objectView, Rect r) {
        random = new Random();
        this.objectView = objectView;
        this.rect = new Rect(r);
        this.rect.set(0, 0, randomBetween(MAXSSIZE, MINSSIZE), randomBetween(MAXSSIZE, MINSSIZE));
        this.width = rect.width();
        this.height = rect.height();
        this.xSpeed = random.nextInt(MAXSPEED) - MINSPEED;
        this.ySpeed = random.nextInt(MAXSPEED) - MINSPEED;
        this.p = new Paint();
        this.p.setARGB(255, randomBetween(255, 0), randomBetween(255, 0), randomBetween(255, 0));
    }

    /**
     *
     * @param objectView Canvas to draw
     * @param id specify which grapical object should be drawn.
     */
    public Sprite(ObjectView objectView, int id) {
        random = new Random();
        this.objectView = objectView;
        this.id = id;
        this.bmp = Bitmap.createBitmap(randomBetween(MAXSSIZE, MINSSIZE),randomBetween(MAXSSIZE, MINSSIZE), Bitmap.Config.ARGB_8888);
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        this.xSpeed = random.nextInt(MAXSPEED) - MINSPEED;
        this.ySpeed = random.nextInt(MAXSPEED) - MINSPEED;
        this.p = new Paint();
        this.p.setARGB(255, randomBetween(255, 0), randomBetween(255, 0), randomBetween(255, 0));
    }

    private void bounceOff() {
        if(x > objectView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;

        if(y > objectView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
    }

    public void onDraw(Canvas canvas) {
        bounceOff();

        if(bmp != null) {
            canvas.drawBitmap(bmp, x, y, null);
        }
        if(rect != null) {
            rect.offsetTo(x, y);
            canvas.drawRect(rect, p);
        }
        if(bmp != null && id == 1) {
            canvas.drawCircle(x, y, 20, p);
        }

    }

    private int randomBetween(int big, int small) {
        random = new Random();
        return random.nextInt((big + 1) - small) + small;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.xSpeed = (int) event.values[0] * -1;
        this.ySpeed = (int) event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
