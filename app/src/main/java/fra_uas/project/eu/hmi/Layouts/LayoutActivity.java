package fra_uas.project.eu.hmi.Layouts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fra_uas.project.eu.hmi.R;

public class LayoutActivity extends Activity implements View.OnClickListener {

    private static final int QUESTION_DIALOG = 1;

    private Button bLinearLayout, bRelativeLayout, bTableLayout, bListView, bGridLayout, bFrameLayout, bAbsoluteLayout, bSwipeviewLayout, bActionbar, bNavigationDrawer;
    private ImageButton faqButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_layout);

        bLinearLayout = (Button) findViewById(R.id.buttonLinear);
        bRelativeLayout = (Button) findViewById(R.id.buttonRelative);
        bTableLayout = (Button) findViewById(R.id.buttonTable);
        bListView = (Button) findViewById(R.id.buttonList);
        bGridLayout = (Button) findViewById(R.id.buttonGrid);
        bFrameLayout = (Button) findViewById(R.id.buttonFrame);
        bAbsoluteLayout = (Button) findViewById(R.id.buttonAbsolute);
        bSwipeviewLayout = (Button) findViewById(R.id.buttonSwipe);
        bActionbar = (Button) findViewById(R.id.buttonActionbar);
        bNavigationDrawer = (Button) findViewById(R.id.buttonNavigationdrawer);
        faqButton = (ImageButton) findViewById(R.id.buttonQuestion);

        bLinearLayout.setOnClickListener(this);
        bRelativeLayout.setOnClickListener(this);
        bTableLayout.setOnClickListener(this);
        bListView.setOnClickListener(this);
        bGridLayout.setOnClickListener(this);
        bFrameLayout.setOnClickListener(this);
        bAbsoluteLayout.setOnClickListener(this);
        bSwipeviewLayout.setOnClickListener(this);
        bActionbar.setOnClickListener(this);
        bNavigationDrawer.setOnClickListener(this);
        faqButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLinear:
                startActivity(new Intent(LayoutActivity.this, LinearActivity.class));
                break;
            case R.id.buttonRelative:
                startActivity(new Intent(LayoutActivity.this, RelativeActivity.class));
                break;
            case R.id.buttonTable:
                startActivity(new Intent(LayoutActivity.this, TableActivity.class));
                break;
            case R.id.buttonList:
                startActivity(new Intent(LayoutActivity.this, ListActivity.class));
                break;
            case R.id.buttonGrid:
                startActivity(new Intent(LayoutActivity.this, GridActivity.class));
                break;
            case R.id.buttonFrame:
                startActivity(new Intent(LayoutActivity.this, FrameActivity.class));
                break;
            case R.id.buttonAbsolute:
                startActivity(new Intent(LayoutActivity.this, AbsoluteActivity.class));
                break;
            case R.id.buttonSwipe:
                startActivity(new Intent(LayoutActivity.this, SwipeviewActivity.class));
                break;
            case R.id.buttonActionbar:
                startActivity(new Intent(LayoutActivity.this, ActionBarTabsActivity.class));
                break;
            case R.id.buttonNavigationdrawer:
                startActivity(new Intent(LayoutActivity.this, NavigationDrawerActivity.class));
                break;
            case R.id.buttonQuestion:
                showDialog(QUESTION_DIALOG);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqLayout);
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
