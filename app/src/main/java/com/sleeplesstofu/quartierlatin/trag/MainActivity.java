package com.sleeplesstofu.quartierlatin.trag;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import xyz.hanks.library.SmallBang;

/**
 * Created by QuartierLatin on 13/2/2559.
 */
public class MainActivity extends Activity {
    private Button btnSetting, btnLeaderBoard, btnPlayModeSelect, btnDoQuit;
    public static MediaPlayer tragMediaPlayer;

    private TragDatabase tragDatabase;
    private SQLiteDatabase tragSQLDatabase;
    private Cursor csr;
    public static int soundAvailableValue;
    public static int vibrateAvailableValue;

    public static Typeface tragFont;

    public static Animation flipAnimation;

    public static CountDownTimer countDownTimer;

    //Animation Properties
    public static SmallBang bangAnimation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        setWidget();
        setWidgetListener();
        tragMediaPlayer = new MediaPlayer().create(this, R.raw.trag_theme_b_non_stop);
        tragMediaPlayer.setLooping(true);

        doOption();

    }

    private void doOption() {
        tragDatabase = new TragDatabase(this);
        tragSQLDatabase = tragDatabase.getReadableDatabase();
        csr = tragSQLDatabase.rawQuery("SELECT * FROM " + tragDatabase.TABLE_SETTING, null);

        csr.moveToFirst();
        short isMusicOn_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));
        csr.moveToNext();
        short isSoundOn_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));
        csr.moveToNext();
        short isVibrator_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));

        if (isMusicOn_N == 1) {
            MainActivity.tragMediaPlayer.start();
        }

        if (isSoundOn_N == 1) {
            soundAvailableValue = 1;
        }

        if (isVibrator_N == 1) {
            vibrateAvailableValue = 1;
        }
    }

    private void setWidget() {
        tragFont = Typeface.createFromAsset(getAssets(), "fonts/RaiNgan.ttf");
        bangAnimation = SmallBang.attach2Window(this);
        btnPlayModeSelect = (Button) findViewById(R.id.btnPlay);
        btnLeaderBoard = (Button) findViewById(R.id.btnLeaderBoard);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnDoQuit = (Button) findViewById(R.id.btnQuit);
        initAnimation();


        btnPlayModeSelect.setTypeface(tragFont);
        btnLeaderBoard.setTypeface(tragFont);
        btnSetting.setTypeface(tragFont);
        btnDoQuit.setTypeface(tragFont);

    }

    private void initAnimation() {
        YoYo.with(Techniques.RubberBand).delay(1300).duration(250).playOn(btnPlayModeSelect);
        YoYo.with(Techniques.RubberBand).delay(1500).duration(250).playOn(btnLeaderBoard);
        YoYo.with(Techniques.RubberBand).delay(2000).duration(250).playOn(btnSetting);
        YoYo.with(Techniques.Bounce).delay(2500).duration(250).playOn(btnDoQuit);
    }

    private void setWidgetListener() {
        btnPlayModeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                playMenuClick();
                startActivity(new Intent(getApplicationContext(), SelectModeActivity.class));
            }
        });
        btnLeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                playMenuClick();
                startActivity(new Intent(getApplicationContext(), LeaderBoardActivity.class));

            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                playMenuClick();
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));

            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            csr.moveToPosition(0);
            stopTragMedia();
        } catch (Exception e) {

        }
        super.onDestroy();
    }

    // Media Control

    private static void stopTragMedia() {
        try {
            tragMediaPlayer.stop();
            tragMediaPlayer = null;
        } catch (Exception e) {

        }
    }
    // Media Control


    @Override
    protected void onStop() {
//        MainActivity.tragMediaPlayer.pause();
        super.onStop();
    }

    @Override
    protected void onResume() {
//        MainActivity.tragMediaPlayer.start();
        initAnimation();
        super.onResume();
    }

    public void doQuit(View v){
        finish();
    }

//    public void playMenuClick(){
//        if (MainActivity.soundAvailableValue == 1) {
//            SoundPool playMenuClick = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
//            playMenuClick.play(playMenuClick.load(this,R.raw.menu_click_sound,1),1,1,0,0,1);
//        }
//    }
}
