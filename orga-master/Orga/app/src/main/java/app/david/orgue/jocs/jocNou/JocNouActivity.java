package app.david.orgue.jocs.jocNou;

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
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.historia.InfoActivity;
import app.david.orgue.jocs.FinalActivitu;

public class JocNouActivity extends AppCompatActivity {

    public static final String RONDA_UN_NOU = "app.david.orgue.jocs.jocNou.JocNouActivity";
    private GestorBDPartida gbdRest = new GestorBDPartida(this);
    private static final String RESPUESTA_NOU_CORRECTA_ID = "Més de 40";
    private static final String RONDA_NUMERO_NOU = "2";
    private static final int NUM_OPCIONS_JOC_NOU = 3;

    private RadioGroup radioGroup;
    private int fallosTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_nou);

        Context context = this;

        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBotoAcert = soundPool1.load(this, R.raw.act, 0);
        int idEfecteBotoFallo = soundPool1.load(this, R.raw.efecte_boto, 0);

        fallosTotal = gbdRest.createRonda(RONDA_NUMERO_NOU, NUM_OPCIONS_JOC_NOU);
        TextView fallos = findViewById(R.id.fallosJoc10);
        if(-1 != fallosTotal) {
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }
        final int[] numFallos = {0};
        radioGroup = findViewById(R.id.radioJoc2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);

                String volumen = Constantes.readFile(context, Constantes.FILESOUND);
                float volumenFloat = Constantes.parseFileSouns(volumen);

                if (checkedRadioButton.getText().equals(RESPUESTA_NOU_CORRECTA_ID)) {
                    checkedRadioButton.setTextColor(Color.GREEN);
                    gbdRest.updateRonda(RONDA_NUMERO_NOU, true, NUM_OPCIONS_JOC_NOU, "drag");

                    soundPool1.play(idEfecteBotoAcert, volumenFloat, volumenFloat, 1, 0, 1);

                    Constantes.writeFile(context, RONDA_UN_NOU, Constantes.FILEPARTIDAACBADA);

                    Intent infoPage = new Intent(JocNouActivity.this, InfoActivity.class);
                    infoPage.putExtra("id_ronda", RONDA_NUMERO_NOU);
                    infoPage.putExtra("total_opcions", Integer.toString(NUM_OPCIONS_JOC_NOU));
                    startActivity(infoPage);
                } else {
                    checkedRadioButton.setTextColor(Color.RED);

                    soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);

                    numFallos[0]++;
                    fallosTotal = fallosTotal + 1;
                    fallos.setText("Errades: " + (fallosTotal));
                    gbdRest.updateRonda(RONDA_NUMERO_NOU, false, NUM_OPCIONS_JOC_NOU, null);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_UN_NOU)){
            Constantes.writeFile(this, "app.david.orgue.jocs.FinalActivitu", Constantes.FILEROUNDNAME);

            radioGroup.setOnCheckedChangeListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocNouActivity.this, FinalActivitu.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_UN_NOU)){
            Constantes.writeFile(this, "app.david.orgue.jocs.FinalActivitu", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_UN_NOU, Constantes.FILEROUNDNAME);
        }

        Intent main = new Intent(JocNouActivity.this, MainActivity.class);
        startActivity(main);
    }
}
