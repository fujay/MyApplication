package fra_uas.project.eu.hmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends Activity {

    private static final int QUESTION_DIALOG = 1;
    private static final int QUESTION_CONTEXT = 2;

    private Button bContextMenu;
    private ImageButton ibFAQ, ibContext;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        bContextMenu = (Button) findViewById(R.id.buttonContextMenu);
        registerForContextMenu(bContextMenu);
        ibContext = (ImageButton) findViewById(R.id.buttonQuestionContext);
        ibContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_CONTEXT);
            }
        });
        ibFAQ = (ImageButton) findViewById(R.id.buttonQuestion);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });
        tvInfo = (TextView) findViewById(R.id.textViewInfo);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                tvInfo.setText(item.getTitle());
                return true;
            case R.id.item2:
                tvInfo.setText(item.getTitle());
                return true;
            case R.id.item3:
                tvInfo.setText(item.getTitle());
                return true;
            default:
            return super.onContextItemSelected(item);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqMenu);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case QUESTION_CONTEXT:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqKontextMenu);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                tvInfo.setText(item.getTitle());
                return true;
            case R.id.item2:
                tvInfo.setText(item.getTitle());
                return true;
            case R.id.item3:
                tvInfo.setText(item.getTitle());
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}