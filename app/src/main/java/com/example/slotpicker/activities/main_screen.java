package com.example.slotpicker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slotpicker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class main_screen extends AppCompatActivity  {

    CalendarView calendarView;
    Spinner spinner;
    String slots[];
    Date today;

    Calendar selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        initviews();
        long time= System.currentTimeMillis();
        calendarView.setMinDate(time);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Toast.makeText(getApplicationContext(),String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(dayOfMonth),Toast.LENGTH_LONG).show();
                selected = Calendar.getInstance();
                selected.set(year,month,dayOfMonth);
                today = new Date();
                Date selected_calendar = selected.getTime();
                System.out.println(selected_calendar);
                int start_hr= getSpinnerStart(today, selected_calendar);
                setSpinner(start_hr);


            }
        });

    }

    private int getSpinnerStart(Date today, Date selected_calendar) {
        if(selected_calendar.compareTo(today)>0)
            return 0;
        else
        {
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            int current_hr = Integer.parseInt(currentTime.split(":")[0]);
            return current_hr;
        }
    }

    public void initviews()
    {
        calendarView = findViewById(R.id.calender);
        spinner = findViewById(R.id.time_slots);
        slots = new String[12];
        for(int i = 0; i<12;i++)
        {
            int start = i+9;
            int end = i+10;

            slots[i] = getTimeInAMPM(start)+"-"+getTimeInAMPM(end);
        }
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        int current_hr = Integer.parseInt(currentTime.split(":")[0]);
        setSpinner(current_hr);




    }

    private String getTimeInAMPM(int hr) {
        if(hr<12)
            return hr+"AM";
        else if(hr==12)
            return hr+"PM";
        else
            return (hr%12)+"PM";

    }

    private void setSpinner(final int hr) {
        ArrayAdapter<String> slotsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, slots){
            @Override
            public boolean isEnabled(int position){

                if(position <= hr-9)
                {
                    //Disable the third item of spinner.
                    return false;
                }
                else
                {
                    return true;
                }
               /* if(position <= hr-9)
                    return false;
                else
                return true;*/

            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent)
            {
                View spinnerview = super.getDropDownView(position, convertView, parent);

                TextView spinnertextview = (TextView) spinnerview;

                if(position <= hr-9) {

                    //Set the disable spinner item color fade .
                    spinnertextview.setTextColor(Color.parseColor("#bcbcbb"));
                }
                else {

                    spinnertextview.setTextColor(Color.BLACK);

                }
                return spinnerview;
            }
        };

        spinner.setAdapter(slotsAdapter);
        if(hr>=8)
        spinner.setSelection(hr-8);
        else
            spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}