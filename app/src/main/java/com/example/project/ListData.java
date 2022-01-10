package com.example.project;import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListData {



    public  static HashMap<String, List<String>> getData( List<UserModel> users ){



        HashMap<String, List<String>> detailedData = new HashMap<String, List<String>>();
        List<String> users_username_list = new ArrayList<String>();

        for(int i=0; i<users.size(); i++){
            users_username_list.add(users.get(i).getUsername());
        }


        detailedData.put("Registered users",users_username_list);


        return detailedData;

    }
    public  static HashMap<String, List<String>> getUserData( List<UserModel> users ){



        HashMap<String, List<String>> detailedData = new HashMap<String, List<String>>();
        List<String> users_username_list = new ArrayList<String>();

        for(int i=0; i<users.size(); i++){
            users_username_list.add(users.get(i).getUsername());
        }


        detailedData.put("Select user",users_username_list);


        return detailedData;

    }
}
