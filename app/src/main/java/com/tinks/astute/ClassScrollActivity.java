package com.tinks.astute;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import android.content.Intent;
import android.widget.TextView;
import android.view.ViewGroup;

public class ClassScrollActivity extends AppCompatActivity implements OnItemClickListener {
    ListView deptView;
    ListView classView;
    String curDept;

    String[] afst = new String[] {"100", "150", "205", "250", "283", "306"};
    String[] csci = new String[] {"141", "241", "243", "301", "303", "304", "312", "420", "423", "424", "426", "444"};

    String[] departments = new String[] {
            "AFST",
            "AMES",
            "ANTH",
            "ART",
            "ARTH",
            "BUAD",
            "CHEM",
            "CLCV",
            "CSCI",
            "DANC",
            "ECON",
            "EDUC",
            "FREN",
            "GEOL",
            "GOVT",
            "GREK",
            "GRMN",
            "GSWS",
            "HBRW",
            "HISP",
            "HIST",
            "INRL",
            "INTR",
            "ITAL"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_scroll);

        deptView = (ListView) findViewById(R.id.dept_list);
        deptView.setAdapter(new ArrayAdapter<String>(this, R.layout.class_layout, departments){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                String special = ClassScrollActivity.this.getResources().getString(R.string.compsci);
                int textColor = textView.getText().toString().equals(special) ? R.color.textBlack : R.color.textGrey;
                textView.setTextColor(ClassScrollActivity.this.getResources().getColor(textColor));

                return textView;
            }
        });
        deptView.setOnItemClickListener(this);
        deptView.setItemChecked(0, true);

        classView = (ListView) findViewById(R.id.classes_list);
        classView.setAdapter(new ArrayAdapter<String>(this, R.layout.class_layout, afst));
        classView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        if(view.getParent().equals(findViewById(R.id.dept_list))){
            String text = deptView.getItemAtPosition(position).toString();
            curDept = text;
            deptView.setItemChecked(position, true);

            if (text.equals("CSCI")) {
                classView.setAdapter(new ArrayAdapter<String>(this, R.layout.class_layout, csci){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TextView textView = (TextView) super.getView(position, convertView, parent);

                        String special = ClassScrollActivity.this.getResources().getString(R.string.cs301);
                        int textColor = textView.getText().toString().equals(special) ? R.color.textBlack : R.color.textGrey;
                        textView.setTextColor(ClassScrollActivity.this.getResources().getColor(textColor));

                        return textView;
                    }
                });
            }
            else if (text.equals("AFST")){
                classView.setAdapter(new ArrayAdapter<String>(this, R.layout.class_layout, afst));
            }
            else{
                classView.setAdapter(null);

                String toastText= "Clicked on Department - Classes Not Uploaded";
                Toast.makeText(ClassScrollActivity.this, toastText , Toast.LENGTH_SHORT).show();
            }
        }
        else {

            String text = classView.getItemAtPosition(position).toString();
            classView.setItemChecked(position, true);
            if (text.equals("301") && curDept.equals("CSCI")) {
                String newStr = curDept + text;
                Intent myIntent = new Intent(ClassScrollActivity.this, MainActivity.class);
                myIntent.putExtra("class", newStr); //Optional parameters
                setResult(Activity.RESULT_OK, myIntent);
                finish();
            } else {
                String toastText = "Clicked on Class - Functionality not Implemented";
                Toast.makeText(ClassScrollActivity.this, toastText, Toast.LENGTH_SHORT).show();
            }
        }
    }
}