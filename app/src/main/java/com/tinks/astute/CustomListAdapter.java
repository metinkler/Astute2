package com.tinks.astute;

/**
 * Created by tinks on 4/25/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter implements Filterable {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<Integer> imgid;
    private final ArrayList<Integer> members;
    private final ArrayList<Integer> times;
    private final ArrayList<Boolean> editable;
    private final ArrayList<String> descriptions;
    private final ArrayList<String> timeStart;
    private final ArrayList<String> timeEnd;


    ArrayList<String> objects = new ArrayList<String>();
    ArrayList<String> filteredList = new ArrayList<String>();


    String item = new String();

    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<Integer> imgid,
                             ArrayList<Integer> members, ArrayList<Integer> times,
                             ArrayList<Boolean> editable, ArrayList<String> descriptions,
                             ArrayList<String> timeStart, ArrayList<String> timeEnd) {
        //super(context, R.layout.list_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.members=members;
        this.times=times;
        this.editable = editable;
        this.descriptions = descriptions;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;

        objects = itemname;
        filteredList = itemname;
    }


    public View getView(final int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(filteredList.get(position));
        imageView.setImageResource(imgid.get(position));


        extratxt.setText(Integer.toString(members.get(position)));
        time.setText(Integer.toString(times.get(position))+ " m");

        rowView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (editable.get(position) == false){
                    // join session - user didn't make it
                    Intent i = new Intent(context, JoinSession.class);
                    i.putExtra("members",members.get(position).toString());
                    String[] location = itemname.get(position).split("\n");
                    i.putExtra("className", location[0]);
                    i.putExtra("location", location[1]);
                    i.putExtra("timeStart", timeStart.get(position));
                    i.putExtra("timeEnd", timeEnd.get(position));
                    i.putExtra("description", descriptions.get(position));

                    context.startActivity(i);
                }
                else{
                    // edit session - user made it
                    Intent i = new Intent(context, EditSession.class);
                    context.startActivityForResult(i, 125);
                }
            }
        });


        return rowView;

    }


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
