package br.com.naka.dogs.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UtilsPreferences {

    // SHARED PREFERENCES NAME
    public static final String PREFERENCES_NAME = "SNIFF";
    private static final String TOKEN = "TOKEN";

    private static SharedPreferences getSharedPreference() {
        Context context = MyApplication.getAppContext();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs;
    }

    private static void saveSharedPreference(String key, String value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getSharedPreference(String key) {
        SharedPreferences prefs = getSharedPreference();
        String pref = prefs.getString(key, null);
        return pref;
    }

    public static String getToken(){
        String savedValue = getSharedPreference(TOKEN);
        return (TextUtils.isEmpty(savedValue))? "" : savedValue;
    }

    public static void setToken (String json){
        saveSharedPreference(TOKEN, json);

    }
}
