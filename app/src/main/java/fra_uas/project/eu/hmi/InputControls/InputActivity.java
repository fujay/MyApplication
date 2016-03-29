package fra_uas.project.eu.hmi.InputControls;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fra_uas.project.eu.hmi.R;

public class InputActivity extends Activity implements View.OnClickListener {

    private Button bButton, bTextfield, bCheckbox, bRadiobutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);

        bButton = (Button) findViewById(R.id.buttonButton);
        bTextfield = (Button) findViewById(R.id.buttonEdittext);
        bCheckbox = (Button) findViewById(R.id.buttonCheckbox);
        bRadiobutton = (Button) findViewById(R.id.buttonRadiobutton);

        bButton.setOnClickListener(this);
        bTextfield.setOnClickListener(this);
        bCheckbox.setOnClickListener(this);
        bRadiobutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonButton:
                startActivity(new Intent(InputActivity.this, ButtonActivity.class));
                break;
            case R.id.buttonEdittext:
                startActivity(new Intent(InputActivity.this, EdittextActivity.class));
                break;
            case R.id.buttonCheckbox:
                startActivity(new Intent(InputActivity.this, CheckboxActivity.class));
                break;
            case R.id.buttonRadiobutton:
                startActivity(new Intent(InputActivity.this, RadiobuttonActivity.class));
                break;
        }
    }

}
