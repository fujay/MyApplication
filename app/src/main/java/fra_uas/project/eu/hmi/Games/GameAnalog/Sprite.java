package fra_uas.project.eu.hmi.Games.GameAnalog;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

public class Sprite implements View.OnClickListener {

    interface OnRespawnListener {
        void onBurst(Sprite sprite);
    }

    private float x;
    private float y;
    private float size;
    private ImageView view;
    private OnRespawnListener burstListener;

    public Sprite(FrameLayout container, float sizeMax, Random rnd, Drawable drawable, OnRespawnListener listener) {
        size = (0.5f + rnd.nextFloat() / 2) * sizeMax;
        x = rnd.nextFloat() * (container.getWidth() - size) + drawable.getMinimumWidth();
        y = rnd.nextFloat() * (container.getHeight() - size)+ drawable.getMinimumHeight();
        view = new ImageView(container.getContext());
        view.setImageDrawable(drawable);
        burstListener = listener;
        view.setOnClickListener(this);
        container.addView(view);
    }

    public void move(Button bUP, Button bDOWN, Button bLEFT, Button bRIGHT) {
        if(bUP.isPressed()) y -= 2;
        if(bDOWN.isPressed()) y += 2;
        if(bLEFT.isPressed()) x -= 2;
        if(bRIGHT.isPressed()) x += 2;
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = Math.round(size);
        params.height = Math.round(size);
        params.leftMargin = Math.round(x);
        params.topMargin = Math.round(y);
        params.gravity = Gravity.LEFT + Gravity.TOP;
        view.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        x = view.getWidth();
        y = view.getHeight();
    }
}
