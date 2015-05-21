package com.dlillard.musicproject.model.library;

import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.dlillard.musicproject.controller.network.SpotifyModule;
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

        switch(SpotifyModule.searchCase) {
            case 1:
                insertFromDelegate(delegate, AttributeName.TITLE, "name");
                break;
            case 2:
                insertFromDelegate(delegate, AttributeName.ARTIST, "name");
                break;
        }
        insertFromDelegate(delegate, AttributeName.ID, "id");

        //attributes.put(/*AttributeName.URL, attributes.get(AttributeName.URL) + "?client_id=" + "e8d0c93320fe4ac1a42d3f328496b00c"*/);
    }
}
