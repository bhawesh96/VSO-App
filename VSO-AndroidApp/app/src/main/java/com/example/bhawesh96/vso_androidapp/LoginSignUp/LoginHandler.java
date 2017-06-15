package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.util.Log;

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

public class LoginHandler
{
    private static final String REGISTER_URL = "http://vso.manipal.edu/Login.aspx/";
    private static final String KEY_VIEWSTATE = "__VIEWSTATE";
    private static final String KEY_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    private static final String KEY_EVENTVALIDATION = "__EVENTVALIDATION";
    private static final String KEY_CAMPUS = "ctl00$ContentPlaceHolder1$Login_Campus";
    private static final String KEY_ID = "ctl00$ContentPlaceHolder1$Volunteer_ID_Login_Tbx";
    private static final String KEY_PASS = "ctl00$ContentPlaceHolder1$Password_Login_Tbx";
    private static final String KEY_BUTTON = "ctl00$ContentPlaceHolder1$Login_Btn";

    private static final String campus = "MPL";
    private static final String viewState = "/wEPDwUKMTk1OTE1Mzc2OQ9kFgJmD2QWAgIFD2QWAgIBD2QWAgIDDxAPFgIeC18hRGF0YUJvdW5kZ2QPFgICAQICFgIQBQlNYW5nYWxvcmUFA01HTGcQBQdNYW5pcGFsBQNNUExnZGRkCs/N/z+4nhISjlCDErdPtqt8kC4qFYl0PnkC2IYC4iM=";
    private static final String viewGenerator = "C2EE9ABB";
    private static final String eventValidation = "/wEdAAf2N/1ZHhNNGXpFLo0PxzmSZp4Dw4ucrLwCygR207pI8zcXm7ev/8YxdK4ssia8AjCPTB4vylHYdZV5RrEfPehzGrar4K3Blz4Hip0eDIeAc16qyCBm1uK60xFA5GT+ahKJ1pJj3o6akd704EfIC1nT9AJ0LLqxkU/VY3HaracQxpjrt6b9Lb3jDMXkZPBL0Zw=";
//    private static final String id = "VSO_2016100082";
//    private static final String pass = "vso2016100082";
    private static final String button = "Login";


    public static boolean getResponse(String uname, String password)
    {
        Log.e("LoginHandler", "Sending GET request");
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        try
        {
            HttpGet httpget = new HttpGet(REGISTER_URL);
            httpclient.execute(httpget);

            Log.e("LoginHandler", "GET request completed");
            List<Cookie> cookies = cookieStore.getCookies();
            if (cookies.isEmpty())
                Log.e("LoginHandler", "Couldn't get session ID");

            Log.e("LoginHandler", "Sending POST request");
            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI(REGISTER_URL))
                    .addParameter(KEY_CAMPUS, campus)
                    .addParameter(KEY_VIEWSTATE, viewState)
                    .addParameter(KEY_VIEWSTATEGENERATOR, viewGenerator)
                    .addParameter(KEY_EVENTVALIDATION, eventValidation)
                    .addParameter(KEY_ID, uname)
                    .addParameter(KEY_PASS, password)
                    .addParameter(KEY_BUTTON, button)
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);

            Log.e("post", "Login form get: " + response2.getStatusLine());
            if (response2.getStatusLine().toString().contains("302"))
                return true;
        }
        catch (Exception e)
        {
            Log.e("post", Log.getStackTraceString(e));
        }
        return false;
    }
}
