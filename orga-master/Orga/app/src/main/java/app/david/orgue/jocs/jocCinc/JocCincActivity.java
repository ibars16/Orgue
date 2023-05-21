package app.david.orgue.jocs.jocCinc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.Constantes;
import app.david.orgue.historia.InformacioJocActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;

public class JocCincActivity extends AppCompatActivity {

    public static final String RONDA_UN_CINC = "app.david.orgue.jocs.jocCinc.JocCincActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_cinc);

        Button buttonSeguent = findViewById(R.id.siguiente);
        buttonSeguent.setOnClickListener(v -> {
        Intent infoPage = new Intent(JocCincActivity.this, JocCincUnActivity.class);
        startActivity(infoPage);
    });

        Button butoInformacio = findViewById(R.id.infoJoc5);
        butoInformacio.setOnClickListener(v -> {
        Intent infoPage = new Intent(JocCincActivity.this, InformacioJocActivity.class);
        infoPage.putExtra("id_informacio", "4");
        startActivity(infoPage);
    });
    }

    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_UN_CINC, Constantes.FILEROUNDNAME);

        Intent main = new Intent(JocCincActivity.this, MainActivity.class);
        startActivity(main);
    }
}
