package fra_uas.project.eu.hmi;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class OptischeTaeuschungActivity extends Activity {

    private static final int[] IMAGE_FIELDS = {
            R.drawable.cafe_wall, R.drawable.simultankontrast,
            R.drawable.gestaltgesetze, R.drawable.gradient_optical_illusion,
            R.drawable.grey_square_optical_illusion, R.drawable.hermann_gitter,
            R.drawable.nachbild, R.drawable.necker_wuerfelrp, R.drawable.opt_taeuschung_groesse,
            R.drawable.revolving_circles, R.drawable.straightlines, R.drawable.ebbinghaus_illusion,
            R.drawable.zollner_illusion
    };

    private static int[] IMAGES_TITLE = {
            R.string.cafewall, R.string.simultankontrast,
            R.string.gestaltgesetze, R.string.gradientOptIll,
            R.string.gradientSquareOptIll, R.string.hermanngitter,
            R.string.nachbild, R.string.neckerWuerfel, R.string.optTaeschungGroesse,
            R.string.revolvingCircles, R.string.straightlines, R.string.ebbinghausIll,
            R.string.zollnerillusion
    };

    private static final int CAFE_WALL = 0;
    private static final int SIMULTANKONTRAST = 1;
    private static final int GESTALTGESETZE = 2;
    private static final int GRADIENT_OPTICAL_ILLUSION = 3;
    private static final int GREY_SQUARE_OPTICAL_ILLUSION = 4;
    private static final int HERMANN_GITTER = 5;
    private static final int NACHBILD = 6;
    private static final int NECKER_WUERFEL3D = 7;
    private static final int TAESCHUNG_GROESSE = 8;
    private static final int REVOLVING_CIRCLES = 9;
    private static final int STRAIGHTLINES = 10;
    private static final int EBBING_HAUS_ILLUSION = 11;
    private static final int ZOLLNER_ILLUSION = 12;

    private static final int IMAGE_NUMBER = IMAGE_FIELDS.length;

    private static int CURRENT_INDEX = -1;

    private TextView tvInfo;
    private ImageSwitcher imageSwitcher;
    private ImageButton buttonInfo, buttonNext, buttonPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optische_taeuschung_layout);

        tvInfo = (TextView) findViewById(R.id.opticalInfo);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        buttonInfo = (ImageButton) findViewById(R.id.imagebuttonInfo);
        buttonNext = (ImageButton) findViewById(R.id.imagebuttonNext);
        buttonPrevious = (ImageButton) findViewById(R.id.imagebuttonPrevious);
        Animation slideIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });
        imageSwitcher.setInAnimation(slideIn);
        imageSwitcher.setOutAnimation(slideOut);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(CURRENT_INDEX);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_INDEX++;
                if(CURRENT_INDEX == IMAGE_NUMBER) {
                    CURRENT_INDEX = 0;
                }
                imageSwitcher.setImageResource(IMAGE_FIELDS[CURRENT_INDEX]);
                tvInfo.setText(IMAGES_TITLE[CURRENT_INDEX]);
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CURRENT_INDEX--;
                if(CURRENT_INDEX == -1) {
                    CURRENT_INDEX = IMAGE_NUMBER - 1;
                }
                imageSwitcher.setImageResource(IMAGE_FIELDS[CURRENT_INDEX]);
                tvInfo.setText(IMAGES_TITLE[CURRENT_INDEX]);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder;
        switch (id) {
            case CAFE_WALL:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.cafewallInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case SIMULTANKONTRAST:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.simultankontrastInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case GESTALTGESETZE:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.gestaltgesetzeInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case GRADIENT_OPTICAL_ILLUSION:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.gradientOptIllInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case GREY_SQUARE_OPTICAL_ILLUSION:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.gradientSquareOptIllInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case HERMANN_GITTER:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.hermanngitterInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case NACHBILD:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.nachbildInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case NECKER_WUERFEL3D:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.neckerWuerfelInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case TAESCHUNG_GROESSE:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.optTaeschungGroesseInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case REVOLVING_CIRCLES:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.revolvingCirclesInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case STRAIGHTLINES:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.straightlinesInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case EBBING_HAUS_ILLUSION:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.ebbinghausIllInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                return builder.create();
            case ZOLLNER_ILLUSION:
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.zollnerillusionInfo);
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