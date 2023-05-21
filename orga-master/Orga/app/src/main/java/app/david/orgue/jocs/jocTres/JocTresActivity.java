package app.david.orgue.jocs.jocTres;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.GestorBDPartida;
import app.david.orgue.historia.InfoActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.MarcarImatgeService;
import app.david.orgue.jocs.jocQuatre.JocQuatreActivity;

public class JocTresActivity extends AppCompatActivity {

    private static final String CUANDRANT_CORRECTE_RONDA_DOS = "3";
    private static final String RONDA_NUMERO_TRES = "3";
    private static final int NUM_OPCIONS_JOC_DOS = 6;
    private static final String RONDA_TRES_NOM = "app.david.orgue.jocs.jocTres.JocTresActivity";

    private ImageView imageGame;
    private GestorBDPartida gbdRest;
    private MarcarImatgeService marcarImatge;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_tres);

        Context context = this;

        gbdRest = new GestorBDPartida(this);
        int fallosTotal = gbdRest.createRonda(RONDA_NUMERO_TRES, NUM_OPCIONS_JOC_DOS);

        imageGame = findViewById(R.id.imagen2Joc3);
        TextView fallos = findViewById(R.id.fallosJoc3);
        if(-1 != fallosTotal){
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }
        marcarImatge = new MarcarImatgeService(gbdRest, CUANDRANT_CORRECTE_RONDA_DOS, RONDA_NUMERO_TRES, fallos, fallosTotal,NUM_OPCIONS_JOC_DOS);

        SoundPool soundPool1 = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);
        int idEfecteBotoAcert = soundPool1.load(this, R.raw.act, 0);
        int idEfecteBotoFallo = soundPool1.load(this, R.raw.efecte_boto, 0);

        imageGame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                marcarImatge.marcarImatge(imageGame, event, v);
                String volumen = Constantes.readFile(context, Constantes.FILESOUND);
                float volumenFloat = Constantes.parseFileSouns(volumen);

                if (marcarImatge.getOpcioCorrecte()) {
                    soundPool1.play(idEfecteBotoAcert, volumenFloat, volumenFloat, 1, 0, 1);

                    Constantes.writeFile(context, RONDA_TRES_NOM, Constantes.FILEPARTIDAACBADA);

                    Intent infoPage = new Intent(JocTresActivity.this, InfoActivity.class);
                    infoPage.putExtra("id_ronda", RONDA_NUMERO_TRES);
                    infoPage.putExtra("total_opcions", String.valueOf(NUM_OPCIONS_JOC_DOS));
                    startActivity(infoPage);
                }else{
                    soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);
                }

                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_TRES_NOM)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocQuatre.JocQuatreActivity", Constantes.FILEROUNDNAME);

            imageGame.setOnTouchListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocTresActivity.this, JocQuatreActivity.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_TRES_NOM)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocQuatre.JocQuatreActivity", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_TRES_NOM, Constantes.FILEROUNDNAME);
        }

        Intent main = new Intent(JocTresActivity.this, MainActivity.class);
        startActivity(main);
    }
}
