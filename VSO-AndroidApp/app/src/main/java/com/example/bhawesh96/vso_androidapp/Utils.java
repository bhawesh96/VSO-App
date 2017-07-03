package com.example.bhawesh96.vso_androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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

        public static String FNAME;
        public static String LNAME;
        public static String EMAIL;
        public static String CONTACT;

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

//        public void signUp1(Person person) {
//            editor.putString(FNAME, person.getFirstName());
//            editor.putString(LNAME, lname);
//            editor.putString(EMAIL, email);
//            editor.putString(CONTACT, contact);
//            editor.commit();
//        }

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

    public static void dropDownSetup(Spinner spinner, Activity activity, String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item)
        {

            @Override
            @NonNull
            public View getView(int position, View convertView, @NonNull ViewGroup parent)
            {

                View v = super.getView(position, convertView, parent);
                if (position == getCount())
                {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                    ((TextView)v.findViewById(android.R.id.text1)).setHintTextColor(Color.parseColor("#FFFFFF"));
                }

                return v;
            }

            @Override
            public int getCount()
            {
                return super.getCount()-1; // you don't display last item. It is used as hint.
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(array);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ((TextView)view).setTextColor(Color.parseColor("#FFFFFF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
