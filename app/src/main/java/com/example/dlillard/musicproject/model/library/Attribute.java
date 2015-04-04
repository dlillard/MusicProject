package com.example.dlillard.musicproject.model.library;

/**
 * Created by dlillard on 4/3/15.
 */
public class Attribute {
    private AttributeName name;
    private String value;
    public enum AttributeName {
        TITLE, ARTIST, ALBUM, FILE, SONG;///!!!WATCH OUT FOR THAT CONSTANT

        private int getIntValue() {
            switch (this) {
                case TITLE:
                    return 0;
                case ARTIST:
                    return 1;
                case ALBUM:
                    return 2;
                case FILE:
                    return 3;
                case SONG:
                    return 4;
                default:
                    return -1;
            }
        }

        public static int getTypeCount() {///!!!HARDCODED
            return 5;
        }
    }

    public Attribute(AttributeName name, String value){
        this.name=name;
        this.value=value;
    }

    public String getValue(){
        return value;
    }

    public AttributeName getType(){
        return name;
    }

    public int getIndex(){
        return name.getIntValue();
    }
}
