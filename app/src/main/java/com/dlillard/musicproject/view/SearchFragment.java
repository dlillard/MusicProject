package com.dlillard.musicproject.view;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlillard.musicproject.MainActivity;
import com.dlillard.musicproject.R;
import com.dlillard.musicproject.controller.Playback.SoundCloudPlayer;
import com.dlillard.musicproject.controller.Playback.SpotifyPlayer;
import com.dlillard.musicproject.controller.network.SearchAttributeSetsReceiver;
import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.dlillard.musicproject.controller.network.SpotifyModule;
import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.Song;
import com.dlillard.musicproject.model.library.SoundCloudAttributeSet;
import com.dlillard.musicproject.model.library.SpotifyAttributeSet;
import com.dlillard.musicproject.util.ApplicationContext;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by dlillard on 5/12/15.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, SearchAttributeSetsReceiver {
    private SoundCloudModule soundCloudModule;
    private SpotifyModule spotifyModule;
    private MainActivity activity;
    private ResultsListAdapter adapter;
    private LinearLayout layout;
    private ListView resultsList;
//    private LoadingIndicator loadingIndicator;
    private ProgressBar loadingIndicator;
    private boolean loading;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //searchView.setIconifiedByDefault(false);

        soundCloudModule = new SoundCloudModule();
        spotifyModule = new SpotifyModule();

//        loadingIndicator = new LoadingIndicator(getActivity());

        super.onCreate(savedInstanceState);
    }

    private void setLoading(boolean loading){
        if(loading && !this.loading){
            resultsList.setVisibility(View.GONE);
            loadingIndicator.setVisibility(View.VISIBLE);
//            layout.addView(loadingIndicator);
//            System.out.println("met");
        }else if(!loading && this.loading){
//            layout.removeView(loadingIndicator);
            loadingIndicator.setVisibility(View.GONE);
            resultsList.setVisibility(View.VISIBLE);
        }
        this.loading=loading;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        System.out.println("?>?>?>?>");
        layout = (LinearLayout) inflater.inflate(R.layout.search_fragment_layout, null);
        resultsList = (ListView) layout.findViewById(R.id.results_list);
        adapter = new ResultsListAdapter();
        resultsList.setAdapter(adapter);
        loadingIndicator=(ProgressBar) layout.findViewById(R.id.loading);

        resultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long arg3)
            {
                System.out.println("Testicles1");
                AttributeSet s = adapter.results.get(position);
                if(s instanceof SoundCloudAttributeSet){
                    ApplicationContext.soundCloudPlayer = new SoundCloudPlayer((SoundCloudAttributeSet) s);
                    System.out.println("Testicles");
                    if(ApplicationContext.spotifyPlayer!=null)
                        ApplicationContext.spotifyPlayer.play();
                }else if(s instanceof SpotifyAttributeSet){
                    ArrayList<String> toPass = new ArrayList<String>();
                    toPass.add(((SpotifyAttributeSet) s).getValue(AttributeSet.AttributeName.ID));
                    ApplicationContext.spotifyPlayer = new SpotifyPlayer(toPass);
                    ApplicationContext.spotifyPlayer.play();
                    if(ApplicationContext.soundCloudPlayer!=null)
                        ApplicationContext.soundCloudPlayer.pause();
                }
            }
        });



        return layout;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText != null && newText.length()>0) {
            soundCloudModule.search(this, AttributeSet.AttributeName.TITLE, newText);
            spotifyModule.search(this, AttributeSet.AttributeName.TITLE, newText);
        }

        setLoading(true);

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

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater, MainActivity activity) {
        this.activity = activity;
        onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        System.out.println("<><><>");
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
     //   searchView.requestFocus();
        searchView.clearFocus();


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
        setLoading(false);
        if(results.size()>0){
            if(results.get(0) instanceof SpotifyAttributeSet){
//                spotifyResults = new ArrayList<AttributeSet>();
//                spotifyResults.addAll(results);
                adapter.setResults(results);
            }else{
//                soundCloudResults = new ArrayList<AttributeSet>();
//                soundCloudResults.addAll(results);
                adapter.setResults(results);
            }
        }
    }

    private class ResultsListAdapter extends BaseAdapter {
        private ArrayList<AttributeSet> results=new ArrayList<AttributeSet>();
        private ArrayList<AttributeSet> soundCloudResults=new ArrayList<AttributeSet>();;
        private ArrayList<AttributeSet> spotifyResults=new ArrayList<AttributeSet>();;

        public void setResults(ArrayList<AttributeSet> results){
            if(results.size()!=0 && results.get(0) instanceof SpotifyAttributeSet) {
                spotifyResults.clear();
                spotifyResults.addAll(results);
            }else if(results.size()!=0 && results.get(0) instanceof SoundCloudAttributeSet) {
                soundCloudResults.clear();
                soundCloudResults.addAll(results);
            }
            this.results.clear();
            for(int i=0;i<Math.min(spotifyResults.size(), soundCloudResults.size());i++){
                this.results.add(spotifyResults.get(i));
                this.results.add(soundCloudResults.get(i));
            }


            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(results==null) return 0;
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            return results.get(position);
        }

        @Override
        public boolean hasStableIds(){
            return true;
        }

        @Override
        public long getItemId(int position) {
            return results.get(position).hashCode();
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout cell;
            if(convertView==null){
                cell = (LinearLayout)((LayoutInflater) ApplicationContext.app.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.search_results_list_cell, null);
            }else{
                cell=(LinearLayout)convertView;
            }
            TextView title = (TextView) cell.findViewById(R.id.title);
            TextView service = (TextView) cell.findViewById(R.id.service_name);
            AttributeSet result = results.get(position);

            title.setText(result.getValue(AttributeSet.AttributeName.TITLE));
            if(result instanceof SoundCloudAttributeSet){
                service.setText(SoundCloudModule.SERVICE_NAME);
            }else if(result instanceof SpotifyAttributeSet){
                service.setText(SpotifyModule.SERVICE_NAME);
            }
            return cell;
        }
    }
}
