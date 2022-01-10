package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

import android.os.Bundle;

import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;


public class ClientActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    TextView welcome_text;

    ListView simpleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        AppWidgetManager appWidgetManager ;
        RemoteViews remoteViews = null;
        simpleList = findViewById(R.id.simpleListView);
        welcome_text = findViewById(R.id.welcome_text);
        String user = "Unknown";

        Bundle extras = getIntent().getExtras();
        user = extras.getString("username");


        databaseHelper=new DBHelper(ClientActivity.this);




        welcome_text.setText("Welcome "+ user);

        CustomAdapter arrayAdapter = new CustomAdapter(getApplicationContext(), databaseHelper.getALLUserMessages(user));
        simpleList.setAdapter(arrayAdapter);

        if (extras != null) {

            Context context = this;
            appWidgetManager = AppWidgetManager.getInstance(context);
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.client_widget);
            ComponentName thisWidget = new ComponentName(context, ClientWidget.class);
            remoteViews.setTextViewText(R.id.appwidget_heading, "Latest message:" );
//            remoteViews.setTextViewText(R.id.appwidget_text,  user);
            if(arrayAdapter.getCount() >0 ){
                MessageModel msg = (MessageModel) arrayAdapter.getItem(arrayAdapter.getCount()-1);
                remoteViews.setTextViewText(R.id.appwidget_text,  msg.getMessage());
            }else{
                remoteViews.setTextViewText(R.id.appwidget_text, "None");

            }


            appWidgetManager.updateAppWidget(thisWidget, remoteViews);
            //The key argument here must match that used in the other activity
        }



    }


}