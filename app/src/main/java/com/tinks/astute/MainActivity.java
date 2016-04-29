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
import android.widget.LinearLayout;
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
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
    };

    Integer[] members = {
            1,
            3,
            4,
            2,
            8,
            1,
            5,
            2,
            3,
            8,
            5
    };

    Integer[] times = {
            5,
            15,
            20,
            23,
            30,
            32,
            35,
            39,
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

        //Start navigation drawer
        drawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //End navigation drawer

        // Defined Array values to show in ListView
        ArrayList<String> values = new ArrayList<String>();
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
                // Perform action on click
                Toast.makeText(getApplicationContext(),"adding class",Toast.LENGTH_LONG).show();
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

        // filters news feed
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
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



