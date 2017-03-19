package com.sleeplesstofu.quartierlatin.trag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sleeplesstofu.quartierlatin.trag.trag.extra.GameStanbyActivity_A;
import com.sleeplesstofu.quartierlatin.trag.trag.extra.GameStanbyActivity_B;

import xyz.hanks.library.SmallBang;

/**
 * Created by QuartierLatin on 13/2/2559.
 */
public class SelectModeActivity extends Activity {
    private Button btnNormalMode, btnNoneStopMode, btnPlayBack;
    //Animation Properties
    private static SmallBang bangAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_mode_from_main_layout);
        setWidget();
        setWidgetListener();
    }

    private void setWidget() {
        bangAnimation = SmallBang.attach2Window(this);

        btnNormalMode = (Button) findViewById(R.id.btnPlay_time_limit);
        btnNoneStopMode = (Button) findViewById(R.id.btnPlay_none_stop);
        btnPlayBack = (Button) findViewById(R.id.btnPlay_back);
        btnNormalMode.setTypeface(MainActivity.tragFont);
        btnNoneStopMode.setTypeface(MainActivity.tragFont);
        btnPlayBack.setTypeface(MainActivity.tragFont);

        initAnimation();
    }

    private void initAnimation() {

            YoYo.with(Techniques.Wobble).delay(1300).duration(250).playOn(btnNormalMode);
            YoYo.with(Techniques.Wobble).delay(1500).duration(250).playOn(btnNoneStopMode);
            YoYo.with(Techniques.Wobble).delay(2000).duration(250).playOn(btnPlayBack);
    }

    private void setWidgetListener() {
        btnNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Play_A_AcradeMode.class));
                startActivity(new Intent(getApplicationContext(), GameStanbyActivity_A.class));

            }
        });
        btnNoneStopMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Play_B_NoneStopMode.class));
                startActivity(new Intent(getApplicationContext(), GameStanbyActivity_B.class));
            }
        });
    }

    public void goBack(View v) {
        finish();
    }

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
}
