package com.example.proven.view.dataAnalytics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proven.location.LocationManager;
import com.example.proven.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.FirebaseDatabase;

public class CustomBtnActivity extends AppCompatActivity {

    Button timeSpent, numOfClicks,getTheLocation;
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
        getTheLocation=(Button)findViewById(R.id.getLocation);


        timeSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTheTime();
               // recordScreenView();

            }
        });

        numOfClicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNumOfClicks();

            }
        });

        getTheLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CustomBtnActivity.this, LocationManager.class);
                startActivity(intent);
            }
        });



    }

    private void getTheTime() {

        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        duration = (endTime - startTime); //give me the time in nanoseconds

        FirebaseDatabase.getInstance().getReference().child("time spent in the CustomBtnActivity").setValue(duration);

        Toast.makeText(getApplicationContext(), "time spent in the CustomBtnActivity  " + duration, Toast.LENGTH_SHORT).show();

    }



    //that is another way to send the data to the firebase and will appear in the dashboard of firebase analytics.
    //I get it during search so that will give us the chance to discuss it to deepen my understand about it.
//    private void recordScreenView(){
//
//      //This string must be <= 36 characters long.
//        String screenName = "CustomBtn Screen";
//
//        // [START set_current_screen]
//        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
//        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "CustomBtn");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
//        // [END set_current_screen]
//    }




    private void getNumOfClicks() {
        int clickcount = 0;

        clickcount++;


        FirebaseDatabase.getInstance().getReference().child("Number of clicks on Button 2 ").setValue(clickcount);

        Toast.makeText(getApplicationContext(), "Number Of Clicks on Button 2 " + clickcount, Toast.LENGTH_SHORT).show();


    }



}







