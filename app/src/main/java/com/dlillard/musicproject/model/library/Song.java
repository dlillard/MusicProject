package com.dlillard.musicproject.model.library;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.dlillard.musicproject.controller.network.SpotifyModule;
import com.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dlillard on 4/3/15.
 */
public class Song {
    private SoundCloudAttributeSet soundCloudAttributeSet;
    private SpotifyAttributeSet spotifyAttributeSet;

    public void addAttributeSet(AttributeSet toAdd){
        if(toAdd instanceof SoundCloudAttributeSet)
            soundCloudAttributeSet = (SoundCloudAttributeSet) toAdd;
        else if(toAdd instanceof SpotifyAttributeSet)
            spotifyAttributeSet = (SpotifyAttributeSet) toAdd;
    }

    public SpotifyAttributeSet getSpotifyAttributeSet() {
            return spotifyAttributeSet;
    }

    public SoundCloudAttributeSet getSoundCloudAttributeSet(){
            return soundCloudAttributeSet;
    }
}
