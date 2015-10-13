package com.incture.leaveme.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.incture.leaveme.R;
import com.incture.leaveme.data.LeaveHistoryData;

import java.util.ArrayList;

/**
 * Created by Mohammed on 10/12/2015.
 */
public class AdapterApprovedLeaveHistory extends RecyclerView.Adapter<AdapterApprovedLeaveHistory.ViewHolder> {
    static Context context;
    ArrayList<LeaveHistoryData> nList;

    private OnItemClickListener mOnItemClickListener;
    LayoutInflater layoutInflater;


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView hdate,htype,hdesc,hnumday,monthtext;
        public final LinearLayout sep;
        public final ImageView divider;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            hdate = (TextView) view.findViewById(R.id.hdate);
            htype=(TextView)view.findViewById(R.id.htype);
            hdesc=(TextView)view.findViewById(R.id.hdesc);
            hnumday=(TextView)view.findViewById(R.id.hdays);
            monthtext=(TextView)view.findViewById(R.id.monthtext);
            sep = (LinearLayout)view.findViewById(R.id.separatorlayout);
            divider = (ImageView)view.findViewById(R.id.topdivider);

            Typeface typeface_regular= Typeface.createFromAsset(context.getAssets(),"Roboto-Bold.ttf");
            monthtext.setTypeface(typeface_regular);

            Typeface typeface= Typeface.createFromAsset(context.getAssets(),"Roboto-Regular.ttf");
            hdesc.setTypeface(typeface);
            htype.setTypeface(typeface);
            hnumday.setTypeface(typeface);
        }
    }

    public AdapterApprovedLeaveHistory(ArrayList<LeaveHistoryData> items, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        nList = items;
    }


    @Override
    public AdapterApprovedLeaveHistory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(context);
        }

        View view = new View(context);

      /* switch (this.viewType) {
            case 0:
                view = layoutInflater.inflate(R.layout.leavehistory_separator, parent, false);
                break;
            case 1:*/
        view = layoutInflater.inflate(R.layout.approvedleavehistory_item, parent, false);
          /*      break;
        }*/
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        LeaveHistoryData oList;
        oList = nList.get(position);
        switch (oList.getHead().toUpperCase()){
            case "FIRST" :return 1;
            case "HEADER" :return 2;
            case "ITEM":return 3;
        }
        return 4;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        LeaveHistoryData oList;
        oList = nList.get(position);
        holder.hdate.setText(oList.getDate());
        holder.htype.setText(oList.getType());
        holder.hdesc.setText(oList.getDesc());
        holder.hnumday.setText(oList.getNumdays());

        try {
            switch (getItemViewType(position)) {
                case 1:
                    holder.monthtext.setText(oList.getMonth());
                    holder.divider.setVisibility(View.GONE);
                    break;
                case 2:
                    holder.monthtext.setText(oList.getMonth());
                    break;
                case 3:
                    holder.sep.setVisibility(View.GONE);
                    break;
                default: break;

            }
        } catch (IndexOutOfBoundsException e) {

        } catch (NullPointerException e) {
        }
          /*  try {
                if (oList.getHead().equalsIgnoreCase("first")) {
                    holder.monthtext.setText(oList.getMonth());
                    holder.divider.setVisibility(View.GONE);
                }
                if(oList.getHead().equalsIgnoreCase("header")){
                    holder.monthtext.setText(oList.getMonth());
                }
                if(oList.getHead().equalsIgnoreCase("Item")){
                    holder.sep.setVisibility(View.GONE);
                }
               *//* } else if (nList.get(position).getMonth().equalsIgnoreCase(nList.get(position - 1).getMonth())) {
                    holder.sep.setVisibility(View.GONE);
                } else {
                    holder.monthtext.setText(oList.getMonth());
                }*//*
            } catch (IndexOutOfBoundsException e) {

            } catch (NullPointerException e) {
            }*/
    }
    //else
   /*     {
            oList = nList.get(position);

        }*/
    //  }
    @Override
    public int getItemCount() {
        return nList.size();
    }


}
