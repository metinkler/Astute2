package com.tinks.astute;

/**
 * Created by tinks on 4/25/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter implements Filterable {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final Integer[] imgid;
    private final Integer[] members;
    private final Integer[] times;

    ArrayList<String> objects = new ArrayList<String>();
    ArrayList<String> filteredList = new ArrayList<String>();

    String item = new String();

    public CustomListAdapter(Activity context, ArrayList<String> itemname, Integer[] imgid, Integer[] members, Integer[] times) {
        //super(context, R.layout.list_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.members=members;
        this.times=times;

        for (int k = 0; k < itemname.size(); k++) {
            System.out.println("Item: " + k + "Text: " + itemname.get(k));
        }

        objects = itemname;
        filteredList = itemname;
    }


    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(filteredList.get(position));
        imageView.setImageResource(imgid[position]);


        extratxt.setText(Integer.toString(members[position]));
        time.setText(Integer.toString(times[position])+ " m");

        return rowView;

    };

    // trying to start join session here
    /*
    private void startJoinSession(){
        Intent i = new Intent(context, JoinSession.class);
        context.startActivity(i);
    }
    */

    //For this helper method, return based on filteredData
    public int getCount()
    {
        return filteredList.size();
    }

    public Object getItem(int position)
    {
        return filteredList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {

            /* (non-Javadoc)
             * @see android.widget.Filter#performFiltering(java.lang.CharSequence)
             */
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<String> tempList = new ArrayList<String>();
                // constraint is our string the user entered in search bar
                // filtering from objects
                if(constraint != null && objects!=null) {
                    int length=objects.size();
                    int i=0;
                    while(i<length){
                        if (objects.get(i).toLowerCase().contains(constraint.toString().toLowerCase())){
                            tempList.add(objects.get(i));
                        }

                        i++;
                    }

                    // print filtered list
                    for (int k = 0; k < tempList.size(); k++){
                        System.out.println("Item: " + k + "Text: " + tempList.get(k));

                    }

                    results.values = tempList;
                    results.count = tempList.size();
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                filteredList = (ArrayList<String>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

        };
    }
}
