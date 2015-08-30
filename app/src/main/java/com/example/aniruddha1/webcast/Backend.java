package com.example.aniruddha1.webcast;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Aniruddha 1 on 03-08-2015.
 */
public class Backend{
    ArrayList <String> name = new ArrayList<>();
    ArrayList<String> imgurl = new ArrayList<>();
    ArrayList<Integer>id = new ArrayList<>();
    public String connect() throws MalformedURLException, IOException {
        URL url = new URL("http://api.tvmaze.com/shows");
        URLConnection uRLConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
        String string;
        StringBuilder builder = new StringBuilder();
        while ((string = bufferedReader.readLine()) != null) {
            builder.append(string);
        }
        return builder.toString();
    }

    public void parseJson(String input) throws JSONException {

        JSONArray jarray = new JSONArray(input);
        for (int i = 0; i < jarray.length(); i++) {
            JSONObject jSONObject = jarray.getJSONObject(i);
            JSONObject jobj2 = jSONObject.getJSONObject("image");
            name.add(jSONObject.getString("name"));
            imgurl.add( jobj2.getString("original"));
            id.add(jSONObject.getInt("id"));
        }
    }
    public ArrayList<String> getName()
    {
        return name;
    }

    public ArrayList<String> getImgurl()
    {
        return imgurl;
    }
    public ArrayList<Integer> getId()
    {
        return id;
    }

}
