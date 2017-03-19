package com.sleeplesstofu.quartierlatin.trag.trag.extra;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sleeplesstofu.quartierlatin.trag.MainActivity;
import com.sleeplesstofu.quartierlatin.trag.R;
import com.sleeplesstofu.quartierlatin.trag.trag.Player;

import java.util.ArrayList;

/**
 * Created by QuartierLatin on 24/8/2559.
 */
public class PlayerAdapter extends ArrayAdapter {

    private int resId;
    private Context context;
    private ArrayList<Player> players;

    public PlayerAdapter(Context context, int resource, ArrayList<Player> players) {
        super(context, resource, players);
        this.players = players;
        this.resId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        row = layoutInflater.inflate(resId, null);
        TextView txtNumber = (TextView) row.findViewById(R.id.txt_number);
        TextView txtPlayerName = (TextView) row.findViewById(R.id.txt_player_name);
        TextView txtScore = (TextView) row.findViewById(R.id.txt_score);
        Log.v("Player", players.toString());
        txtNumber.setTypeface(MainActivity.tragFont);
        txtPlayerName.setTypeface(MainActivity.tragFont);
        txtScore.setTypeface(MainActivity.tragFont);
        txtNumber.setText(position + 1 + "");
        txtPlayerName.setText(players.get(position).getPlayerName());
        txtScore.setText(players.get(position).getHighScore() + "");

        return row;
    }
}
