package app.david.orgue.jocs.jocDos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.Constantes;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.jocTres.JocTresActivity;

public class JocDosActivity extends AppCompatActivity {
    private static final String RESPUESTA_DOS_CORRECTA_ID = "Més de 40";
    private static final String RONDA_DOS_ID = "2";
    private static final int NUM_OPCIONS_DOS = 3;
    private static final String RONDA_DOS_NOM = "app.david.orgue.jocs.jocDos.JocDosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_dos);
        Button buttonSeguent = findViewById(R.id.siguiente);

        buttonSeguent.setOnClickListener(v -> {
        Intent infoPage = new Intent(JocDosActivity.this, JocTresActivity.class);
        startActivity(infoPage);
    });
    }

    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_DOS_NOM, Constantes.FILEROUNDNAME);

        Intent main = new Intent(JocDosActivity.this, MainActivity.class);
        startActivity(main);
    }
}
