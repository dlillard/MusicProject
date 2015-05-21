package com.dlillard.musicproject.controller.Playback;

import android.media.MediaPlayer;

import com.dlillard.musicproject.model.library.AttributeSet;
import com.dlillard.musicproject.model.library.Song;
import com.dlillard.musicproject.model.library.SoundCloudAttributeSet;
import com.dlillard.musicproject.util.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dlillard on 4/19/15.
 */
public class SoundCloudPlayer implements Playback, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener{
    private ArrayList<Song> queue;
    private ArrayList<MediaPlayer> mediaPlayers;
    int currentIndex=0;
    private boolean play;

    public SoundCloudPlayer(ArrayList<Song> songs){
        queue=new ArrayList<Song>();
        queue.addAll(songs);
        mediaPlayers = new ArrayList<MediaPlayer>();
        mediaPlayers.add(new MediaPlayer());
        prepare();
    }

    public SoundCloudPlayer(SoundCloudAttributeSet s){
        queue=new ArrayList<Song>();
        Song s1 = new Song();
        s1.addAttributeSet(s);
        queue.add(s1);
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

    public void onCompletion(MediaPlayer mediaPlayer){
        ApplicationContext.toast(currentIndex);
        next();
    }

    public void onPrepared(MediaPlayer mediaPlayer){
        ApplicationContext.toast("niggaa");
        if(mediaPlayer.equals(mediaPlayers.get(currentIndex)) && play)
            mediaPlayer.start();
    }

    public ArrayList<Song> getQueue(){
        return queue;
    }

    public void play(){
        play=true;
    }

    public void pause(){
        mediaPlayers.get(currentIndex).pause();
    }

    public void next(){
        mediaPlayers.get(currentIndex).stop();

        currentIndex++;

        mediaPlayers.get(currentIndex).start();
        prepare();
    }

    public void prev(){
        prevPrepare();
        currentIndex--;
        if(currentIndex>=0)
            mediaPlayers.get(currentIndex).start();
//        mediaPlayers.get(currentIndex).setNextMediaPlayer(mediaPlayers.get(currentIndex+1));
    }

    private String getSongURL(int index){
        return queue.get(index).getSoundCloudAttributeSet().getValue(AttributeSet.AttributeName.URL);
    }

    private void prevPrepare(){
        MediaPlayer current = mediaPlayers.get(currentIndex);
        current.reset();
        try {
            current.setDataSource(getSongURL(currentIndex));
            mediaPlayers.get(currentIndex).prepare();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    private void prepare(){
        ApplicationContext.toast(currentIndex);
        if(queue.size()==0) {
            return;
        }

        MediaPlayer prev=null, current=null, next=null;
        String prevURL, currentURL, nextURL;

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
                next.setOnPreparedListener(this);
                next.prepare();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        current = mediaPlayers.get(currentIndex);
        current.reset();
//        if(next!=null)
//            current.setNextMediaPlayer(next);
        currentURL = getSongURL(currentIndex);
        try{
            current.setDataSource(currentURL);
            current.setOnPreparedListener(this);
            current.prepare();
        }catch(IOException e){
            e.printStackTrace();
        }



        if(currentIndex>0) {
            prev = mediaPlayers.get(currentIndex - 1);
            prev.reset();
            prevURL = getSongURL(currentIndex - 1);
            try{
                prev.setDataSource(prevURL);
//                prev.setNextMediaPlayer(current);
                prev.setOnPreparedListener(this);
                prev.prepare();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
