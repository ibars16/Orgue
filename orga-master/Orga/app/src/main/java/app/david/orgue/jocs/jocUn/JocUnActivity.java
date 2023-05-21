package app.david.orgue.jocs.jocUn;

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
import app.david.orgue.historia.InformacioJocActivity;
//import com.example.orga.historia.InfoActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.MarcarImatgeService;
import app.david.orgue.jocs.jocDos.JocDosActivity;


public class JocUnActivity extends AppCompatActivity {
    private static final String CUANDRANT_CORRECTE_RONDA_UN = "4";
    private static final String RONDA_NUMERO_UN = "1";
    private static final int NUM_OPCIONS_JOC_UN = 6;
    public static final String RONDA_UN_NOM = "app.david.orgue.jocs.jocUn.JocUnActivity";
    public MarcarImatgeService marcarImatge;
    public TextView fallos;
    public ImageView imageGame;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_inici);

        GestorBDPartida gbdRest = new GestorBDPartida(this);
        Context context = this;
        int fallosTotal = gbdRest.createRonda(RONDA_NUMERO_UN, NUM_OPCIONS_JOC_UN);

        imageGame = findViewById(R.id.imagen2Joc1);
        fallos = findViewById(R.id.fallosJoc1);
        if(-1 != fallosTotal){
            fallos.setText("Errades: " + String.valueOf(fallosTotal));
        }else{
            fallosTotal = 0;
        }

        marcarImatge = new MarcarImatgeService(
                gbdRest,
        CUANDRANT_CORRECTE_RONDA_UN,
        RONDA_NUMERO_UN,
        fallos, fallosTotal,
        NUM_OPCIONS_JOC_UN
        );

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

                    Constantes.writeFile(context, RONDA_NUMERO_UN, Constantes.FILEPARTIDAACBADA);

                    final Intent seguentJoc = new Intent(JocUnActivity.this, InfoActivity.class);
                    seguentJoc.putExtra("id_ronda", RONDA_NUMERO_UN);
                    seguentJoc.putExtra("total_opcions", Integer.toString(NUM_OPCIONS_JOC_UN));
                    startActivity(seguentJoc);
                }else{
                    soundPool1.play(idEfecteBotoFallo, volumenFloat, volumenFloat, 1, 0, 1);
                }

                return true;
            }
        });

        final Button butoInformacio = findViewById(R.id.infoJoc1);
        butoInformacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent infoPage = new Intent(JocUnActivity.this, InformacioJocActivity.class);
                infoPage.putExtra("id_informacio", "0");
                startActivity(infoPage);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_NUMERO_UN)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocDos.JocDosActivity", Constantes.FILEROUNDNAME);

            imageGame.setOnTouchListener(null);
            Button seguent = findViewById(R.id.seguent);
            seguent.setVisibility(View.VISIBLE);
            seguent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent main = new Intent(JocUnActivity.this, JocDosActivity.class);
                    startActivity(main);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        String rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA);
        if(rondaAcabada.equals(RONDA_NUMERO_UN)){
            Constantes.writeFile(this, "app.david.orgue.jocs.jocDos.JocDosActivity", Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_UN_NOM, Constantes.FILEROUNDNAME);
        }

        final Intent main = new Intent(JocUnActivity.this, MainActivity.class);
        startActivity(main);
    }
}