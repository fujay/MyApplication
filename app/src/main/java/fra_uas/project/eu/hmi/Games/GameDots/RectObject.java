package fra_uas.project.eu.hmi.Games.GameDots;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class RectObject extends AbstractObjects {

    private Rect rect;

    public RectObject() {
        super();
        this.rect = new Rect();
        this.rect.set(0, 0, randomBetween(MAXSSIZE, MINSSIZE), randomBetween(MAXSSIZE, MINSSIZE));
        this.width = rect.width();
        this.height = rect.height();
        this.paint = new Paint();
        this.paint.setARGB(255, randomBetween(255, 0), randomBetween(255, 0), randomBetween(255, 0));
    }

    public RectObject(Paint paint) {
        super();
        this.rect = new Rect();
        this.rect.set(0, 0, randomBetween(MAXSSIZE, MINSSIZE), randomBetween(MAXSSIZE, MINSSIZE));
        this.width = rect.width();
        this.height = rect.height();
        this.paint = paint;
    }

    @Override
    void onDraw(Canvas canvas, int posLeft, int posTop) {
        rect.offsetTo(posLeft, posTop);
        canvas.drawRect(rect, paint);
    }
}