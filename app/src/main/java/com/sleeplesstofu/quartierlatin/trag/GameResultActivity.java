package com.sleeplesstofu.quartierlatin.trag;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import xyz.hanks.library.SmallBang;

/**
 * Created by QuartierLatin on 2/23/2016.
 */
public class GameResultActivity extends Activity {

    private TextView txtScoreEarned;
    private TextView txtLevelEarned, txtResult, txtScore, txtLevel;
    private Bundle getGameResult;
    private Handler elementHandler;
    private Button btnBack;

    public static SmallBang bangAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);
        getGameResult = getIntent().getExtras();
        elementHandler = new Handler();


        txtScoreEarned = (TextView) findViewById(R.id.txt_show_score);
        txtLevelEarned = (TextView) findViewById(R.id.txt_show_level);
        txtResult = (TextView) findViewById(R.id.txt_result);
        txtScore = (TextView) findViewById(R.id.txt_score_earned);
        txtLevel = (TextView) findViewById(R.id.txt_level_earned);

        initAnimation();


        txtScoreEarned.setTypeface(MainActivity.tragFont);
        txtLevelEarned.setTypeface(MainActivity.tragFont);
        txtResult.setTypeface(MainActivity.tragFont);
        txtScore.setTypeface(MainActivity.tragFont);
        txtLevel.setTypeface(MainActivity.tragFont);

        txtScoreEarned.setTypeface(MainActivity.tragFont);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setVisibility(View.INVISIBLE);
        btnBack.setTypeface(MainActivity.tragFont);

        //From Game putExtra
        final boolean IS_BEAT_HIGH_SCORE = getGameResult.getBoolean("isBeatHighScore");

        if (getGameResult != null) {


            elementHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (IS_BEAT_HIGH_SCORE) {
                        txtScoreEarned.setText(getGameResult.getInt("gameScore") + " คะแนนสูงสุด!");
                        bangAnimation.bang(txtScoreEarned);
                    } else {
                        txtScoreEarned.setText(getGameResult.getInt("gameScore") + "");
                        YoYo.with(Techniques.FlipInX).delay(0).duration(250).playOn(txtScoreEarned);
                    }
                }
            }, 500);


            elementHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (IS_BEAT_HIGH_SCORE) {
                        txtLevelEarned.setText(getGameResult.getString("gameLevel"));
                        bangAnimation.bang(txtLevelEarned);
                    } else {
                        txtLevelEarned.setText(getGameResult.getString("gameLevel"));
                        YoYo.with(Techniques.FlipInX).delay(0).duration(250).playOn(txtLevelEarned);
                    }
                }
            }, 750);

            elementHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnBack.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn).delay(0).duration(150).playOn(btnBack);
                }
            }, 2000);

        } else {
            txtScoreEarned.setText("N/A");
            txtLevelEarned.setText("N/A");
        }
    }

    private void initAnimation() {
        bangAnimation = SmallBang.attach2Window(this);
        bangAnimation.setColors(new int[]{Color.WHITE, Color.RED, Color.GREEN, Color.BLUE});
    }

    public void doFinish(View v) {
        finish();
    }

}
