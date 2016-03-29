package fra_uas.project.eu.hmi.Games.GameKugel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class BaseGameActivity extends Activity {

    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = getResources().getDisplayMetrics().density;
    }

    protected float scale(float v) {
        return  density * v;
    }

    protected void hideView(int id) {
        findViewById(id).setVisibility(View.GONE);
    }
    protected void showView(int id) {
        findViewById(id).setVisibility(View.VISIBLE);
    }

    protected  void setText(int id, String text) {
        ((TextView)findViewById(id)).setText(text);
    }
}
