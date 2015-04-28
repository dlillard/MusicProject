package com.dlillard.musicproject.model.library;

import com.dlillard.musicproject.controller.network.SoundCloudModule;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dlillard on 4/28/15.
 */
public class SoundCloudAttributeSet extends AttributeSet {

    @JsonCreator
    public SoundCloudAttributeSet(Map<String,Object> delegate) {
        attributes = new HashMap<AttributeName, String>();

        insertFromDelegate(delegate, AttributeName.TITLE, "title");
        insertFromDelegate(delegate, AttributeName.ID, "id");
        insertFromDelegate(delegate, AttributeName.DATE, "created_at");
        insertFromDelegate(delegate, AttributeName.USER, "user");
        insertFromDelegate(delegate, AttributeName.URL, "stream_url");
        attributes.put(AttributeName.URL, attributes.get(AttributeName.URL) + "?client_id=" + SoundCloudModule.CLIENT_ID);
    }
}
