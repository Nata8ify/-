package com.sleeplesstofu.quartierlatin.trag;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.sleeplesstofu.quartierlatin.trag.trag.extra.CreditActivity;

public class SettingActivity extends AppCompatActivity {
    private Switch switchMusic;
    private Switch switchSound;
    private Switch switchVibrator;
    private TextView txtMusicSwitch, txtSoundSwitch, txtVibratorSwitch;

    private SQLiteDatabase optionDatabase;
    private TragDatabase tragDatabase;
    private Cursor csr;
    private String primarySQLCmd;
    private ContentValues optionContentValues;

    private short isMusicOn_N;
    private short isSoundOn_N;
    private short isVibratorOn_N;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);

        // Initial things
        initialDatabase();
        startUpWidget();
        widgetListener();
    }

    private void widgetListener() {
        switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    optionDatabase = tragDatabase.getWritableDatabase();
                    optionContentValues = new ContentValues();
                    optionContentValues.put(tragDatabase.COL_TABLE_AVAILABLE, switchValue(isMusicOn_N));
                    optionDatabase.update(tragDatabase.TABLE_SETTING, optionContentValues, "setting_id = ? ", new String[]{"1"});
                    disableMedia(isChecked);
                } catch (Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    optionDatabase = tragDatabase.getWritableDatabase();
                    optionContentValues = new ContentValues();
                    optionContentValues.put(tragDatabase.COL_TABLE_AVAILABLE, switchValue(isSoundOn_N));
                    optionDatabase.update(tragDatabase.TABLE_SETTING, optionContentValues, "setting_id = ? ", new String[]{"2"});
                   disableSound(isChecked);
                } catch (Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });

        switchVibrator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    optionDatabase = tragDatabase.getWritableDatabase();
                    optionContentValues = new ContentValues();
                    optionContentValues.put(tragDatabase.COL_TABLE_AVAILABLE, switchValue(isVibratorOn_N));
                    optionDatabase.update(tragDatabase.TABLE_SETTING, optionContentValues, "setting_id = ? ", new String[]{"3"});
                    disableVibrator(isChecked);
                } catch (Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
    }

    private Short switchValue(short value) {
        if (value == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    private void startUpWidget() {
        txtMusicSwitch = (TextView) findViewById(R.id.txt_music_switch);
        txtSoundSwitch = (TextView) findViewById(R.id.txt_sound_switch);
        txtVibratorSwitch = (TextView) findViewById(R.id.txt_sound_vibrate);
        switchMusic = (Switch) findViewById(R.id.switch_music);
        switchSound = (Switch) findViewById(R.id.switch_sound);
        switchVibrator = (Switch) findViewById(R.id.switch_vibrate);

        txtMusicSwitch.setTypeface(MainActivity.tragFont);
        txtSoundSwitch.setTypeface(MainActivity.tragFont);
        txtVibratorSwitch.setTypeface(MainActivity.tragFont);

        isMusicOn_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));
        csr.moveToPosition(1);
        isSoundOn_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));
        csr.moveToPosition(2);
        isVibratorOn_N = csr.getShort(csr.getColumnIndex(tragDatabase.COL_TABLE_AVAILABLE));

        if (isMusicOn_N == 1) {
            switchMusic.setChecked(true);
        }

        if (isSoundOn_N == 1) {
            switchSound.setChecked(true);
        }

        if (isVibratorOn_N == 1) {
            switchVibrator.setChecked(true);
        }
    }

    private void initialDatabase() {
        tragDatabase = new TragDatabase(this);
        optionDatabase = tragDatabase.getReadableDatabase();
        primarySQLCmd = "SELECT * FROM " + tragDatabase.TABLE_SETTING;
        csr = optionDatabase.rawQuery(primarySQLCmd, null);
        csr.moveToPosition(0);
    }

    private static void disableMedia(boolean isOn) {
        if (isOn) {
            MainActivity.tragMediaPlayer.start();
        } else {
            MainActivity.tragMediaPlayer.pause();
        }
    }

    private void disableSound(boolean isOn) {
        if (isOn) {
            MainActivity.soundAvailableValue = 1;

            SoundPool soundForSoundSwitch = new SoundPool(1, AudioManager.STREAM_NOTIFICATION,1);
            soundForSoundSwitch.play(soundForSoundSwitch.load(this, R.raw.tap_sound, 1), 1, 1, 0, 0, 1);
        } else {
            MainActivity.soundAvailableValue = 0;
        }
    }

    private void disableVibrator(boolean isOn) {
        if (isOn) {
            MainActivity.vibrateAvailableValue = 1;
            ((Vibrator)this.getSystemService(VIBRATOR_SERVICE)).vibrate(10);
        } else {
            MainActivity.vibrateAvailableValue = 0;
        }
    }

    public void goCredit(View v) {
        startActivity(new Intent(getApplicationContext(), CreditActivity.class));
    }

    public void goBackFromSetting(View v) {
        finish();
    }
}
