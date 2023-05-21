package app.david.orgue.jocs.jocQuatre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.GestorBDPartida;
import app.david.orgue.MainActivity;
import app.david.orgue.R;

public class JocQuatreActivity extends AppCompatActivity {
    private GestorBDPartida gbdRest = new GestorBDPartida(this);

    public static final String RONDA_QUATRE_NOM = "app.david.orgue.jocs.jocQuatre.JocQuatreActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc_quatre);

        Button buttonSeguent = findViewById(R.id.siguiente);
        buttonSeguent.setOnClickListener(view -> {
        Intent infoPage = new Intent(JocQuatreActivity.this, JocQuatreUnActivity.class);
        startActivity(infoPage);
    });
    }

    @Override
    public void onBackPressed() {
        Constantes.writeFile(this, RONDA_QUATRE_NOM, Constantes.FILEROUNDNAME);

        Intent main = new Intent(JocQuatreActivity.this, MainActivity.class);
        startActivity(main);
    }
}