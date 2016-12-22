package com.example.rid.swolemate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rid on 12/22/16.
 */

public class ListViewAdapter extends BaseAdapter {

    Activity context;
    ArrayList<String> workoutName;
    ArrayList<Integer> sets;
    ArrayList<Integer> reps;

    public ListViewAdapter(Activity contextIn, ArrayList<String> workoutNameIn, ArrayList<Integer> setsIn, ArrayList<Integer> repsIn){
        context = contextIn;
        workoutName = workoutNameIn;
        sets = setsIn;
        reps = repsIn;
        //setsXreps = setsXrepsIn;
    }
    @Override
    public int getCount() {
        return workoutName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView workoutNameLabel;
        TextView setsXrepsLabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();

        if(convertView == null){
            convertView = inflater.inflate(R.layout.listitem_row, null);
            holder = new ViewHolder();
            holder.workoutNameLabel = (TextView)convertView.findViewById(R.id.textView1);
            holder.setsXrepsLabel = (TextView)convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.workoutNameLabel.setText(workoutName.get(position));
        holder.setsXrepsLabel.setText(sets.get(position) + " x " + reps.get(position));
        return convertView;
    }
}
