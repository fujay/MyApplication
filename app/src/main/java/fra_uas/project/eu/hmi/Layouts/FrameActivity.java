package fra_uas.project.eu.hmi.Layouts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fra_uas.project.eu.hmi.MainActivity;
import fra_uas.project.eu.hmi.R;

public class FrameActivity extends Activity {

    private static final int QUESTION_DIALOG = 1;

    private TextView tvFrameButton;
    private ImageView ivFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        ivFrame = (ImageView) findViewById(R.id.imageviewFrame);
        tvFrameButton = (TextView) findViewById(R.id.textviewFrameButton);
        tvFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivFrame.isShown()) {
                    ivFrame.setVisibility(View.INVISIBLE);
                } else {
                    ivFrame.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemFAQ:
                showDialog(QUESTION_DIALOG);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.framelayout);
                builder.setMessage(R.string.faqFrame);
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
}