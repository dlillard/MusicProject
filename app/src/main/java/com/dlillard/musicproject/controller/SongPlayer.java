package com.dlillard.musicproject.controller;

import android.media.MediaPlayer;

import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.Song;

/**
 * Created by dlillard on 4/19/15.
 */
public class SongPlayer {
    private Song song;
    private MediaPlayer mediaPlayer;
    public SongPlayer(Song song){
        this.song=song;
        mediaPlayer = new MediaPlayer();
    }

    public void play(){
        //TODO
    }
    public void pause(){
        //TODO
    }
    public void skip(){
        //TODO
    }

    public void prepare(){
        if(song.getAttributeSetCount()==0)
            return;

        AttributeSet attributeSet = song.getAttributeSet(0);

        String url = attributeSet.getValue(AttributeSet.AttributeName.URL);
        if(url!=null){
            try{
                mediaPlayer.setDataSource(url);
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }
}
