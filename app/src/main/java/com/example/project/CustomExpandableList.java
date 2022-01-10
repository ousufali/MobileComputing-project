package com.example.project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableList extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitle;
    private HashMap<String, List<String>> listDetail;

    public CustomExpandableList(Context context, List<String> listTitle, HashMap<String, List<String>> listDetail) {
        this.context = context;
        this.listTitle = listTitle;
        this.listDetail = listDetail;
    }

    @Override
    public int getGroupCount() {
        return this.listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDetail.get(this.listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        TextView tv1 = convertView.findViewById(R.id.lg1);
        String sGroup = String.valueOf(getGroup(groupPosition));
        tv1.setText(sGroup);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setTextColor(Color.BLUE);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        TextView tv2 = convertView.findViewById(R.id.li1);
        String sChild = String.valueOf(getChild(groupPosition,childPosition));
        tv2.setText(sChild);
//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(parent.getContext(),sChild,Toast.LENGTH_LONG).show();
//            }
//        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
