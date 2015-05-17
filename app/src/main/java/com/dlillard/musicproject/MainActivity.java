package com.dlillard.musicproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlillard.musicproject.controller.Playback.SpotifyPlayer;
import com.dlillard.musicproject.controller.network.SearchAttributeSetsReceiver;
import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.dlillard.musicproject.controller.network.SpotifyModule;
import com.dlillard.musicproject.controller.ui.AppDrawerAdapter;
import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.Song;
import com.dlillard.musicproject.util.ApplicationContext;
import com.dlillard.musicproject.view.SearchFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements SearchAttributeSetsReceiver {
    private FragmentManager fm;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        fm=getFragmentManager();
        setContentView(R.layout.activity_main);

        AppDrawerAdapter appDrawerAdapter = new AppDrawerAdapter();
        ListView drawer = (ListView) findViewById(R.id.left_drawer);
        drawer.setAdapter(appDrawerAdapter);

        SpotifyModule module = new SpotifyModule();
        module.search(this, AttributeSet.AttributeName.TITLE, "I want it that way");

        Intent intent = new Intent(context, SpotifyPlayer.class);

        //startActivity(intent);
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

    public void onSearchLoaded(String originalSearch, ArrayList<AttributeSet> searchResults){
        if(searchResults.size()==0){
            Toast.makeText(ApplicationContext.app, "Empty set loaded.", Toast.LENGTH_SHORT).show();
            return;
        }

       // Toast.makeText(this, "Playing results for " + originalSearch, Toast.LENGTH_LONG).show();
        System.out.println(searchResults.get(0).getName() + " results:");
        for(int i=0;i<searchResults.size();i++){
            System.out.println(searchResults.get(i));
        }


        /*ArrayList<Song> songResults = new ArrayList<Song>();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<searchResults.size();i++){
            Song song = new Song();
            song.addAtributeSet(searchResults.get(i));
            builder.append(searchResults.get(i));
            songResults.add(song);
        }

        ((TextView) findViewById(R.id.text)).setText(builder.toString());

        ApplicationContext.playback = new Playback(songResults);
        ApplicationContext.playback.play();*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchFragment = new SearchFragment();
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(searchFragment);




//        searchView.requestFocus();

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
        if(id == R.id.search){
            fm.beginTransaction().replace(R.id.content_frame, searchFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}
