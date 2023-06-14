package com.example.penguin_project.view.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.penguin_project.R;
import com.example.penguin_project.view.fragment.MenuFragment;

import java.security.Provider;
import java.util.List;
import java.util.Map;

public class BackgroundMusicService extends Service {

    private MediaPlayer mediaPlayer;
    private BroadcastReceiver musicUpdateReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        MenuFragment.soundSettingsSP = getSharedPreferences("sound_setting", Context.MODE_PRIVATE);

        String sound = MenuFragment.soundSettingsSP.getString("sound_setting", "Nope");
        switch (sound) {
            case "Nope":
                mediaPlayer = new MediaPlayer();
                updateMusic("Nope");
                break;
            case "Chill music":
                mediaPlayer = MediaPlayer.create(this, R.raw.chill_music);
                updateMusic("Chill music");
                break;
            case "Fire camp":
                mediaPlayer = MediaPlayer.create(this, R.raw.music_firecamp);
                updateMusic("Fire camp");
                break;
            case "Rain":
                mediaPlayer = MediaPlayer.create(this, R.raw.music_rain);
                updateMusic("Rain");
                break;
            case "Piano":
                mediaPlayer = MediaPlayer.create(this, R.raw.music_piano);
                updateMusic("Piano");
                break;
            case "Guitar":
                mediaPlayer = MediaPlayer.create(this, R.raw.music_guitar);
                updateMusic("Guitar");
                break;
        }


        musicUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String music = intent.getStringExtra("music");
                if (music != null) {
                    updateMusic(music);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.penguin_project.UPDATE_MUSIC");
        registerReceiver(musicUpdateReceiver, filter);
    }


    private void updateMusic(String music) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();

        if (music.equals("Chill music")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.chill_music);
        } else if (music.equals("Nope")) {
            // No action needed since we want silence
            mediaPlayer.stop();
            return;
        } else if (music.equals("Fire camp")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music_firecamp);
        } else if (music.equals("Guitar")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music_guitar);
        } else if (music.equals("Piano")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music_piano);
        } else if (music.equals("Rain")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music_rain);
        }

        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(musicUpdateReceiver);

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}