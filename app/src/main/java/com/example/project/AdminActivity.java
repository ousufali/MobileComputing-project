package com.example.project;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class AdminActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    TextView selected_user;
    EditText user_message;
    Button send_message_button,view_messages, wirte_to_other_user;
    String user_selected;
    ExpandableListView elv;
    HashMap<String, List<String>> eld;
    List<String> listTitle;
    CustomExpandableList cel;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        selected_user = findViewById(R.id.selected_user);

        send_message_button = findViewById(R.id.send_message_button);
        wirte_to_other_user = findViewById(R.id.write_to_other_user);
        user_message = findViewById(R.id.user_message);
        view_messages = findViewById(R.id.view_message);



        databaseHelper=new DBHelper(AdminActivity.this);
        List<UserModel> db_users  =databaseHelper.getALLUsers();


        elv =  findViewById(R.id.e1);
        eld = ListData.getData( db_users);
        listTitle = new ArrayList<String>(eld.keySet());
        cel = new CustomExpandableList(this,listTitle, eld);
        elv.setAdapter(cel);

        wirte_to_other_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowWriteDialog();
            }
        });

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int g_pos, int c_pos, long l) {

                selected_user.setText("User:    "+eld.get( listTitle.get(g_pos)).get(  c_pos));
                user_selected = eld.get( listTitle.get(g_pos)).get(  c_pos).toString();
                expandableListView.collapseGroup(g_pos);
                return  true;
            }
        });

        send_message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Editable message = user_message.getText();
               System.out.println("###############view_messages###--------------#########################");
//               System.out.println(message);
                System.out.println(message.length());
               System.out.println("##################--------------#########################");

               if( selected_user.getText().length() < 1 ){
                   Toast.makeText(getApplicationContext(),"User not selected.",Toast.LENGTH_SHORT).show();

               }else if(  message.length() < 1){
                    Toast.makeText(getApplicationContext(),"Message field is empty.",Toast.LENGTH_SHORT).show();

                }else {
                   MessageModel msg = new MessageModel(user_selected , message.toString());
                   if(databaseHelper.AddMessage(msg)){
                       selected_user.setText("");
                       user_message.setText("");
                       Toast.makeText(getApplicationContext(),"Message send",Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(getApplicationContext(),"Failed to send message",Toast.LENGTH_SHORT).show();
                   }
               }

            }
        });

        view_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_admin_view_messages.class );
                startActivity(intent);

            }
        });

    }

    private void ShowWriteDialog() {

        AlertDialog.Builder al=new AlertDialog.Builder(AdminActivity.this);
        View view=getLayoutInflater().inflate(R.layout.write_dialog,null);
        final String[] user = new String[1];
        TextView selected_user = view.findViewById(R.id.selected_user);
        Button send_message_button = view.findViewById(R.id.send_message_button);
        EditText user_message = view.findViewById(R.id.user_message);
//        ###################################################
        elv = view.findViewById(R.id.e1);
//        eld = vieListData.getData( db_users);
//        listTitle = new ArrayList<String>(eld.keySet());
//        cel = new CustomExpandableList(this,listTitle, eld);

        elv.setAdapter(cel);
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int g_pos, int c_pos, long l) {

                selected_user.setText("User:    "+eld.get( listTitle.get(g_pos)).get(  c_pos));
                user[0] = eld.get( listTitle.get(g_pos)).get(  c_pos).toString();
                expandableListView.collapseGroup(g_pos);
                return  true;
            }
        });

        al.setView(view);

        final AlertDialog alertDialog=al.show();

        send_message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable message = user_message.getText();
                System.out.println("###############view_messages###--------------#########################");
//               System.out.println(message);
                System.out.println(message.length());
                System.out.println("##################--------------#########################");

                if( selected_user.getText().length() < 1 ){
                    Toast.makeText(getApplicationContext(),"User not selected.",Toast.LENGTH_SHORT).show();

                }else if(  message.length() < 1){
                    Toast.makeText(getApplicationContext(),"Message field is empty.",Toast.LENGTH_SHORT).show();

                }else {
                    MessageModel msg = new MessageModel( user[0] , message.toString());
                    if(databaseHelper.AddMessage(msg)){
                        selected_user.setText("");
                        user_message.setText("");
                        Toast.makeText(getApplicationContext(),"Message send",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }else {
                        Toast.makeText(getApplicationContext(),"Failed to send message",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}