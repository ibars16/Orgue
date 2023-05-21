package app.david.orgue.historia;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.R;

import java.util.ArrayList;

public class InformacioActivit extends AppCompatActivity {

    private String[] INFORMACIO_ORGA_TITUL = {"L’EDIFICI"};
    private String[] INFORMACIO_ORGA_DESCRIPCIO = {String.valueOf(R.string.orgaEdifici)};
    private int[] INFORMACIO_ORGA_IMATGE = {R.drawable.ic_launcher_background};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_view);

        ArrayList<Historia> historiaList = new ArrayList<>();
        for (int i = 0; i < Constantes.INFORMACIO_ORGA_TITUL.length ; i++){
            historiaList.add(new Historia(Constantes.INFORMACIO_ORGA_TITUL[i], Constantes.INFORMACIO_ORGA_DESCRIPCIO[i], Constantes.INFORMACIO_ORGA_IMATGE[i]));
        }

        ListView incidenciasMostrar = (ListView) findViewById(R.id.list);

        HistoriaAdapter incidenciasAdapter = new HistoriaAdapter(InformacioActivit.this, historiaList);
        incidenciasMostrar.setAdapter(incidenciasAdapter);


        incidenciasMostrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                lanzarModificarEstadoIncidencia(null, position);
            }
        });

    }

    public void lanzarModificarEstadoIncidencia(View view, int id) {
        Intent i = new Intent(this, MostrarInformacio.class);
        i.putExtra("id",String.valueOf(id));
        startActivity(i);
    }
}


