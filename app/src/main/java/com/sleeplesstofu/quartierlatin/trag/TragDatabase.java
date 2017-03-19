package com.sleeplesstofu.quartierlatin.trag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QuartierLatin on 3/1/2016.
 */
public class TragDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Trag_database";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "score";
    public static final String COL_TABLE_HIGH_SCORE = "current_high_score_score";
    public static final String COL_TABLE_HIGH_SCORE_PLAYER = "high_score_player_name";

    public static final String TABLE_SETTING = "setting";
    public static final String COL_TABLE_OPTION = "option";
    public static final String COL_TABLE_AVAILABLE = "available";

    public static final String TABLE_ACHIEVEMENT = "achievement";
    public static final String COL_TABLE_ACHIEVEMENT_NAME = "achievement_name";
    public static final String COL_TABLE_ACHIEVEMENT_CONDITION = "achievement_condition";
    public static final String COL_TABLE_ACHIEVEMENT_IS_SUCCESS = "success_state";
    public static final String COL_TABLE_ACHIEVEMENT_REWARD = "reward";
    public static final String COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD = "reward_state";

    public static final String TABLE_CUMULATIVE = "cumulative";
    public static final String COL_TABLE_CUMULATIVE_FACTOR = "factor";
    public static final String COL_TABLE_CUMULATIVE_INT_VALUE = "integer_value";
    public static final String COL_TABLE_CUMULATIVE_NON_INT_VALUE = "non_integer_value";

    public TragDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        creatSettingTable(db);
        creatLeaderBoardTable(db);
        creatAchievement(db);
        createCumulative(db);
    }


    private void creatLeaderBoardTable(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(score_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TABLE_HIGH_SCORE + " INTEGER," +
                    COL_TABLE_HIGH_SCORE_PLAYER + "TEXT" + ");");

            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_TABLE_HIGH_SCORE + ") VALUES ( 0 );");
            db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_TABLE_HIGH_SCORE + ") VALUES ( 0 );");
        } catch (Exception e) {
//            Log.i("Exception creatLeaderBoardTable():  ",e.toString());
        }
    }

    private void creatSettingTable(SQLiteDatabase db) {
        try {
            // Setting Properties
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SETTING +
                    "(setting_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TABLE_OPTION + " TEXT," +
                    COL_TABLE_AVAILABLE + " INTEGER " + ");");

            db.execSQL("INSERT INTO " + TABLE_SETTING + " (" + COL_TABLE_OPTION + "," + COL_TABLE_AVAILABLE + ") VALUES ( 'Music' ,1 );");
            db.execSQL("INSERT INTO " + TABLE_SETTING + " (" + COL_TABLE_OPTION + "," + COL_TABLE_AVAILABLE + ") VALUES ( 'Sound' ,1 );");
            db.execSQL("INSERT INTO " + TABLE_SETTING + " (" + COL_TABLE_OPTION + "," + COL_TABLE_AVAILABLE + ") VALUES ( 'Vibrator' ,0 );");

        } catch (Exception e) {
//        Log.i("Exception creatSettingTable():  ",e.toString());
        }
    }

    private void creatAchievement(SQLiteDatabase db) {
        try {
            // Setting Properties
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ACHIEVEMENT +
                    "(achievement_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TABLE_ACHIEVEMENT_NAME + " TEXT," +
                    COL_TABLE_ACHIEVEMENT_CONDITION + " TEXT, " +
                    COL_TABLE_ACHIEVEMENT_IS_SUCCESS + " INTEGER, " +
                    COL_TABLE_ACHIEVEMENT_REWARD + " INTEGER, "+
                    COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD + " INTEGER, "+ ");");

            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+ ") VALUES ( 'ศูนย์!!' ,'เก็บศูนย์คะแนนสำหรับหนึ่งเกม' ,0 , 100, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เหนือฟ้ายังมีฟ้า' ,'พิชิตคะแนนสูงสุดจำนวน 10 ครั้ง ' ,0, 10000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เหนือฟ้ายังมีชั้นบรรยากาศ' ,'พิชิตคะแนนสูงสุดจำนวน 50 ครั้ง' ,0, 500000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เหนือชั้นบรรยากาศยังมดาวเทียม' ,'เก็บศูนย์คะแนนสำหรับหนึ่งเกมพิชิตคะแนนสูงสุดจำนวน 100 ครั้ง' ,0, 1000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เซียนแตะ Lv1' ,'เก็บการแตะทั้งหมดในเกมเป็นจำนวน 100 ครั้ง' ,0, 100, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เซียนแตะ Lv2' ,'เก็บการแตะทั้งหมดในเกมเป็นจำนวน 1000 ครั้ง' ,0, 1000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เซียนแตะ Lv3' ,'เก็บการแตะทั้งหมดในเกมเป็นจำนวน 10000 ครั้ง' ,0, 10000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เซียนแตะ Lv4' ,'เก็บการแตะทั้งหมดในเกมเป็นจำนวน 100000 ครั้ง' ,0, 100000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'เซียนแตะ Lv5' ,'เก็บการแตะทั้งหมดในเกมเป็นจำนวน 1000000 ครั้ง' ,0, 10000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'คนหัดแตะ' ,'จบเกมในระดับ พึ่งแตะสมาร์ทโฟน ในโหมดธรรมดา' ,0, 100, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ผู้แตะธรรมดา' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟนเป็นปกต ในโหมดธรรมดา' ,0, 500, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ผู้แตะประจำ' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟน์บ่อย ในโหมดธรรมดา' ,0, 1000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นเก๋า' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟน์อย่างคล่องแคล่ว ในโหมดธรรมดา' ,0, 5000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นเซียน' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ชั้นแก่ประสบการณ ในโหมดธรรมดา' ,0, 100000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นศาสดา' ,'จบเกมในระดับ ผู้เชี่ยวชาญการแตะสมาร์ทโฟน์ ในโหมดธรรมดา' ,0, 1000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นเทพ' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ขั้นพระเจ้า ในโหมดธรรมดา' ,0, 5000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นปู่ทวดเทพ' ,'จบเกมในระดับ ผู้แตะสมาร์ทโฟน์ขั้นปู่ทวดของพระเจ้าอีกที ในโหมดธรรมดา' ,0, 10000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'ขั้นผู้จุติ' ,'จบเกมในระดับ ศาสดาแห่งการแตะผู้จุติมาเพื่อแตะสมาร์ทโฟน ในโหมดธรรมดา' ,0, 999999999, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'D' ,'เก็บระดับ D ในโหมด Non stop' ,0, 100, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'C' ,'เก็บระดับ C ในโหมด Non stop' ,0, 500, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'B' ,'เก็บระดับ B ในโหมด Non stop' ,0, 1000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'A' ,'เก็บระดับ A ในโหมด Non stop' ,0, 100000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'S' ,'เก็บระดับ S ในโหมด Non stop' ,0, 10000000, 0);");
            db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENT + " (" + COL_TABLE_ACHIEVEMENT_NAME + "," + COL_TABLE_ACHIEVEMENT_CONDITION + "," + COL_TABLE_ACHIEVEMENT_IS_SUCCESS +","+ COL_TABLE_ACHIEVEMENT_REWARD+", "+COL_TABLE_ACHIEVEMENT_IS_RECIVED_REWARD+  ") VALUES ( 'SS' ,'เก็บระดับ SS ในโหมด Non stop' ,0, 999999999, 0);");


        } catch (Exception e) {
//        Log.i("Exception creatSettingTable():  ",e.toString());
        }
    }

    private void createCumulative(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CUMULATIVE +
                "(factor_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TABLE_CUMULATIVE_FACTOR + " TEXT," +
                COL_TABLE_CUMULATIVE_INT_VALUE + " INTEGER, " +
                COL_TABLE_CUMULATIVE_NON_INT_VALUE + " TEXT" + ");");


        db.execSQL("INSERT INTO " + TABLE_CUMULATIVE + " (" + COL_TABLE_CUMULATIVE_FACTOR+", "+COL_TABLE_CUMULATIVE_INT_VALUE + ") VALUES ( 'Cumulative_Taps', 0 );");
        db.execSQL("INSERT INTO " + TABLE_CUMULATIVE + " (" + COL_TABLE_CUMULATIVE_FACTOR+", "+COL_TABLE_CUMULATIVE_INT_VALUE + ") VALUES ( 'Cumulative_Scores', 0 );");
        db.execSQL("INSERT INTO " + TABLE_CUMULATIVE + " (" + COL_TABLE_CUMULATIVE_FACTOR+", "+COL_TABLE_CUMULATIVE_INT_VALUE + ") VALUES ( 'Cumulative_T', 0 );");
        db.execSQL("INSERT INTO " + TABLE_CUMULATIVE + " (" + COL_TABLE_CUMULATIVE_FACTOR+", "+COL_TABLE_CUMULATIVE_INT_VALUE + ") VALUES ( 'Cumulative_Beats_High_Score', 0 );");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
