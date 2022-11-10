package com.example.proven.view.dataAnalytics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proven.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;

public class CustomBtn extends AppCompatActivity {

    Button timeSpent, numOfClicks;
    long duration;

    private FirebaseAnalytics mFirebaseAnalytics;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        numOfClicks = (Button) findViewById(R.id.NumOfClicks);
        timeSpent = (Button) findViewById(R.id.timeSpent);


        timeSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTheTime();
                recordScreenView();

            }
        });

        numOfClicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumOfClicks();

            }
        });


    }

    private void getTheTime() {

        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        duration = (endTime - startTime); //give me the time in nanoseconds

        FirebaseDatabase.getInstance().getReference().child("time spent").setValue(duration);

        Toast.makeText(getApplicationContext(), "Time spent is " + duration, Toast.LENGTH_SHORT).show();

    }



    //that is another way to send the data to the firebase and will appear in the dashboard of firebase analytics.
    //I get it during search so that will give us the chance to discuss it to deepen my understand about it.
    private void recordScreenView() {
        // This string must be <= 36 characters long.
        String screenName = "CustomBtn Screen";

        // [START set_current_screen]
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "CustomBtn");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        // [END set_current_screen]
    }




    private void getNumOfClicks() {
        int clickcount = 0;

        clickcount++;


        FirebaseDatabase.getInstance().getReference().child("Number of clicks").setValue(clickcount);

        Toast.makeText(getApplicationContext(), "Number Of Clicks " + clickcount, Toast.LENGTH_SHORT).show();


    }



}







