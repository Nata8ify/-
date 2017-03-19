package com.sleeplesstofu.quartierlatin.trag;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by QuartierLatin on 5/3/2559.
 */
public class Trag extends Application {
    private static Context tragContexxt;
    public Typeface RAI_NGAN_FONT_FACE = Typeface.createFromAsset(this.getAssets(),"fonts/RaiNgan.ttf");

    @Override
    public void onCreate() {
        super.onCreate();
        Trag.tragContexxt = getApplicationContext();
    }

    public static Context getTragContext(){
        return Trag.tragContexxt;
    }

}
