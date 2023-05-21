package app.david.orgue.jocs.jocSis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.GestorBDPartida;
import app.david.orgue.historia.InfoActivity;
import app.david.orgue.historia.InformacioJocActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.jocSet.JocSetActivity;

public class JocSisActivity extends AppCompatActivity {
    private static final String RESPUESTA_SIS_CORRECTA_ID = "Està suspès en una balconada";
    private static final String RONDA_NUMERO_SIS = "6";
    private static final int NUM_OPCIONS_JOC_SIS = 2;
    public static final String RONDA_UN_SIS = "app.david.orgue.jocs.jocSis.JocSisActivity";

    private GestorBDPartida gbdRest = new GestorBDPartida(this);
    private RadioGroup radioGroup;
    private int fallosTotal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_sissss);

        fallosTotal = gbdRest.createRonda(RONDA_NUMERO_SIS, NUM_OPCIONS_JOC_SIS);
        Context context = this;

        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBotoAcert = soundPool1.load(this, R.raw.act, 0);
        int idEfecteBotoFallo = soundPool1.load(this, R.raw.efecte_boto, 0);

        final TextView fallos = findViewById(R.id.fallosJoc6);
        if(-1 != fallosTotal){
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }

        final int[] numFallos = {0};
        radioGroup = findViewById(R.id.radioJoc9);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton checkedRadioButton = group.findViewById(checkedId);

            String volumen = Constantes.readFile(context, Constantes.FILESOUND);
            float volumenFloat = Constantes.parseFileSouns(volumen);

            if (checkedRadioButton.getText().equals(RESPUESTA_SIS_CORRECTA_ID)) {
                checkedRadioButton.setTextColor(Color.GREEN);
                gbdRest.updateRonda(RONDA_NUMERO_SIS, true, NUM_OPCIONS_JOC_SIS, "test2");

                soundPool1.play(idEfecteBotoAcert, volumenFloat, volumenFloat, 1, 0, 1);

                Constantes.writeFile(context, RONDA_UN_SIS, Constantes.FILEPARTIDAACBADA);

                final Intent infoPage = new Intent(JocSisActivity.this, InfoActivity.class);
                infoPage.putExtra("id_ronda", RONDA_NUMERO_SIS);
                infoPage.putExtra("total_opcions", Integer.toString(NUM_OPCIONS_JOC_SIS));
                startActivity(infoPage);
            } else {
                soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);

                checkedRadioButton.setTextColor(Color.RED);
                numFallos[0]++;
                fallosTotal = fallosTotal + 1;
                fallos.setText("Errades: " + (fallosTotal));
                gbdRest.updateRonda(RONDA_NUMERO_SIS, false, NUM_OPCIONS_JOC_SIS, null);
            }
        });

        final Button butoInformacio = findViewById(R.id.infoJoc6);
        butoInformacio.setOnClickListener(v -> {
            final Intent infoPage = new Intent(JocSisActivity.this, InformacioJocActivity.class);
            infoPage.putExtra("id_informacio", "2");
            startActivity(infoPage);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_UN_SIS)){
            Constantes.writeFile(this,  "app.david.orgue.jocs.jocSet.JocSetActivity", Constantes.FILEROUNDNAME);

            radioGroup.setOnCheckedChangeListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocSisActivity.this, JocSetActivity.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);

        if(rondaAcabada.equals(RONDA_UN_SIS)){
            Constantes.writeFile(this,  "app.david.orgue.jocs.jocSet.JocSetActivity", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_UN_SIS, Constantes.FILEROUNDNAME);
        }

        final Intent main = new Intent(JocSisActivity.this, MainActivity.class);
        startActivity(main);
    }
}
