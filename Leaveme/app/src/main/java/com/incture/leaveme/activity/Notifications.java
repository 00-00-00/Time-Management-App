package com.incture.leaveme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.incture.leaveme.R;
import com.incture.leaveme.adapter.CustomList;


/**
 * Created by JANANI.N on 02-09-2015.
 */
public class Notifications extends AppCompatActivity {
    ListView list;
    String[] web = {
            "Your leave request for March 20 has been approved",
            "Your leave request for March 19 has been rejected"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notifications");


        //Set the custom toolbar as the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CustomList adapter = new
                CustomList(Notifications.this, web);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}