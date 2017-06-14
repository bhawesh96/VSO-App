package com.example.bhawesh96.vso_androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by bhawesh96 on 13/6/17.
 */

public class Post extends Activity {


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_post);

        textView = (TextView) findViewById(R.id.textView);
        try2();
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

            System.out.println("Login form get: " + response1.getStatusLine());
//            EntityUtils.consume(entity);

            System.out.println("Initial set of cookies:");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty())
                System.out.println("None");
            else
                for (int i = 0; i < cookies.size(); i++)
                    System.out.println("- " + cookies.get(i).toString());

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

            System.out.println("Login form get: " + response2.getStatusLine());
//            EntityUtils.consume(entity1);
//            textView.setText(response2.getEntity().toString());

            System.out.println("Post logon cookies:");
            List<Cookie> cookies1 = cookieStore.getCookies();
            if (cookies1.isEmpty())
                System.out.println("None");
            else
                for (int i = 0; i < cookies1.size(); i++)
                    System.out.println("- " + cookies1.get(i).toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
