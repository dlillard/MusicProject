package com.example.dlillard.musicproject;


import com.example.dlillard.musicproject.model.library.Attribute;
import com.example.dlillard.musicproject.model.library.SongList;

/**
 * Created by dlillard on 4/3/15.
 */
public interface APIModule {
    public SongList search(Attribute attribute);


}
