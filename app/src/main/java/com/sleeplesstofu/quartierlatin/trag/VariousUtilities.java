package com.sleeplesstofu.quartierlatin.trag;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by QuartierLatin on 3/2/2016.
 */
public class VariousUtilities {
    public static void backupDatabase() throws IOException {
        //Open your local db as the input stream
        String inFileName = "/data/data/com.example.quartierlatin.trag/databases/database.sqlite";
        File dbFile = new File(inFileName);
        FileInputStream fis = new FileInputStream(dbFile);

        String outFileName = Environment.getExternalStorageDirectory()
                + "/database.sqlite";
        //Open the empty db as the output stream
        OutputStream output = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        //Close the streams
        output.flush();
        output.close();
        fis.close();
    }
}
