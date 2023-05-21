package app.david.orgue.jocs.jocQuatre;

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
//import com.example.orga.historia.InfoActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.jocCinc.JocCincActivity;

public class JocQuatreUnActivity extends AppCompatActivity {
    private GestorBDPartida gbdRest = new GestorBDPartida(this);

    private static final String RESPUESTA_QUATRE_CORRECTA_ID = "Els castells i el campanar";
    private static final String RONDA_NUMERO_QUATRE = "4";
    private static final int NUM_OPCIONS_JOC_QUATRE = 3;
    private static final String RONDA_QUATRE_UN_NOM = "app.david.orgue.jocs.jocQuatre.JocQuatreUnActivity";
    private RadioGroup radioGroup;
    private int fallosTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_cuatre_un);


        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBotoAcert = soundPool1.load(this, R.raw.act, 0);
        int idEfecteBotoFallo = soundPool1.load(this, R.raw.efecte_boto, 0);

        fallosTotal = gbdRest.createRonda(RONDA_NUMERO_QUATRE, NUM_OPCIONS_JOC_QUATRE);
        Context context = this;
        TextView fallos = findViewById(R.id.fallosJoc4);

        if(-1 != fallosTotal) {
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }

        final int[] numFallos = {0};

        radioGroup = findViewById(R.id.radioJoc4);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);

                String volumen = Constantes.readFile(context, Constantes.FILESOUND);
                float volumenFloat = Constantes.parseFileSouns(volumen);

                if (checkedRadioButton.getText().equals(RESPUESTA_QUATRE_CORRECTA_ID)) {
                    checkedRadioButton.setTextColor(Color.GREEN);
                    gbdRest.updateRonda(RONDA_NUMERO_QUATRE, true, NUM_OPCIONS_JOC_QUATRE, "test");

                    soundPool1.play(idEfecteBotoAcert, volumenFloat, volumenFloat, 1, 0, 1);

                    Constantes.writeFile(context, RONDA_QUATRE_UN_NOM, Constantes.FILEPARTIDAACBADA);

                    Intent infoPage = new Intent(JocQuatreUnActivity.this, InfoActivity.class);
                    infoPage.putExtra("id_ronda", RONDA_NUMERO_QUATRE);
                    infoPage.putExtra("total_opcions", Integer.toString(NUM_OPCIONS_JOC_QUATRE));
                    startActivity(infoPage);
                } else {
                    soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);

                    checkedRadioButton.setTextColor(Color.RED);
                    numFallos[0]++;
                    fallosTotal = fallosTotal + 1;
                    fallos.setText("Errades: " + (fallosTotal));
                    gbdRest.updateRonda(RONDA_NUMERO_QUATRE, false, NUM_OPCIONS_JOC_QUATRE, null);
                }
            }
        });

        Button butoInformacio = findViewById(R.id.infoJoc4);
        butoInformacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoPage = new Intent(JocQuatreUnActivity.this, InformacioJocActivity.class);
                infoPage.putExtra("id_informacio", "3");
                startActivity(infoPage);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_QUATRE_UN_NOM)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocCinc.JocCincActivity", Constantes.FILEROUNDNAME);

            radioGroup.setOnCheckedChangeListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocQuatreUnActivity.this, JocCincActivity.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_QUATRE_UN_NOM)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocCinc.JocCincActivity", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_QUATRE_UN_NOM, Constantes.FILEROUNDNAME);
        }

        Intent main = new Intent(JocQuatreUnActivity.this, MainActivity.class);
        startActivity(main);
    }
}
