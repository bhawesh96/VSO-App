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

public class SignupHandler
{
    private static final String REGISTER_URL = "http://vso.manipal.edu/SignUp.aspx";
    public static final String KEY_EVENTTARGET = "__EVENTTARGET";
    public static final String KEY_EVENTARGUMENT = "__EVENTARGUMENT";
    public static final String KEY_LASTFOCUS = "__LASTFOCUS";
    public static final String KEY_VIEWSTATE = "__VIEWSTATE";
    public static final String KEY_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String KEY_SCROLLPOSITIONX = "__SCROLLPOSITIONX";
    public static final String KEY_SCROLLPOSITIONY = "__SCROLLPOSITIONY";
    public static final String KEY_EVENTVALIDATION = "__EVENTVALIDATION";
    public static final String KEY_CAMPUS = "ctl00$ContentPlaceHolder1$SignUp_Campus";
    public static final String KEY_FIRSTNAME = "ctl00$ContentPlaceHolder1$FirstName_Tbx";
    public static final String KEY_LASTNAME = "ctl00$ContentPlaceHolder1$LastName_Tbx";
    public static final String KEY_EMAIL = "ctl00$ContentPlaceHolder1$Email_Tbx";
    public static final String KEY_DESIGNATION = "ctl00$ContentPlaceHolder1$Designation_DD";
    public static final String KEY_CONTACT = "ctl00$ContentPlaceHolder1$Contact_No_Tbx";
    public static final String KEY_GENDER = "ctl00$ContentPlaceHolder1$Gender_DD";
    public static final String KEY_BLOODGROUP = "ctl00$ContentPlaceHolder1$Blood_Group_DD";
    public static final String KEY_DOB = "ctl00$ContentPlaceHolder1$DOB_Tbx";
    public static final String KEY_NATIONALITY = "ctl00$ContentPlaceHolder1$Nationality_DD";
    public static final String KEY_PERMADDRESS = "ctl00$ContentPlaceHolder1$Permanent_Address_Tbx";
    public static final String KEY_PRESENTADDRESS = "ctl00$ContentPlaceHolder1$Present_Address_Tbx";
    public static final String KEY_SKILLS = "ctl00$ContentPlaceHolder1$Specific_Skills_Tbx";
    public static final String KEY_KANNADA = "ctl00$ContentPlaceHolder1$DropDownList1_Kannada";
    public static final String KEY_IMAGE = "ctl00$ContentPlaceHolder1$File_Upload_1";
    public static final String KEY_TERMS = "ctl00$ContentPlaceHolder1$Terms_And_Conditions_Cbx";
    public static final String KEY_BUTTON = "ctl00$ContentPlaceHolder1$SignUp_Btn";


    public static final String eventTarget = "";
    public static final String eventArgument = "";
    public static final String lastFocus = "";
    public static final String viewStateold = "/wEPDwUKMTk1OTE1Mzc2OQ9kFgJmD2QWAgIFD2QWAgIBD2QWAgIDDxAPFgIeC18hRGF0YUJvdW5kZ2QPFgICAQICFgIQBQlNYW5nYWxvcmUFA01HTGcQBQdNYW5pcGFsBQNNUExnZGRkCs/N/z+4nhISjlCDErdPtqt8kC4qFYl0PnkC2IYC4iM=";
    public static final String viewState = "/wEPDwUKLTc4MzQ4NTY4OQ9kFgJmD2QWAgIFD2QWAgIBDxYCHgdlbmN0eXBlBRNtdWx0aXBhcnQvZm9ybS1kYXRhFhYCAw8QDxYCHgtfIURhdGFCb3VuZGdkDxYCAgECAhYCEAUJTWFuZ2Fsb3JlBQNNR0xnEAUHTWFuaXBhbAUDTVBMZ2RkAhUPEA8WAh8BZ2QPFgYCAQICAgMCBAIFAgYWBhAFDVN0dWRlbnQgb2YgTVUFBkRlc18wMWcQBRZSZXRpcmVkIEVtcGxveWVlIG9mIE1VBQZEZXNfMDJnEAUNRmFjdWx0eSBvZiBNVQUGRGVzXzAzZxAFFVNwb3VzZSBvZiBNVSBFbXBsb3llZQUGRGVzXzA0ZxAFBlB1YmxpYwUGRGVzXzA1ZxAFDkVtcGxveWVlIG9mIE1VBQZEZXNfMDZnFgFmZAIZDxYCHgdWaXNpYmxlaBYCAgMPEGRkFgFmZAIbDxYCHwJoFgICAw8WAh4FdmFsdWVkZAIdDxYCHwJoFgICAQ9kFgQCAQ8QZGQWAWZkAgMPD2QPEBYBZhYBFgIeDlBhcmFtZXRlclZhbHVlZRYBZmRkAh8PFgIfAmgWAgIDDxYCHwNkZAIpDxYCHwNkZAIrDxAPFgIfAWdkEBXkAQtOYXRpb25hbGl0eQxBZmdoYW5pc3RhbiAIQXJtZW5pYSAMSXNsZSBvZiBNYW4gB0lzcmFlbCAGSXRhbHkgCEphbWFpY2EgBkphcGFuIAdKZXJzZXkgB0pvcmRhbiALS2F6YWtoc3RhbiAGS2VueWEgCUtpcmliYXRpIAZBcnViYSANS29yZWEsIE5vcnRoIA1Lb3JlYSwgU291dGggB0t1d2FpdCALS3lyZ3l6c3RhbiAFTGFvcyAHTGF0dmlhIAhMZWJhbm9uIAhMZXNvdGhvIAhMaWJlcmlhIAZMaWJ5YSAKQXVzdHJhbGlhIA5MaWVjaHRlbnN0ZWluIApMaXRodWFuaWEgC0x1eGVtYm91cmcgBk1hY2F1IApNYWNlZG9uaWEgC01hZGFnYXNjYXIgB01hbGF3aSAJTWFsYXlzaWEgCU1hbGRpdmVzIAVNYWxpIAhBdXN0cmlhIAZNYWx0YSARTWFyc2hhbGwgSXNsYW5kcyALTWFydGluaXF1ZSALTWF1cml0YW5pYSAKTWF1cml0aXVzIAhNYXlvdHRlIAdNZXhpY28gFU1pY3JvbmVzaWEsIEZlZC4gU3QuIAhNb2xkb3ZhIAdNb25hY28gC0F6ZXJiYWlqYW4gCU1vbmdvbGlhIAtNb250c2VycmF0IAhNb3JvY2NvIAtNb3phbWJpcXVlIAhOYW1pYmlhIAZOYXVydSAGTmVwYWwgDE5ldGhlcmxhbmRzIBVOZXRoZXJsYW5kcyBBbnRpbGxlcyAOTmV3IENhbGVkb25pYSANQmFoYW1hcywgVGhlIAxOZXcgWmVhbGFuZCAKTmljYXJhZ3VhIAZOaWdlciAITmlnZXJpYSATTi4gTWFyaWFuYSBJc2xhbmRzIAdOb3J3YXkgBU9tYW4gCVBha2lzdGFuIAZQYWxhdSAHUGFuYW1hIAhCYWhyYWluIBFQYXB1YSBOZXcgR3VpbmVhIAlQYXJhZ3VheSAFUGVydSAMUGhpbGlwcGluZXMgB1BvbGFuZCAJUG9ydHVnYWwgDFB1ZXJ0byBSaWNvIAZRYXRhciAIUmV1bmlvbiAIUm9tYW5pYSALQmFuZ2xhZGVzaCAHUnVzc2lhIAdSd2FuZGEgDVNhaW50IEhlbGVuYSAUU2FpbnQgS2l0dHMgJiBOZXZpcyAMU2FpbnQgTHVjaWEgFVN0IFBpZXJyZSAmIE1pcXVlbG9uICFTYWludCBWaW5jZW50IGFuZCB0aGUgR3JlbmFkaW5lcyAGU2Ftb2EgC1NhbiBNYXJpbm8gFFNhbyBUb21lICYgUHJpbmNpcGUgCUJhcmJhZG9zIA1TYXVkaSBBcmFiaWEgCFNlbmVnYWwgB1NlcmJpYSALU2V5Y2hlbGxlcyANU2llcnJhIExlb25lIApTaW5nYXBvcmUgCVNsb3Zha2lhIAlTbG92ZW5pYSAQU29sb21vbiBJc2xhbmRzIAhTb21hbGlhIAhCZWxhcnVzIA1Tb3V0aCBBZnJpY2EgBlNwYWluIApTcmkgTGFua2EgBlN1ZGFuIAlTdXJpbmFtZSAKU3dhemlsYW5kIAdTd2VkZW4gDFN3aXR6ZXJsYW5kIAZTeXJpYSAHVGFpd2FuIAhBbGJhbmlhIAhCZWxnaXVtIAtUYWppa2lzdGFuIAlUYW56YW5pYSAJVGhhaWxhbmQgBVRvZ28gBlRvbmdhIBJUcmluaWRhZCAmIFRvYmFnbyAIVHVuaXNpYSAHVHVya2V5IA1UdXJrbWVuaXN0YW4gElR1cmtzICYgQ2FpY29zIElzIAdCZWxpemUgB1R1dmFsdSAHVWdhbmRhIAhVa3JhaW5lIBVVbml0ZWQgQXJhYiBFbWlyYXRlcyAPVW5pdGVkIEtpbmdkb20gDlVuaXRlZCBTdGF0ZXMgCFVydWd1YXkgC1V6YmVraXN0YW4gCFZhbnVhdHUgClZlbmV6dWVsYSAGQmVuaW4gCFZpZXRuYW0gD1ZpcmdpbiBJc2xhbmRzIBJXYWxsaXMgYW5kIEZ1dHVuYSAKV2VzdCBCYW5rIA9XZXN0ZXJuIFNhaGFyYSAGWWVtZW4gB1phbWJpYSAJWmltYmFid2UgCEJlcm11ZGEgB0JodXRhbiAIQm9saXZpYSAVQm9zbmlhICYgSGVyemVnb3ZpbmEgCUJvdHN3YW5hIAdCcmF6aWwgE0JyaXRpc2ggVmlyZ2luIElzLiAIQWxnZXJpYSAHQnJ1bmVpIAlCdWxnYXJpYSANQnVya2luYSBGYXNvIAZCdXJtYSAIQnVydW5kaSAJQ2FtYm9kaWEgCUNhbWVyb29uIAdDYW5hZGEgC0NhcGUgVmVyZGUgD0NheW1hbiBJc2xhbmRzIA9BbWVyaWNhbiBTYW1vYSAVQ2VudHJhbCBBZnJpY2FuIFJlcC4gBUNoYWQgBkNoaWxlIAZDaGluYSAJQ29sb21iaWEgCENvbW9yb3MgEUNvbmdvLCBEZW0uIFJlcC4gFUNvbmdvLCBSZXB1Yi4gb2YgdGhlIA1Db29rIElzbGFuZHMgC0Nvc3RhIFJpY2EgCEFuZG9ycmEgDkNvdGUgZCdJdm9pcmUgCENyb2F0aWEgBUN1YmEgB0N5cHJ1cyAPQ3plY2ggUmVwdWJsaWMgCERlbm1hcmsgCURqaWJvdXRpIAlEb21pbmljYSATRG9taW5pY2FuIFJlcHVibGljIAtFYXN0IFRpbW9yIAdBbmdvbGEgCEVjdWFkb3IgBkVneXB0IAxFbCBTYWx2YWRvciASRXF1YXRvcmlhbCBHdWluZWEgCEVyaXRyZWEgCEVzdG9uaWEgCUV0aGlvcGlhIA5GYXJvZSBJc2xhbmRzIAVGaWppIAhGaW5sYW5kIAlBbmd1aWxsYSAHRnJhbmNlIA5GcmVuY2ggR3VpYW5hIBFGcmVuY2ggUG9seW5lc2lhIAZHYWJvbiAMR2FtYmlhLCBUaGUgC0dhemEgU3RyaXAgCEdlb3JnaWEgCEdlcm1hbnkgBkdoYW5hIApHaWJyYWx0YXIgEkFudGlndWEgJiBCYXJidWRhIAdHcmVlY2UgCkdyZWVubGFuZCAIR3JlbmFkYSALR3VhZGVsb3VwZSAFR3VhbSAKR3VhdGVtYWxhIAlHdWVybnNleSAHR3VpbmVhIA5HdWluZWEtQmlzc2F1IAdHdXlhbmEgCkFyZ2VudGluYSAGSGFpdGkgCUhvbmR1cmFzIApIb25nIEtvbmcgCEh1bmdhcnkgCEljZWxhbmQgBkluZGlhIApJbmRvbmVzaWEgBUlyYW4gBUlyYXEgCElyZWxhbmQgFeQBCkNvdW50cnlfSUQIY291bnRyeTEJY291bnRyeTEwCmNvdW50cnkxMDAKY291bnRyeTEwMQpjb3VudHJ5MTAyCmNvdW50cnkxMDMKY291bnRyeTEwNApjb3VudHJ5MTA1CmNvdW50cnkxMDYKY291bnRyeTEwNwpjb3VudHJ5MTA4CmNvdW50cnkxMDkJY291bnRyeTExCmNvdW50cnkxMTAKY291bnRyeTExMQpjb3VudHJ5MTEyCmNvdW50cnkxMTMKY291bnRyeTExNApjb3VudHJ5MTE1CmNvdW50cnkxMTYKY291bnRyeTExNwpjb3VudHJ5MTE4CmNvdW50cnkxMTkJY291bnRyeTEyCmNvdW50cnkxMjAKY291bnRyeTEyMQpjb3VudHJ5MTIyCmNvdW50cnkxMjMKY291bnRyeTEyNApjb3VudHJ5MTI1CmNvdW50cnkxMjYKY291bnRyeTEyNwpjb3VudHJ5MTI4CmNvdW50cnkxMjkJY291bnRyeTEzCmNvdW50cnkxMzAKY291bnRyeTEzMQpjb3VudHJ5MTMyCmNvdW50cnkxMzMKY291bnRyeTEzNApjb3VudHJ5MTM1CmNvdW50cnkxMzYKY291bnRyeTEzNwpjb3VudHJ5MTM4CmNvdW50cnkxMzkJY291bnRyeTE0CmNvdW50cnkxNDAKY291bnRyeTE0MQpjb3VudHJ5MTQyCmNvdW50cnkxNDMKY291bnRyeTE0NApjb3VudHJ5MTQ1CmNvdW50cnkxNDYKY291bnRyeTE0Nwpjb3VudHJ5MTQ4CmNvdW50cnkxNDkJY291bnRyeTE1CmNvdW50cnkxNTAKY291bnRyeTE1MQpjb3VudHJ5MTUyCmNvdW50cnkxNTMKY291bnRyeTE1NApjb3VudHJ5MTU1CmNvdW50cnkxNTYKY291bnRyeTE1Nwpjb3VudHJ5MTU4CmNvdW50cnkxNTkJY291bnRyeTE2CmNvdW50cnkxNjAKY291bnRyeTE2MQpjb3VudHJ5MTYyCmNvdW50cnkxNjMKY291bnRyeTE2NApjb3VudHJ5MTY1CmNvdW50cnkxNjYKY291bnRyeTE2Nwpjb3VudHJ5MTY4CmNvdW50cnkxNjkJY291bnRyeTE3CmNvdW50cnkxNzAKY291bnRyeTE3MQpjb3VudHJ5MTcyCmNvdW50cnkxNzMKY291bnRyeTE3NApjb3VudHJ5MTc1CmNvdW50cnkxNzYKY291bnRyeTE3Nwpjb3VudHJ5MTc4CmNvdW50cnkxNzkJY291bnRyeTE4CmNvdW50cnkxODAKY291bnRyeTE4MQpjb3VudHJ5MTgyCmNvdW50cnkxODMKY291bnRyeTE4NApjb3VudHJ5MTg1CmNvdW50cnkxODYKY291bnRyeTE4Nwpjb3VudHJ5MTg4CmNvdW50cnkxODkJY291bnRyeTE5CmNvdW50cnkxOTAKY291bnRyeTE5MQpjb3VudHJ5MTkyCmNvdW50cnkxOTMKY291bnRyeTE5NApjb3VudHJ5MTk1CmNvdW50cnkxOTYKY291bnRyeTE5Nwpjb3VudHJ5MTk4CmNvdW50cnkxOTkIY291bnRyeTIJY291bnRyeTIwCmNvdW50cnkyMDAKY291bnRyeTIwMQpjb3VudHJ5MjAyCmNvdW50cnkyMDMKY291bnRyeTIwNApjb3VudHJ5MjA1CmNvdW50cnkyMDYKY291bnRyeTIwNwpjb3VudHJ5MjA4CmNvdW50cnkyMDkJY291bnRyeTIxCmNvdW50cnkyMTAKY291bnRyeTIxMQpjb3VudHJ5MjEyCmNvdW50cnkyMTMKY291bnRyeTIxNApjb3VudHJ5MjE1CmNvdW50cnkyMTYKY291bnRyeTIxNwpjb3VudHJ5MjE4CmNvdW50cnkyMTkJY291bnRyeTIyCmNvdW50cnkyMjAKY291bnRyeTIyMQpjb3VudHJ5MjIyCmNvdW50cnkyMjMKY291bnRyeTIyNApjb3VudHJ5MjI1CmNvdW50cnkyMjYKY291bnRyeTIyNwljb3VudHJ5MjMJY291bnRyeTI0CWNvdW50cnkyNQljb3VudHJ5MjYJY291bnRyeTI3CWNvdW50cnkyOAljb3VudHJ5MjkIY291bnRyeTMJY291bnRyeTMwCWNvdW50cnkzMQljb3VudHJ5MzIJY291bnRyeTMzCWNvdW50cnkzNAljb3VudHJ5MzUJY291bnRyeTM2CWNvdW50cnkzNwljb3VudHJ5MzgJY291bnRyeTM5CGNvdW50cnk0CWNvdW50cnk0MAljb3VudHJ5NDEJY291bnRyeTQyCWNvdW50cnk0Mwljb3VudHJ5NDQJY291bnRyeTQ1CWNvdW50cnk0Ngljb3VudHJ5NDcJY291bnRyeTQ4CWNvdW50cnk0OQhjb3VudHJ5NQljb3VudHJ5NTAJY291bnRyeTUxCWNvdW50cnk1Mgljb3VudHJ5NTMJY291bnRyeTU0CWNvdW50cnk1NQljb3VudHJ5NTYJY291bnRyeTU3CWNvdW50cnk1OAljb3VudHJ5NTkIY291bnRyeTYJY291bnRyeTYwCWNvdW50cnk2MQljb3VudHJ5NjIJY291bnRyeTYzCWNvdW50cnk2NAljb3VudHJ5NjUJY291bnRyeTY2CWNvdW50cnk2Nwljb3VudHJ5NjgJY291bnRyeTY5CGNvdW50cnk3CWNvdW50cnk3MAljb3VudHJ5NzEJY291bnRyeTcyCWNvdW50cnk3Mwljb3VudHJ5NzQJY291bnRyeTc1CWNvdW50cnk3Ngljb3VudHJ5NzcJY291bnRyeTc4CWNvdW50cnk3OQhjb3VudHJ5OAljb3VudHJ5ODAJY291bnRyeTgxCWNvdW50cnk4Mgljb3VudHJ5ODMJY291bnRyeTg0CWNvdW50cnk4NQljb3VudHJ5ODYJY291bnRyeTg3CWNvdW50cnk4OAljb3VudHJ5ODkIY291bnRyeTkJY291bnRyeTkwCWNvdW50cnk5MQljb3VudHJ5OTIJY291bnRyeTkzCWNvdW50cnk5NAljb3VudHJ5OTUJY291bnRyeTk2CWNvdW50cnk5Nwljb3VudHJ5OTgJY291bnRyeTk5FCsD5AFnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dnZ2dkZAIvDxYCHglpbm5lcmh0bWxlZAIxDxYCHwVlZAIzDxYCHwVlZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAQUyY3RsMDAkQ29udGVudFBsYWNlSG9sZGVyMSRUZXJtc19BbmRfQ29uZGl0aW9uc19DYnh6QDbsPC4uxErceMxFUCNiFOMr+RGubr7fS4WEQJSVIw==";
    public static final String viewGenerator = "36F90C25";
    public static final String scrollPositionX = "0";
    public static final String scrollPositionY = "1400";
    public static final String eventValidation = "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";
//    public static final String campus = "MPL";
//    public static final String firstName = "Rakshit";
//    public static final String lastName = "Thakkar";
//    public static final String email = "rakshitthakkar97@gmail.com";
//    public static final String designation = "Des_01";
//    public static final String contact = "9714069339";
//    public static final String gender = "M";
//    public static final String bloodGroup = "A+";
//    public static final String dob = "1997-10-31";
//    public static final String nationality = "country95";
//    public static final String permAddress = "Room 415, Block 9, MIT Manipal";
//    public static final String presentAddress = "Room 415, Block 9, MIT Manipal";
//    public static final String skills = "Developing official mobile app for VSO, this is for testing";
//    public static final String kannada = "N";
//    public static final String terms = "on";
    public static final String button = "Sign Up";

    public static boolean getResponse(String campus, String firstName, String lastName, String email, String designation, String contact, String gender,
                                      String bloodGroup, String dob, String nationality, String permAddress, String presentAddress,
                                      String skills, String kannada, String terms)
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
            HttpUriRequest signup = RequestBuilder.post()
                    .setUri(new URI(REGISTER_URL))
                    .addParameter(KEY_EVENTTARGET, eventTarget)
                    .addParameter(KEY_EVENTARGUMENT, eventArgument)
                    .addParameter(KEY_LASTFOCUS, lastFocus)
                    .addParameter(KEY_VIEWSTATE, viewState)
                    .addParameter(KEY_VIEWSTATEGENERATOR, viewGenerator)
                    .addParameter(KEY_SCROLLPOSITIONX, scrollPositionX)
                    .addParameter(KEY_SCROLLPOSITIONY, scrollPositionY)
                    .addParameter(KEY_EVENTVALIDATION, eventValidation)
                    .addParameter(KEY_CAMPUS, campus)
                    .addParameter(KEY_FIRSTNAME, firstName)
                    .addParameter(KEY_LASTNAME, lastName)
                    .addParameter(KEY_EMAIL, email)
                    .addParameter(KEY_DESIGNATION, designation)
                    .addParameter(KEY_CONTACT, contact)
                    .addParameter(KEY_GENDER, gender)
                    .addParameter(KEY_BLOODGROUP, bloodGroup)
                    .addParameter(KEY_DOB, dob)
                    .addParameter(KEY_NATIONALITY, nationality)
                    .addParameter(KEY_PERMADDRESS, permAddress)
                    .addParameter(KEY_PRESENTADDRESS, presentAddress)
                    .addParameter(KEY_SKILLS, skills)
                    .addParameter(KEY_KANNADA, kannada)
                    .addParameter(KEY_IMAGE, "")
                    .addParameter(KEY_TERMS, terms)
                    .addParameter(KEY_BUTTON, button)
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(signup);

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
