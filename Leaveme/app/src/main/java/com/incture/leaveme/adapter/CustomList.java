package com.incture.leaveme.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.leaveme.R;

/**
 * Created by JANANI.N on 03-09-2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
   // private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web) {
        super(context, R.layout.list_item, web);
        this.context = context;
        this.web = web;
       // this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        imageView.setBackgroundResource(R.drawable.ic_account_circle_black_48dp);


       // imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
