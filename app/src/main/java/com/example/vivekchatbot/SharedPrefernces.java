package com.example.vivekchatbot;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vivekchatbot.utility.Constants;

public class SharedPrefernces {

    SharedPreferences sp;

    public SharedPrefernces(Context context) {
        sp =context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setValueString(String key,String param){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,param);
        editor.apply();
    }

    public String getValueString(String key){return sp.getString(key,"None");}

    public void setValueInt(String key,int param){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,param);
        editor.apply();
    }
    public int getValueInt(String key){return sp.getInt(key,0);}

    public void setValueBool(String key,boolean param){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,param);
        editor.apply();
    }
    public boolean getValueBool(String key){return sp.getBoolean(key,false);}
}
