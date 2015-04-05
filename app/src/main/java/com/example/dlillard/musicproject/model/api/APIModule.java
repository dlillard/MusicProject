package com.example.dlillard.musicproject.model.api;


import com.example.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.example.dlillard.musicproject.model.library.SongList;

/**
 * Created by dlillard on 4/3/15.
 */
public interface APIModule {
    public void search(SearchAttributeSetsReceiver receiver, AttributeName attribute, String value);


}
