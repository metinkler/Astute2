package com.tinks.astute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JoinSession extends AppCompatActivity {

    String location = "";
    String members = "";
    String className = "";
    String timeStart = "";
    String timeEnd = "";
    String description = "";

    Button button;

    TextView memberText;
    TextView locationText;
    TextView classNameText;
    TextView timeStartText;
    TextView timesEndText;
    TextView descriptionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        location = intent.getStringExtra("location");
        members = intent.getStringExtra("members");
        className = intent.getStringExtra("className");
        timeStart = intent.getStringExtra("timeStart");
        timeEnd = intent.getStringExtra("timeEnd");
        description = intent.getStringExtra("description");


        Log.v("JoinSession", "In Join Session for " + className);

        memberText = (TextView) findViewById(R.id.membersText);
        locationText = (TextView) findViewById(R.id.locationText);
        classNameText = (TextView) findViewById(R.id.classNameText);
        timeStartText = (TextView) findViewById(R.id.timeStartText);
        timesEndText = (TextView) findViewById(R.id.timeEndText);
        descriptionText = (TextView) findViewById(R.id.descriptionText);
        button = (Button) findViewById(R.id.button);

        System.out.println(location);
        System.out.println(members);

        String memberString = "";

        if (members.equals("1")) {
            memberString = "1 person is already here!";
        }
        else {
            memberString = members + " people are already here!";
        }

        memberText.setText(memberString);
        locationText.setText(location);
        classNameText.setText(className);
        timeStartText.setText(timeStart);
        timesEndText.setText(timeEnd);
        descriptionText.setText(description);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Would update members for this session " +
                        "and add to \"My Sessions\"", Toast.LENGTH_LONG).show();
            }
        });

    }

}
