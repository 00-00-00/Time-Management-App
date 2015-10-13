package com.incture.leaveme.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incture.leaveme.DataHandle.RejectedLeaveHistoryAsyncTask;
import com.incture.leaveme.R;
import com.incture.leaveme.activity.ApplyLeavePage;
import com.incture.leaveme.data.LeaveHistoryData;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by JANANI.N on 04-09-2015.
 */
public class Pending extends Fragment {

    RecyclerView rv;
    ArrayList<LeaveHistoryData> list = new ArrayList<LeaveHistoryData>();

    public Pending(){


      /*  list.add(new LeaveHistoryData("FEBRUARY","13","Casual leave","Have destist oppointment tomorrow","2d","header"));

        list.add(new LeaveHistoryData("APRIL","19","Casual","Have destist oppointment tomorrow","2d","header"));

        list.add(new LeaveHistoryData("MAY","19","Casual","Have destist oppointment tomorrow","2d","header"));

        list.add(new LeaveHistoryData("JUNE","19","Casual","Have destist oppointment tomorrow","2d","header"));

        list.add(new LeaveHistoryData("JULY","05","Casual leave","Have destist oppointment tomorrow","2d","Header"));
        list.add(new LeaveHistoryData("JULY","15","Sick Leave","Sufferening from typhoid so cannot come","2d","Item"));

        list.add(new LeaveHistoryData("AUGUST","13","Casual leave","Have destist oppointment tomorrow","2d","header"));

        list.add(new LeaveHistoryData("SEPTEMBER","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));


        list.add(new LeaveHistoryData("OCTOBER","08","Priviledge Leave","Have destist oppointment tomorrow","2d","header"));
        list.add(new LeaveHistoryData("OCTOBER","19","Casual","Have destist oppointment tomorrow","2d","Item"));*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pending,container,false);
        FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ApplyLeavePage.class);
                getActivity().startActivity(intent);
            }
        });

        /*rv = (RecyclerView)view.findViewById(R.id.recyclerview_pending_leave);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        AdapterPendingLeaveHistory adapter = new AdapterPendingLeaveHistory(list,  new AdapterPendingLeaveHistory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        rv.setAdapter(adapter);
*/

        try {
            Log.d("LEAVE", "inside URi");
            //   URL uri = new URL("http://172.31.99.106:8000/leave-history");
            URL uri = new URL("http://172.16.10.157:8000/leave-history");
            // new LeaveHistoryAsyncTask(uri, ctx).execute();

            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
                new RejectedLeaveHistoryAsyncTask(uri,getActivity()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            else
                new RejectedLeaveHistoryAsyncTask(uri,getActivity()).execute();

            Log.d("LEAVE", "inside after URi");
        }catch (MalformedURLException e) {e.printStackTrace();
        }


        return view;
    }
}
