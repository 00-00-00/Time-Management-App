package com.incture.leaveme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incture.leaveme.R;
import com.incture.leaveme.activity.ApplyLeavePage;
import com.incture.leaveme.adapter.AdapterApprovedLeaveHistory;
import com.incture.leaveme.data.LeaveHistoryData;

import java.util.ArrayList;

/**
 * Created by JANANI.N on 04-09-2015.
 */
public class Approved extends Fragment {
        RecyclerView rv;
        ArrayList<LeaveHistoryData> list = new ArrayList<LeaveHistoryData>();

        public Approved(){

                list.add(new LeaveHistoryData("JANUARY","05","Casual leave","Have destist oppointment tomorrow","2d","First"));
                list.add(new LeaveHistoryData("JANUARY","15","Sick Leave","Sufferening from typhoid so cannot come","2d","Item"));


                list.add(new LeaveHistoryData("FEBRUARY","13","Casual leave","Have destist oppointment tomorrow","2d","header"));


                list.add(new LeaveHistoryData("MARCH","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));
                list.add(new LeaveHistoryData("MARCH","19","Casual","Have destist oppointment tomorrow","2d","Item"));


                list.add(new LeaveHistoryData("APRIL","19","Casual","Have destist oppointment tomorrow","2d","header"));

                list.add(new LeaveHistoryData("MAY","19","Casual","Have destist oppointment tomorrow","2d","header"));

                list.add(new LeaveHistoryData("JUNE","19","Casual","Have destist oppointment tomorrow","2d","header"));

                list.add(new LeaveHistoryData("JULY","05","Casual leave","Have destist oppointment tomorrow","2d","Header"));
                list.add(new LeaveHistoryData("JULY","15","Sick Leave","Sufferening from typhoid so cannot come","2d","Item"));

                list.add(new LeaveHistoryData("AUGUST","13","Casual leave","Have destist oppointment tomorrow","2d","header"));
                list.add(new LeaveHistoryData("AUGUST","27","Casual","Have destist oppointment tomorrow","2d","Item"));
                list.add(new LeaveHistoryData("SEPTEMBER","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));
                list.add(new LeaveHistoryData("SEPTEMBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));
                list.add(new LeaveHistoryData("SEPTEMBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));

                list.add(new LeaveHistoryData("OCTOBER","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));
                list.add(new LeaveHistoryData("OCTOBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));
                list.add(new LeaveHistoryData("OCTOBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));

                list.add(new LeaveHistoryData("NOVEMBER","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));
                list.add(new LeaveHistoryData("NOVEMBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));
                list.add(new LeaveHistoryData("NOVEMBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));

        }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.approved,container,false);
        FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ApplyLeavePage.class);
                        getActivity().startActivity(intent);
                }
        });

        rv = (RecyclerView)view.findViewById(R.id.recyclerview_approved_leave);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        AdapterApprovedLeaveHistory adapter = new AdapterApprovedLeaveHistory(list,  new AdapterApprovedLeaveHistory.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }
        });
        rv.setAdapter(adapter);
        return view;

        }
}
