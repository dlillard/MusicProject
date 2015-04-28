package com.dlillard.musicproject.controller;

import android.media.MediaPlayer;
import android.widget.Toast;

import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.Song;
import com.dlillard.musicproject.util.ApplicationContext;

import org.w3c.dom.Attr;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dlillard on 4/19/15.
 */
public class Playback {
    private ArrayList<Song> queue;
    private ArrayList<MediaPlayer> mediaPlayers;
    int currentIndex=0;

    public Playback(ArrayList<Song> songs){
        queue=new ArrayList<Song>();
        queue.addAll(songs);
        mediaPlayers = new ArrayList<MediaPlayer>();
        mediaPlayers.add(new MediaPlayer());
        prepare();
    }

    public void shuffleSongs(){
        //TODO
    }

    public Song getCurrentSong(){
        return queue.get(currentIndex);
    }

    public ArrayList<Song> getQueue(){
        return queue;
    }

    public void play(){
        mediaPlayers.get(currentIndex).start();
    }

    public void pause(){
        mediaPlayers.get(currentIndex).pause();
    }

    public void next(){
        mediaPlayers.get(currentIndex).stop();
        mediaPlayers.get(currentIndex+1).start();
        currentIndex++;
        prepare();
    }

    public void prev(){
        mediaPlayers.get(currentIndex).stop();
        currentIndex--;
        if(currentIndex>=0)
            mediaPlayers.get(currentIndex).start();
        prepare();
    }

    private String getSongURL(int index){
        return queue.get(index).getAttributeSet(0).getValue(AttributeSet.AttributeName.URL);
    }

    private void prepare(){
        if(queue.size()==0) {
            return;
        }

        Toast.makeText(ApplicationContext.app, "IMAD IS GAY " + currentIndex, Toast.LENGTH_LONG).show();

        MediaPlayer prev, current, next;
        String prevURL, currentURL, nextURL;

        if(currentIndex>0) {
            prev = mediaPlayers.get(currentIndex - 1);
            prev.reset();
            prevURL = getSongURL(currentIndex - 1);
            try{
                prev.setDataSource(prevURL);
                prev.prepare();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        current = mediaPlayers.get(currentIndex);
        current.reset();
        currentURL = getSongURL(currentIndex);
        try{
            current.setDataSource(currentURL);
            current.prepare();
        }catch(IOException e){
            e.printStackTrace();
        }

        if(queue.size()>currentIndex-1) {
            if(mediaPlayers.size()<currentIndex+1) {
                next = mediaPlayers.get(currentIndex + 1);
            } else {
                mediaPlayers.add(new MediaPlayer());
                next = mediaPlayers.get(currentIndex + 1);
            }
            next.reset();
            nextURL = getSongURL(currentIndex);
            try{
                next.setDataSource(nextURL);
                next.prepare();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
