package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.incture.leaveme.AdapterHolidayList;
import com.incture.leaveme.R;
import com.incture.leaveme.data.temp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

        String responseString = new String();

        try {Log.d("LEAVE","doInBackground starts here");


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(5000);

            Log.d("LEAVE", "doInBackground after conTimeouts");


            httpURLConnection.setRequestMethod("GET");   //change to post
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
            httpURLConnection.setRequestProperty("uniqueid","56179c3bd303v95d0e5793bb");

            Log.d("LEAVE", "doInBackground after setRequestProperty");
            /*
            //Create the Json object to send to the server
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user-id :","Blah blah");   //change the object
            String request = jsonObject.toString();
            */

            //  OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            //Add the param objects to the request
         /*   for (int i = 0; i < params.length; i++) {
                outputStreamWriter.write(params[i].toString());
            }
            outputStreamWriter.close();
*/
            Log.d("LEAVE","inside async task");

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                Log.d("LEAVE","inside HTTP_OK");

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bufferedReader.close();
                responseString = response.toString();
            } else {
                LeaveResponseDbOpenHelper leaveResponseDbOpenHelper = new LeaveResponseDbOpenHelper(context);
                Log.d("LEAVE","else of inside HTTP_OK");
                return leaveResponseDbOpenHelper.getResponseStructure();


            }


            //id response successful add to table
            if (responseString != null && responseString.length() != 0) {
                try {

                    JSONArray jsonArray = new JSONArray(responseString);
                    //Parse the JSON and insert into DB;
                    LeaveResponseDbOpenHelper memberListTableOpenHelper = new LeaveResponseDbOpenHelper(context);
                    //Parsing JSON
                    memberListTableOpenHelper.addMember(responseString);
                    Log.i("com.incture","The response structure added to db @OpenHelper: "+responseString);
                    return responseString;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                //if response failed then read from database

                LeaveResponseDbOpenHelper leaveResponseDbOpenHelper = new LeaveResponseDbOpenHelper(context);
                return leaveResponseDbOpenHelper.getResponseStructure();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(final String o) {


        Log.d("LEAVE","onPostExecute");
        if (o != null) {
            try {
                JSONObject reader = new JSONObject(o);
                JSONArray jarray = reader.getJSONArray("data");

                ArrayList<temp> data = new ArrayList<temp>();

                String month="";
                int posCount=0;
                for(int i=0;i<jarray.length();i++){

                    JSONObject object = jarray.getJSONObject(i);

                    String fromDate= object.getString("date");
                    DateFormat fDateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
                    Date fDate = fDateFormat.parse(fromDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fDate);

                    String prevMonth= month;
                    month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.ENGLISH).toUpperCase().toString();
                    String date = cal.getDisplayName(Calendar.DATE,Calendar.LONG,Locale.ENGLISH).toString();
                    String day = cal.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.ENGLISH).toString();

                    String name= object.getString("name");
                    String type= object.getString("type");

                   /* if(prevMonth.isEmpty()||prevMonth.equalsIgnoreCase(""))
                        data.add(new temp(month,date,day,name,type,1,1));
                    else */if(prevMonth.equalsIgnoreCase(month)||prevMonth.isEmpty()||prevMonth.equalsIgnoreCase(""))
                        data.add(new temp(month,date,day,name,type,1,++posCount));
                    else
                        data.add(new temp(month,date,day,name,type,1,1));
                        posCount=1;
                }

                RecyclerView rv = (RecyclerView)((AppCompatActivity)context).findViewById(R.id.list);
                rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

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
