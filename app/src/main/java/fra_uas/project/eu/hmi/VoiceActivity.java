package fra_uas.project.eu.hmi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class VoiceActivity extends Activity implements TextToSpeech.OnInitListener {

    private static final int RQ_VOICE_RECOGNITION = 1;
    private static final int RQ_TTS_DATA = 2;

    private final Hashtable<String, Locale> supportedLanguages = new Hashtable<String, Locale>();
    private Button bStartVoice, bStartTTS, bTime;
    private TextView tvVoice;
    private EditText etInput;
    private Spinner spinner;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_layout);

        tts = null;
        spinner = (Spinner) findViewById(R.id.spinnerLanguages);
        etInput = (EditText) findViewById(R.id.edittextInput);
        tvVoice = (TextView) findViewById(R.id.textviewVoice);
        bStartVoice = (Button) findViewById(R.id.buttonVoiceRecognition);
        bStartVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognition();
            }
        });

        bTime = (Button) findViewById(R.id.buttonTime);
        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("EEEE 'den' dd.MMMM 'und' HH:mm 'Uhr'");
                String date = df.format(Calendar.getInstance().getTime());
                tts.speak(date, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        bStartTTS = (Button) findViewById(R.id.buttonTextToSpeech);
        bStartTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etInput.getText().toString();
                String language = (String) spinner.getSelectedItem();
                Locale locale = supportedLanguages.get(language);
                if (locale != null) {
                    tts.setLanguage(locale);
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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
            //etInput.setEnabled(false);
            //bStartTTS.setEnabled(false);
            //bStartTTS.setBackgroundResource(android.R.drawable.stat_notify_call_mute);
            finish();
        }
        String[] languages = Locale.getISOLanguages();
        for (String language : languages) {
            Locale loc = new Locale(language);
            switch (tts.isLanguageAvailable(loc)) {
                case TextToSpeech.LANG_MISSING_DATA:
                case TextToSpeech.LANG_NOT_SUPPORTED:
                    break;
                default:
                    String key = loc.getDisplayLanguage();
                    if (!supportedLanguages.containsKey(key)) {
                        supportedLanguages.put(key, loc);
                    }
                    break;
            }
        }
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_spinner_item, supportedLanguages.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RQ_VOICE_RECOGNITION && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches.size() > 0) {
                tvVoice.setText(matches.get(0));
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

    private void checkTextToSpeech() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, RQ_TTS_DATA);
    }

    private void checkVoiceRecognition() {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if(activities.size() == 0) {
            tvVoice.setText(R.string.noTTS);
            bStartVoice.setEnabled(false);
            bStartVoice.setBackgroundResource(android.R.drawable.stat_notify_call_mute);
        }
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.prompt));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent, RQ_VOICE_RECOGNITION);
    }

}
