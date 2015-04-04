package com.example.dlillard.musicproject.model.library;

import java.util.ArrayList;
import com.example.dlillard.musicproject.model.library.Attribute.AttributeName;

/**
 * Created by dlillard on 4/3/15.
 */
public class Song {
    private ArrayList<Attribute> data;

    public Song(){
        data=new ArrayList<Attribute>();
        for(int i=0;i<AttributeName.getTypeCount();i++){
            data.add(null);
        }
    }

    public void addAttribute(Attribute toAdd){
        data.add(toAdd.getIndex(), toAdd);
    }

    public String get(Attribute type){
        return data.get(type.getIndex()).getValue();
    }
}
