package com.example.aniruddha1.webcast;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static android.view.View.*;


public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.example.aniruddha1.webcast.MAinActivity";
    ArrayList<String> TestNameList;
    ArrayList<String> imgUrl;
    ArrayList<Integer> id;
    Backend backend = new Backend();
    String jstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);*/
        try {
           jstring=backend.connect();
            Log.d("Connected",jstring);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            backend.parseJson(jstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TestNameList = backend.getName();
        imgUrl = backend.getImgurl();
        id=backend.getId();
       Log.d("State", String.valueOf(TestNameList));
        CustomList customList = new CustomList(MainActivity.this,backend.name,imgUrl);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Loading " + TestNameList.get(i), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Details.class);
                Integer position = i;
                intent.putExtra(EXTRA_MESSAGE,id.get(i));
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
