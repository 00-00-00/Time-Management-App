package com.incture.leaveme;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incture.leaveme.DataHandle.ServerDetails;
import com.incture.leaveme.activity.ApplyLeavePage;
import com.incture.leaveme.activity.Holiday_Calendaar;
import com.incture.leaveme.activity.Leave_history;
import com.incture.leaveme.activity.Notifications;
import com.incture.leaveme.activity.Reports;
import com.incture.leaveme.helper.HTTPDataHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JANANI.N on 03-09-2015.
 */
public class Landing_page extends AppCompatActivity {
    Button b1,bb;

    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private static final String NAV_ITEM_ID = "navItemId";

    private DrawerLayout mDrawerLayout;
    private int mNavItemId;
    private ActionBarDrawerToggle mDrawerToggle;

    CalendarView cal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        pagesetup();

        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing_page.this, ApplyLeavePage.class);
                startActivity(intent);
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        RelativeLayout l1=(RelativeLayout)findViewById(R.id.l1);
        RelativeLayout l2=(RelativeLayout)findViewById(R.id.l2);
        l1.getLayoutParams().width=width/2;
        l2.getLayoutParams().width=width/2;

        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Landing_page.this, Holiday_Calendaar.class);
                startActivity(i);
            }

        });

        bb = (Button) findViewById(R.id.button2);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Landing_page.this, Leave_history.class);
                startActivity(i);

            }

        });

        new  LeaveRecord_AsyncTask().execute();

        cal = (CalendarView) findViewById(R.id.calendarView);
        cal.setShowWeekNumber(false);

        // Get the Drawable custom_progressbar
        Drawable draw=getResources().getDrawable(R.drawable.custom_progressbar);
// set the drawable as progress drawable
        ProgressBar progressBar =(ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setProgressDrawable(draw);

       // cal.setMinDate(min.getTime());
    }

    public void pagesetup(){

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Leave");

        //Set the custom toolbar as the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */

                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //  getSupportActionBar().setTitle("Open");

            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Close");

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        ((NavigationView)findViewById(R.id.navigation_view1)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.drawer_item_1:
                        Intent i = new Intent(Landing_page.this, Landing_page.class);
                        startActivity(i);
                        return true;
                    case R.id.drawer_item_2:
                        Intent i1 = new Intent(Landing_page.this, ApplyLeavePage.class);
                        startActivity(i1);
                        return true;
                    case R.id.drawer_item_3:
                        Intent i2 = new Intent(Landing_page.this, Notifications.class);
                        startActivity(i2);
                        break;
                    case R.id.drawer_item_4:
                        Intent i3 = new Intent(Landing_page.this, Reports.class);
                        startActivity(i3);
                        break;
                    case R.id.drawer_item_5:
                        Intent i4 = new Intent(Landing_page.this, Holiday_Calendaar.class);
                        startActivity(i4);
                        break;
                }
                return false;
            }

        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    class LeaveRecord_AsyncTask extends AsyncTask<String, Void, String> {
        private String urlstring = ServerDetails.LEAVE_SUMMARY;

        Context ctx = getApplicationContext();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {

            String stream = null;
            String urlString = urlstring;
            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);
            return stream;
        }

        protected void onPostExecute(String stream){
            String availVal= null, availableVal=null;

            Log.i("JSON", " onPostExecute ");
            //..........Process JSON DATA................
            if(stream !=null){
                try{
                    Log.i("JSON", " stream got data ");
                    JSONObject reader= new JSONObject(stream);

                    JSONObject coord = reader.getJSONObject("data");

                    availVal = coord.getString("availed");

                    availableVal = coord.getString("available");
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

                TextView availed = (TextView)findViewById(R.id.availed);
                availed.setText(availVal);

                TextView available = (TextView)findViewById(R.id.available);
                available.setText(availableVal);

            } // if statement end
        } // onPostExecute() end
    }



}
