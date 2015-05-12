package com.dlillard.musicproject.util;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dlillard.musicproject.controller.Playback.SoundCloudPlayer;

/**
 * Created by dlillard on 4/4/15.
 */
public class ApplicationContext extends Application {
    public static ApplicationContext app; // Access the application context from anywhere
    public static RequestQueue requestQueue;//single RequestQueue for all network operations
    //may also do MySQL or other network code here
    public static SoundCloudPlayer soundCloudPlayer;

    @Override
    public void onCreate() {
        requestQueue = Volley.newRequestQueue(this);

        super.onCreate();

        app = this;
    }

    public static void toast(String text){
        Toast.makeText(ApplicationContext.app, text, Toast.LENGTH_LONG).show();
    }
    public static void toast(Object toPrint){
        ApplicationContext.toast(toPrint.toString());
    }
}
