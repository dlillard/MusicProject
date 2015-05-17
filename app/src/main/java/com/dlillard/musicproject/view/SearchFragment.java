package com.dlillard.musicproject.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.dlillard.musicproject.MainActivity;
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
    private MainActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //searchView.setIconifiedByDefault(false);

        soundCloudResults = new ArrayList<AttributeSet>();
        spotifyResults = new ArrayList<AttributeSet>();
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
        if(newText != null)
            soundCloudModule.search(this, AttributeSet.AttributeName.TITLE, newText);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        System.out.println("><>niggers");
        int id = menuItem.getItemId();
        if(id == R.id.search){
            ((MainActivity) getActivity()).launchSearchFragment();
        }
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater, MainActivity activity){
        this.activity=activity;
        onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        System.out.println("<><><>");
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.requestFocus();


        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                item.getActionView().requestFocus();
                activity.launchSearchFragment();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).launchSearchFragment();
            }
        });
    }

    @Override
    public void onSearchLoaded(String originalQuery, ArrayList<AttributeSet> results) {
        if(results.size()>0){
            if(results.get(0) instanceof SpotifyAttributeSet){
                spotifyResults = new ArrayList<AttributeSet>();
                spotifyResults.addAll(results);
            }else{
                soundCloudResults = new ArrayList<AttributeSet>();
                soundCloudResults.addAll(results);
            }
        }
    }
}
