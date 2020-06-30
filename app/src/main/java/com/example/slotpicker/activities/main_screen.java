package com.example.slotpicker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.slotpicker.R;

public class main_screen extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        inti();
        long time= System.currentTimeMillis();
        calendarView.setMinDate(time);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Toast.makeText(getApplicationContext(),String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(dayOfMonth),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void inti()
    {
        calendarView=findViewById(R.id.calender);
    }
}