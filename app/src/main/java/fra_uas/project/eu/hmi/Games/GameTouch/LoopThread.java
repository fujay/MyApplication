package fra_uas.project.eu.hmi.Games.GameTouch;

import android.graphics.Canvas;

public class LoopThread extends Thread {

    private static final long FPS = 20;

    private ObjectView theView;
    private boolean isRunning = false;

    public LoopThread(ObjectView view) {
        this.theView = view;
    }

    public void setRunning(boolean run) {
        this.isRunning = run;
    }

    @Override
    public void run() {
        long TPS = 1000 / FPS;
        long startTime, sleepTime;
        while(isRunning) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();
            try {
                canvas = theView.getHolder().lockCanvas();
                synchronized (theView.getHolder()) {
                    theView.onDraw(canvas);
                }
            } finally {
                if(canvas != null) {
                    theView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = TPS - (System.currentTimeMillis() - startTime);
            try {
                if(sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
