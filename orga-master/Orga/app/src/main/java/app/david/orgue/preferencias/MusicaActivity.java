package app.david.orgue.preferencias;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.david.orgue.Constantes;
import app.david.orgue.MyApplication;
import app.david.orgue.R;

public class MusicaActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private MyApplication myApplication;
    TextView reproductoText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        setContentView(R.layout.activity_music);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);

        String indexLectura1 = Constantes.readFile(this, Constantes.FILEINDEX);
        int indexCanco;

        if(indexLectura1.equals("")){
            indexCanco = 0;
        }else{
            indexCanco = Integer.parseInt(indexLectura1);
        }

        String nombreCanco = Constantes.rawsName[indexCanco];
        reproductoText = findViewById(R.id.nombreCanco);
        reproductoText.setText(nombreCanco);

        myApplication = (MyApplication) getApplicationContext();
        AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

        ImageView resume = findViewById(R.id.resume);
        ImageView next = findViewById(R.id.next);
        ImageView back = findViewById(R.id.back);
        ImageView restart = findViewById(R.id.restart);

        Button sortir = findViewById(R.id.salirMusica);

        if (myApplication != null && myApplication.getMediaPlayer() != null) {
            if (myApplication.getMediaPlayer().isPlaying()) {
                resume.setImageResource(R.drawable.pause);
            } else {
                resume.setImageResource(R.drawable.start);
            }
        } else {
            resume.setImageResource(R.drawable.start);
        }

        resume.setOnClickListener(v -> {
            String volumenMusicaMedia = Constantes.readFile(this, Constantes.FILEMUSIC);
            float volumenFloatMusica = Constantes.parseFileSouns(volumenMusicaMedia);

            myApplication = (MyApplication) getApplicationContext();
            if (myApplication != null && myApplication.getMediaPlayer() != null) {
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);
                if (myApplication.getMediaPlayer().isPlaying()) {
                    myApplication.getMediaPlayer().pause();
                    resume.setImageResource(R.drawable.start);
                } else {
                    resume.setImageResource(R.drawable.pause);
                    myApplication.getMediaPlayer().start();
                }
            } else {
                String indexLectura = Constantes.readFile(this, Constantes.FILEINDEX);
                int index;
                Constantes.writeFile(this, "0", Constantes.FILEINDEX);

                if (indexLectura.equals("") || indexLectura == null) {
                    Constantes.writeFile(this, "0", Constantes.FILEINDEX);
                    index = 0;
                } else {
                    index = Integer.parseInt(indexLectura);
                }

                myApplication.setMediaPlayer(MediaPlayer.create(MusicaActivity.this, Constantes.raws[index]));
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);
                myApplication.getMediaPlayer().start();
                resume.setImageResource(R.drawable.pause);
            }
        });

        next.setOnClickListener(v -> {

            if (myApplication != null && myApplication.getMediaPlayer() != null) {
                String volumenMusicaMedia = Constantes.readFile(this, Constantes.FILEMUSIC);
                float volumenFloatMusica = Constantes.parseFileSouns(volumenMusicaMedia);
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);
                String indexLectura4 = Constantes.readFile(this, Constantes.FILEINDEX);
                myApplication.getMediaPlayer().stop();
                int index1 = Integer.parseInt(indexLectura4) + 1;
                if(index1 >= Constantes.raws.length){
                    index1 = 0;
                }
                String nombreCanco1 = Constantes.rawsName[index1];
                reproductoText.setText(nombreCanco1);

                Constantes.writeFile(this, String.valueOf((index1)), Constantes.FILEINDEX);
                myApplication.setMediaPlayer(MediaPlayer.create(MusicaActivity.this, Constantes.raws[index1]));
                myApplication.getMediaPlayer().start();
            }

        });

        back.setOnClickListener(v -> {
            if (myApplication != null && myApplication.getMediaPlayer() != null) {
                String volumenMusicaMedia = Constantes.readFile(this, Constantes.FILEMUSIC);
                float volumenFloatMusica = Constantes.parseFileSouns(volumenMusicaMedia);
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);

                String indexLectura2 = Constantes.readFile(this, Constantes.FILEINDEX);
                int index2 = Integer.parseInt(indexLectura2) - 1;
                if(index2 < 0){
                    index2 = Constantes.raws.length-1;
                }
                String nombreCanco2 = Constantes.rawsName[index2];
                reproductoText.setText(nombreCanco2);

                myApplication.getMediaPlayer().stop();
                Constantes.writeFile(this, String.valueOf((index2)), Constantes.FILEINDEX);
                myApplication.setMediaPlayer(MediaPlayer.create(MusicaActivity.this, Constantes.raws[index2]));
                myApplication.getMediaPlayer().start();
            }
        });

        restart.setOnClickListener(v -> {
            if (myApplication != null && myApplication.getMediaPlayer() != null) {
                String volumenMusicaMedia = Constantes.readFile(this, Constantes.FILEMUSIC);
                float volumenFloatMusica = Constantes.parseFileSouns(volumenMusicaMedia);
                myApplication.getMediaPlayer().setVolume(volumenFloatMusica, volumenFloatMusica);
                myApplication.getMediaPlayer().seekTo(0);
            }
        });

        sortir.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
