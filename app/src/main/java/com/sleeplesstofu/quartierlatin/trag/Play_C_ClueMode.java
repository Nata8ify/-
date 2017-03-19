package com.sleeplesstofu.quartierlatin.trag;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;

public class Play_C_ClueMode extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cell_a1, btn_cell_a2, btn_cell_a3, btn_cell_b1, btn_cell_b2, btn_cell_b3, btn_cell_c1, btn_cell_c2, btn_cell_c3, btnGenerate;
    private TextView eventNotification;
    private LinearLayout tapSection;

    private static CountDownTimer gameTimer;
    private Handler playingTimeHandler;
    private Runnable playingTimeRunnable;

    private NoneStopModeProperties noneStopB;
    private String[] initialButtonStartup;
    private int scoreEarnned;

    private SQLiteDatabase sqLiteDatabase;
    private TragDatabase tragDatabase;
    private Cursor csr;

    //Animaton Propertes
//    private static SmallBang bangAnimation;

    //Extra Properties
    private boolean isBeatHighScore;
    private SoundPool tragSoundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__b__none_stop_mode);
        noneStopB = new NoneStopModeProperties();

        tragDatabase = new TragDatabase(this);
        sqLiteDatabase = tragDatabase.getReadableDatabase();
        csr = sqLiteDatabase.rawQuery("SELECT " + tragDatabase.COL_TABLE_HIGH_SCORE + " FROM " + tragDatabase.TABLE_NAME + " WHERE score_id = 2", null);
        initialElements();
        elementListener();


        //startUpTragMedia();

        newGame();

    }

    private void startUpTragMedia() {
        if (MainActivity.tragMediaPlayer != null) {
            MainActivity.tragMediaPlayer.stop();
            MainActivity.tragMediaPlayer.release();
            MainActivity.tragMediaPlayer = null;
        }

        MainActivity.tragMediaPlayer = new MediaPlayer().create(this, R.raw.trag_theme_b_non_stop);
        MainActivity.tragMediaPlayer.start();

        MainActivity.tragMediaPlayer.setLooping(true);
    }

    private void stopTragMedia() {
        if (MainActivity.tragMediaPlayer != null) {
            MainActivity.tragMediaPlayer.stop();
            MainActivity.tragMediaPlayer.release();
            MainActivity.tragMediaPlayer = null;
        }

        MainActivity.tragMediaPlayer = new MediaPlayer().create(this, R.raw.trag_theme);
        MainActivity.tragMediaPlayer.start();
        MainActivity.tragMediaPlayer.setLooping(true);
    }

    public void initialElements() {

        btn_cell_a1 = (Button) findViewById(R.id.cell_a1);
        btn_cell_a2 = (Button) findViewById(R.id.cell_a2);
        btn_cell_a3 = (Button) findViewById(R.id.cell_a3);
        btn_cell_b1 = (Button) findViewById(R.id.cell_b1);
        btn_cell_b2 = (Button) findViewById(R.id.cell_b2);
        btn_cell_b3 = (Button) findViewById(R.id.cell_b3);
        btn_cell_c1 = (Button) findViewById(R.id.cell_c1);
        btn_cell_c2 = (Button) findViewById(R.id.cell_c2);
        btn_cell_c3 = (Button) findViewById(R.id.cell_c3);
        btnGenerate = (Button) findViewById(R.id.cell_generate);
        eventNotification = (TextView) findViewById(R.id.event_notification);
        tapSection = (LinearLayout) findViewById(R.id.tap_section);
        btn_cell_a1.setTypeface(MainActivity.tragFont);
        btn_cell_a2.setTypeface(MainActivity.tragFont);
        btn_cell_a3.setTypeface(MainActivity.tragFont);
        btn_cell_b1.setTypeface(MainActivity.tragFont);
        btn_cell_b2.setTypeface(MainActivity.tragFont);
        btn_cell_b3.setTypeface(MainActivity.tragFont);
        btn_cell_c1.setTypeface(MainActivity.tragFont);
        btn_cell_c2.setTypeface(MainActivity.tragFont);
        btn_cell_c3.setTypeface(MainActivity.tragFont);
        btnGenerate.setTypeface(MainActivity.tragFont);

        soundPoolProperties(R.raw.tap_sound);
    }

    private void soundPoolProperties(int raw) {
        tragSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = tragSoundPool.load(getApplicationContext(), raw, 1);
    }

    public void elementListener() {
        btn_cell_a1.setOnClickListener(this);
        btn_cell_a2.setOnClickListener(this);
        btn_cell_a3.setOnClickListener(this);
        btn_cell_b1.setOnClickListener(this);
        btn_cell_b2.setOnClickListener(this);
        btn_cell_b3.setOnClickListener(this);
        btn_cell_c1.setOnClickListener(this);
        btn_cell_c2.setOnClickListener(this);
        btn_cell_c3.setOnClickListener(this);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Techniques tch = Techniques.BounceIn;
        long duration = 0;
        switch (v.getId()) {
            case R.id.cell_a1:
                if (!btn_cell_a1.getText().toString().equals("")) {
                    clickContinue(btn_cell_a1);
                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_a2:
                if (!btn_cell_a2.getText().toString().equals("")) {
                    clickContinue(btn_cell_a2);


                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_a3:
                if (!btn_cell_a3.getText().toString().equals("")) {
                    clickContinue(btn_cell_a3);

                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_b1:
                if (!btn_cell_b1.getText().toString().equals("")) {
                    clickContinue(btn_cell_b1);

                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_b2:
                if (!btn_cell_b2.getText().toString().equals("")) {
                    clickContinue(btn_cell_b2);

                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_b3:
                if (!btn_cell_b3.getText().toString().equals("")) {
                    clickContinue(btn_cell_b3);

                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_c1:
                if (!btn_cell_c1.getText().toString().equals("")) {
                    clickContinue(btn_cell_c1);


                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_c2:
                if (!btn_cell_c2.getText().toString().equals("")) {
                    clickContinue(btn_cell_c2);


                } else {
                    endGameRound();
                }
                break;
            case R.id.cell_c3:
                if (!btn_cell_c3.getText().toString().equals("")) {
                    clickContinue(btn_cell_c3);


                } else {
                    endGameRound();
                }
                break;
        }
    }

    private void clickContinue(Button btn_cell) {
        try {
            //Sound performing
            if (MainActivity.soundAvailableValue == 1) {
                tragSoundPool.play(soundId, 0.3f, 0.3f, 0, 0, 1);
            }

            if ((Integer.parseInt(btn_cell.getText().toString()) - 1) >= 0) {
                btn_cell.setText((Integer.parseInt(btn_cell.getText().toString())) - 1 == 0 ? "" : (Integer.parseInt(btn_cell.getText().toString())) - 1 + "" + "");
                scoreEarnned += 2;
            } else {
                // NOTHING TO COUNT IN NON STOP
            }

        } catch (NumberFormatException x) {
            //Toast.makeText(this, "เริ่มใหม่เพื่อเล่นอีกครั้ง :D )!", Toast.LENGTH_SHORT).show();
        }
    }

    private void endGameRound() {

        doForHighScore(); //1
        viewResult(); //2
        stopTimerGamer();
        disableFailedEvent();
        btnGenerate.setVisibility(View.VISIBLE);

        eventNotification.setText("จบเกมจร้า คะแนนรวมคือ : " + scoreEarnned + " | เกรด : " + noneStopB.levelEarned(scoreEarnned));
        finish();

    }


    public void disableFailedEvent() {
        btn_cell_a1.setText("X");
        btn_cell_a1.setBackgroundColor(Color.RED);
        btn_cell_a1.setTextColor(Color.WHITE);
        btn_cell_a2.setText("X");
        btn_cell_a2.setBackgroundColor(Color.RED);
        btn_cell_a2.setTextColor(Color.WHITE);
        btn_cell_a3.setText("X");
        btn_cell_a3.setBackgroundColor(Color.RED);
        btn_cell_a3.setTextColor(Color.WHITE);
        btn_cell_b1.setText("X");
        btn_cell_b1.setBackgroundColor(Color.RED);
        btn_cell_b1.setTextColor(Color.WHITE);
        btn_cell_b2.setText("X");
        btn_cell_b2.setBackgroundColor(Color.RED);
        btn_cell_b2.setTextColor(Color.WHITE);
        btn_cell_b3.setText("X");
        btn_cell_b3.setBackgroundColor(Color.RED);
        btn_cell_b3.setTextColor(Color.WHITE);
        btn_cell_c1.setText("X");
        btn_cell_c1.setBackgroundColor(Color.RED);
        btn_cell_c1.setTextColor(Color.WHITE);
        btn_cell_c2.setText("X");
        btn_cell_c2.setBackgroundColor(Color.RED);
        btn_cell_c2.setTextColor(Color.WHITE);
        btn_cell_c3.setText("X");
        btn_cell_c3.setBackgroundColor(Color.RED);
        btn_cell_c3.setTextColor(Color.WHITE);
    }

    public void buttonStartup() {
        initialButtonStartup = noneStopB.buttonStartup();
//        buttonGenarate();
        btn_cell_a1.setText(initialButtonStartup[0]);
        btn_cell_a2.setText(initialButtonStartup[1]);
        btn_cell_a3.setText(initialButtonStartup[2]);
        btn_cell_b1.setText(initialButtonStartup[3]);
        btn_cell_b2.setText(initialButtonStartup[4]);
        btn_cell_b3.setText(initialButtonStartup[5]);
        btn_cell_c1.setText(initialButtonStartup[6]);
        btn_cell_c2.setText(initialButtonStartup[7]);
        btn_cell_c3.setText(initialButtonStartup[8]);
    }


    public void buttonRealTimeGenerate() {
        initialButtonStartup = noneStopB.realTimeGenerate(initialButtonStartup, scoreEarnned);
        //buttonGenarate();
        btn_cell_a1.setText(initialButtonStartup[0]);
        btn_cell_a2.setText(initialButtonStartup[1]);
        btn_cell_a3.setText(initialButtonStartup[2]);
        btn_cell_b1.setText(initialButtonStartup[3]);
        btn_cell_b2.setText(initialButtonStartup[4]);
        btn_cell_b3.setText(initialButtonStartup[5]);
        btn_cell_c1.setText(initialButtonStartup[6]);
        btn_cell_c2.setText(initialButtonStartup[7]);
        btn_cell_c3.setText(initialButtonStartup[8]);
    }

    public void buttonGenarate() {
        btn_cell_a1.setBackgroundColor(Color.WHITE);
        btn_cell_a1.setTextColor(Color.BLACK);
        btn_cell_a2.setBackgroundColor(Color.WHITE);
        btn_cell_a2.setTextColor(Color.BLACK);
        btn_cell_a3.setBackgroundColor(Color.WHITE);
        btn_cell_a3.setTextColor(Color.BLACK);
        btn_cell_b1.setBackgroundColor(Color.WHITE);
        btn_cell_b1.setTextColor(Color.BLACK);
        btn_cell_b2.setBackgroundColor(Color.WHITE);
        btn_cell_b2.setTextColor(Color.BLACK);
        btn_cell_b3.setBackgroundColor(Color.WHITE);
        btn_cell_b3.setTextColor(Color.BLACK);
        btn_cell_c1.setBackgroundColor(Color.WHITE);
        btn_cell_c1.setTextColor(Color.BLACK);
        btn_cell_c2.setBackgroundColor(Color.WHITE);
        btn_cell_c2.setTextColor(Color.BLACK);
        btn_cell_c3.setBackgroundColor(Color.WHITE);
        btn_cell_c3.setTextColor(Color.BLACK);

    }

    public void getCurrentButtonState() {
        initialButtonStartup[0] = btn_cell_a1.getText().toString();
        initialButtonStartup[1] = btn_cell_a2.getText().toString();
        initialButtonStartup[2] = btn_cell_a3.getText().toString();
        initialButtonStartup[3] = btn_cell_b1.getText().toString();
        initialButtonStartup[4] = btn_cell_b2.getText().toString();
        initialButtonStartup[5] = btn_cell_b3.getText().toString();
        initialButtonStartup[6] = btn_cell_c1.getText().toString();
        initialButtonStartup[7] = btn_cell_c2.getText().toString();
        initialButtonStartup[8] = btn_cell_c3.getText().toString();

    }

    public void newGame() {
        noneStopB = new NoneStopModeProperties();
        buttonStartup();
        tapSection.setVisibility(View.VISIBLE);
        btnGenerate.setVisibility(View.GONE);

        scoreEarnned = 0;
        startGameTimer();
        playingTimeHandler = new Handler();
        playingTimeRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };
    }

    public void viewResult() {
        Intent resultIntent = new Intent(this, GameResultActivity.class);
        resultIntent.putExtra("gameScore", scoreEarnned);
        resultIntent.putExtra("gameLevel", noneStopB.levelEarned(scoreEarnned));
        resultIntent.putExtra("isBeatHighScore", isBeatHighScore);
//        if(tragSoundPool != null) {
//            soundPoolProperties(R.raw.game_end);
//            tragSoundPool.play(soundId, 1, 1, 0, 0, 1);
//        }
        startActivity(resultIntent);
    }


    public void stopTimerGamer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
    }


    @Override
    protected void onDestroy() {
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        if (playingTimeHandler != null) { // Destroy for processing methods (handler,runnable)
            playingTimeHandler.removeCallbacks(playingTimeRunnable);
            playingTimeHandler = null;
            playingTimeRunnable = null;
        }
        stopTimerGamer();
        //stopTragMedia();
        super.onDestroy();
    }

    public void startGameTimer() {

        playingTimeHandler = new Handler();
        playingTimeRunnable = new Runnable() {
            @Override
            public void run() {
                getCurrentButtonState();
                try {
                    try {
                        buttonRealTimeGenerate();
                        playingTimeHandler.postDelayed(this, 500);
                    } catch (NullPointerException nExc) {
                        finish();
                    }
                } catch (StringIndexOutOfBoundsException stoob) {
                    if (!isFinishing()) {
                        endGameRound();
                        playingTimeHandler.removeCallbacks(playingTimeRunnable);
                        Log.v("endGameRound():", "Cause of out of tap");
                    }
                }

            }
        };

        playingTimeHandler.post(playingTimeRunnable);

    }

    private void doForHighScore() {
        csr.moveToFirst();
        int currentHighScore = csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_HIGH_SCORE));
        if (currentHighScore < scoreEarnned) {
            isBeatHighScore = true;
            Toast.makeText(this, "คะแนนที่ได้: " + scoreEarnned + " | ทำลายคะแนนสูงสุดก่อนหน้า: " + currentHighScore, Toast.LENGTH_LONG).show();
            ContentValues contentValues = new ContentValues();
            contentValues.put(tragDatabase.COL_TABLE_HIGH_SCORE, scoreEarnned);
            sqLiteDatabase = tragDatabase.getWritableDatabase();
            sqLiteDatabase.update(tragDatabase.TABLE_NAME, contentValues, "score_id = ?", new String[]{"2"});

        } else {
            isBeatHighScore = false;
        }
    }
}
