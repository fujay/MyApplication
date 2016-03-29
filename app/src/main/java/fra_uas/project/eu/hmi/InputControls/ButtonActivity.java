package fra_uas.project.eu.hmi.InputControls;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fra_uas.project.eu.hmi.R;

public class ButtonActivity extends Activity {

    private Button ivButtonRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);

        ivButtonRotate = (Button) findViewById(R.id.buttonButtonRotate);
        ivButtonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivButtonRotate.setRotation(ivButtonRotate.getRotation() + 1);
            }
        });
    }

    public void toastMessage(View view) {
        switch (view.getId()) {
            case R.id.buttonButton:
                Toast.makeText(this, R.string.button, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonImage:
                Toast.makeText(getApplicationContext(), R.string.imagebutton, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonButtonIcon:
                Toast.makeText(this, R.string.button_icon, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonBorderless:
                Toast.makeText(getApplicationContext(), R.string.button_borderless, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonButtonXML:
                Toast.makeText(this, R.string.button_custom_xml, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonButtonPNG:
                Toast.makeText(getApplicationContext(), R.string.button_custom_png, Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonButtonRotate:
                Toast.makeText(this, R.string.button_rotate, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
