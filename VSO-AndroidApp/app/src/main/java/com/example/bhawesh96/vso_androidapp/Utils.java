package com.example.bhawesh96.vso_androidapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils
{
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static class SessionManager
    {
        private Context _context;
        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;
        public static final String PREF_NAME = "USER_INFO";
        public static final String KEY_STATUS = "STATUS";

        public SessionManager(Context context)
        {
            this._context = context;
            preferences = _context.getSharedPreferences(PREF_NAME, 0);
            editor = preferences.edit();
        }

        public void createSession()
        {
            editor.putBoolean(KEY_STATUS, true);
            editor.commit();
        }

        public boolean isLoggedIn()
        {
            return preferences.getBoolean(KEY_STATUS, false);
        }

        public void logout()
        {
            editor.clear();
            editor.commit();
        }
    }
}
