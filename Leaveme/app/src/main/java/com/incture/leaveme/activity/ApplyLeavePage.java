package com.incture.leaveme.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.incture.leaveme.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ApplyLeavePage extends AppCompatActivity {

    Button b;
    EditText fromtext,totext,ftext,ttext;
    Calendar myCalendar,myCalendar1;

    String mySwitch,days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_leave_page);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Apply Leave");
        b=(Button)findViewById(R.id.button);
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#2979FF"));
        }
*/
        // s.setBackgroundResource(R.drawable.bg_shade);

        ImageButton fromdate=(ImageButton)findViewById(R.id.imageButton1);
        ImageButton todate=(ImageButton)findViewById(R.id.imageButton2);
        fromtext=(EditText)findViewById(R.id.textView2);
        totext=(EditText)findViewById(R.id.textView3);
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    myCalendar1 = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            // TODO Auto-generated method stub
                            myCalendar1.set(Calendar.YEAR, year);
                            myCalendar1.set(Calendar.MONTH, monthOfYear);
                            myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabel1();


                        }

                    };
                    new DatePickerDialog(ApplyLeavePage.this, date, myCalendar1
                            .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                            myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
                }

            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();


                    }

                };
                new DatePickerDialog(ApplyLeavePage.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Switch Vacationplanner = (Switch)  findViewById(R.id.switch2);
        Vacationplanner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LayoutInflater inflater = ApplyLeavePage.this.getLayoutInflater();
                    final View myView = inflater.inflate(R.layout.vacation_planner, null);

                    final AlertDialog.Builder dialog = new AlertDialog.Builder(ApplyLeavePage.this);

                    dialog.setView(myView);


                    EditText day=(EditText) myView.findViewById(R.id.num);

                    days=day.getText().toString();

                    Log.d("getdays", "" + days);

                    ImageButton button1 = (ImageButton) myView.findViewById(R.id.imageButton1);
                    ImageButton button2 = (ImageButton) myView.findViewById(R.id.imageButton2);
                    ftext=(EditText)myView.findViewById(R.id.textView2);
                    ttext=(EditText)myView.findViewById(R.id.textView3);

                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myCalendar = Calendar.getInstance();
                            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    // TODO Auto-generated method stub
                                    myCalendar.set(Calendar.YEAR, year);
                                    myCalendar.set(Calendar.MONTH, monthOfYear);
                                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                    updateLabel3();


                                }

                            };
                            new DatePickerDialog(ApplyLeavePage.this, date, myCalendar
                                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    });

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myCalendar1 = Calendar.getInstance();
                            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    // TODO Auto-generated method stub
                                    myCalendar1.set(Calendar.YEAR, year);
                                    myCalendar1.set(Calendar.MONTH, monthOfYear);
                                    myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                    updateLabel4();


                                }

                            };
                            new DatePickerDialog(ApplyLeavePage.this, date, myCalendar1
                                    .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                                    myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
                        }

                    });


                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface x, int id) {
                            // if this button is clicked, close
                                    // current activity
                            planner();

                            x.cancel();
                        }
                    })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    fromtext.setText("");
                                    Vacationplanner.setChecked(false);
                                    totext.setText("");
                                    dialog.cancel();
                                }
                            });
                    final AlertDialog alertDialog = dialog.create();
                    alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#2196F3"));
                            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#727272"));
                        }
                    });

                    // show it
                    alertDialog.show();
                }else{
                    fromtext.setText("");
                    totext.setText("");
                }

            }

        });


        Switch onOffSwitch = (Switch)  findViewById(R.id.switch1);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                RelativeLayout ll=(RelativeLayout)findViewById(R.id.to_details);
                if(isChecked==true)
                    ll.setVisibility(View.GONE);
                else
                    ll.setVisibility(View.VISIBLE);
            }

        });

     /*   b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Applied for Leave",
                        Toast.LENGTH_LONG).show();
                onBackPressed();

            }
        });*/

    }

    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        fromtext.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel1() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        totext.setText(sdf.format(myCalendar1.getTime()));
    }

    private void updateLabel3() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        fromtext.setText(sdf.format(myCalendar.getTime()));
        ftext.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel4() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        totext.setText(sdf.format(myCalendar1.getTime()));
        ttext.setText(sdf.format(myCalendar1.getTime()));


    }

    public void planner()
    {
        LayoutInflater inflater = ApplyLeavePage.this.getLayoutInflater();
        final View myView1 = inflater.inflate(R.layout.vacation_planner_popup, null);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(ApplyLeavePage.this);

        TextView abc=(TextView) myView1.findViewById(R.id.days);
        Log.d("daysvalue", "" + days);

        abc.setText("10");

        dialog.setView(myView1);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface x, int id) {
                // if this button is clicked, close
                // current activity

                x.cancel();
            }
        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        fromtext.setText("");
                        totext.setText("");
                        dialog.cancel();
                    }
                });
        final AlertDialog alertDialog = dialog.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#2196F3"));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#727272"));
            }
        });

        // show it
        alertDialog.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
