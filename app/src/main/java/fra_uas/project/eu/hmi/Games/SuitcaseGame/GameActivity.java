package fra_uas.project.eu.hmi.Games.SuitcaseGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fra_uas.project.eu.hmi.R;

public class GameActivity extends Activity implements TextToSpeech.OnInitListener {

    private static final int RQ_VOICE_RECOGNITION = 1;
    private static final int RQ_TTS_DATA = 2;
    private static final int QUESTION_DIALOG = 3;

    private TextToSpeech tts;
    private boolean bRunning;
    private int runde, spielerAnzahl, spieler;
    private ImageButton ibFAQ;
    private EditText etSpieler;
    private TextView tvInfo;
    private Button bStartGame, bNextPlayer;
    private ArrayList<String> lKofferObjekte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_koffer_layout);

        tts = null;
        lKofferObjekte = new ArrayList<String>();
        ibFAQ = (ImageButton) findViewById(R.id.buttonQuestion);
        ibFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(QUESTION_DIALOG);
            }
        });

        tvInfo = (TextView) findViewById(R.id.textviewInfo);
        etSpieler = (EditText) findViewById(R.id.edittextSpieler);
        bNextPlayer = (Button) findViewById(R.id.buttonNextPlayer);
        bStartGame = (Button) findViewById(R.id.buttonStartGame);
        bStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sSpieler = etSpieler.getText().toString();
                spielerAnzahl = Integer.parseInt(sSpieler);
                if(spielerAnzahl < 1) {
                    spielerAnzahl = 2;
                }
                bRunning = true;
                starteSpiel();
                bNextPlayer.setText(getString(R.string.nextPlayer, spieler));
            }
        });
        bNextPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognition();
                runde++;
                spieler++;
                bNextPlayer.setText(getString(R.string.nextPlayer, spieler));
                if(spieler > spielerAnzahl) {
                    spieler = 1;
                }
            }
        });

        checkTextToSpeech();
        checkVoiceRecognition();
    }

    @Override
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.SUCCESS) {
            finish();
        }
        tts.setLanguage(Locale.getDefault());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RQ_VOICE_RECOGNITION && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches.size() > 0) {
                if(runde == 1) {
                    lKofferObjekte.addAll(matches);
                } else if((lKofferObjekte.containsAll(matches) == false)) {
                    {
                        bRunning = false;
                        //spielvorbei();
                    }
                }
            }
        }

        if(requestCode == RQ_TTS_DATA) {
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(this, this);
            } else {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
                finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case QUESTION_DIALOG:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.faqKofferGame);
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

    private void starteSpiel() {
        runde = 1;
        spieler = 1;
        bNextPlayer.setVisibility(View.VISIBLE);
        etSpieler.setVisibility(View.GONE);
        bStartGame.setVisibility(View.GONE);
        ibFAQ.setVisibility(View.GONE);
        tvInfo.setText(getString(R.string.runde, runde, spieler));
    }

    private void spielvorbei() {
        bNextPlayer.setVisibility(View.GONE);
        etSpieler.setVisibility(View.VISIBLE);
        bStartGame.setVisibility(View.VISIBLE);
        ibFAQ.setVisibility(View.VISIBLE);
    }

    private void checkTextToSpeech() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, RQ_TTS_DATA);
    }

    private void checkVoiceRecognition() {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if(activities.size() == 0) {
            tvInfo.setText(R.string.noTTS);
            bStartGame.setEnabled(false);
            bStartGame.setBackgroundResource(android.R.drawable.stat_notify_call_mute);
        }
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.kofferAblauf));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, runde + 1);
        startActivityForResult(intent, RQ_VOICE_RECOGNITION);
    }

}
