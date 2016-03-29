package fra_uas.project.eu.hmi.InputControls;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import fra_uas.project.eu.hmi.R;

public class RadiobuttonActivity extends Activity {

    private RadioGroup rgSound, rgTeilnahme;
    private RadioButton rbSound, rbTeilnahme;
    private TextView tvSound, tvTeilnahme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobutton_layout);

        tvSound = (TextView) findViewById(R.id.textviewSound);
        tvTeilnahme = (TextView) findViewById(R.id.textviewAttending);

        rgSound = (RadioGroup) findViewById(R.id.radiogroupSound);
        rgSound.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbSound = (RadioButton) findViewById(checkedId);
                tvSound.setText(rbSound.getText());
            }
        });
        rgTeilnahme = (RadioGroup) findViewById(R.id.radiogroupAttending);
        rgTeilnahme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobuttonYes:
                        rbTeilnahme = (RadioButton) findViewById(checkedId);
                        tvTeilnahme.setText(rbTeilnahme.getText());
                        break;
                    case R.id.radiobuttonMaybe:
                        rbTeilnahme = (RadioButton) findViewById(checkedId);
                        tvTeilnahme.setText(rbTeilnahme.getText());
                        break;
                    case R.id.radiobuttonNo:
                        rbTeilnahme = (RadioButton) findViewById(checkedId);
                        tvTeilnahme.setText(rbTeilnahme.getText());
                        break;
                }
            }
        });
    }
}
