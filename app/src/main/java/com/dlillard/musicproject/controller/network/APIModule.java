package com.dlillard.musicproject.controller.network;


import com.dlillard.musicproject.model.library.AttributeSet.AttributeName;

/**
 * Created by imad on 4/3/15.
 */
public interface APIModule {
    public void search(SearchAttributeSetsReceiver receiver, AttributeName attribute, String value);


}
