package com.sleeplesstofu.quartierlatin.trag.trag.extra;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by QuartierLatin on 14/3/2559.
 */
public class SoundPoolFullProperties {

    private SoundPool gameSoundPool;
    private Context gameContext;
    private int[] rawSet;

    public SoundPoolFullProperties(Context context, int maxStream) {
        this.gameContext = context;
        this.gameSoundPool = new SoundPool(maxStream, AudioManager.STREAM_MUSIC, 0);
    }

    public void addRawSet(int[] rawSet) {
        int i =0;
        for (int raw : rawSet) {
            rawSet[i++] = gameSoundPool.load(gameContext, raw, 0);
        }
    }

    public void playSoundInShortcut(int rawArrayChannel) {
        gameSoundPool.play(rawSet[rawArrayChannel],1,1,0,0,1);
    }
}
