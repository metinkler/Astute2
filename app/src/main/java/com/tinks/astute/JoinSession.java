package com.tinks.astute;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ImageView;

import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.text.TextWatcher;
import android.content.Intent;
import java.util.ArrayList;
import android.support.v4.widget.DrawerLayout;

import android.os.PersistableBundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.content.res.Configuration;
import android.widget.Button;
import android.widget.Filter;
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
