package com.example.bhawesh96.vso_androidapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.util.List;

public class Post extends Activity
{
    private static final String REGISTER_URL = "http://vso.manipal.edu/Login.aspx/";
    public static final String KEY_VIEWSTATE = "__VIEWSTATE";
    public static final String KEY_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String KEY_EVENTVALIDATION = "__EVENTVALIDATION";
    public static final String KEY_CAMPUS = "ctl00$ContentPlaceHolder1$Login_Campus";
    public static final String KEY_ID = "ctl00$ContentPlaceHolder1$Volunteer_ID_Login_Tbx";
    public static final String KEY_PASS = "ctl00$ContentPlaceHolder1$Password_Login_Tbx";
    public static final String KEY_BUTTON = "ctl00$ContentPlaceHolder1$Login_Btn";

    public static final String campus = "MPL";
    public static final String viewState = "/wEPDwUKMTk1OTE1Mzc2OQ9kFgJmD2QWAgIFD2QWAgIBD2QWAgIDDxAPFgIeC18hRGF0YUJvdW5kZ2QPFgICAQICFgIQBQlNYW5nYWxvcmUFA01HTGcQBQdNYW5pcGFsBQNNUExnZGRkCs/N/z+4nhISjlCDErdPtqt8kC4qFYl0PnkC2IYC4iM=";
    public static final String viewGenerator = "C2EE9ABB";
    public static final String eventValidation = "/wEdAAf2N/1ZHhNNGXpFLo0PxzmSZp4Dw4ucrLwCygR207pI8zcXm7ev/8YxdK4ssia8AjCPTB4vylHYdZV5RrEfPehzGrar4K3Blz4Hip0eDIeAc16qyCBm1uK60xFA5GT+ahKJ1pJj3o6akd704EfIC1nT9AJ0LLqxkU/VY3HaracQxpjrt6b9Lb3jDMXkZPBL0Zw=";
    public static final String id = "VSO_2016100082";
    public static final String pass = "vso2016100082";
    public static final String button = "Login";

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_post);

        textView = (TextView) findViewById(R.id.textView);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params)
            {
                try2();
                return null;
            }
        };
        task.execute();
    }

    private static void try2()
    {
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        try
        {
            HttpGet httpget = new HttpGet(REGISTER_URL);
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            HttpEntity entity = response1.getEntity();

            Log.e("post", "Login form get: " + response1.getStatusLine());
            entity.getContent();

            Log.e("post", "Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty())
                Log.e("post", "None");
            else
                for (int i = 0; i < cookies.size(); i++)
                    Log.e("post", "- " + cookies.get(i).toString());

            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI(REGISTER_URL))
                    .addParameter(KEY_CAMPUS, campus)
                    .addParameter(KEY_VIEWSTATE, viewState)
                    .addParameter(KEY_VIEWSTATEGENERATOR, viewGenerator)
                    .addParameter(KEY_EVENTVALIDATION, eventValidation)
                    .addParameter(KEY_ID, id)
                    .addParameter(KEY_PASS, pass)
                    .addParameter(KEY_BUTTON, button)
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);

            HttpEntity entity1 = response2.getEntity();

            Log.e("post", "Login form get: " + response2.getStatusLine());
            entity1.getContent();

            Log.e("post", "Post logon cookies:");
            List<Cookie> cookies1 = cookieStore.getCookies();
            if (cookies1.isEmpty())
                Log.e("post", "None");
            else
                for (int i = 0; i < cookies1.size(); i++)
                    Log.e("post", "- " + cookies1.get(i).toString());
        }
        catch (Exception e)
        {
            Log.e("post", Log.getStackTraceString(e));
        }
    }
}
