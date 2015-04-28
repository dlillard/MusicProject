package com.dlillard.musicproject.controller.network;

import com.dlillard.musicproject.model.library.AttributeSet;

import java.util.ArrayList;

/**
 * Created by dlillard on 4/4/15.
 */
public interface SearchAttributeSetsReceiver {
    public void onSearchLoaded(String originalQuery, ArrayList<AttributeSet> results);
}
