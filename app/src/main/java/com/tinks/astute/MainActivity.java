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

// going to be the newsfeed page

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    NavigationView mDrawer;
    ActionBarDrawerToggle drawerToggle;
    int mSelectedId;
    Toolbar toolbar;
    int newId=0;

    MenuItem mPreviousMenuItem;

    String[] added = new String[20];
    int current = 0;

    ListView listView;
    ImageView nav;
    ImageView faves;
    ImageView profile;
    ImageView sessions;
    ImageView add;
    ImageView settings;

    // Search EditText
    EditText inputSearch;
    CustomListAdapter adapter;

    String newLoc;

    final ArrayList<Boolean> editable = new ArrayList<Boolean>();
    // Defined Array values to show in ListView
    final ArrayList<String> values = new ArrayList<String>();
    final ArrayList<Integer> imgid = new ArrayList<Integer>();
    final ArrayList<Integer> members = new ArrayList<Integer>();
    final ArrayList<Integer> times = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
        Intent intent = getIntent();


        newLoc = intent.getStringExtra("location");

        TextView text = (TextView) findViewById(R.id.empty);
        text.setVisibility(View.INVISIBLE);


        // Get objects from xml
        listView = (ListView) findViewById(R.id.list);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        nav = (ImageView) findViewById(R.id.nav);
        faves = (ImageView) findViewById(R.id.faves);
        profile = (ImageView) findViewById(R.id.profile);
        sessions = (ImageView) findViewById(R.id.sessions);
        add = (ImageView) findViewById(R.id.add);
        settings = (ImageView) findViewById(R.id.settings);

        //Start navigation drawer
        drawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //End navigation drawer

        if (!newLoc.equals("")){
            values.add("CSCI415\n"+newLoc);
            imgid.add(R.drawable.ic_launcher);
            members.add(1);
            times.add(1);
            editable.add(true);
        }

        //adding times
        times.add(4);
        times.add(10);
        times.add(15);
        times.add(19);
        times.add(25);
        times.add(30);
        times.add(33);
        times.add(42);
        times.add(48);
        times.add(56);
        times.add(60);

        // adding members
        members.add(1);
        members.add(3);
        members.add(4);
        members.add(2);
        members.add(8);
        members.add(1);
        members.add(5);
        members.add(2);
        members.add(3);
        members.add(8);
        members.add(5);

        // adding strings
        values.add("CSCI420\nSwem 140");
        values.add("CSCI241\nJones 214");
        values.add("ENGL212\nSwem Read & Relax");
        values.add("KINES110\nBlow 333");
        values.add("MATH211\nBlair 220");
        values.add("ENGL310\nSwem 163");
        values.add("PSYCH201\nSwem 264");
        values.add("HIST320\nBlow 331");
        values.add("CHEM103\nSwem 230");
        values.add("PHYS420\nSwem Read & Relax");
        values.add("MATH214\nTuck 110");

        if (newLoc.equals("")){
            for (int i = 0; i < values.size();i++){
                imgid.add(R.drawable.ic_launcher);
            }
        }
        else{
            for (int i = 0; i < values.size()-1;i++){
                imgid.add(R.drawable.ic_launcher);
            }
        }

        // adding times
        /*
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        imgid.add(R.drawable.ic_launcher);
        */

        if (newLoc.equals("")){
            for (int i = 0; i < values.size();i++){
                editable.add(false);
            }
        }
        else{
            for (int i = 0; i < values.size()-1;i++){
                editable.add(false);
            }
        }

        // Define a new Adapter
        this.adapter = new CustomListAdapter(this, values, imgid, members, times);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Actual items in the news feed listen to click
        // clicks take you to either join or edit pages
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition   = position;

                System.out.println(editable.get(position));

                if (editable.get(position) == false){
                    // join session - user didn't make it
                    Intent i = new Intent(MainActivity.this, JoinSession.class);
                    i.putExtra("members",members.get(position));
                    String[] location = values.get(position).split("\n");
                    i.putExtra("location", location[1]);
                    MainActivity.this.startActivity(i);
                }
                else{
                    // edit session - user made it
                    Intent i = new Intent(MainActivity.this, EditSession.class);
                    MainActivity.this.startActivity(i);
                }

            }

        });

        // navigation button opens the navigation drawer as well
        nav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawer);

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start new activity here
                Intent i = new Intent(MainActivity.this, CreateSession.class);
                MainActivity.this.startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getApplicationContext(),"Takes user to their profile",Toast.LENGTH_LONG).show();
            }
        });

        sessions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to sessions page?
                // Perform action on click
                Toast.makeText(getApplicationContext(),"Shows users sessions they are currently a part of",Toast.LENGTH_LONG).show();
            }
        });

        faves.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getApplicationContext(),"Shows user their favorite classes",Toast.LENGTH_LONG).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Toast.makeText(getApplicationContext(),"Allows user to edit app settings",Toast.LENGTH_LONG).show();
            }
        });

        // filters news feed
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
                System.out.println(MainActivity.this.adapter);
                MainActivity.this.adapter.getFilter().filter(cs,new Filter.FilterListener() {
                    public void onFilterComplete(int count) {
                        if(count == 0){
                            Toast.makeText(getApplicationContext(),"None Found :(",Toast.LENGTH_LONG).show();
                            ListView list = (ListView) findViewById(R.id.list);
                            list.setVisibility(View.INVISIBLE);
                            TextView text = (TextView) findViewById(R.id.empty);
                            text.setVisibility(View.VISIBLE);
                        }
                        else{

                            ListView list = (ListView) findViewById(R.id.list);
                            list.setVisibility(View.VISIBLE);
                            TextView text = (TextView) findViewById(R.id.empty);
                            text.setVisibility(View.INVISIBLE);
                        }
                    }
                });
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
        MainActivity.this.adapter.getFilter().filter("");
        ListView list = (ListView) findViewById(R.id.list);
        list.setVisibility(View.VISIBLE);
        TextView text = (TextView) findViewById(R.id.empty);
        text.setVisibility(View.INVISIBLE);

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

    private void itemSelection(int mSelectedId, MenuItem menuItem) {

        switch(mSelectedId){

            case R.id.add_class:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(getBaseContext(), ClassScrollActivity.class);
                startActivityForResult(i, 123);
                break;

            case R.id.show_all:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                MainActivity.this.adapter.getFilter().filter("");
                ListView list = (ListView) findViewById(R.id.list);
                list.setVisibility(View.VISIBLE);
                TextView text = (TextView) findViewById(R.id.empty);
                text.setVisibility(View.INVISIBLE);
                break;

            default:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                CharSequence title = menuItem.getTitle();
                MainActivity.this.adapter.getFilter().filter(title, new Filter.FilterListener() {
                public void onFilterComplete(int count) {
                    if(count == 0){
                        ListView list = (ListView) findViewById(R.id.list);
                        list.setVisibility(View.INVISIBLE);
                        TextView text = (TextView) findViewById(R.id.empty);
                        text.setVisibility(View.VISIBLE);
                    }
                    else{

                        ListView list = (ListView) findViewById(R.id.list);
                        list.setVisibility(View.VISIBLE);
                        TextView text = (TextView) findViewById(R.id.empty);
                        text.setVisibility(View.INVISIBLE);
                    }
                }
            });
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
        itemSelection(mSelectedId, menuItem);
        Log.v("app", String.valueOf(mSelectedId));
        if (mPreviousMenuItem != null) {
            mPreviousMenuItem.setChecked(false);
        }
        mPreviousMenuItem = menuItem;
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }

}



