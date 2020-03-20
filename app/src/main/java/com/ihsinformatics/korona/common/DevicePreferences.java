package com.ihsinformatics.korona.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.ihsinformatics.korona.model.Language;


public class DevicePreferences {


    public static final String SERVER_KEY = "server_address";
    public static final String PORT_KEY = "port_number";
    private static final String IS_FIRST_TIME = "isFirstTime";
    public static final String USER = "user";
    public static final String REMEMBER = "IS_REMEMBER";
    public static final String PROFILE = "profile";
    public static final String TOKEN = "Token";
    public static final String PUSH_TOKEN = "pushToken";

    public static final String LOTTERY_NOTIFICATION = "lotteryNotification";
    public static final String LANGUAGE = "language";
    public static final String ACTIVE_USER = "activeUser";
    SharedPreferences preferences;

    public DevicePreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    private void putInteger(String key, int data) {
        preferences.edit().putInt(key, data).apply();
    }

    private int getInteger(String key) {
        return preferences.getInt(key, 0);
    }

    private void putString(String key, String data) {
        preferences.edit().putString(key, data).apply();
    }

    private String getString(String key) {
        return preferences.getString(key, "");
    }

    private boolean getBoolean(String key) {
        return preferences.getBoolean(key, true);
    }

    public void saveLanguage(Language language) {
        String json = new Gson().toJson(language);
        preferences.edit().putString(LANGUAGE, json).apply();
    }

    public Language getLanguage() {
        String json = preferences.getString(LANGUAGE, "");
        Language language = new Gson().fromJson(json, Language.class);
        return language;
    }



    public boolean isFirstTime() {
        return preferences.getBoolean(IS_FIRST_TIME, true);
    }

    public void invalidateFirstTimeFlag() {
        preferences.edit().putBoolean(IS_FIRST_TIME, false).apply();
    }



}
