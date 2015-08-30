package com.example.aniruddha1.webcast;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Aniruddha 1 on 04-08-2015.
 */
public class DetailsBackend {
    int postion;
    public String name;
    public String summary;
    public double  rating;
    public URL detailurl;
    String imgurl;
    public DetailsBackend(int postion)
    {
        this.postion=postion;
        try {
            detailurl= new URL("http://api.tvmaze.com/shows/" + this.postion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException, JSONException {
        URLConnection urlConnection = detailurl.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String string;
        StringBuilder builder = new StringBuilder();
        while ((string = bufferedReader.readLine()) != null) {
            builder.append(string);
        }
        string=builder.toString();

        JSONObject jsonObject = new JSONObject(string);
        name = jsonObject.getString("name");
        summary = jsonObject.getString("summary");
        summary = android.text.Html.fromHtml(summary).toString();
        JSONObject reuse = jsonObject.getJSONObject("rating");
        rating = reuse.getDouble("average");
        reuse = jsonObject.getJSONObject("image");
        imgurl = reuse.getString("original");
        Log.d("Backend Image",imgurl);
    }
}
