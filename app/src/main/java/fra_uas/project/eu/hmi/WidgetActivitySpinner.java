package fra_uas.project.eu.hmi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Switch;

public class WidgetActivitySpinner extends Activity {

	private static final String TAG = WidgetActivitySpinner.class.getSimpleName();

	private ViewGroup.LayoutParams lParams;
	private FrameLayout fLayout;
	private Spinner sText;
	private Switch switchLittle;
	private Button button;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_layout_spinner);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowCustomEnabled(true);

        lParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        fLayout = (FrameLayout) findViewById(R.id.frame);
		sText = (Spinner) findViewById(R.id.spinnertext);
		switchLittle = new Switch(this);
		switchLittle.setOnCheckedChangeListener(onCheckedChangeListener);
		getActionBar().setCustomView(switchLittle);

        button = (Button) findViewById(R.id.buttonapply);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String widgetName = String.valueOf(sText.getSelectedItem());
				try {
					// Objekt instantiieren
					Class c = Class.forName(widgetName);
					Object o = c.getDeclaredConstructor(Context.class).newInstance(WidgetActivitySpinner.this);
					if (o instanceof View) {
						// alte Views löschen und neue hinzufügen
						fLayout.removeAllViews();
						fLayout.addView((View) o, lParams);
						fLayout.forceLayout();
					}
				} catch (Throwable e) {
					Log.e(TAG, "Fehler beim Instantiieren von " + widgetName, e);
				}
			}
		});
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
                if(buttonView == switchLittle) {
					startActivity(new Intent(WidgetActivitySpinner.this, WidgetActivity.class));
                }
			}
		}
	};
}