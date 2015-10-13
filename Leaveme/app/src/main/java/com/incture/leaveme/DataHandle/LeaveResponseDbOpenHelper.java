package com.incture.leaveme.DataHandle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Arjun on 12-10-2015.
 */
public class LeaveResponseDbOpenHelper extends SQLiteOpenHelper {


    public static String DATABASE_NAME = "com.incture.leaveresponse";
    private static final int DATABASE_VERSION = 1;

    //add table feed
    private static final String HISTORY_TABLE_NAME = "Leave_History_Response";

    private static final String HISTORY_TABLE_ID = "Id";
    private static final String HISTORY_TABLE_DATE = "Data";


    public LeaveResponseDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SUBTYPE_TEXT_TABLE = "CREATE TABLE " + HISTORY_TABLE_NAME + "("
                + HISTORY_TABLE_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_SUBTYPE_TEXT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HISTORY_TABLE_NAME);
        onCreate(db);
    }


    // Add the POJO for the response structure
    public void addMember(String response)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HISTORY_TABLE_DATE, response);

     //   db.insert(HISTORY_TABLE_ID, null, values);
        db.insert(HISTORY_TABLE_NAME,null,values);

        Log.d("DB","Insert success at addMember");

        db.close();
    }

    public String getResponseStructure()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + HISTORY_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String response = cursor.getString(1);
        Log.i("com.incture","Response from Database @ OpenHelper :"+response);
        Log.d("DB", "At getResponseStructure");
        return response;

    }


}
