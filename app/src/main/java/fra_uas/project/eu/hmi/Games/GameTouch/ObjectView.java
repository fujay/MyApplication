package fra_uas.project.eu.hmi.Games.GameTouch;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

import fra_uas.project.eu.hmi.R;

public class ObjectView extends SurfaceView implements View.OnClickListener, View.OnLongClickListener{

    private static int counter = 0;

    private SurfaceHolder surfaceHolder;
    private LoopThread loopThread;
    private Bitmap bmp;
    private Rect rect;
    private Sprite sprite;
    private ArrayList<Sprite> spriteList;

    public ObjectView(Context context) {
        super(context);

        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        loopThread = new LoopThread(this);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas theCanvas = surfaceHolder.lockCanvas(null);
                onDraw(theCanvas);
                surfaceHolder.unlockCanvasAndPost(theCanvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                loopThread.setRunning(true);
                loopThread.start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                loopThread.setRunning(false);
                while (retry) {
                    try {
                        loopThread.join();
                        retry = false;
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });

        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        spriteList = new ArrayList<Sprite>();
        spriteList.add(new Sprite(this, bmp));
        spriteList.add(new Sprite(this, rect));
        spriteList.add(new Sprite(this, 1));
        spriteList.add(new Sprite(this, rect));
        spriteList.add(new Sprite(this, 1));
    }

    @Override
     protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);

        for(int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).onDraw(canvas);
        }

   }

    @Override
    public void onClick(View v) {
        counter++;
        if (counter % 2 == 0) {
            spriteList.add(new Sprite(this, rect));
        } else {
            if (counter % 2 == 1) {
                spriteList.add(new Sprite(this, 1));
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(spriteList.size() > 0) {
            spriteList.remove(spriteList.size() - 1);
            return true;
        } else {
            return false;
        }
    }
}
