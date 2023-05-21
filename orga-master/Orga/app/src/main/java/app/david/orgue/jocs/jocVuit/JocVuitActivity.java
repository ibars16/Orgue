package app.david.orgue.jocs.jocVuit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.Constantes;
import app.david.orgue.historia.InformacioJocActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;

public class JocVuitActivity extends AppCompatActivity {

    public static final String RONDA_UN_VUIT = "app.david.orgue.jocs.jocVuit.JocVuitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_vuit);

        Button buttonSeguent = findViewById(R.id.siguiente);
        buttonSeguent.setOnClickListener(v -> {
            Intent infoPage = new Intent(JocVuitActivity.this, JocVuitUnActivity.class);
            startActivity(infoPage);
        });

        Button butoInformacio = findViewById(R.id.infoJoc8);
        butoInformacio.setOnClickListener(v -> {
            Intent infoPage = new Intent(JocVuitActivity.this, InformacioJocActivity.class);
            infoPage.putExtra("id_informacio", "6");
            startActivity(infoPage);
        });
    }



    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_UN_VUIT, Constantes.FILEROUNDNAME);

        Intent main = new Intent(JocVuitActivity.this, MainActivity.class);
        startActivity(main);
    }
}
