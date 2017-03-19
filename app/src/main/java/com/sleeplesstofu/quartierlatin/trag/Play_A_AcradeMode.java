package com.sleeplesstofu.quartierlatin.trag;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Play_A_AcradeMode extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cell_a1, btn_cell_a2, btn_cell_a3, btn_cell_b1, btn_cell_b2, btn_cell_b3, btn_cell_c1, btn_cell_c2, btn_cell_c3, btnGenerate;
    private TextView eventNotification, levelNotification, timeNotification;
    private LinearLayout tapSection;


    private GameProperties gameTapProp;

    private CountDownTimer gameTimer;
    private long startTime;
    private long endTime;
    private int tapTimes;
    private int scoreEarned;
    private String level;
    private int totalTapTimes;

    private SQLiteDatabase sqLiteDatabase;
    private TragDatabase tragDatabase;
    private Cursor csr;
    private String sqlCmd;
    private int soundAvailableBit;

    private Achievement achievement;

    //Extra Properties
    private boolean isBeatHighScore;
    private SoundPool tragSoundPool;
    private int tapSoundId;
    private int endGameSoundId;
    private int beatsHighScoreSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__a__acrade_mode);
        //Initial section.

        tragDatabase = new TragDatabase(this);
        sqLiteDatabase = tragDatabase.getReadableDatabase();

        initialElements();
        gameTapProp = new GameProperties();
        //Do event section.
        elementListener();

        //startUpTragMedia();

        gameStart();
    }

    public void initialElements() {
        totalTapTimes = 0;

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
        levelNotification = (TextView) findViewById(R.id.level_notification);
        timeNotification = (TextView) findViewById(R.id.game_timer);
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

        sqlCmd = "SELECT " + tragDatabase.COL_TABLE_AVAILABLE + " FROM " + tragDatabase.TABLE_SETTING + " WHERE setting_id = 2;";
        csr = sqLiteDatabase.rawQuery(sqlCmd, null);
        csr.moveToFirst();
        soundAvailableBit = csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));

        soundPoolProperties(R.raw.tap_sound, R.raw.tubellar_for_game_end, R.raw.beats_high_score);

    }

    private void soundPoolProperties(int rawTap, int rawGameEnd, int rawbeatsForHigh) {
        tragSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        tapSoundId = tragSoundPool.load(getApplicationContext(), rawTap, 1);
        endGameSoundId = tragSoundPool.load(getApplicationContext(), rawGameEnd, 1);
        beatsHighScoreSoundId = tragSoundPool.load(getApplicationContext(), rawbeatsForHigh, 2);
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
                btnGenerate.setClickable(false);

                tapSection.setVisibility(View.VISIBLE);
                btnGenerate.setVisibility(View.GONE);
                eventNotification.setTextColor(Color.BLACK);

                startTime = System.currentTimeMillis();
                tapTimes = 0;
                scoreEarned = 0;
                gameTapProp.resetAll();
                eventNotification.setText("");
                buttonGenarate();
                // Start timer
                gameTimer = new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        timeNotification.setText("เวลาที่เหลือ : " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        timeNotification.setText("หมดเวลา!");
                        endGameTimeUp();
                    }
                };
                gameTimer.start();
            }
        });
    }

    @Override
    public void onClick(View v) {
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

    public void clickContinue(Button btn_cell) {
        try {
            //ocClickAnimation

            //Sound performing
            onClickAction();
            ++totalTapTimes;

            if ((Integer.parseInt(btn_cell.getText().toString()) - 1) >= 0) {
                btn_cell.setText((Integer.parseInt(btn_cell.getText().toString())) - 1 == 0 ? "" : (Integer.parseInt(btn_cell.getText().toString())) - 1 + "" + "");
            } else {
                endGameRound();
            }
            if (gameTapProp.getTimeToTap() == tapTimes) {
                eventNotification.setText("เวลาที่ใช้คือ " + (System.currentTimeMillis() - startTime) / 1000f + " วินาที");
                earnScoresAndLevel();
                startTimeReset();
                tapTimes = 0;
                gameTapProp.scoreForDifficult(scoreEarned);
                buttonGenarate();

            } else {
                tapTimes++;
            }


        } catch (NumberFormatException x) {
//            Toast.makeText(this, "เริ่มใหม่เพื่อเล่นอีกครั้ง :D )!", Toast.LENGTH_SHORT).show();
        }
    }

    public void endGameRound() {
        doPlayEndGameSound();
        doVibrate(250);
        doForHighScore();
        doCumulativeThing(scoreEarned, totalTapTimes);
        // doAchievment();
        eventNotification.setTextColor(Color.RED);
        eventNotification.setText("พบการแตะผิดพลาด! คะแนนสะสมทั้งหมด : " + scoreEarned);
        btnGenerate.setVisibility(View.VISIBLE);
        btnGenerate.setClickable(true);
        disableFailedEvent();
        gameTimer.cancel();

        viewResult();
        finish();
        //Toast.makeText(this, "Mistake Tap!", Toast.LENGTH_SHORT).show();
    }

    private void doPlayEndGameSound() {
        if (MainActivity.soundAvailableValue == 1) {
            tragSoundPool.play(endGameSoundId, 1, 1, 0, 0, 1);
        }
    }


    public void endGameTimeUp() {
        endGameRound();
        tapSection.setVisibility(View.INVISIBLE);
        eventNotification.setTextColor(Color.RED);
        eventNotification.setText("หมดเวลา! คะแนนสะสมที่ทำได้ : " + scoreEarned);

        //Toast.makeText(this, "Mistake Tap!", Toast.LENGTH_SHORT).show();
    }

    public void disableCompleteEvent() {
        btn_cell_a1.setText("O");
        btn_cell_a1.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_a1.setTextColor(Color.WHITE);
        btn_cell_a2.setText("O");
        btn_cell_a2.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_a2.setTextColor(Color.WHITE);
        btn_cell_a3.setText("O");
        btn_cell_a3.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_a3.setTextColor(Color.WHITE);
        btn_cell_b1.setText("O");
        btn_cell_b1.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_b1.setTextColor(Color.WHITE);
        btn_cell_b2.setText("O");
        btn_cell_b2.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_b2.setTextColor(Color.WHITE);
        btn_cell_b3.setText("O");
        btn_cell_b3.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_b3.setTextColor(Color.WHITE);
        btn_cell_c1.setText("O");
        btn_cell_c1.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_c1.setTextColor(Color.WHITE);
        btn_cell_c2.setText("O");
        btn_cell_c2.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_c2.setTextColor(Color.WHITE);
        btn_cell_c3.setText("O");
        btn_cell_c3.setBackgroundColor(Color.rgb(0, 153, 0));
        btn_cell_c3.setTextColor(Color.WHITE);
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

    public void buttonGenarate() {
        int[] numberSet = gameTapProp.gameCellProperties();
        btn_cell_a1.setText(numberSet[0] == 0 ? "" : numberSet[0] + "");
        btn_cell_a1.setBackgroundColor(Color.WHITE);
        btn_cell_a1.setTextColor(Color.BLACK);
        btn_cell_a2.setText(numberSet[1] == 0 ? "" : numberSet[1] + "");
        btn_cell_a2.setBackgroundColor(Color.WHITE);
        btn_cell_a2.setTextColor(Color.BLACK);
        btn_cell_a3.setText(numberSet[2] == 0 ? "" : numberSet[2] + "");
        btn_cell_a3.setBackgroundColor(Color.WHITE);
        btn_cell_a3.setTextColor(Color.BLACK);
        btn_cell_b1.setText(numberSet[3] == 0 ? "" : numberSet[3] + "");
        btn_cell_b1.setBackgroundColor(Color.WHITE);
        btn_cell_b1.setTextColor(Color.BLACK);
        btn_cell_b2.setText(numberSet[4] == 0 ? "" : numberSet[4] + "");
        btn_cell_b2.setBackgroundColor(Color.WHITE);
        btn_cell_b2.setTextColor(Color.BLACK);
        btn_cell_b3.setText(numberSet[5] == 0 ? "" : numberSet[5] + "");
        btn_cell_b3.setBackgroundColor(Color.WHITE);
        btn_cell_b3.setTextColor(Color.BLACK);
        btn_cell_c1.setText(numberSet[6] == 0 ? "" : numberSet[6] + "");
        btn_cell_c1.setBackgroundColor(Color.WHITE);
        btn_cell_c1.setTextColor(Color.BLACK);
        btn_cell_c2.setText(numberSet[7] == 0 ? "" : numberSet[7] + "");
        btn_cell_c2.setBackgroundColor(Color.WHITE);
        btn_cell_c2.setTextColor(Color.BLACK);
        btn_cell_c3.setText(numberSet[8] == 0 ? "" : numberSet[8] + "");
        btn_cell_c3.setBackgroundColor(Color.WHITE);
        btn_cell_c3.setTextColor(Color.BLACK);

    }

    public void startTimeReset() {
        startTime = System.currentTimeMillis();
    }

    public void gameStart() {
        btnGenerate.setClickable(false);

        tapSection.setVisibility(View.VISIBLE);
        btnGenerate.setVisibility(View.GONE);
        eventNotification.setTextColor(Color.BLACK);

        startTime = System.currentTimeMillis();
        tapTimes = 0;
        scoreEarned = 0;

        eventNotification.setText("");
        gameTapProp.resetAll();
        buttonGenarate();
        // Start timer
        gameTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeNotification.setText("กติกา : เก็บคะแนนจากการ tap ตัวเลขให้มากที่สุดใน 1 นาที หากพบการ tap พลาดหรือหมดเวลา ถือเป็นการจบเกม \n เวลาที่เหลือ : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timeNotification.setText("หมดเวลา!");
                endGameTimeUp();
            }


        };
        gameTimer.start();
    }

    // ส่วนจัดการคะแนนแต่ละหนึ่งรอบสุ่ม
    public void earnScoresAndLevel() {

        if (scoreEarned < 50) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้ใช้ระดับพึ่งแตะสมาร์ทโฟน";
        } else if (scoreEarned >= 50 && scoreEarned < 150) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟนเป็นปกติ";
        } else if (scoreEarned >= 150 && scoreEarned < 250) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟน์บ่อย";
        } else if (scoreEarned >= 250 && scoreEarned < 350) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟน์อย่างคล่องแคล่ว";
        } else if (scoreEarned >= 350 && scoreEarned < 450) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟน์ชั้นแก่ประสบการณ์";
        } else if (scoreEarned >= 450 && scoreEarned < 550) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้เชี่ยวชาญการแตะสมาร์ทโฟน์";
        } else if (scoreEarned >= 550 && scoreEarned < 650) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟน์ขั้นพระเจ้า";
        } else if (scoreEarned >= 650 && scoreEarned < 750) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ผู้แตะสมาร์ทโฟน์ขั้นปู่ทวดของพระเจ้าอีกที";
        } else if (scoreEarned >= 750 && scoreEarned < 8050) {
            scoreEarned += gameTapProp.getTimeToTap() - (System.currentTimeMillis() - startTime) / 1000 <= 0 ? 0 : 15 - (System.currentTimeMillis() - startTime) / 1000;
            level = "ระดับ : ศาสดาแห่งการแตะผู้จุติมาเพื่อแตะสมาร์ทโฟน";
        }
        levelNotification.setText(level);
    }

    @Override
    protected void onDestroy() {
        //stopTragMedia();
        gameTimer.cancel();
        super.onDestroy();
    }

    public void viewResult() {
        Intent resultIntent = new Intent(this, GameResultActivity.class);
        resultIntent.putExtra("gameScore", scoreEarned);
        if (scoreEarned == 0) {
            level = "ระดับ : ไม่มีระดับสำหรับ 0 คะแนน";
        }
        resultIntent.putExtra("gameLevel", level);
        resultIntent.putExtra("isBeatHighScore", isBeatHighScore);
//        if(tragSoundPool != null) {
//            soundPoolProperties(R.raw.game_end);
//            tragSoundPool.play(soundId, 1, 1, 0, 0, 1);
//        }
        startActivity(resultIntent);
    }

    private void doForHighScore() {
        sqlCmd = "SELECT " + tragDatabase.COL_TABLE_HIGH_SCORE + " FROM " + tragDatabase.TABLE_NAME + " WHERE score_id = 1;";
        csr = sqLiteDatabase.rawQuery(sqlCmd, null);
        csr.moveToFirst();
        int cuurentMaxScore = csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_HIGH_SCORE));
        Log.v("cuurentMaxScore", cuurentMaxScore + "");
        if (cuurentMaxScore < scoreEarned) {
            if (MainActivity.soundAvailableValue == 1) {
                tragSoundPool.play(beatsHighScoreSoundId, 0.2f, 0.2f, 0, 0, 1);
            }
            isBeatHighScore = true;
            Toast.makeText(this, "คะแนนที่ได้: " + scoreEarned + " | ทำลายคะแนนสูงสุดก่อนหน้า: " + cuurentMaxScore, Toast.LENGTH_LONG).show();
            ContentValues contentValues = new ContentValues();
            contentValues.put(tragDatabase.COL_TABLE_HIGH_SCORE, scoreEarned);
            sqLiteDatabase = tragDatabase.getWritableDatabase();
            sqLiteDatabase.update(tragDatabase.TABLE_NAME, contentValues, "score_id = ?", new String[]{"1"});
        } else {
            isBeatHighScore = false;
        }

    }

    private void doVibrate(long forMillis) {
        if (MainActivity.vibrateAvailableValue == 1) {
            ((Vibrator) this.getSystemService(this.VIBRATOR_SERVICE)).vibrate(forMillis);
        }
    }

    private void onClickAction() {
        if (MainActivity.soundAvailableValue == 1) {
            tragSoundPool.play(tapSoundId, 0.3f, 0.3f, 0, 0, 1);
        }

        if (MainActivity.vibrateAvailableValue == 1) {
            doVibrate(1);
        }
    }


    private void doCumulativeThing(int scoreEarned, int totalTapTimes) {
        sqLiteDatabase = tragDatabase.getReadableDatabase();
        csr = sqLiteDatabase.rawQuery("SELECT * FROM " + tragDatabase.TABLE_CUMULATIVE + ";", null);
        csr.moveToFirst();
        int currentCumulativeTaps = totalTapTimes + csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE));
        csr.moveToNext();
        int currentCumulativeScores = scoreEarned +  csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE));
        sqLiteDatabase = tragDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE, currentCumulativeTaps);
        sqLiteDatabase.update(tragDatabase.TABLE_CUMULATIVE, contentValues, "factor_id = ? ", new String[]{"1"});
        contentValues.put(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE, currentCumulativeScores);
        sqLiteDatabase.update(tragDatabase.TABLE_CUMULATIVE, contentValues, "factor_id = ? ", new String[]{"2"});

//        Achievement achievement_A = new Achievement(this,scoreEarned,totalTapTimes,currentCumulativeScores,currentCumulativeTaps,'a');

    }

}

