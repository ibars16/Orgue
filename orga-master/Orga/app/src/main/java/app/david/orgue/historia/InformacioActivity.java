package app.david.orgue.historia;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import app.david.orgue.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformacioActivity extends AppCompatActivity {

    private static final Map<Integer, String> INFORMACIO_ORGA_TITUL = new HashMap<Integer, String>() {{
        put(0, "L’EDIFICI");
    }};

    private static final Map<Integer, Integer> INFORMACIO_ORGA_DESCRIPCIO = new HashMap<Integer, Integer>() {{
        put(0, R.string.orgaEdifici);
    }};

    private static final Map<Integer, Integer> INFORMACIO_ORGA_IMATGE = new HashMap<Integer, Integer>() {{
        put(0, R.drawable.ic_launcher_background);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacio);

        List<Informacio> list = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : INFORMACIO_ORGA_TITUL.entrySet()) {
        int key = entry.getKey();
        String value = entry.getValue();
        String description = getString(INFORMACIO_ORGA_DESCRIPCIO.get(key));
        int image = INFORMACIO_ORGA_IMATGE.get(key);

        list.add(new Informacio(value, description, image));
    }
    }
}
