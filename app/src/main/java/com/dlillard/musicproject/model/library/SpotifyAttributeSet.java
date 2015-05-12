package com.dlillard.musicproject.model.library;

import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Imad on 4/28/15.
 */
public class SpotifyAttributeSet extends AttributeSet {

    @JsonCreator
    public SpotifyAttributeSet(Map<String, Object> delegate) {
        attributes = new HashMap<AttributeName, String>();

        insertFromDelegate(delegate, AttributeName.ARTWORK, "tracks");
        //insertFromDelegate(delegate, AttributeName.ID, "artis");

        /*
        insertFromDelegate(delegate, AttributeName.DATE, "created_at");
        insertFromDelegate(delegate, AttributeName.USER, "user");
        insertFromDelegate(delegate, AttributeName.URL, "stream_url");
        */
        //attributes.put(/*AttributeName.URL, attributes.get(AttributeName.URL) + "?client_id=" + "e8d0c93320fe4ac1a42d3f328496b00c"*/);
    }
}
