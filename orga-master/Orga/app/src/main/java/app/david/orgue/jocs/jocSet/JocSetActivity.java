package app.david.orgue.jocs.jocSet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.Constantes;
import app.david.orgue.historia.InformacioJocActivity;
import app.david.orgue.MainActivity;
import app.david.orgue.R;

public class JocSetActivity extends AppCompatActivity {

    public static final String RONDA_UN_SET = "app.david.orgue.jocs.jocSet.JocSetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_set);

        Button buttonSeguent = findViewById(R.id.siguiente);
        buttonSeguent.setOnClickListener(v -> {
            Intent infoPage = new Intent(JocSetActivity.this, JocSetUnActivity.class);
            startActivity(infoPage);
        });

        Button butoInformacio = findViewById(R.id.infoJoc7);
        butoInformacio.setOnClickListener(v -> {
            Intent infoPage = new Intent(JocSetActivity.this, InformacioJocActivity.class);
            infoPage.putExtra("id_informacio", "5");
            startActivity(infoPage);
        });
    }

    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_UN_SET, Constantes.FILEROUNDNAME);

        Intent main = new Intent(JocSetActivity.this, MainActivity.class);
        startActivity(main);
    }
}
