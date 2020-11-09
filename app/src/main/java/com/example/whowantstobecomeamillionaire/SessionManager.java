package com.example.whowantstobecomeamillionaire;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class SessionManager {
    SharedPreferences shp;
    SharedPreferences.Editor edi;
    public static final String prefName = "userpref";

    public SessionManager(Context context){
        shp = context.getSharedPreferences(prefName,0);
        edi = shp.edit();
        edi.commit();
    }

    public void setScore(int score){
        edi.putInt("key_score", score);
        edi.commit();
    }
    public int getScore(){
        return shp.getInt("key_score", 0);
    }
}
