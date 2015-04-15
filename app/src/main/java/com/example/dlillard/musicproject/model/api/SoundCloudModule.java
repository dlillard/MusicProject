package com.example.dlillard.musicproject.model.api;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dlillard.musicproject.ApplicationContext;
import com.example.dlillard.musicproject.model.library.AttributeSet;
import com.example.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.example.dlillard.musicproject.model.library.SongList;
import com.example.dlillard.musicproject.util.APICredentialLoader;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by dlillard on 4/4/15.
 */
public class SoundCloudModule implements APIModule {
    private final String BASE_URL="https://api.soundcloud.com";
    private static final String CLIENT_ID=APICredentialLoader.getSoundCloudClientId().toString();
    private final String SERVICE_NAME="SoundCloud";

    private SearchAttributeSetsReceiver receiver;

    ///tracks?client_id=YOUR_CLIENT_ID
    public void search(SearchAttributeSetsReceiver receiver, AttributeName criteria, String value){
        this.receiver=receiver;
        getSearchResults(criteria, value);
    }

    private void JSONArrayToAttributeSets(JSONArray jsonArray){
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("MADE IT TO JSONARRAY PARSING.");
        System.out.println("JSONARRAY:\n" + jsonArray.toString());
        System.out.println(jsonArray.length());

        ArrayList<AttributeSet> results = new ArrayList<AttributeSet>();
        for(int i=0;i<jsonArray.length();i++) {
            AttributeSet attributeSet=null;
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println("\t" + i + "\t" + jsonObject);
                attributeSet = mapper.readValue(jsonObject.toString(), AttributeSet.class);
            }catch(Exception e){
                e.printStackTrace();
            }
            if(attributeSet!=null) {
                attributeSet.setName(SERVICE_NAME);
                results.add(attributeSet);
            }
        }
        receiver.onSearchLoaded(results);
    }



    private void getSearchResults(AttributeName attribute, String value){
        String searchURL=BASE_URL;
        switch(attribute){
            case TITLE:
                searchURL=searchURL + "/tracks.json";
                break;
        }
        searchURL=searchURL + "?q=" + value.toLowerCase() + "&client_id=" + CLIENT_ID;
        System.out.println("SEARCHING " + searchURL);//http://api.soundcloud.com/tracks.json?q=starfucker&client_id=2df053c5da34356a1c19cc6e6d5ab5cd

        final SoundCloudModule thisModule=this;
        JsonArrayRequest request = new JsonArrayRequest(searchURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                thisModule.JSONArrayToAttributeSets(response);
            }
        },  new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        ApplicationContext.requestQueue.add(request);
    }
}
