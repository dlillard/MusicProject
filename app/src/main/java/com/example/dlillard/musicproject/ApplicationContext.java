package com.example.dlillard.musicproject;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dlillard on 4/4/15.
 */
public class ApplicationContext extends Application {
    public static ApplicationContext app; // Access the application context from anywhere
    public static RequestQueue requestQueue;//single RequestQueue for all network operations
    //may also do MySQL or other network code here

    @Override
    public void onCreate() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MET.");
        requestQueue = Volley.newRequestQueue(this);

        super.onCreate();

        app = this;
    }
}
