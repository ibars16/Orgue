package app.david.orgue.historia;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.R;

public class MostrarInformacio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_informacio);

        int idInformacio = Integer.parseInt(getIntent().getStringExtra("id"));

        TextView titulo = findViewById(R.id.tituloMostrarInformacio);
        TextView descripcio = findViewById(R.id.descripcioMostrarInformacio);
        ImageView imagen = findViewById(R.id.imageMostrarInformacio);

        titulo.setText(Constantes.INFORMACIO_ORGA_TITUL[idInformacio]);
        descripcio.setText(Constantes.INFORMACIO_ORGA_DESCRIPCIO[idInformacio]);
        imagen.setImageResource(Constantes.INFORMACIO_ORGA_IMATGE[idInformacio]);
    }
}
