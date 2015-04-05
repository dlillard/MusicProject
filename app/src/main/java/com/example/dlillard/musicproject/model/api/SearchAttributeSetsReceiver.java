package com.example.dlillard.musicproject.model.api;

import com.example.dlillard.musicproject.model.library.AttributeSet;

import java.util.ArrayList;

/**
 * Created by dlillard on 4/4/15.
 */
public interface SearchAttributeSetsReceiver {
    public void onSearchLoaded(ArrayList<AttributeSet> results);
}
