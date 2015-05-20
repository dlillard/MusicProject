package com.dlillard.musicproject.controller.Playback;

/**
 * Created by Imad on 5/14/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dlillard.musicproject.R;
import com.dlillard.musicproject.model.library.Song;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;

import java.util.ArrayList;

public class SpotifyPlayer extends Activity implements
        PlayerNotificationCallback, ConnectionStateCallback, Playback{

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "e8d0c93320fe4ac1a42d3f328496b00c";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "myniftyapp://callback";

    // Request code that will be passed together with authentication result to the onAuthenticationResult callback
    private static final int REQUEST_CODE = 1337;
    private ArrayList<Song> queue;
    private Player mPlayer;
    //AttributeSet (songs are a list of attributesets and each attribute set is information recieved from one service (Links)
    //Enum within AttributeSet

    public SpotifyPlayer(ArrayList<Song> songs){
        queue.addAll(songs);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            System.out.println("========================================================TEST=========================================================");
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
                    @Override
                    public void onInitialized(Player player) {
                        mPlayer.addConnectionStateCallback(SpotifyPlayer.this);
                        mPlayer.addPlayerNotificationCallback(SpotifyPlayer.this);
                        mPlayer.play("spotify:track:3ISTk34XFWxJJnUs5youLa");

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        Log.d("MainActivity", "Playback event received: " + eventType.name());
        switch (eventType) {
            // Handle event type as necessary
            default:
                break;
        }
    }
    public void play(){
    }

    public void pause(){
        mPlayer.pause();
    }

    public void next(){
        mPlayer.skipToNext();
    }

    public void prev(){
        mPlayer.skipToPrevious();
    }
    public ArrayList<Song> getQueue(){
       return null;
    }
    public Song getCurrentSong(){
        return null;
    }


    @Override
    public void onPlaybackError(ErrorType errorType, String errorDetails) {
        Log.d("MainActivity", "Playback error received: " + errorType.name());
        switch (errorType) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }
}