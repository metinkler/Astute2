package com.tinks.astute;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditSession extends AppCompatActivity {

    EditText location;
    Button editSession;
    Button deleteSession;

    String location_str;
    String start_hour;
    String start_min;
    String start_ampm;
    String end_hour;
    String end_min;
    String end_ampm;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.activity_edit_session);

        location = (EditText) findViewById(R.id.location);
        editSession = (Button) findViewById(R.id.edit);
        deleteSession = (Button) findViewById(R.id.delete);

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

        editSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSession();
            }
        });

        deleteSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSession();
            }
        });
    }

    private void editSession(){

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

        EditText text1 = (EditText) findViewById(R.id.location);
        location_str = text1.getText().toString();
        if(location_str == null){
            location_str = " ";
        }

        EditText text2 = (EditText) findViewById(R.id.description);
        description = text2.getText().toString();
        if (description == null){
            description = " ";
        }

        Intent myIntent = new Intent(EditSession.this, MainActivity.class);
        //String[] str_arr = new String[] {dept, course, start_hour, start_min, start_ampm, end_hour, end_min, end_ampm, location_str, description};
        //Bundle b = new Bundle();
        //b.putStringArray("newLoc", str_arr);
        //myIntent.putExtra("arr", str_arr); //Optional parameters
        setResult(Activity.RESULT_OK, myIntent);
        finish();

    }

    private void deleteSession(){

        Intent myIntent = new Intent(EditSession.this, MainActivity.class);
        myIntent.putExtra("delete", "delete"); //Optional parameters
        setResult(Activity.RESULT_OK, myIntent);
        finish();

    }


}
