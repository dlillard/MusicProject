package com.example.dlillard.musicproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dlillard.musicproject.model.api.SearchAttributeSetsReceiver;
import com.example.dlillard.musicproject.model.api.SoundCloudModule;
import com.example.dlillard.musicproject.model.library.AttributeSet;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SearchAttributeSetsReceiver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SoundCloudModule module = new SoundCloudModule();
//        module.search(this, AttributeSet.AttributeName.TITLE, "Starfucker");

        /*
        RequestQueue queue = Volley.newRequestQueue(this);

        final TextView mTextView = (TextView) findViewById(R.id.text);

        String url ="http://www.google.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);*/
    }

    public void onSearchLoaded(ArrayList<AttributeSet> results){
        Toast.makeText(this, "loaded", Toast.LENGTH_LONG).show();
        if(results.size()==0){
            Toast.makeText(ApplicationContext.app, "Empty set loaded.", Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(results.get(0).getName() + " results:");
        for(int i=0;i<results.size();i++){
            System.out.println(results.get(i));
        }
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
