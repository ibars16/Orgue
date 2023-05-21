package app.david.orgue.puntuacio;

import static com.google.android.material.color.MaterialColors.getColor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import app.david.orgue.R;
import app.david.orgue.jocs.Ronda;


public class PuntuacioAdapter extends ArrayAdapter<Ronda> {
    String[] types = {"Breu", "Mitjana", "Alta"};
    Spinner typeSpinner;
    private Context mContext;


    public PuntuacioAdapter(Context context, List<Ronda> objects) {
        super(context, 0, objects);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_row_puntuacio,
                    parent,
                    false);
        }

        View view = convertView;

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        ImageView avatar1 = (ImageView) convertView.findViewById(R.id.iv_avatar1);
        ImageView avatar2 = (ImageView) convertView.findViewById(R.id.iv_avatar2);

        TextView title = (TextView) convertView.findViewById(R.id.nombreIncidencia);
        TextView name = (TextView) convertView.findViewById(R.id.id_incidencia);

        // Lead actual.
        Ronda lead = getItem(position);
        int fallos =  lead.getNumOpcions() - lead.getFallos();
        if(fallos < 0){
            fallos = 0;
        }
        // Setup.
        title.setText("Joc " + lead.getIdronda());
        name.setText("Puntuació: " + fallos + "/" + lead.getNumOpcions());

        int percentatge = lead.getResultat();


        if (percentatge < 0) {
            percentatge = percentatge * -1;
        }

        if (percentatge >= 80 && percentatge <= 100) {
            avatar.setColorFilter(Color.YELLOW);
            avatar1.setColorFilter(Color.YELLOW);
            avatar2.setColorFilter(Color.YELLOW);
        } else if (percentatge > 35 && percentatge < 80) {
            avatar.setColorFilter(Color.YELLOW);
            avatar1.setColorFilter(Color.YELLOW);
        } else if (percentatge > 0 && percentatge <= 35) {
            avatar.setColorFilter(Color.YELLOW);
        }

        return convertView;
    }

}