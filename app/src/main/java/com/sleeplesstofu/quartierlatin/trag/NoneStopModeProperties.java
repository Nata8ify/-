package com.sleeplesstofu.quartierlatin.trag;

import android.util.Log;

/**
 * Created by QuartierLatin on 14/2/2559.
 */
public class NoneStopModeProperties {
    private String[] tapButtonSet;
    private int minimumNumber;

    public NoneStopModeProperties(){
        tapButtonSet = new String[9];
    }

    public String[] realTimeGenerate(String[] availableTapSet,int scoreEarned){
        String availableSet ="";
        minimumNumber = 1;
        for(int i = 0; i < 9 ; i++){
            if(availableTapSet[i].toString().equals("")){

                availableSet = availableSet+i;

            }
            else{
                Log.d("availableTapSet["+i+"] :",availableTapSet[i]);
            }
        }
        //-->Problem?//
        int chopIntegerAt = ((int) (Math.random() * ((availableSet.length()-1) - 0 + 1) + 0));
        Log.d("chopIntegerAt".toUpperCase(),chopIntegerAt+"");
        int putToButton = Integer.parseInt(availableSet.charAt((chopIntegerAt))+"");
        availableSet = availableSet.replace(availableSet.charAt((chopIntegerAt))+"","");
        Log.d("availableSet".toUpperCase(),availableSet);


        if(scoreEarned < 50){ // lv 0
            availableTapSet[putToButton] = (int)(Math.random()*(4-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 50 && scoreEarned < 150){ // lv 1
            minimumNumber = 2;
            availableTapSet[putToButton] = (int)(Math.random()*(5-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 150 && scoreEarned < 250){ // lv 2
            minimumNumber = 3;
            availableTapSet[putToButton] = (int)(Math.random()*(6-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 250 && scoreEarned < 350){ // lv 3
            minimumNumber = 4;
            availableTapSet[putToButton] = (int)(Math.random()*(7-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 350 && scoreEarned < 450){ // lv 4
            minimumNumber = 5;
            availableTapSet[putToButton] = (int)(Math.random()*(8-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 450 && scoreEarned < 550){ // lv 5
            minimumNumber = 6;
            availableTapSet[putToButton] = (int)(Math.random()*(9-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 550 && scoreEarned < 650){ // lv 6
            minimumNumber = 7;
            availableTapSet[putToButton] = (int)(Math.random()*(10-minimumNumber+1)+minimumNumber)+"";
        }
        else if(scoreEarned >= 650 && scoreEarned < 1050){ // lv 7
            minimumNumber = 8;
            availableTapSet[putToButton] = (int)(Math.random()*(10-minimumNumber+1)+minimumNumber)+"";
        }

        return availableTapSet;
    }


    public String[] buttonStartup(){
        String buttonContains = "";
        for(int btns = 0 ; btns < 9 ; btns++ ){
            tapButtonSet[btns] = "";
        }
        for(int randomTime = 0 ; randomTime < 3 ; randomTime++){
            int buttonNumber = (int)(Math.random()*(8-0+1)+0);
            String buttonValue = (int)(Math.random()*(4)+1)+"";
            if(!buttonContains.contains(buttonNumber+"")){
                tapButtonSet[buttonNumber] = buttonValue;
                buttonContains = buttonContains.concat(buttonNumber+"");
            }
            else{
                randomTime--;
                continue;
            }
        }
        return tapButtonSet;
    }



    public String levelEarned(int scoreEarned){
        if(scoreEarned < 20){
            return "F";
        }
        else if(scoreEarned < 50){ // lv 0
            return "D";
        }
        else if(scoreEarned >= 50 && scoreEarned < 150){ // lv 1
            return "C";
        }
        else if(scoreEarned >= 150 && scoreEarned < 250){ // lv 2
            return "C+";
        }
        else if(scoreEarned >= 250 && scoreEarned < 350){ // lv 3
            return "B";
        }
        else if(scoreEarned >= 350 && scoreEarned < 450){ // lv 4
            return "B+";
        }
        else if(scoreEarned >= 450 && scoreEarned < 550){ // lv 5
            return "A";
        }
        else if(scoreEarned >= 550 && scoreEarned < 650){ // lv 6
            return "A+";
        }
        else if(scoreEarned >= 650 && scoreEarned < 1050){ // lv 7
            return "S";
        }
        else{
            return "SS";
        }

    }

}
