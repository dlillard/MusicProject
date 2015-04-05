package com.example.dlillard.musicproject.model.library;

import java.util.ArrayList;
import com.example.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dlillard on 4/3/15.
 */
public class Song {
    private ArrayList<AttributeSet> data;

    public Song() {
        data = new ArrayList<AttributeSet>();
    }

    /*public void addAttribute(Attribute toAdd){
    }

    public String get(Attribute type){
        return data.get(type.getIndex()).getValue();
    }*/
}
