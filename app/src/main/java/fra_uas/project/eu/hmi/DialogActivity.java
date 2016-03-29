package fra_uas.project.eu.hmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DialogActivity extends Activity {

    private static final int DIALOG_ALERT = 1;
    private static final int DIALOG_DATEPICKER = 2;
    private static final int DIALOG_TIMEPICKER = 3;
    private static final int QUESTION_DIALOG = 4;
    private static final int TOAST = 5;

    private EditText etToastText;
    private TextView textView;
    private Button buttonDialog, buttonDatePicker, buttonTimePicker;
    private ImageButton ibFAQ, ibToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        etToastText = (EditText) findViewById(R.id.editTextToast);
        textView = (TextView) findViewById(R.id.textviewInfo);
        buttonDialog = (Button) findViewById(R.id.buttonAlertDialog);
        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ALERT);
            }
        });
        buttonDatePicker = (Button) findViewById(R.id.buttonDatePickerDialog);
        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATEPICKER);
            }
        });
        buttonTimePicker = (Button) findViewById(R.id.buttonTimePickerDialog);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_TIMEPICKER);
            }
        });
        ibFAQ = (ImageButton) findViewById(R.id.buttonQuestion);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });
        ibToast = (ImageButton) findViewById(R.id.buttonToast);
        ibToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TOAST);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ALERT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.abc);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(getString(R.string.button_AlertDialog));
                    }
                });
                return builder.create();
            case DIALOG_DATEPICKER:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textView.setText(R.string.button_DatePicker);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                return datePickerDialog;
            case DIALOG_TIMEPICKER:
                Calendar calendarTime = Calendar.getInstance();
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(R.string.button_TimePicker);
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime.get(Calendar.MINUTE), true);
                return timePickerDialog;
            case QUESTION_DIALOG:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqDialog);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case TOAST:
                textView.setText(R.string.button_DatePicker);
                Toast.makeText(this, etToastText.getText(), Toast.LENGTH_LONG).show();
            default:
                return super.onCreateDialog(id);
        }
    }
}
