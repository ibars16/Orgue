package app.david.orgue.historia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.Constantes;
import app.david.orgue.R;

public class InformacioJocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");

        setContentView(R.layout.activity_informacio_joc);

        TextView descripcio = findViewById(R.id.textJocComplet);
        int id_informacio = Integer.parseInt(getIntent().getStringExtra("id_informacio"));

        descripcio.setText(Constantes.INFORMACIO_QR[id_informacio]);

        Button tancar = findViewById(R.id.tancaInfoJoc);
        tancar.setOnClickListener(view -> finish());
    }

}
