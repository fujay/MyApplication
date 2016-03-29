package fra_uas.project.eu.hmi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.ViewFlipper;

public class WidgetActivity extends Activity {

    private ImageView imageView;
    private Chronometer chronometer;
	private WebView webview;
	private Switch switchFull;
	private Button bStart, bStop, bChange;
    private ViewFlipper viewFlipper;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_layout);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowCustomEnabled(true);

		switchFull = new Switch(this);
		switchFull.setOnCheckedChangeListener(onCheckedChangeListener);
        getActionBar().setCustomView(switchFull);
        imageView = (ImageView) findViewById(R.id.ImageViewChange);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        bStart = (Button) findViewById(R.id.start_button);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });
        bStop = (Button) findViewById(R.id.stop_button);
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });
        bChange = (Button) findViewById(R.id.change_button);
        bChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = (Integer) imageView.getTag();
                integer = integer == null ? 0 : integer;
                switch (integer) {
                    case R.drawable.android:
                        imageView.setImageResource(R.drawable.androidfl);
                        imageView.setTag(R.drawable.androidfl);
                        break;
                    case R.drawable.androidfl:
                        imageView.setImageResource(R.drawable.androidplatform);
                        imageView.setTag(R.drawable.androidplatform);
                        break;
                    default: //R.drawable.androidplatform:
                        imageView.setImageResource(R.drawable.android);
                        imageView.setTag(R.drawable.android);
                        break;
                }
            }
        });
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(500);
        viewFlipper.startFlipping();
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
		webview = (WebView) findViewById(R.id.webView);
		webview.loadUrl("https://www.google.de");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if(isChecked) {
                if(buttonView == switchFull) {
                    startActivity(new Intent(WidgetActivity.this, WidgetActivitySpinner.class));
                }
			}
		}
	};
}