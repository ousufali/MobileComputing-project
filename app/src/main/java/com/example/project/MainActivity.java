package com.example.project;

 import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

 import android.content.Intent;
 import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 import android.widget.Toast;

 import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper databaseHelper;
    TextView datalist;
    Button login;
    Button register;
    EditText user;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// _____________+++++++++++++++++++++++++++++++++++++++++++++++++
        Intent intent = new Intent(getApplicationContext(), AdminActivity.class );
        startActivity(intent);

//        -------------------------------------------------------------------
        setContentView(R.layout.login_register);

        databaseHelper=new DBHelper(MainActivity.this);

        login=findViewById(R.id.login_button);
        register=findViewById(R.id.register_button);
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        datalist=findViewById(R.id.registered_users);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_value = user.getText().toString();
                String password_value = password.getText().toString();

                if(username_value.length()<3  || password_value.length() < 5)
                {

                    Toast.makeText(getApplicationContext(),"Username or Password is not provided or their length is less then 3,5",Toast.LENGTH_SHORT).show();
                }else{
                    if(databaseHelper.isUserExist(username_value)){
                        Toast.makeText(getApplicationContext(),"Username already exist",Toast.LENGTH_SHORT).show();

                    }else {
                        UserModel userModel=new UserModel();
                        userModel.setUsername(username_value);
                        userModel.setPassword(password_value);

                        databaseHelper.AddUser(userModel);

                        user.setText("");
                        password.setText("");
//                        refreshData();
                    }


                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username_value = user.getText().toString();
                String password_value = password.getText().toString();

                if(databaseHelper.isValidUser(new UserModel(username_value,password_value) )){
                    Intent intent;
                    if(username_value.equals("admin")){
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                    }else {
                        intent = new Intent(getApplicationContext(), ClientActivity.class);
                        intent.putExtra("username", username_value);
                    }
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Incorrect username or password",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

    private void refreshData() {

        List<UserModel> userModelList=databaseHelper.getALLUsers();
        datalist.setText("");
        for(UserModel userModel:userModelList){
            datalist.append("user: "+userModel.getUsername()+
                   " \n\n");
        }
    }



}
