package com.dlillard.musicproject.controller.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dlillard.musicproject.R;
import com.dlillard.musicproject.util.ApplicationContext;

/**
 * Created by dlillard on 5/11/15.
 */
public class AppDrawerAdapter extends BaseAdapter{
    private static final String[] items= {"Search", "Recent", "Songs", "Playlists", "Artists", "Albums"};
    public AppDrawerAdapter(){
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result;
        if(convertView!=null && convertView instanceof TextView){
            ((TextView) convertView).setText(items[position]);
            result=convertView;
        }else{
            LayoutInflater inflater = (LayoutInflater)ApplicationContext.app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inflater.inflate(R.layout.app_drawer_text, null);
            ((TextView) result).setText(items[position]);
        }

        return result;
    }
}
