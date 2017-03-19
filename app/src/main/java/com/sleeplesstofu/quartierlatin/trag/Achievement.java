package com.sleeplesstofu.quartierlatin.trag;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by QuartierLatin on 2/26/2016.
 */
public class Achievement extends Activity {
    private TragDatabase tragDatabase;
    private SQLiteDatabase writableSqLiteDatabase;
    private SQLiteDatabase readableSqLiteDatabase;
    private ContentValues contentValues;

    private Cursor csr;
    private static String sqlCmd;

    private char mode;
    private int totalTapTimes;
    private int totalScoreEarned;
    private int lastestGameTapTimes;
    private int lastestGameScores;

    private char fromMode;


    public Achievement(int totalTapTimes, int totalScoreEarned, char mode) {
        this(null,0, 0, totalScoreEarned, totalTapTimes, mode);
    }

    public Achievement(Context context,int lastestGameScores, int lastestGameTapTimes, int totalScoreEarned, int totalTapTimes, char mode) {
        this.lastestGameScores = lastestGameScores;
        this.lastestGameTapTimes = lastestGameTapTimes;
        this.totalScoreEarned = totalScoreEarned;
        this.totalTapTimes = totalTapTimes;
        this.mode = mode;

        sqlCmd = "SELECT * FROM " + tragDatabase.TABLE_ACHIEVEMENT+";";
        this.tragDatabase = new TragDatabase(context);
        this.writableSqLiteDatabase = this.tragDatabase.getWritableDatabase();
        this.readableSqLiteDatabase = this.tragDatabase.getReadableDatabase();

        this.contentValues = new ContentValues();
        this.csr = readableSqLiteDatabase.rawQuery(sqlCmd, null);
        this.fromMode = mode;

        doAcheivement();
    }

    private void doAcheivement() {
        switch (fromMode) {
            case 'a':
                achievement_1();
                achievement_2();
                achievement_3();
                achievement_4();
                achievement_5();
                achievement_6();
                achievement_7();
                achievement_8();
                achievement_9();
                achievement_10();
                achievement_11();
                achievement_12();
                achievement_13();
                achievement_14();
                achievement_15();
                achievement_16();
                achievement_17();
                achievement_18();

                break;
            case 'b':
                achievement_1();
                achievement_2();
                achievement_3();
                achievement_4();
                achievement_5();
                achievement_6();
                achievement_7();
                achievement_8();
                achievement_9();
                achievement_19();
                achievement_20();
                achievement_21();
                achievement_22();
                achievement_23();
                achievement_24();
                break;
            case 'c':
                break;
            default:
                Log.v("Defualt Case:>>", "!@#!@#!@#!@#!@#!@#");
        }
    }


    public void achievement_1() {
//เก็บศูนย์คะแนนสำหรับหนึ่งเกม
        if (lastestGameScores == 0) {
            csr = readableSqLiteDatabase.rawQuery("SELECT * FROM " + tragDatabase.TABLE_CUMULATIVE + "WHERE factor_id = 1;", null);
            csr.moveToFirst();
            int totalT = csr.getInt(csr.getColumnIndex(tragDatabase.COL_TABLE_ACHIEVEMENT_REWARD));

            contentValues.put(tragDatabase.COL_TABLE_ACHIEVEMENT_IS_SUCCESS, 1);
            writableSqLiteDatabase.update(tragDatabase.TABLE_ACHIEVEMENT, contentValues, "achievement_id=?", new String[]{"1"});
            int tReward = 100;
            contentValues.put(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE, totalT +tReward);
            writableSqLiteDatabase.update(tragDatabase.TABLE_CUMULATIVE, contentValues, "factor_id = ?", new String[]{"3"});
        }

    }

    public void achievement_2() {
//พิชิตคะแนนสูงสุดจำนวน 10 ครั้ง

    }

    public void achievement_3() {
//พิชิตคะแนนสูงสุดจำนวน 50 ครั้ง

    }

    public void achievement_4() {
//เก็บศูนย์คะแนนสำหรับหนึ่งเกมพิชิตคะแนนสูงสุดจำนวน 100 ครั้ง

    }

    public void achievement_5() {
//เก็บการแตะทั้งหมดในเกมเป็นจำนวน 100 ครั้ง

    }

    public void achievement_6() {
//เก็บการแตะทั้งหมดในเกมเป็นจำนวน 1000 ครั้ง

    }

    public void achievement_7() {
//เก็บการแตะทั้งหมดในเกมเป็นจำนวน 10000 ครั้ง

    }

    public void achievement_8() {
//เก็บการแตะทั้งหมดในเกมเป็นจำนวน 100000 ครั้ง

    }

    public void achievement_9() {
//เก็บการแตะทั้งหมดในเกมเป็นจำนวน 1000000 ครั้ง

    }

    public void achievement_10() {
//จบเกมในระดับ พึ่งแตะสมาร์ทโฟน ในโหมดธรรมดา

    }

    public void achievement_11() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟนเป็นปกต ในโหมดธรรมดา

    }

    public void achievement_12() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟน์บ่อย ในโหมดธรรมดา

    }

    public void achievement_13() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟน์อย่างคล่องแคล่ว ในโหมดธรรมดา

    }

    public void achievement_14() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ชั้นแก่ประสบการณ ในโหมดธรรมดา

    }

    public void achievement_15() {
//จบเกมในระดับ ผู้เชี่ยวชาญการแตะสมาร์ทโฟน์ ในโหมดธรรมดา

    }

    public void achievement_16() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ขั้นพระเจ้า ในโหมดธรรมดา

    }

    public void achievement_17() {
//จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ขั้นปู่ทวดของพระเจ้าอีกที ในโหมดธรรมดา

    }

    public void achievement_18() {
//จบเกมในระดับ ศาสดาแห่งการแตะผู้จุติมาเพื่อแตะสมาร์ทโฟน ในโหมดธรรมดา

    }

    public void achievement_19() {
//เก็บระดับ D ในโหมด Non stop

    }

    public void achievement_20() {
//เก็บระดับ C ในโหมด Non stop

    }

    public void achievement_21() {
//เก็บระดับ B ในโหมด Non stop

    }

    public void achievement_22() {
//เก็บระดับ A ในโหมด Non stop

    }

    public void achievement_23() {
//เก็บระดับ S ในโหมด Non stop

    }

    public void achievement_24() {
//เก็บระดับ SS ในโหมด Non stop

    }

}
