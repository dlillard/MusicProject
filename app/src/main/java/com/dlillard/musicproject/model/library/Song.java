package com.dlillard.musicproject.model.library;

import java.util.ArrayList;
import com.dlillard.musicproject.model.library.AttributeSet.AttributeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dlillard on 4/3/15.
 */
public class Song {
    private ArrayList<com.dlillard.musicproject.model.library.AttributeSet> data;

    public Song() {
        data = new ArrayList<AttributeSet>();
    }

    public void addAtributeSet(AttributeSet serviceData){
        data.add(serviceData);
    }

    public int getAttributeSetCount(){
        return data.size();
    }

    public AttributeSet getAttributeSet(int index){
        return data.get(index);
    }
}
