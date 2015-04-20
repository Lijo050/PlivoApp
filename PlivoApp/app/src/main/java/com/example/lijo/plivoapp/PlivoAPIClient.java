package com.example.lijo.plivoapp;

import android.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by lijo on 20/4/15.
 */
public class PlivoAPIClient {

    //Here MAZGZIMJFHZDJLOTJKYW is my AUTH ID and NmQ2Y2NmOWNhYTBhNzA3MTg1NDNjYWU0NGFjM2Jk is my AUTH TOKEN.

    private static final String URL = "https://api.plivo.com/v1/Account/MAZGZIMJFHZDJLOTJKYW/Call/";
    private static final String basicAuth = "Basic " + Base64.encodeToString("MAZGZIMJFHZDJLOTJKYW:NmQ2Y2NmOWNhYTBhNzA3MTg1NDNjYWU0NGFjM2Jk".getBytes(), Base64.NO_WRAP);

    public static boolean executePostCall(String to){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        post.setHeader("Authorization", basicAuth);
        JSONObject request = new JSONObject();

        try {
            request.put("from", "917353659758");  //this is the phone number from which call is made
            request.put("to", to);
            request.put("answer_url", "http://plivoapp.hostingsiteforfree.com/speak.xml"); //this site host the xml which speaks "Welcome to Jungle!"
            request.put("answer_method", "GET");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(request.toString());
            stringEntity.setContentType("application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        post.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.getStatusLine().getStatusCode() == 200) {
            return true;
        }
        return false;
    }
}
