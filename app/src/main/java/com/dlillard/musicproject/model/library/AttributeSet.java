package com.dlillard.musicproject.model.library;

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

    public AttributeSet(){}

    public AttributeSet(String name) {
        this.name=name;
        attributes = new HashMap<AttributeName, String>();
    }

    @JsonCreator
    public AttributeSet(Map<String,Object> delegate) {
        attributes = new HashMap<AttributeName, String>();

        insertFromDelegate(delegate, AttributeName.TITLE, "title");

        insertFromDelegate(delegate, AttributeName.ID, "id");

        insertFromDelegate(delegate, AttributeName.DATE, "created_at");

        insertFromDelegate(delegate, AttributeName.USER, "user");

    }

    private void insertFromDelegate(Map<String,Object> delegate, AttributeName name, String key){
        if(delegate.containsKey(key)) {
            Object value = delegate.get(key);
            if(value!=null)
                attributes.put(name, value.toString());
        }
    }


    public void add(AttributeName name, String value) {
        attributes.put(name, value.toString());
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
        builder.append("(");
        while(i.hasNext()){
            Map.Entry<AttributeName, String> entry = (Map.Entry<AttributeName, String>) i.next();
            builder.append(entry.getKey().name());
            builder.append(", ");
            builder.append(entry.getValue());
        }
        builder.delete(builder.length()-2,builder.length());
        builder.append(")");
        return builder.toString();
    }

    public enum AttributeName {
        TITLE, ARTIST, ALBUM, ARTWORK, ID, DATE, USER, URL;

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
                case URL:
                    return 7;
                default:
                    return -1;
            }
        }


    }
}
