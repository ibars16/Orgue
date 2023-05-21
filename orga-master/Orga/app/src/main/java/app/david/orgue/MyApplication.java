package app.david.orgue;

import android.app.Application;
import android.media.MediaPlayer;

public class MyApplication extends Application {
    private MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}

