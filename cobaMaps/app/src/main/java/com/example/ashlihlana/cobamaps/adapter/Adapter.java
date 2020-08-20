package com.example.ashlihlana.cobamaps.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.ashlihlana.cobamaps.AppController;
import com.example.ashlihlana.cobamaps.R;
import com.android.volley.toolbox.ImageLoader;
import com.example.ashlihlana.cobamaps.module.Jarak;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private List<Jarak> jarakItems;
    private ImageLoader imageLoader;
    private LayoutInflater inflater;

    public Adapter(Activity activity, List<Jarak> jarakItems) {
        this.activity = activity;
        this.jarakItems = jarakItems;
    }

    @Override
    public int getCount() {
        return jarakItems.size();
    }

    @Override
    public Object getItem(int location) {
        return jarakItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        TextView txt_nama = (TextView) convertView.findViewById(R.id.txt_nama);
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.tgambar);

        Jarak j = jarakItems.get(position);

        txt_nama.setText(j.getNama());
        thumbNail.setImageUrl(j.getGambar(),imageLoader);

        return convertView;
    }
}
