package com.example.aniruddha1.webcast;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aniruddha 1 on 02-08-2015.
 */
public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final  ArrayList<String> web;
    private final ArrayList<String> Imgurl;

    //constructor
    public CustomList(Activity context, ArrayList<String> web, ArrayList<String> Imgurl)
    {
        super(context,R.layout.list_string , web);
        this.context=context;
        this.web=web;
        Log.d("CustomView", String.valueOf(web));
        this.Imgurl = Imgurl;
    }
    @Override
    public  View getView (int postion, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_string, null, true);
        TextView textView = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        textView.setText(web.get(postion));
        Picasso.with(context).load(Imgurl.get(postion)).fit().into(imageView);
        return rowView;
    }
}
