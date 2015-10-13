package com.incture.leaveme;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incture.leaveme.data.temp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class AdapterHolidayList extends RecyclerView.Adapter<AdapterHolidayList.ViewHolder> {
    static Context context;
    ArrayList<temp> nList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mTextView,month_indicator,date,day,type;
        public final ImageView mArtView;
        public final LinearLayout linearLayout;
        public final RelativeLayout fm;
        int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.HolidayName);
            mArtView= (ImageView)view.findViewById(R.id.HolidayPicture);
            month_indicator=(TextView)view.findViewById(R.id.month_indicator);
            date=(TextView)view.findViewById(R.id.hdate);
            day=(TextView)view.findViewById(R.id.hday);
            type=(TextView)view.findViewById(R.id.Holidaytype);
            linearLayout=(LinearLayout)view.findViewById(R.id.gridfooter);
            fm= (RelativeLayout)view.findViewById(R.id.frameblur);

            Typeface typeface_regular= Typeface.createFromAsset(context.getAssets(),"Roboto-Regular.ttf");
            month_indicator.setTypeface(typeface_regular);
            day.setTypeface(typeface_regular);

            Typeface typeface= Typeface.createFromAsset(context.getAssets(),"Roboto-Thin.ttf");
            mTextView.setTypeface(typeface);
            date.setTypeface(typeface);
            type.setTypeface(typeface);

          /*  int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            linearLayout.setBackgroundColor(randomAndroidColor);
            fm.setBackgroundColor();*/

            int r = (int) (0xff * Math.random());
            int g = (int) (0xff * Math.random());
            int b = (int) (0xff * Math.random());
            // int alpha = B3;
            int alpha = 0xB3;
            int alphafooter=0xFF;
            linearLayout.setBackgroundColor(Color.argb(alphafooter, r, g, b));
            fm.setBackgroundColor(Color.argb(alpha, r, g, b));
        }
    }

    public AdapterHolidayList(ArrayList<temp> items, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        nList = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holiday_calender_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        temp oList;
        oList = nList.get(position);
        switch (oList.getPosition()){
            case  1: return 1;
            case  2: return 2;
            case  3: return 3;
            default: return 4;
        }
    }

    private int count=0;
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        temp oList = nList.get(position);
        switch(getItemViewType(position)){
            case 1: holder.month_indicator.setVisibility(View.VISIBLE);
                    holder.month_indicator.setText("" + oList.getMonth());
                    break;
            case 2:  holder.month_indicator.setVisibility(View.VISIBLE);
                     holder.month_indicator.setText("");
                     break;
            case 3:  holder.month_indicator.setVisibility(View.VISIBLE);
                     holder.month_indicator.setText("");
                     break;
            default:  holder.month_indicator.setVisibility(View.GONE);
                      break;
        }
        holder.mTextView.setText(oList.getEvent());

        Picasso.with(context)
                .load("http://172.16.10.157:8000"+oList.getLink())
                        .error(R.drawable.abc_action_bar_item_background_material)
                        .into(holder.mArtView);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
       /* try{
        if(position< nList.size())
           pList = nList.get(position+1);
        }catch (IndexOutOfBoundsException e){}
        Log.i("COUNT", "Initially:" + count);

        if(count==0){
            holder.month_indicator.setVisibility(View.VISIBLE);
            holder.month_indicator.setText("" + oList.getMonth());
            Log.i("COUNT", "Initially:" +oList.getMonth());
        }else if(count==1 || count==2){
            holder.month_indicator.setVisibility(View.VISIBLE);
            holder.month_indicator.setText("");
        }else {
            holder.month_indicator.setVisibility(View.GONE);
        }

        holder.mTextView.setText(oList.getEvent());

        Picasso.with(context)
                .load(oList.getImage())
                .error(R.drawable.abc_action_bar_item_background_material)
                .into(holder.mArtView);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });

        try {
            if (oList.getMonth() == pList.getMonth()) {
                count++;
                Log.i("COUNT","INCREMENTED to :"+count);
            } else {
                count = 0;
                Log.i("COUNT","RESET to :"+count);
            }
        }catch (NullPointerException e){

        }
*/
    }
    @Override
    public int getItemCount() {
        return nList.size();
    }


}
