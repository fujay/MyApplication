package fra_uas.project.eu.hmi.Layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fra_uas.project.eu.hmi.R;

public class ListViewAdapter extends ArrayAdapter<String> {

    public ListViewAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ListViewAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_element_graphic, parent, false);
        String[] values = context.getResources().getStringArray(R.array.operatingsystems);
        TextView text = (TextView) view.findViewById(R.id.listLabelOS);
        ImageView image = (ImageView) view.findViewById(R.id.imageviewOperating);

        text.setText(values[position]);
        if(values[position].equals("Windows")) {
            image.setImageResource(R.drawable.windows);
        } else if(values[position].equals("Mac OS")) {
            image.setImageResource(R.drawable.macos);
        } else if(values[position].equals("Linux")) {
            image.setImageResource(R.drawable.linux);
        }

        return view;//super.getView(position, convertView, parent);
    }
}
