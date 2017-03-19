package com.sleeplesstofu.quartierlatin.trag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.sleeplesstofu.quartierlatin.trag.trag.Player;
import com.sleeplesstofu.quartierlatin.trag.trag.extra.PlayerAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by QuartierLatin on 3/1/2016.
 */
public class LeaderBoardActivity extends Activity {

    private TextView scoreForNormal, scoreForNonStop, lvlNormalMode, lvlNonStopMode, txtHighScoreNormalMode, txtHighScoreNonStopMode, txtTotalTaps, totalTaps, txtAchievement, txtRescues;
    private ListView achieveListView;
    private SQLiteDatabase sqLite;
    private TragDatabase tragDatabase;
    private Cursor tragCursor;
    private final GameProperties MODE_A = new GameProperties();
    private final NoneStopModeProperties MODE_B = new NoneStopModeProperties();

    private ListView listTop10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_board_activity);

        int scoreEarned = 0;
        int totalTaps = 0;
        tragDatabase = new TragDatabase(this);
        sqLite = tragDatabase.getReadableDatabase();
        startupElement();
        tragCursor = sqLite.rawQuery("SELECT " + tragDatabase.COL_TABLE_HIGH_SCORE + " FROM " + tragDatabase.TABLE_NAME + ";", null);

        tragCursor.moveToFirst();
        scoreEarned = tragCursor.getInt(tragCursor.getColumnIndex(tragDatabase.COL_TABLE_HIGH_SCORE));
        scoreForNormal.setText(scoreEarned + "");
        if (scoreEarned != 0) {
            lvlNormalMode.setText(MODE_A.levelEarned(scoreEarned).replace("ระดับ ", "ระดับสูงสุดที่ปัจจุบันทำได้ "));
        } else {
            lvlNormalMode.setText("ยังไม่มีการสร้างสถิติคะแนนที่สูงกว่า 0");
        }

        tragCursor.moveToNext();
        scoreEarned = tragCursor.getInt(tragCursor.getColumnIndex(tragDatabase.COL_TABLE_HIGH_SCORE));
        scoreForNonStop.setText(scoreEarned + "");
        if (scoreEarned != 0) {
            lvlNonStopMode.setText("เกรด : " + MODE_B.levelEarned(scoreEarned));
        } else {
            lvlNonStopMode.setText("ยังไม่มีการสร้างสถิติคะแนนเท่ากับกว่า 0");
        }

        tragCursor = sqLite.rawQuery("SELECT * FROM " + tragDatabase.TABLE_CUMULATIVE + " WHERE factor_id = 1;", null);
        tragCursor.moveToFirst();
        totalTaps = tragCursor.getInt(tragCursor.getColumnIndex(tragDatabase.COL_TABLE_CUMULATIVE_INT_VALUE));
        if (totalTaps != 0) {
            this.totalTaps.setText("::::: " + totalTaps);
        } else {
            this.totalTaps.setText("ยังไม่มีผลลัพธ์");
        }
    }

    public void startupElement() {
        scoreForNormal = (TextView) findViewById(R.id.score_normal_mode);
        scoreForNonStop = (TextView) findViewById(R.id.score_non_stop_mode);
        lvlNormalMode = (TextView) findViewById(R.id.level_normal_mode);
        lvlNonStopMode = (TextView) findViewById(R.id.level_non_stop_mode);
        txtHighScoreNormalMode = (TextView) findViewById(R.id.txt_high_score_normal_mode);
        txtHighScoreNonStopMode = (TextView) findViewById(R.id.txt_high_score_non_stop_mode);
        txtTotalTaps = (TextView) findViewById(R.id.txt_total_taps);
        totalTaps = (TextView) findViewById(R.id.total_taps);
        listTop10 = (ListView) findViewById(R.id.list_top10);

        scoreForNormal.setTypeface(MainActivity.tragFont);
        scoreForNonStop.setTypeface(MainActivity.tragFont);
        lvlNormalMode.setTypeface(MainActivity.tragFont);
        lvlNonStopMode.setTypeface(MainActivity.tragFont);
        txtHighScoreNormalMode.setTypeface(MainActivity.tragFont);
        txtHighScoreNonStopMode.setTypeface(MainActivity.tragFont);
        txtTotalTaps.setTypeface(MainActivity.tragFont);
        totalTaps.setTypeface(MainActivity.tragFont);

//        new LoadTop10ScoreAsyncTask().execute();
        doLoadTop10List();
    }

    private ArrayList<Player> players;
    private PlayerAdapter playerAdapter;
    private static final String URL = "http://54.179.140.250:8080/HallOfTrag/App?opt=top10";
    private JsonObject top10ListJSON;
    private JsonArray playersJSON;
    private String s;

    public void doLoadTop10List() {
        try {
            players = Ion.with(LeaderBoardActivity.this)
                    .load(URL)
                    .as(new TypeToken<ArrayList<Player>>() {
                    })
                    .get();
//           s = Ion.with(LeaderBoardActivity.this)
//                    .load(URL)
//                    .asString()
////                    .get();
//            Log.v("top10ListJSON", playersJSON.getAsString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Player>>() {
//        }.getType();
//        players = gson.fromJson(playersJSON, type);
        Log.v("players", players.toString());
        playerAdapter = new PlayerAdapter(LeaderBoardActivity.this, R.layout.player_row, players);
        listTop10.setAdapter(playerAdapter);
    }


    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    class LoadTop10ScoreAsyncTask extends AsyncTask {
        private static final String topUrl = "http://54.179.140.250:8080/HallOfTrag/App?opt=top10";
        private JsonObject top10ListJSON;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                top10ListJSON = Ion.with(LeaderBoardActivity.this)
                        .load("topUrl")
                        .asJsonObject()
                        .get();
                Log.v("top10ListJSON", top10ListJSON.getAsString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Player>>() {
            }.getType();
            players = gson.fromJson(top10ListJSON, type);
            Log.v("players", players.toString());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

        }

    }
}
