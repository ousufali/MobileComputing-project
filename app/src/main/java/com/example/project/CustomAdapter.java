package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<MessageModel> messages_model_list;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<MessageModel> message_model) {
        this.context = context;
        this.messages_model_list = message_model;
        inflter = (LayoutInflater.from(applicationContext));
//        System.out.println("############=============================");
//        System.out.println(messages_model_list.size());
//        System.out.println("############=============================");
    }

    @Override
    public int getCount() {
        return messages_model_list.size();
    }

    @Override
    public Object getItem(int i) {

        return messages_model_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView msg = view.findViewById(R.id.TextView);
//        System.out.println("############=============================");
//        System.out.println(messages_model_list.get(i).getMessage());
//        System.out.println("############=============================");

        msg.setText(messages_model_list.get(i).getMessage());
        return view;
    }
}
