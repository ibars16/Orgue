package app.david.orgue.preferencias;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import app.david.orgue.R;

public class Buto extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_botones, container, false);
        ImageButton myImageButton = view.findViewById(R.id.imageButtonAjustes);
        ImageButton myImageMusic = view.findViewById(R.id.imageButtonMusica);

        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PreferenciasActivity.class);
                startActivity(i);

            }
        });

        myImageMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MusicaActivity.class);
                startActivity(i);

            }
        });

        return view;
    }

}
