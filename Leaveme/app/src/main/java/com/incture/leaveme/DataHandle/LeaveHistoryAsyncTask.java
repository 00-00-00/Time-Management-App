package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.AdapterAllLeaveHistory;
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
 * Created by Arjun on 12-10-2015.
 */
public class LeaveHistoryAsyncTask extends AsyncTask<Void,Void,String> {
    URL url;
    Context context;
    RecyclerView rv;

    public LeaveHistoryAsyncTask(URL url, Context context) {
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

      /*  String responseString = new String();

        try {
            Log.d("LEAVE","doInBackground starts here");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setDoInput(true);
         //   httpURLConnection.setConnectTimeout(5000);

            Log.d("LEAVE", "doInBackground after conTimeouts");

            httpURLConnection.setRequestMethod("GET");   //change to post
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/json");
          //  httpURLConnection.setRequestProperty("uniqueid","56179c3bd303b95d0e5793bb");  //Vikram
            httpURLConnection.setRequestProperty("uniqueid","561ca12921d98b4b0e72b086");    //Monisha
            Log.d("LEAVE", "doInBackground after setRequestProperty");
            *//*
            //Create the Json object to send to the server
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user-id :","Blah blah");   //change the object
            String request = jsonObject.toString();
            *//*

          //  OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            //Add the param objects to the request
         *//*   for (int i = 0; i < params.length; i++) {
                outputStreamWriter.write(params[i].toString());
            }
            outputStreamWriter.close();
*//*
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
                return responseString;
            }*/ /*else {
                LeaveResponseDbOpenHelper leaveResponseDbOpenHelper = new LeaveResponseDbOpenHelper(context);
                Log.d("LEAVE","else of inside HTTP_OK");
                return leaveResponseDbOpenHelper.getResponseStructure();


            }*/

            //id response successful add to table
           /* if (responseString != null && responseString.length() != 0) {

                    //Parse the JSON and insert into DB;
                    LeaveResponseDbOpenHelper memberListTableOpenHelper = new LeaveResponseDbOpenHelper(context);
                    //Parsing JSON
                    memberListTableOpenHelper.addMember(responseString);
                    Log.i("com.incture","The response structure added to db @OpenHelper: "+responseString);
                    return responseString;


            } else {
                //if response failed then read from database
                LeaveResponseDbOpenHelper leaveResponseDbOpenHelper = new LeaveResponseDbOpenHelper(context);
                return leaveResponseDbOpenHelper.getResponseStructure();

            }*/

        /*} catch (IOException e) {
            e.printStackTrace();
        }*/

      //  return null;
    }
    ArrayList<LeaveHistoryData> data = new ArrayList<LeaveHistoryData>();

    @Override
    protected void onPostExecute(final String o) {


        Log.d("LEAVE","onPostExecute");
        if (o != null) {
         try {

             JSONObject reader = new JSONObject(o);
             JSONArray jarray = reader.getJSONArray("data");
             Log.d("DATA","before forloop JSONArray");
             String month="";
             for(int i=0;i<jarray.length();i++){

                 Log.d("DATA", "INside for loop JSONArray");

                 JSONObject object = jarray.getJSONObject(i);

                 String fromDate= object.getString("from");
                 Log.d("DATA", "INside for loop JSONArray " + fromDate);

                 String type= object.getString("type");
                 Log.d("DATA","INside for loop JSONArray type "+type);

                 String desc= object.getString("reason");
                 String ndays= object.getString("noOfDays");

                 String stats = object.getString("status");

                 DateFormat fDateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                 Date fDate = fDateFormat.parse(fromDate);

                 Log.d("DATA","INside for loop JSONArray fDate "+fDate);
                 Calendar cal = Calendar.getInstance();
                 cal.setTime(fDate);
                 Log.d("DATA", "INside for loop JSONArray After Calender " + fDate);

                /* TimeZone tz = TimeZone.getTimeZone("UTC");
                 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                 df.setTimeZone(tz);
                 String nowAsISO = df.format(new Date());

                 Date fDate = fDateFormat.parse(fromDate);*/

                 String prevMonth= month;
                 month = cal.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.ENGLISH).toUpperCase().toString();
                 Log.d("DATA","INside for loop JSONArray month :"+month);

                 String date = TimeUtils.getDateMonthFromDate(fromDate);
                 Log.d("date","INside for loop JSONArray Date :"+date);

                /* String type= object.getString("type");
                 Log.d("DATA","INside for loop JSONArray type "+type);

                 String desc= object.getString("reason");
                 String ndays= object.getString("noOfDays");*/

                 Log.d("DATA","month :"+month+"date :"+date+"type :"+type);

                 if(prevMonth.isEmpty()||prevMonth.equalsIgnoreCase(""))
                     data.add(new LeaveHistoryData(month,date,type,desc,ndays,"First",stats));
                 else if(prevMonth.equalsIgnoreCase(month))
                     data.add(new LeaveHistoryData(month,date,type,desc,ndays,"header",stats));
                 else
                     data.add(new LeaveHistoryData(month,date,type,desc,ndays,"Item",stats));
                 Log.d("DATA","month :"+month+"date :"+date+"type :"+type);
             }

             Log.d("DATA","Outside Array");

             rv = (RecyclerView)((AppCompatActivity)context).findViewById(R.id.recyclerview_all_leave);
             rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

             AdapterAllLeaveHistory adapter = new AdapterAllLeaveHistory(data,  new AdapterAllLeaveHistory.OnItemClickListener() {
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