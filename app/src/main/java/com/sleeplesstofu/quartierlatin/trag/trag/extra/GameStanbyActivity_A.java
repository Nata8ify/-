package com.sleeplesstofu.quartierlatin.trag.trag.extra;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sleeplesstofu.quartierlatin.trag.MainActivity;
import com.sleeplesstofu.quartierlatin.trag.Play_A_AcradeMode;
import com.sleeplesstofu.quartierlatin.trag.R;

public class GameStanbyActivity_A extends AppCompatActivity {
    private TextView txtStandByTimer,txtPleaseWait,txtDescription, txtStanbyMode;
    private CountDownTimer stanbyTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stanby_activity_a);
        startUpWidget();

        stanbyTimer.start();
    }

    public void startUpWidget(){
        txtStandByTimer = (TextView)findViewById(R.id.txt_stand_by_timer);
        txtPleaseWait = (TextView)findViewById(R.id.txt_plz_wait);
        txtDescription = (TextView) findViewById(R.id.txt_plz_description);
        txtStanbyMode = (TextView) findViewById(R.id.txt_stanby_mode);

        txtPleaseWait.setTypeface(MainActivity.tragFont);
        txtStandByTimer.setTypeface(MainActivity.tragFont);
//        txtDescription.setTypeface(MainActivity.tragFont);
        txtStanbyMode.setTypeface(MainActivity.tragFont);

        stanbyTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                txtStandByTimer.setText("Stand By...");
//                txtPleaseWait.setText("Please Stand by".concat(" . "));
                if(millisUntilFinished/1000 == 1){
                    txtStandByTimer.setText("Ready!");
                    YoYo.with(Techniques.FlipInX).duration(400).playOn(txtStandByTimer);
                }
            }

            @Override
            public void onFinish() {
                txtStandByTimer.setText("GO!");
                YoYo.with(Techniques.FlipInX).duration(400).playOn(txtStandByTimer);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), Play_A_AcradeMode.class));
                        finish();
                    }
                }, 200);
            }
        };
    }

    @Override
    protected void onDestroy() {
        if(stanbyTimer != null) {
            stanbyTimer.cancel();
            stanbyTimer = null;
        }
        super.onDestroy();
    }
}
