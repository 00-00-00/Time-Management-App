package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.incture.leaveme.AdapterHolidayList;
import com.incture.leaveme.R;
import com.incture.leaveme.data.temp;
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
public class HolidayCalenderAsyncTask extends AsyncTask<Void,Void,String> {
    URL url;
    Context context;
    RecyclerView rv;

    public HolidayCalenderAsyncTask(URL url, Context context) {
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

    ArrayList<temp> data = new ArrayList<temp>();
    @Override
    protected void onPostExecute(final String o) {
        Log.d("LEAVE","onPostExecute");
        if (o != null) {
            try {
                Log.d("LEAVE","onPostExecute inside try");
                JSONObject reader = new JSONObject(o);
                JSONArray jarray = reader.getJSONArray("data");

                String month="";
                String prevMonth="";
                int posCount=0;
                for(int i=0;i<jarray.length();i++){

                    Log.d("LEAVE","onPostExecute inside try for loop");
                    JSONObject object = jarray.getJSONObject(i);
                    String fromDate= object.getString("date");

                    Log.d("LEAVE","onPostExecute inside try for loop fromDate :"+fromDate);

                    DateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    Date fDate = fDateFormat.parse(fromDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fDate);

                    prevMonth = month;
                    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH).toUpperCase().toString();
                    String date = TimeUtils.getDateMonthFromDate(fromDate);

                    Log.d("LEAVE","onPostExecute inside try for loop month :"+month);

                  //  String day = cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.ENGLISH).toString();
                    String day="Mon";
                    String name= object.getString("name");
                    Log.d("LEAVE","onPostExecute inside try for loop name :"+name);

                    String type= object.getString("type");
                    Log.d("LEAVE","onPostExecute inside try for loop type :"+type);

                    String imagname= object.getString("imgname");

                    if(prevMonth.equalsIgnoreCase(month)||prevMonth.isEmpty()||prevMonth.equalsIgnoreCase(""))
                        data.add(new temp(month,date,day,name,type,imagname,++posCount));
                    else
                        data.add(new temp(month,date,day,name,type,imagname,1));
                        posCount=1;

                    Log.d("DATA", "month :" + month + "date :" + date + "type :" + type);
                }

                rv = (RecyclerView)((AppCompatActivity)context).findViewById(R.id.list);
                rv.setLayoutManager(new GridLayoutManager(rv.getContext(),3));
                rv.setHasFixedSize(true);

                AdapterHolidayList adapter = new AdapterHolidayList(data, new AdapterHolidayList.OnItemClickListener() {
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
