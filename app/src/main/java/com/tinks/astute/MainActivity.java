package com.tinks.astute;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import android.widget.EditText;
import android.widget.ListView;
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
// going to be the newsfeed page

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    NavigationView mDrawer;
    ActionBarDrawerToggle drawerToggle;
    int mSelectedId;
    Toolbar toolbar;
    int newId=0;

    String[] added = new String[20];
    int current = 0;

    ListView listView;
    Button nav;

    // Search EditText
    EditText inputSearch;

    //ArrayAdapter<String> adapter;

    CustomListAdapter adapter;

    Integer[] imgid={
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    Integer[] members = {
            1,
            3,
            4,
            2,
            8,
            1,
            5,
            2
    };

    Integer[] times = {
            5,
            15,
            23,
            30,
            32,
            45,
            50,
            60,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
        Intent intent = getIntent();

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        nav = (Button) findViewById(R.id.nav);

        //Start navigation drawer
        drawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //End navigation drawer

        // Defined Array values to show in ListView
        ArrayList<String> values = new ArrayList<String>();
        values.add("CS 420\nSwem 140");
        values.add("CS 241\nJones 214");
        values.add("ENGL 212\nSwem Read & Relax");
        values.add("KINES 110\nBlow 333");
        values.add("MATH 211\nBlair 220");
        values.add("ENGL 310\nSwem 163");
        values.add("PSYCH 201\nSwem 264");
        values.add("HIST 320\nBlow 331");


        // Define a new Adapter
        this.adapter = new CustomListAdapter(this, values, imgid, members, times);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition   = position;

                // ListView Clicked item value
                String  itemValue    = listView.getItemAtPosition(position).toString();

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

        nav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getApplicationContext(),"dope",Toast.LENGTH_LONG).show();
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                System.out.println(MainActivity.this.adapter);
                MainActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Intent intent = getIntent();
        //Gets information from the class scroller
        String value = data.getStringExtra("class");
        Log.v("app", "value " + value);
        newId++;
        if(value != null && !value.isEmpty()){
            int length = added.length;
            int found = 0;
            for(int i=0; i<length; i++){
                if(value.equals(added[i])){
                    found = 1;
                }
            }
            if (found==1){
                String toastText= "Error: Class is already added!";
                Toast.makeText(MainActivity.this, toastText , Toast.LENGTH_LONG).show();
            }
            else{
                MenuItem newMenuItem = mDrawer.getMenu().add(R.id.group1, newId, 3, value);
                newMenuItem.setCheckable(true);
                added[current] = value;
                current++;
            }
        }

    }


    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void initView() {
        mDrawer= (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void itemSelection(int mSelectedId) {

        switch(mSelectedId){

            case R.id.add_class:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(getBaseContext(), ClassScrollActivity.class);
                startActivityForResult(i, 123);
                break;

            default:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);
        Log.v("app", String.valueOf(mSelectedId));
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }
}



