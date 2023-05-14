package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.dataBase.ResultsDBOpenHelper;
import com.example.myapplication.model.Results;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import com.example.myapplication.fragment.GameFragment;
import com.example.myapplication.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnMenu;
    private MenuFragment menuFragment;
    private GameFragment gameFragment;
    private boolean inGame;
    private SharedPreferences sharedPreferences;
    private MediaPlayer mediaPlayer;
    private MaterialButton btnCheck;
    private MaterialButton btnRestart;
    private Chronometer chrTime;
    private ResultsDBOpenHelper resultsDBOpenHelper;
    private boolean enableMusic;

    public void startMusic() {
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    public void pauseMusic() {
        if (mediaPlayer != null)
            mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        resultsDBOpenHelper = new ResultsDBOpenHelper(this);
        menuFragment = new MenuFragment();
        setContentView(R.layout.activity_main);

        chrTime = findViewById(R.id.chr_time);
        chrTime.start();
        btnCheck = findViewById(R.id.btn_check);
        mediaPlayer = MediaPlayer.create(this, R.raw.test);
        mediaPlayer.setLooping(true);
        enableMusic = loadEnabledMusic();
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//                                                      CHECK
                if (gameFragment.checkResult()) {
                    Toast.makeText(
                            MainActivity.this,
                            "WIN with time: " + chrTime.getText(),
                            Toast.LENGTH_SHORT).show();
                    chrTime.stop();
                    Results results;
                    String load = loadUsername();
                    String time = chrTime.getText().toString();
                    if (load.equals("no name"))
                        results = new Results(time);
                    else
                        results = new Results(load, time);
                    resultsDBOpenHelper.insert(results);
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Try another variation",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        gameFragment = (GameFragment) fragments.get(0);
        //cnrl + alt + v выделить в переменную
        //cnrl + alt + с выделить в константу
        //cnrl + alt + m вынести фрагмент кода в метод
        inGame = true;


        btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //                                                     MENU
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (inGame) {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.nav_host_fragment_container, menuFragment)
                            .commit();
                    btnMenu.setText("Game");
                } else {
                    fragmentManager
                            .beginTransaction()
                            .remove(menuFragment)
                            .commit();
                    btnMenu.setText("Menu");
                }
                inGame = !inGame;
            }
        });
        btnRestart = findViewById(R.id.btn_restart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//                                                      RESTART
                gameFragment.reset();
                chrTime.stop();
                chrTime.setBase(SystemClock.elapsedRealtime());
                chrTime.start();
            }
        });
    }

    public void initCardFirstPattern() {
        ImageView imageViewBottom, imageViewRight;
        imageViewBottom = findViewById(R.id.iv_bottom);
        imageViewRight = findViewById(R.id.iv_right);
        imageViewRight.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.t));
        imageViewBottom.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.f));
    }

    public String loadUsername() {
        if (sharedPreferences != null)
            return sharedPreferences.getString("user", "no name");
        return "no name";
    }

    public boolean loadEnabledMusic() {
        if (sharedPreferences != null)
            return sharedPreferences.getBoolean("music", true);
        return true;
    }

    public void saveEnableMusic(boolean check) {
        if (sharedPreferences != null)
            sharedPreferences.edit().putBoolean("music", check).commit();
    }

    public void saveUsername(String username) {
        if (sharedPreferences != null)
            sharedPreferences.edit().putString("user", username).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void setEnableMusic(boolean enableMusic) {
        this.enableMusic = enableMusic;
    }

    public boolean isEnableMusic() {
        return enableMusic;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (enableMusic) {
            startMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseMusic();
    }
}