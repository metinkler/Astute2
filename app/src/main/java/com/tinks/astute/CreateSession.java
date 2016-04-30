package com.tinks.astute;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;

import android.widget.EditText;

import android.view.View;
import android.text.TextWatcher;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.util.Log;
import android.widget.Button;

public class CreateSession extends AppCompatActivity {

    EditText location;
    String newLoc;
    Button createSession;

    String location_str;
    String start_hour;
    String start_min;
    String start_ampm;
    String end_hour;
    String end_min;
    String end_ampm;
    String description;
    String dept;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        location = (EditText) findViewById(R.id.locationText);
        createSession = (Button) findViewById(R.id.createSession);

        location.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                newLoc = cs.toString();
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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.majors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner_courseNum);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.csci_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.start_hour);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.start_min);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.min_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Spinner spinner4 = (Spinner) findViewById(R.id.start_ampm);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.am_pm, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        Spinner spinner5 = (Spinner) findViewById(R.id.end_hour);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.hour_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        Spinner spinner6 = (Spinner) findViewById(R.id.end_min);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.min_array, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        Spinner spinner7 = (Spinner) findViewById(R.id.end_ampm);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.am_pm, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);

        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSession(newLoc);
            }
        });

    }

    private void createSession(String newLoc){

        Spinner mySpinner1=(Spinner) findViewById(R.id.spinner);
        dept = mySpinner1.getSelectedItem().toString();

        Spinner mySpinner2=(Spinner) findViewById(R.id.spinner_courseNum);
        course = mySpinner2.getSelectedItem().toString();

        Spinner mySpinner3=(Spinner) findViewById(R.id.start_hour);
        start_hour = mySpinner3.getSelectedItem().toString();

        Spinner mySpinner4=(Spinner) findViewById(R.id.start_min);
        start_min = mySpinner4.getSelectedItem().toString();

        Spinner mySpinner5=(Spinner) findViewById(R.id.start_ampm);
        start_ampm = mySpinner5.getSelectedItem().toString();

        Spinner mySpinner6=(Spinner) findViewById(R.id.end_hour);
        end_hour= mySpinner6.getSelectedItem().toString();

        Spinner mySpinner7=(Spinner) findViewById(R.id.end_min);
        end_min= mySpinner7.getSelectedItem().toString();

        Spinner mySpinner8=(Spinner) findViewById(R.id.end_ampm);
        end_ampm= mySpinner8.getSelectedItem().toString();

        EditText text1 = (EditText) findViewById(R.id.locationText);
        location_str = text1.getText().toString();
        if(location_str == null){
            location_str = " ";
        }

        EditText text2 = (EditText) findViewById(R.id.description);
        description = text2.getText().toString();
        if (description == null){
            description = " ";
        }

        Log.v("myApp", course + " " + dept);

        Intent myIntent = new Intent(CreateSession.this, MainActivity.class);
        String[] str_arr = new String[] {dept, course, start_hour, start_min, start_ampm, end_hour, end_min, end_ampm, location_str, description};
        Log.v("myApp", " " + str_arr.length);
        //Bundle b = new Bundle();
        //b.putStringArray("newLoc", str_arr);
        myIntent.putExtra("arr", str_arr); //Optional parameters
        setResult(Activity.RESULT_OK, myIntent);
        finish();

    }


}
