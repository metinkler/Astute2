package com.tinks.astute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class JoinSession extends AppCompatActivity {

    String location = "";
    String members = "";

    TextView memberText;
    TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        Log.v("JoinSession", "HERE");

        location = intent.getStringExtra("location");
        members = intent.getStringExtra("members");

        memberText = (TextView) findViewById(R.id.members);
        locationText = (TextView) findViewById(R.id.location);

        System.out.println(location);
        System.out.println(members);

        memberText.setText(members);
        locationText.setText(location);

    }

}
