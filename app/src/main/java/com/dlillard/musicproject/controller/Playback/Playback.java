package com.dlillard.musicproject.controller.Playback;

import com.dlillard.musicproject.model.library.Song;

import java.util.ArrayList;

/**
 * Created by dlillard on 5/8/15.
 */
public interface Playback {

    public void play();

    public void pause();

    public void next();

    public void prev();

    public Song getCurrentSong();

    public ArrayList<Song> getQueue();
}

