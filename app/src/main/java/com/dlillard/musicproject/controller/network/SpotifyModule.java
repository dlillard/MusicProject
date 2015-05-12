package com.dlillard.musicproject.controller.network;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.dlillard.musicproject.model.library.AttributeSet;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dlillard.musicproject.model.library.SpotifyAttributeSet;
import com.dlillard.musicproject.util.ApplicationContext;
import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.dlillard.musicproject.util.APICredentialLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Imad on 4/21/2015.
 */
public class SpotifyModule implements APIModule{
    private SearchAttributeSetsReceiver receiver;
    private String originalValue;
    private static final String SERVICE_NAME = "Spotify";
    private String BASE_URL = "https://api.spotify.com/v1/search?q=";
    public void search(SearchAttributeSetsReceiver receiver, AttributeSet.AttributeName attribute, String value){
        this.receiver=receiver;
        originalValue = value;
        String query = null;
        try {
            query = URLEncoder.encode(value, "utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        System.out.println(attribute + " + " + value);
        getSearchResults(attribute, query);
    }

    private void JSONObjectToAttributeSets(JSONObject jsonObject){
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("============MAdE IT TO JSONOBJECT PARSING================");

        ArrayList<AttributeSet> results = new ArrayList<AttributeSet>();
        for(int i=0;i<jsonObject.length();i++) {
            AttributeSet attributeSet=null;
            try {
                /* Code works
                 JSONObject dan = jsonObject.getJSONObject("tracks");
                JSONArray dan2 = dan.getJSONArray("items");
                JSONObject dan3 = dan2.getJSONObject(0);

                System.out.println("DANNNNNNN IS " + dan3);

                JSONObject dan4 = dan3.getJSONObject("album");

                System.out.println("TEST RETUNS::::::::: " + dan4.getString("available_markets"));
                 */


                JSONObject dan = jsonObject.getJSONObject("tracks");
                JSONArray dan2 = dan.getJSONArray("items");
                JSONObject dan3 = dan2.getJSONObject(0);

                System.out.println("DANNNNNNN IS " + dan3);

                JSONObject dan4 = dan3.getJSONObject("album");

                System.out.println("TEST RETUNS::::::::: " + dan4.getString("name"));



                attributeSet = mapper.readValue(jsonObject.toString(), SpotifyAttributeSet.class);

            }catch(Exception e){
                e.printStackTrace();
            }
            if(attributeSet!=null) {

                attributeSet.setName(SERVICE_NAME);
                results.add(attributeSet);
            }
        }
        receiver.onSearchLoaded(originalValue, results);
    }

    private void getSearchResults(AttributeSet.AttributeName attribute, String value){
        String searchURL = BASE_URL +value;

        searchURL += "&type=";
        switch(attribute){
            case TITLE:
                searchURL += "track";
                break;
            case ARTIST:
                searchURL += "artist";
                break;
            case ALBUM:
                searchURL += "album";
                break;

        }

        final SpotifyModule module = this;
        final JsonObjectRequest request;


        final JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, searchURL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        JSONObject ob = null;
                        try {
                            module.JSONObjectToAttributeSets(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("-------ERRRRRRRORRRRRRRRRRRRRRRRRRRRR---------");//Log.d("Error.Response", response);
                    }
                }

        );
// add it to the RequestQueue
        ApplicationContext.requestQueue.add(getRequest);

    }
}
