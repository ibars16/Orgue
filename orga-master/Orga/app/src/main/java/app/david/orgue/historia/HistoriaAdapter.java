package app.david.orgue.historia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.david.orgue.R;

import java.util.List;

public class HistoriaAdapter extends ArrayAdapter<Historia> {

    public HistoriaAdapter(Context context, List<Historia> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_row,
                    parent,
                    false);
        }

        View view = convertView;

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.nombreIncidencia);
        TextView title = (TextView) convertView.findViewById(R.id.id_incidencia);

        // Lead actual.
        Historia lead = getItem(position);

        // Setup.
        name.setText(lead.getTitul());
        avatar.setImageResource(lead.getImatge());



        return convertView;

    }

}