package fra_uas.project.eu.hmi.Games.GameDots;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CircleObject extends AbstractObjects {

    public CircleObject() {
        super();
        this.width = randomBetween(MAXSSIZE, MINSSIZE);
        this.height = randomBetween(MAXSSIZE, MINSSIZE);
        this.paint = new Paint();
        this.paint.setARGB(255, randomBetween(255, 0), randomBetween(255, 0), randomBetween(255, 0));
    }

    public CircleObject(Paint paint) {
        super();
        this.width = randomBetween(MAXSSIZE, MINSSIZE);
        this.height = randomBetween(MAXSSIZE, MINSSIZE);
        this.paint = paint;
    }

    @Override
    void onDraw(Canvas canvas, int posLeft, int posTop) {
        canvas.drawCircle(posLeft, posTop, width, paint);
    }
}