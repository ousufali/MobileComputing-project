package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activity_admin_view_messages extends AppCompatActivity {
    Button  write_messages;
    DBHelper databaseHelper;
    TextView selected_user, message_header_hint;

    ListView simpleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_messages);

        simpleList = findViewById(R.id.simpleListView);
        message_header_hint = findViewById(R.id.message_header_hint);




        write_messages = findViewById(R.id.write_message);
        selected_user = findViewById(R.id.selected_user);
        databaseHelper=new DBHelper(activity_admin_view_messages.this);
        List<UserModel> db_users  =databaseHelper.getALLUsers();


        ExpandableListView elv =  findViewById(R.id.e1);
        HashMap<String, List<String>> eld = ListData.getUserData( db_users);
        List<String> listTitle = new ArrayList<String>(eld.keySet());
        CustomExpandableList cel = new CustomExpandableList(this,listTitle, eld);
        elv.setAdapter(cel);



        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int g_pos, int c_pos, long l) {

                selected_user.setText("User:    "+eld.get( listTitle.get(g_pos)).get(  c_pos));

                expandableListView.collapseGroup(g_pos);
                message_header_hint.setText("");

                CustomAdapter arrayAdapter = new CustomAdapter(getApplicationContext(), databaseHelper.getALLUserMessages(eld.get( listTitle.get(g_pos)).get(  c_pos)));
                simpleList.setAdapter(arrayAdapter);

                return  true;
            }
        });

        write_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}