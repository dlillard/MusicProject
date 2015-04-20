package com.dlillard.musicproject.model.library;

import java.util.ArrayList;

/**
 * Created by dlillard on 4/3/15.
 */
public class SongList {
    private ArrayList<Song> songs;
    public SongList(ArrayList<Song> songs){
        this.songs=songs;
    }

    public void shuffleSongs(){
        //TODO
    }

    public void getCurrentSong(){
        //TODO
    }

    public Song get(int index){
        return songs.get(index);
    }

    public void next(){
        //TODO
    }

    public void prev(){
        //TODO
    }



}
