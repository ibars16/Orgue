package app.david.orgue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.R;

import app.david.orgue.historia.InformacioActivit;
import app.david.orgue.puntuacio.PuntuacioActivity;
import app.david.orgue.jocs.jocUn.JocUnActivity;

public class MainActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private String ultimaRonda = "";
    private AudioManager mAudioManager;
    MediaPlayer mp;


    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_menu);
//        Constantes.writeFile(this, "", Constantes.FILEROUNDNAME);


        ultimaRonda = Constantes.readFile(this, Constantes.FILEROUNDNAME);

        if (ultimaRonda.equals("") || ultimaRonda  == null){
            ultimaRonda = JocUnActivity.RONDA_UN_NOM;
        }

        Button buttonInfo = findViewById(R.id.butonInformacioMenu);
        Button buttonJoc = findViewById(R.id.butonJocMenu);
        Button buttonPuntuacio = findViewById(R.id.butonPuntuacionsMenu);
        Button buttonAgraiments = findViewById(R.id.butonAgraimentsMenu);

        buttonJoc.setOnClickListener(v -> {
            try {
                Class<?> clazz = Class.forName(ultimaRonda);
                Intent jocPage = new Intent(MainActivity.this, clazz);
                startActivity(jocPage);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buttonInfo.setOnClickListener(v -> {
            Intent infoPage = new Intent(MainActivity.this, InformacioActivit.class);
            startActivity(infoPage);
        });

        buttonPuntuacio.setOnClickListener(v -> {
            Intent jocPage = new Intent(MainActivity.this, PuntuacioActivity.class);
            startActivity(jocPage);
        });

        buttonAgraiments.setOnClickListener(v -> {
            Intent agraimentsPage = new Intent(MainActivity.this, Agraiments.class);
            startActivity(agraimentsPage);
        });
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}