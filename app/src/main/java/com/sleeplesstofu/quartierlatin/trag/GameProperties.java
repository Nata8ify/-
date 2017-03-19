package com.sleeplesstofu.quartierlatin.trag;

import android.util.Log;

/**
 * Created by QuartierLatin on 10/2/2559.
 */
public class GameProperties {
    private String level;
    private int timeToClick;
    private int currentScoreEarn;
    private int maximumTaps;
    private int cellBlankActionCount;
    public int[] gameCellProperties(){


        timeToClick =0;
        difficultLevel();
        int[] numberSet = new int[9]; // ตำแหน่งที่เลขจะปรากฎทั้งเก้า
        int[] addBlankEvent = new int[cellBlankActionCount+5]; // การจองตำแหน่งที่เลขจะไม่ปรากฎ
        for (int i = 0; i < 9; i++) {
            numberSet[i] = (int) (Math.random() * (maximumTaps) + 1);
            timeToClick += numberSet[i];
        }

        String numStrSet = "";
        while (cellBlankActionCount <= 4) {
            int keepToChange = (int) (Math.random() * (8) + 0);
            for (int be : addBlankEvent) {
                numStrSet = numStrSet+be;
            }
            if (numStrSet.contains(keepToChange+"")) {

                continue;
            } else {
                addBlankEvent[cellBlankActionCount] = keepToChange;
                timeToClick -= numberSet[keepToChange];
                numberSet[keepToChange] = 0;
                ++cellBlankActionCount;
            }

        }
        Log.d("Turn click : ",""+timeToClick);
        return numberSet;
    }

    public int getTimeToTap(){
        return timeToClick-1;
    } // จำนวนครั้งที่ถือว่าแตะได้ทั้งหมด


    public void scoreForDifficult(int scoreEarned) {
        currentScoreEarn = scoreEarned;
    }

    public void resetAll() {
        cellBlankActionCount =0;
        maximumTaps = 5;
        currentScoreEarn = 0;
    }

    public void difficultLevel(){
        if(currentScoreEarn < 50){
            cellBlankActionCount = 0; //ว่างแค่ 5-0 ช่อง
            maximumTaps = 5; //เลขสูงสุด 6
        }
        else if(currentScoreEarn >= 50 && currentScoreEarn < 150){
            cellBlankActionCount = 0;
            maximumTaps = 6;
            //currentScoreEarn += 20;
        }
        else if(currentScoreEarn >= 150 && currentScoreEarn < 250){
            maximumTaps = 5; // เลขสูงสุด 5
            cellBlankActionCount = 1; // ว่างแค่ 5-1 ช่อง
           // currentScoreEarn += 30; // คะแนนพิเศษตามความยากที่เพิ่ม
        }
        else if(currentScoreEarn >= 250 && currentScoreEarn < 350){
            maximumTaps = 6;
            cellBlankActionCount = 1;
            //currentScoreEarn += 40;
        }
        else if(currentScoreEarn >= 350 && currentScoreEarn < 450){
            maximumTaps = 6; // เลขสูงสุด 6
            cellBlankActionCount = 2; //ว่างแค่ 5-2 ช่อง
            //currentScoreEarn += 50; // คะแนนพิเศษตามความยากที่เพิ่ม
        }
        else if(currentScoreEarn >= 450 && currentScoreEarn < 550){
            maximumTaps = 7;
            cellBlankActionCount = 1;
            //currentScoreEarn += 60;
        }
    }

    public String levelEarned(int scoreEarned) {

        if (scoreEarned < 50) {

            level = "ระดับ : ผู้ใช้ระดับพึ่งแตะสมาร์ทโฟน";
        } else if (scoreEarned >= 50 && scoreEarned < 150) {

            level = "ระดับ : ผู้แตะสมาร์ทโฟนเป็นปกติ";
        } else if (scoreEarned >= 150 && scoreEarned < 250) {

            level = "ระดับ : ผู้แตะสมาร์ทโฟน์บ่อย";
        } else if (scoreEarned >= 250 && scoreEarned < 350) {

            level = "ระดับ : ผู้แตะสมาร์ทโฟน์อย่างคล่องแคล่ว";
        } else if (scoreEarned >= 350 && scoreEarned < 450) {

            level = "ระดับ : ผู้แตะสมาร์ทโฟน์ชั้นแก่ประสบการณ์";
        } else if (scoreEarned >= 450 && scoreEarned < 550) {

            level = "ระดับ : ผู้เชี่ยวชาญการแตะ";
        } else if (scoreEarned >= 550 && scoreEarned < 650) {

            level = "ระดับ : ผู้แตะขั้นพระเจ้า";
        } else if (scoreEarned >= 650 && scoreEarned < 750) {

            level = "ระดับ : ผู้แตะขั้นปู่ทวดของพระเจ้าอีกที";
        } else if (scoreEarned >= 750 && scoreEarned < 8050) {

            level = "ระดับ : ศาสดาแห่งการแตะผู้จุติมาเพื่อแตะ";
        }
        return level;
    }
}
