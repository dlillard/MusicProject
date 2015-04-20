package com.dlillard.musicproject.util;

import com.dlillard.musicproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dlillard on 4/15/15.
 */
public class APICredentialLoader {
    public static String getSoundCloudClientId(){
        InputStream inputStream = ApplicationContext.app.getResources().openRawResource(R.raw.vals);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while ((line = buffreader.readLine()) != null) {
                if(line.contains("SOUNDCLOUD_CLIENT_ID=")){
                    return line.substring(21);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return "";
    }
}
