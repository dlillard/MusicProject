package com.dlillard.musicproject.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.dlillard.musicproject.R;
import com.dlillard.musicproject.controller.network.SearchAttributeSetsReceiver;
import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.dlillard.musicproject.controller.network.SpotifyModule;
import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.SpotifyAttributeSet;
import com.dlillard.musicproject.util.ApplicationContext;

import java.util.ArrayList;

/**
 * Created by dlillard on 5/12/15.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, SearchAttributeSetsReceiver {
    private SoundCloudModule soundCloudModule;
    private SpotifyModule spotifyModule;
    private ArrayList<AttributeSet> soundCloudResults;
    private ArrayList<AttributeSet> spotifyResults;

    @Override
    public void onCreate(Bundle savedInstanceState){
        setHasOptionsMenu(true);
        //searchView.setIconifiedByDefault(false);

        System.out.println("><><><");
        soundCloudModule = new SoundCloudModule();
        spotifyModule = new SpotifyModule();
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        soundCloudModule.search(this, AttributeSet.AttributeName.TITLE, newText);
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        System.out.println("<><><>");
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.requestFocus();
    }

    @Override
    public void onSearchLoaded(String originalQuery, ArrayList<AttributeSet> results) {
        if(results.size()>0){
            if(results.get(0) instanceof SpotifyAttributeSet){
                spotifyResults.addAll(results);
            }else{
                soundCloudResults.addAll(results);
            }
        }
    }
}
