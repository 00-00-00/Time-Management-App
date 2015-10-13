package com.incture.leaveme.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.incture.leaveme.DataHandle.HolidayCalenderAsyncTask;
import com.incture.leaveme.R;
import com.incture.leaveme.data.temp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by JANANI.N on 04-09-2015.
 */
public class Holiday_Calendaar extends AppCompatActivity {
    ListView list;
    String[] web;
    ArrayList<temp> data = new ArrayList<>();
    RecyclerView rv;

    public Holiday_Calendaar() {
      //  data.add(new temp("Aug 15", "Saturday", "Independence Day", "Mandatory",R.drawable.));
      //  data.add(new temp("Aug 28", "Friday", "Thiruvonam", "Optional"));

       /* data.add(new temp("JANUARY","05", "Saturday", "Janmashtami", "Mandatory",R.drawable.agrasen,1));
        data.add(new temp("JANUARY","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,2));
        data.add(new temp("JANUARY","24", "Thursday", "Bakrid", "Optional",R.drawable.muharramwallpapershdislamic,3));

        data.add(new temp("FEBRUARY","02", "Friday", "Gandhi Jeyanthi", "Mandatory", R.drawable.gandhijayanthi,1));
        data.add(new temp("FEBRUARY","24", "Thursday", "Bakrid", "Optional",R.drawable.muharramwallpapershdislamic,2));
        data.add(new temp("FEBRUARY","02", "Friday", "Gandhi Jeyanthi", "Mandatory", R.drawable.gandhijayanthi,3));

        data.add(new temp("MARCH","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,1));
        data.add(new temp("MARCH","05", "Saturday", "Janmashtami", "Mandatory",R.drawable.agrasen,2));
        data.add(new temp("MARCH","05", "Saturday", "Janmashtami", "Mandatory",R.drawable.gandhijayanthi,3));

        data.add(new temp("APRIL","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,1));
        data.add(new temp("APRIL","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,2));
        data.add(new temp("APRIL","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,3));

        data.add(new temp("MAY","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,1));
        data.add(new temp("MAY","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,2));
        data.add(new temp("MAY","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,3));

        data.add(new temp("JUNE","21", "Monday", "Sree Narayana Guru Samadhi", "Optional",R.drawable.god22h,1));*/

    }

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_calendar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Holiday Calender");

        ctx = this;


        //Set the custom toolbar as the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton b;
        b = (FloatingActionButton) findViewById(R.id.fabBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Holiday_Calendaar.this, ApplyLeavePage.class);
                startActivity(i);
            }
        });
     //   new JSONAsyncTask().execute();

        try {
            Log.d("LEAVE", "inside URi");
            //   URL uri = new URL("hleave-history99.106:8000/leave-history");
            URL uri = new URL("http://172.16.10.157:8000/holidays");
            // new LeaveHistoryAsyncTask(uri, ctx).execute();

            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.HONEYCOMB)
                new HolidayCalenderAsyncTask(uri,ctx).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            else
                new HolidayCalenderAsyncTask(uri,ctx).execute();

            Log.d("LEAVE", "inside after URi");
        }catch (MalformedURLException e) {e.printStackTrace();
        }




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

        }
        return true;
    }

    //Async task
  /*  class JSONAsyncTask extends AsyncTask<String, Void, String> {
        private String urlstring = "http://192.168.0.48:8888/holidays?id=5608f9af98fbab3209bc7431";
        Context ctx = getApplicationContext();
        @Override
        protected String doInBackground(String... urls) {

            String stream = null;
            String urlString = urlstring;
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

    Vector<temp> data = new Vector<temp>();
    protected void onPostExecute(String stream){
        Log.i("JSON", " onPostExecute ");
        //..........Process JSON DATA................
        if(stream !=null){
            try{
                Log.i("JSON", " stream got data ");
                JSONObject reader= new JSONObject(stream);
                JSONArray jarray = reader.getJSONArray("data");

                for (int i = 0; i < jarray.length(); i++) {
                    Log.i("JSON", " stream got data and went inside array");

                    JSONObject object = jarray.getJSONObject(i);

                    String name= object.getString("name");
                    String date = object.getString("date");
                    String day= "sunday";
                    String type= object.getString("type");

                    Log.i("JSON", " " + name + " " + date + " " + type);
                    data.add(new temp(name, date, day, type));

                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            ListView lv= (ListView)findViewById(R.id.list);
            lv.setAdapter(new Holiday_items(ctx, R.layout.list_item_holiday_calendar, data));
        } // if statement end
    } // onPostExecute() end
} // ProcessJSON class end*/
} // Activity end

  /* // Get the full HTTP Data as JSONObject
                JSONObject reader= new JSONObject(stream);
                // Get the JSONObject "data"...........................
                JSONObject coord = reader.getJSONObject("data");
                // Get the value of key "lon" under JSONObject "coord"
                String lon = coord.getString("lon");
                // Get the value of key "lat" under JSONObject "coord"
                String lat = coord.getString("lat");

                // Get the JSONArray weather
                JSONArray weatherArray = reader.getJSONArray("weather");
                // Get the weather array first JSONObject
                JSONObject weather_object_0 = weatherArray.getJSONObject(0);
                String weather_0_id = weather_object_0.getString("id");
                String weather_0_main = weather_object_0.getString("main");
                String weather_0_description = weather_object_0.getString("description");
                String weather_0_icon = weather_object_0.getString("icon");*/





          /*  try {
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    Vector<temp> data1 = new Vector<temp>();
                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("data");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        String name= object.getString("name");
                        String date = object.getString("date");
                        String day= "sunday";
                        String type= object.getString("type");


                        Log.i("JSON"," "+name+" "+date+" "+type);
                        data1.add(new temp(name, date, day, type));



                    }
                    return true;
                }
                //Async task end
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

                        */
