package app.david.orgue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

import app.david.orgue.R;

public class Agraiments extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private String ultimaRonda = "";
    private AudioManager mAudioManager;
    MediaPlayer mp;


    @SuppressLint({"ClickableViewAccessibility", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_agradecimientos);

        ShapeableImageView btnRedirect = findViewById(R.id.iv_avatar);
        TextView text = findViewById(R.id.textJaume);
        TextView text1 = findViewById(R.id.textJaumeUrl);

        btnRedirect.setOnClickListener(longClickListenerJaume);
        text.setOnClickListener(longClickListenerJaume);
        text1.setOnClickListener(longClickListenerJaume);

        ShapeableImageView btnRedirect1 = findViewById(R.id.iv_avatarInnova);
        TextView text2 = findViewById(R.id.textInnova);
        TextView text3 = findViewById(R.id.textInnovaUrl);

        btnRedirect1.setOnClickListener(longClickListenerInnova);
        text2.setOnClickListener(longClickListenerInnova);
        text3.setOnClickListener(longClickListenerInnova);

        ShapeableImageView btnRedirect2 = findViewById(R.id.iv_avatarOrga);
        TextView text4 = findViewById(R.id.textOrga);
        TextView text5 = findViewById(R.id.textOrgaUrl);

        btnRedirect2.setOnClickListener(longClickListenerOrgue);
        text4.setOnClickListener(longClickListenerOrgue);
        text5.setOnClickListener(longClickListenerOrgue);

        ShapeableImageView btnRedirect3 = findViewById(R.id.iv_avatarTarragona);
        TextView text6 = findViewById(R.id.textTarragona);
        TextView text7 = findViewById(R.id.textTarragonaUrl);

        btnRedirect3.setOnClickListener(longClickListenerDipta);
        text6.setOnClickListener(longClickListenerDipta);
        text7.setOnClickListener(longClickListenerDipta);

        ShapeableImageView btnRedirect4 = findViewById(R.id.iv_avatarAjuntament);
        TextView text8 = findViewById(R.id.textAjuntament);
        TextView text9 = findViewById(R.id.textAjuntamentUrl);

        btnRedirect4.setOnClickListener(longClickListenerValls);
        text8.setOnClickListener(longClickListenerValls);
        text9.setOnClickListener(longClickListenerValls);

        ShapeableImageView btnRedirect5 = findViewById(R.id.iv_avatarEducatiu);
        TextView text10 = findViewById(R.id.textEducatiu);
        TextView text11 = findViewById(R.id.textEducatiuUrl);

        btnRedirect5.setOnClickListener(longClickListenerEducatiu);
        text10.setOnClickListener(longClickListenerEducatiu);
        text11.setOnClickListener(longClickListenerEducatiu);



    }
    private View.OnClickListener longClickListenerJaume = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://www.institutjaumehuguet.cat/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener longClickListenerInnova = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://projectes.xtec.cat/impulsfp/innovafp/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener longClickListenerOrgue = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://orguevalls.cat/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener longClickListenerDipta = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://www.dipta.cat/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener longClickListenerValls = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://www.valls.cat/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };

    private View.OnClickListener longClickListenerEducatiu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://serveiseducatius.xtec.cat/altcamp/"; // La URL a la que deseas redirigir

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    };



    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}