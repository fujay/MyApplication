package fra_uas.project.eu.hmi.Layouts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import fra_uas.project.eu.hmi.R;

public class ListActivity extends Activity {

    private static final int QUESTION_DIALOG = 1;

    private ListView listViewProgramming, listViewOperating;
    private String[] programmingValues, operatingsysValues;
    private TextView textViewInfoProgramming, textViewInfoOperating;
    private ImageButton ibFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        programmingValues = getResources().getStringArray(R.array.programming);
        operatingsysValues = getResources().getStringArray(R.array.operatingsystems);

        final ArrayAdapter adapterProgramming = new ArrayAdapter<String>(this, R.layout.listview_element, programmingValues);
        //final ArrayAdapter adapterOperating = new ArrayAdapter<String>(this, R.layout.listview_element, operatingsysValues);
        final ListViewAdapter adapterOperating = new ListViewAdapter(this, R.layout.listview_element_graphic, R.id.listLabelOS, operatingsysValues);

        textViewInfoProgramming = (TextView) findViewById(R.id.textviewInfoProgramming);
        textViewInfoOperating = (TextView) findViewById(R.id.textviewInfoOperating);
        listViewProgramming = (ListView) findViewById(R.id.listviewProgramming);
        listViewOperating = (ListView) findViewById(R.id.listviewOperating);
        ibFAQ = (ImageButton) findViewById(R.id.buttonQuestion);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });

        listViewProgramming.setAdapter(adapterProgramming);
        listViewProgramming.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) adapterProgramming.getItem(position);
                textViewInfoProgramming.setText(" " + item + " ");
            }
        });
        listViewOperating.setAdapter(adapterOperating);
        listViewOperating.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapterOperating.getItem(position);
                textViewInfoOperating.setText(" " + item + " ");
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.listview);
                builder.setMessage(R.string.faqList);
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
