package app.david.orgue;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.R;

public class Agraiments extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private String ultimaRonda = "";
    private AudioManager mAudioManager;
    MediaPlayer mp;


    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_agradecimientos);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}