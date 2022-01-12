package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterRemoveUser extends RecyclerView.Adapter<CustomAdapterRemoveUser.viewHolder> {
    Context context;
    List<UserModel> user_model_list;
    DBHelper databaseHelper;

    public CustomAdapterRemoveUser(Context applicationContext, List<UserModel> user_model_list) {
        this.context = applicationContext;
        this.user_model_list = user_model_list;
//        System.out.println("############=============================");
//        System.out.println(messages_model_list.size());
//        System.out.println("############=============================");
    }

    @NonNull
    @Override
    public CustomAdapterRemoveUser.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        viewHolder view_Holder;
        View view = inflater.inflate(R.layout.activity_listview_remove_user, parent, false);
        view_Holder = new viewHolder(view);
        return view_Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterRemoveUser.viewHolder holder, int position) {
        holder.t.setText(user_model_list.get(position).getUsername());
        UserModel m = user_model_list.get(position);
        holder.b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Removed "+m.getUsername(), Toast.LENGTH_SHORT).show();

                        ((RemoveUser)context).removeUserFromDb_adapter(m);
//                        if (context instanceof RemoveUser) {
//
//                        }
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        if(user_model_list == null){
            return 0;
        }
        return user_model_list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView t;
        Button b;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            b = itemView.findViewById(R.id.remove_user_from_db);
            t = itemView.findViewById(R.id.TextView);
        }
    }
}