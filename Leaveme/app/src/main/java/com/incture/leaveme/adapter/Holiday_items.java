package com.incture.leaveme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.incture.leaveme.R;
import com.incture.leaveme.data.temp;

import java.util.Vector;

/**
 * Created by JANANI.N on 04-09-2015.
 */
public class Holiday_items extends ArrayAdapter {
    Context ctx;
    public Vector <temp>data;

    public Holiday_items(Context context, int resource,Vector<temp> Data) {
        super(context, resource,Data);
        this.ctx=context;
        this.data=Data;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.list_item_holiday_calendar, null, true);

        TextView date = (TextView) rowView.findViewById(R.id.date);
        date.setText(data.get(position).web);

        TextView day = (TextView) rowView.findViewById(R.id.day);
        day.setText(data.get(position).day);
        TextView event = (TextView) rowView.findViewById(R.id.event);
        event.setText(data.get(position).event);
        TextView type = (TextView) rowView.findViewById(R.id.type);
        type.setText(data.get(position).type);

        CheckBox check = (CheckBox) rowView.findViewById(R.id.chk);
        //check.visible
        if(data.get(position).type.contentEquals("Optional")) {
            check.setChecked(true);
        }else{
            check.setVisibility(View.GONE);
        }
        return rowView;
    }
}
