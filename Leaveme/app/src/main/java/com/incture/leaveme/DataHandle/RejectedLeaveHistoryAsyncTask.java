package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.AdapterPendingLeaveHistory;
import com.incture.leaveme.data.LeaveHistoryData;
import com.incture.leaveme.helper.HTTPDataHandler;
import com.incture.leaveme.utility.TimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mohammed on 10/13/2015.
 */
public class RejectedLeaveHistoryAsyncTask extends AsyncTask<Void,Void,String> {
    URL url;
    Context context;
    RecyclerView rv;

    public RejectedLeaveHistoryAsyncTask(URL url, Context context) {
        this.url = url;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("LEAVE", "onPreExecute");
    }

    @Override
    protected String doInBackground(Void... params) {

        String stream = null;
        String urlString = url.toString();
        HTTPDataHandler hh = new HTTPDataHandler();
        stream = hh.GetHTTPData(urlString);
        return stream;
    }
    ArrayList<LeaveHistoryData> data = new ArrayList<LeaveHistoryData>();

    @Override
    protected void onPostExecute(final String o) {


        Log.d("LEAVE","onPostExecute");
        if (o != null) {
            try {
                JSONObject reader = new JSONObject(o);
                JSONArray jarray = reader.getJSONArray("data");
                Log.d("DATA", "before forloop JSONArray");
                String month="";
                String prevMonth="";
                for(int i=0;i<jarray.length();i++){

                    Log.d("DATA", "INside for loop JSONArray");

                    JSONObject object = jarray.getJSONObject(i);

                    String stats = object.getString("status");
                    if(stats.equalsIgnoreCase("rejected")) {

                        String fromDate = object.getString("from");
                        Log.d("DATA", "INside for loop JSONArray " + fromDate);

                        String type = object.getString("type");
                        Log.d("DATA", "INside for loop JSONArray type " + type);

                        String desc = object.getString("reason");
                        String ndays = object.getString("noOfDays");


                        DateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                        Date fDate = fDateFormat.parse(fromDate);

                        Log.d("DATA", "INside for loop JSONArray fDate " + fDate);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(fDate);
                        Log.d("DATA", "INside for loop JSONArray After Calender " + fDate);

                        prevMonth = month;
                        month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase().toString();
                        Log.d("DATA", "INside for loop JSONArray month :" + month);

                        String date = TimeUtils.getDateMonthFromDate(fromDate);
                        Log.d("date", "INside for loop JSONArray Date :" + date);

                        Log.d("DATA", "month :" + month + "date :" + date + "type :" + type);

                        if (prevMonth.equalsIgnoreCase(""))
                            data.add(new LeaveHistoryData(month, date, type, desc, ndays, "First",stats));
                        else if (prevMonth.equalsIgnoreCase(month))
                            data.add(new LeaveHistoryData(month, date, type, desc, ndays, "item",stats));
                        else if(!prevMonth.equalsIgnoreCase(month))
                            data.add(new LeaveHistoryData(month, date, type, desc, ndays, "Header",stats));

                        Log.d("DATA", "month :" + month + "date :" + date + "type :" + type);
                    }
                }

                Log.d("DATA", "Outside Array");

                RecyclerView rv = (RecyclerView)((AppCompatActivity)context).findViewById(R.id.recyclerview_pending_leave);
                rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

                AdapterPendingLeaveHistory adapter = new AdapterPendingLeaveHistory(data,  new AdapterPendingLeaveHistory.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                });
                rv.setAdapter(adapter);


            }catch (JSONException e){

            }catch (ParseException e){

            }


        }

    }
}