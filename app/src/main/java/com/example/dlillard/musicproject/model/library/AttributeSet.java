package com.example.dlillard.musicproject.model.library;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dlillard on 4/4/15.
 */
public class AttributeSet {
    private HashMap<AttributeName, String> attributes;
    private String name;

    public AttributeSet(String name) {
        this.name=name;
        attributes = new HashMap<AttributeName, String>();
    }

    @JsonCreator
    public AttributeSet(Map<String,Object> delegate) {
        attributes.put(AttributeName.TITLE, delegate.get("title").toString());////
        attributes.put(AttributeName.ID, delegate.get("id").toString());
        attributes.put(AttributeName.DATE, delegate.get("created_at").toString());////
        attributes.put(AttributeName.USER, delegate.get("user").toString());
    }

    public void add(AttributeName name, String value) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getValue(AttributeName name) {
        return attributes.get(name);
    }

    public boolean hasAttribute(AttributeName attribute) {
        return attributes.containsKey(attribute);
    }

    public void setAttribute(AttributeName attributeName, String attributeValue){
        attributes.put(attributeName, attributeValue);
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        Iterator i = attributes.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry<AttributeName, String> entry = (Map.Entry<AttributeName, String>) i.next();
            builder.append("(");
            builder.append(entry.getKey().name());
            builder.append(", ");
            builder.append(entry.getValue());
            builder.append("\t");
        }
        return builder.toString();
    }

    public enum AttributeName {
        TITLE, ARTIST, ALBUM, ARTWORK, ID, DATE, USER;

        private int getIntValue() {
            switch (this) {
                case TITLE:
                    return 0;
                case ARTIST:
                    return 1;
                case ALBUM:
                    return 2;
                case ARTWORK:
                    return 3;
                case ID:
                    return 4;
                case DATE:
                    return 5;
                case USER:
                    return 6;
                default:
                    return -1;
            }
        }


    }
}
