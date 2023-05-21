package app.david.orgue.historia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.GestorBDPartida;
import app.david.orgue.MainActivity;
import app.david.orgue.R;
import app.david.orgue.jocs.FinalActivitu;
import app.david.orgue.jocs.jocCinc.JocCincActivity;
import app.david.orgue.jocs.jocDos.JocDosActivity;
import app.david.orgue.jocs.jocNou.JocNouActivity;
import app.david.orgue.jocs.jocQuatre.JocQuatreActivity;
import app.david.orgue.jocs.jocSet.JocSetActivity;
import app.david.orgue.jocs.jocSis.JocSisActivity;
import app.david.orgue.jocs.jocVuit.JocVuitActivity;

public class InfoActivity extends AppCompatActivity {
    private GestorBDPartida gbdRest = new GestorBDPartida(this);

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");

        setContentView(R.layout.activity_acierto);


        String idRonda = getIntent().getStringExtra("id_ronda");
        String numOpcions = getIntent().getStringExtra("total_opcions");

        Cursor ronda = gbdRest.getRonda(idRonda);
        @SuppressLint("Range") int fallos = ronda.getColumnIndex("fallos") != -1 ? ronda.getInt(ronda.getColumnIndex("fallos")) : 0;

        TextView puntuacio = findViewById(R.id.puntuacio);
        int totalPuntuacio = Integer.parseInt(numOpcions) - fallos;
        @SuppressLint("Range") Integer percentatge = ronda.getColumnIndex("porcentageFallos") != -1 ? ronda.getInt(ronda.getColumnIndex("porcentageFallos")) : null;
        TextView veureResultats = findViewById(R.id.verResultados);
        veureResultats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (percentatge != null) {
            if(percentatge < 0){
                percentatge = percentatge * -1;
            }
            ImageView estrella1 = findViewById(R.id.estrella1);
            ImageView estrella2 = findViewById(R.id.estrella2);
            ImageView estrella3 = findViewById(R.id.estrella3);

            if(percentatge >= 80 && percentatge <= 100 ){
                estrella1.setColorFilter(getColor(R.color.yellow));
                estrella2.setColorFilter(getColor(R.color.yellow));
                estrella3.setColorFilter(getColor(R.color.yellow));
            }else if(percentatge > 35 && percentatge < 80 ){
                estrella1.setColorFilter(getColor(R.color.yellow));
                estrella2.setColorFilter(getColor(R.color.yellow));
            }else if(percentatge > 0 && percentatge <= 35 ){
                estrella1.setColorFilter(getColor(R.color.yellow));
            }
        }

        if(fallos > Integer.parseInt(numOpcions)){
            totalPuntuacio = 0;
        }
        puntuacio.setText(puntuacio.getText().toString() + totalPuntuacio + " de " + numOpcions);

        Button buttonSeguent = findViewById(R.id.siguienteAcierto);
        buttonSeguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoPage;
                if(idRonda.equals("1")){
                    infoPage = new Intent(InfoActivity.this, JocDosActivity.class);
                }else if(idRonda.equals("3")){
                    infoPage = new Intent(InfoActivity.this, JocQuatreActivity.class);
                }else if(idRonda.equals("4")){
                    infoPage = new Intent(InfoActivity.this, JocCincActivity.class);
                }else if(idRonda.equals("5")){
                    infoPage = new Intent(InfoActivity.this, JocSisActivity.class);
                }else if(idRonda.equals("6")){
                    infoPage = new Intent(InfoActivity.this, JocSetActivity.class);
                }else if(idRonda.equals("7")){
                    infoPage = new Intent(InfoActivity.this, JocVuitActivity.class);
                }else if(idRonda.equals("8")){
                    infoPage = new Intent(InfoActivity.this, JocNouActivity.class);
                }else if(idRonda.equals("2")){
                    infoPage = new Intent(InfoActivity.this, FinalActivitu.class);
                }else{
                    infoPage = new Intent(InfoActivity.this, MainActivity.class);
                }
                startActivity(infoPage);
            }
        });



        // TODO: Add your code here
    }
}
