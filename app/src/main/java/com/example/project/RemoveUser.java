package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class RemoveUser extends AppCompatActivity {
    RecyclerView simpleList;
    DBHelper databaseHelper;
    EditText user_to_be_removed;
    CustomAdapterRemoveUser arrayAdapter;


    List<UserModel> all_users;
    List<UserModel> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        simpleList = (RecyclerView) findViewById(R.id.user_remove_list);
        databaseHelper=new DBHelper(RemoveUser.this);
        all_users = databaseHelper.getALLUsers();

        user_to_be_removed = findViewById(R.id.user_to_be_removed);
        simpleList = findViewById(R.id.user_remove_list);

        CustomAdapterRemoveUser myRecyclerAdapter = new CustomAdapterRemoveUser(this,all_users);
        simpleList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        simpleList.setAdapter(myRecyclerAdapter);
///..........
//        LayoutInflater inflter = getApplicationContext().getSystemService()
//        View view = inflter.inflate(R.layout.activity_listview_remove_user, null);
//        remove_user_button = view.findViewById(R.id.remove_user_from_db);

//................

//        TextView user = view.findViewById(R.id.TextView);

        arrayAdapter = new CustomAdapterRemoveUser (this, all_users);
        simpleList.setAdapter(arrayAdapter);

        user_to_be_removed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                users = new ArrayList<>();

                for(int i=0; i<all_users.size(); i++){

                    if(all_users.get(i).getUsername().indexOf(user_to_be_removed.getText().toString()) != -1) {
                        users.add(all_users.get(i));
                    }
                }
                arrayAdapter = new CustomAdapterRemoveUser (getApplicationContext(), users );

                simpleList.setAdapter(arrayAdapter);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void removeUserFromDb_adapter(UserModel usr_to_removed) {
        System.out.println("====------------=======---------");
        System.out.println(usr_to_removed);
        System.out.println("====------------=======---------");
        databaseHelper.removeUser(usr_to_removed.getUsername());
        all_users.remove(usr_to_removed);
//        simpleList.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

    }
}