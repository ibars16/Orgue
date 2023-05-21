package app.david.orgue.jocs.jocVuit;

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
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.jocNou.JocNouActivity;

public class JocVuitUnActivity extends AppCompatActivity {

    private GestorBDPartida gbdRest = new GestorBDPartida(this);
    private static final String RESPUESTA_VUIT_CORRECTA_ID = "L’orguener construeix i l’organista toca";
    private static final String RONDA_NUMERO_VUIT = "8";
    private static final int NUM_OPCIONS_JOC_VUIT = 4;
    public static final String RONDA_UN_VUIT_UN = "app.david.orgue.jocs.jocVuit.JocVuitUnActivity";

    private RadioGroup radioGroup;
    private int fallosTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_vuit_un);
        Context context = this;

        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBotoAcert = soundPool1.load(this, R.raw.act, 0);
        int idEfecteBotoFallo = soundPool1.load(this, R.raw.efecte_boto, 0);

        fallosTotal =  gbdRest.createRonda(RONDA_NUMERO_VUIT, NUM_OPCIONS_JOC_VUIT);
        TextView fallos = findViewById(R.id.fallosJoc7);
        if(-1 != fallosTotal) {
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }

        final int[] numFallos = {0};
        radioGroup = findViewById(R.id.radioJoc8);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);

                String volumen = Constantes.readFile(context, Constantes.FILESOUND);
                float volumenFloat = Constantes.parseFileSouns(volumen);

                if (checkedRadioButton.getText().equals(RESPUESTA_VUIT_CORRECTA_ID)) {
                    checkedRadioButton.setTextColor(Color.GREEN);
                    gbdRest.updateRonda(RONDA_NUMERO_VUIT, true, NUM_OPCIONS_JOC_VUIT, "drag");

                    soundPool1.play(idEfecteBotoAcert, volumenFloat, volumenFloat, 1, 0, 1);

                    Constantes.writeFile(context, RONDA_UN_VUIT_UN, Constantes.FILEPARTIDAACBADA);

                    Intent infoPage = new Intent(JocVuitUnActivity.this, InfoActivity.class);
                    infoPage.putExtra("id_ronda", RONDA_NUMERO_VUIT);
                    infoPage.putExtra("total_opcions", Integer.toString(NUM_OPCIONS_JOC_VUIT));
                    startActivity(infoPage);
                } else {
                    checkedRadioButton.setTextColor(Color.RED);

                    soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);

                    numFallos[0]++;
                    fallosTotal = fallosTotal + 1;
                    fallos.setText("Errades: " + (fallosTotal));
                    gbdRest.updateRonda(RONDA_NUMERO_VUIT, false, NUM_OPCIONS_JOC_VUIT, null);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_UN_VUIT_UN)){
            Constantes.writeFile(this,  "app.david.orgue.jocs.jocNou.JocNouActivity", Constantes.FILEROUNDNAME);

            radioGroup.setOnCheckedChangeListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocVuitUnActivity.this, JocNouActivity.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_UN_VUIT_UN)){
            Constantes.writeFile(this,  "app.david.orgue.jocs.jocNou.JocNouActivity", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_UN_VUIT_UN, Constantes.FILEROUNDNAME);
        }

        Intent main = new Intent(JocVuitUnActivity.this, MainActivity.class);
        startActivity(main);
    }
}
