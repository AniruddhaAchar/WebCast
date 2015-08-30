package com.example.aniruddha1.webcast;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class Details extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        int position = 0;
        int pos = intent.getIntExtra(MainActivity.EXTRA_MESSAGE,position);
        DetailsBackend detailsBackend = new DetailsBackend(pos);
        try {
            detailsBackend.connect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ActionBar actionBar =getActionBar();
        String  name = detailsBackend.name;
        actionBar.setTitle(name);
        String rating ="Rating: "+ String.valueOf(detailsBackend.rating);
        TextView TVname = (TextView) findViewById(R.id.name);
        TVname.setText(detailsBackend.name);
        TextView TVrating = (TextView) findViewById(R.id.rating);
        TVrating.setText(rating);
        TextView TVsummary = (TextView) findViewById(R.id.summary);
        TVsummary.setText(detailsBackend.summary);
        TVsummary.setMovementMethod(new ScrollingMovementMethod());
        ImageView IMposter = (ImageView) findViewById(R.id.poster);
        Picasso.with(this).load(detailsBackend.imgurl).into(IMposter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
