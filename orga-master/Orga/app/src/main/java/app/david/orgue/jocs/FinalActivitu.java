package app.david.orgue.jocs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.GestorBDPartida;
import app.david.orgue.MainActivity;
import app.david.orgue.R;

public class FinalActivitu extends AppCompatActivity {

    private GestorBDPartida gbdRest;
    public static final String RONDA_FINAL_NOM = "app.david.orgue.jocs.FinalActivitu";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        gbdRest = new GestorBDPartida(this);

        String str = gbdRest.getPuntuacioFinal();
        TextView textPuntuacio = findViewById(R.id.puntuacioFinal);
        int fallos = gbdRest.getFallos();
        textPuntuacio.setText(str);

        ImageView estrella1 = findViewById(R.id.estrella1);
        ImageView estrella2 = findViewById(R.id.estrella2);
        ImageView estrella3 = findViewById(R.id.estrella3);

        if (fallos >= 0 && fallos <= 5) {
            estrella1.setColorFilter(Color.YELLOW);
            estrella2.setColorFilter(Color.YELLOW);
            estrella3.setColorFilter(Color.YELLOW);
        } else if (fallos > 5 && fallos < 23) {
            estrella1.setColorFilter(Color.YELLOW);
            estrella2.setColorFilter(Color.YELLOW);
        } else if (fallos > 23 && fallos <= 30) {
            estrella1.setColorFilter(Color.YELLOW);
        }

        Button butonMenu = findViewById(R.id.tornar);

        butonMenu.setOnClickListener(view -> {
            Constantes.writeFile(this, RONDA_FINAL_NOM, Constantes.FILEROUNDNAME);

            Intent infoPage = new Intent(FinalActivitu.this, MainActivity.class);
            infoPage.putExtra("id_pantalla", "15");
            startActivity(infoPage);
        });
    }

    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_FINAL_NOM, Constantes.FILEROUNDNAME);

        final Intent main = new Intent(FinalActivitu.this, MainActivity.class);
        startActivity(main);
    }
}
