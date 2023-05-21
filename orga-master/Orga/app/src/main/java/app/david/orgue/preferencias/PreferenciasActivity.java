package app.david.orgue.preferencias;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.MyApplication;
import app.david.orgue.R;

public class PreferenciasActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        //Eliminar bordes que genera la activity que extend del tema de dialog
        setContentView(R.layout.activity_preferences);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);

        setTitle(""); // Esta línea quita el título de la actividad

        String valorSo = Constantes.readFile(this, Constantes.FILESOUND);
        String valorMusica = Constantes.readFile(this, Constantes.FILEMUSIC);

        if (valorSo.equals("")) {
            valorSo = "50.0";
        }

        if (valorMusica.equals("")) {
            valorMusica = "50.0";
        }

        SeekBar volumenSo = findViewById(R.id.medidorSo);
        volumenSo.setProgress((int) Float.parseFloat(valorSo));

        SeekBar volumenMusica = findViewById(R.id.medidorMusica);
        volumenMusica.setProgress((int) Float.parseFloat(valorMusica));

        Button guardar = findViewById(R.id.guardarPreferencias);
        Button sortir = findViewById(R.id.salirPreferencias);

        guardar.setOnClickListener(v -> {
            double volumenSoFormated = volumenSo.getProgress();
            Constantes.writeFile(this, String.valueOf(volumenSoFormated), Constantes.FILESOUND);

            double volumenMusicaFormated = volumenMusica.getProgress();
            Constantes.writeFile(this, String.valueOf(volumenMusicaFormated), Constantes.FILEMUSIC);

            String volumenMusicaMedia = Constantes.readFile(this, Constantes.FILEMUSIC);
            float volumenFloatMusica = Constantes.parseFileSouns(volumenMusicaMedia);
            MyApplication myApplication = (MyApplication) getApplicationContext();
            Toast toast1 = Toast.makeText(getApplicationContext(), "Volum guardat", Toast.LENGTH_SHORT);
            toast1.show();
            if (myApplication != null && myApplication.getMediaPlayer() != null) {
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);
            }
        });

        sortir.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}
