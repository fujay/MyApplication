package fra_uas.project.eu.hmi.InputControls;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import fra_uas.project.eu.hmi.R;

public class CheckboxActivity extends Activity {

    private static final int QUESTION_DIALOG = 1;

    private ImageButton ibFAQ;
    private Switch switchControl;
    private CheckBox checkboxControl1, checkboxControl2;
    private ToggleButton tb1, tb2;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_layout);

        switchControl = (Switch) findViewById(R.id.switch1);
        checkboxControl1 = (CheckBox) findViewById(R.id.checkboxconnected1);
        checkboxControl2 = (CheckBox) findViewById(R.id.checkboxconnected2);
        tb1 = (ToggleButton) findViewById(R.id.tooglebutton1);
        tb2 = (ToggleButton) findViewById(R.id.tooglebutton2);

        switchControl.setOnCheckedChangeListener(onCheckedChangeListener);
        checkboxControl1.setOnCheckedChangeListener(onCheckedChangeListener);
        checkboxControl2.setOnCheckedChangeListener(onCheckedChangeListener);

        tb1.setOnCheckedChangeListener(onCheckedToggleChangeListener);
        tb2.setOnCheckedChangeListener(onCheckedToggleChangeListener);

        ibFAQ = (ImageButton) findViewById(R.id.buttonQuestion);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinnerPlanets);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switchControl.setChecked(isChecked);
            checkboxControl1.setChecked(isChecked);
            checkboxControl2.setChecked(isChecked);
        }
    };

    private final CompoundButton.OnCheckedChangeListener onCheckedToggleChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                if(buttonView == tb1) {
                    Toast.makeText(getApplicationContext(), R.string.syncContacts, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.syncCalendar, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private final AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqInput);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            default:
                return super.onCreateDialog(id);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox1:
                if(checked) {
                    Toast.makeText(this, R.string.syncContacts, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkbox2:
                if(checked) {
                    Toast.makeText(this, R.string.syncCalendar, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
