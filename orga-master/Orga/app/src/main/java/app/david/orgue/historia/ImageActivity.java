package app.david.orgue.historia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");

        setContentView(R.layout.activity_informacio_foto);

        ImageView descripcio = findViewById(R.id.textJocComplet);
        int id_foto = Integer.parseInt(getIntent().getStringExtra("id_foto"));

        int image = 0;

        if(id_foto == 1){
            image = R.drawable.barnilles;
        }else if(id_foto == 2){
            image = R.drawable.caixaexpressiu;
        }else if(id_foto == 3){
            image = R.drawable.manuals;
        }else if(id_foto == 4){
            image = R.drawable.manxaorguevalls;
        }else if(id_foto == 5){
            image = R.drawable.pedalexpressio;
        }else if(id_foto == 6){
            image = R.drawable.pedaler;
        }else if(id_foto == 7){
            image = R.drawable.pomsregistres;
        }else if(id_foto == 8){
            image = R.drawable.secrets;
        }else if(id_foto == 9){
            image = R.drawable.tubsinterior;
        }

        descripcio.setImageResource(image);


        Button tancar = findViewById(R.id.tancaInfoJoc);
        tancar.setOnClickListener(view -> finish());
    }

}