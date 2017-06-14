package com.example.bhawesh96.vso_androidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.accounts.AccountManager.KEY_PASSWORD;

public class PostMan extends AppCompatActivity {

    TextView textView2;

    public static final String REGISTER_URL = "http://vso.manipal.edu/Login.aspx";
    public static final String KEY_VIEWSTATE = "__VIEWSTATE";
    public static final String KEY_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String KEY_EVENTVALIDATION = "__EVENTVALIDATION";
    public static final String KEY_CAMPUS = "ctl00$ContentPlaceHolder1$Login_Campus";
    public static final String KEY_ID = "ctl00$ContentPlaceHolder1$Volunteer_ID_Login_Tbx";
    public static final String KEY_PASS = "ctl00$ContentPlaceHolder1$Password_Login_Tbx";
    public static final String KEY_BUTTON = "ctl00$ContentPlaceHolder1$Login_Btn";

    String campus = "MPL";
    String viewState = "/wEPDwUKMTk1OTE1Mzc2OQ9kFgJmD2QWAgIFD2QWAgIBD2QWAgIDDxAPFgIeC18hRGF0YUJvdW5kZ2QPFgICAQICFgIQBQlNYW5nYWxvcmUFA01HTGcQBQdNYW5pcGFsBQNNUExnZGRkCs/N/z+4nhISjlCDErdPtqt8kC4qFYl0PnkC2IYC4iM=";
    String viewGenerator = "C2EE9ABB";
    String eventValidation = "/wEdAAf2N/1ZHhNNGXpFLo0PxzmSZp4Dw4ucrLwCygR207pI8zcXm7ev/8YxdK4ssia8AjCPTB4vylHYdZV5RrEfPehzGrar4K3Blz4Hip0eDIeAc16qyCBm1uK60xFA5GT+ahKJ1pJj3o6akd704EfIC1nT9AJ0LLqxkU/VY3HaracQxpjrt6b9Lb3jDMXkZPBL0Zw=";
    String id = "VSO_2016100082";
    String pass = "vso2016100082";
    String button = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_man);
        textView2 = (TextView) findViewById(R.id.textView2);
        registerUser();
    }


    private void registerUser() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView2.setText(response);
//                        Toast.makeText(PostMan.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostMan.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_CAMPUS, campus);
                params.put(KEY_VIEWSTATE, viewState);
                //params.put(KEY_VIEWSTATEGENERATOR, viewGenerator);
                params.put(KEY_EVENTVALIDATION, eventValidation);
                params.put(KEY_ID, id);
                params.put(KEY_PASS, pass);
                params.put(KEY_BUTTON, button);

                return params;
            }
        };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
