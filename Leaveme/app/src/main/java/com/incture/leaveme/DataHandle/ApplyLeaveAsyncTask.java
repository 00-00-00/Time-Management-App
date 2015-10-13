package com.incture.leaveme.DataHandle;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Arjun on 13-10-2015.
 */
public class ApplyLeaveAsyncTask extends AsyncTask<Object, Void, Object>
{
    Context context;
    URL url;

    public ApplyLeaveAsyncTask(Context context, URL url)
    {
        this.context = context;
        this.url = url;
    }



    @Override
    protected Object doInBackground(Object... params) {
        String responseString = new String();

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);


            httpURLConnection.setRequestMethod("POST");   //change to post
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/json");


            httpURLConnection.setRequestProperty("uniqueid", ServerDetails.UNIQUE_ID);



            /*
            //Create the Json object to send to the server
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user-id :","Blah blah");   //change the object
            String request = jsonObject.toString();
            */

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());

            //Add the param objects to the request
            for(int i=0;i<params.length;i++)
            {
                outputStreamWriter.write(params[i].toString());
            }



            outputStreamWriter.close();

            if(httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK)
            {

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                StringBuffer response = new StringBuffer();
                while((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                bufferedReader.close();

                responseString = response.toString();
                Log.i("com.incture","Response recieved @background :"+responseString);
                return responseString;
            }
            else
            {
               // Retrieve from database
            }


            //id response successful add to table
            if(responseString!=null && responseString.length()!=0) {
               return responseString;
            }
            else
            {
                //if response failed then read from database

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object o) {

        if(o!=null)
        {
            try {

                Log.i("com.incture","Response recieved @onPostExecute: "+o.toString());
                JSONObject jsonResponse = new JSONObject(o.toString());
//                if(jsonResponse.optString("status").equals(ServerDetails.RESPONSE_SUCCESS))
                {
                    Toast.makeText(context, jsonResponse.optString("data"),Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
