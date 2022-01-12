package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.JsonToken;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "user_db";
    private static final String TABLE_NAME = "users";
    private static final String username = "user";
    private static final String password = "password";

    private static final String Message_TABLE_NAME = "messages";
    private static final String message = "message";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_tableQuery = "CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                username+" TEXT PRIMARY KEY,"+
                password+" TEXT "+
                ")";
        String message_tableQuery = "CREATE TABLE if not EXISTS "+Message_TABLE_NAME+
                "("+
                username+" TEXT ,"+
                message+" TEXT "+
                ")";
        db.execSQL(user_tableQuery);
        db.execSQL(message_tableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Message_TABLE_NAME);
    }

    public void AddUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(username,userModel.getUsername());
        contentValues.put(password,userModel.getPassword());
        db.insert(TABLE_NAME,null, contentValues);
        db.close();

    }

    public List<UserModel> getALLUsers(){
        List<UserModel> userModelList = new ArrayList<>();
        String query = "SELECT * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor =  db.rawQuery(query,null);
       if(cursor.moveToFirst()){
           do{
               UserModel userModel = new UserModel(
                       cursor.getString(0),
                       cursor.getString(1)
               );
               userModelList.add(userModel);
           }while (cursor.moveToNext());
       }
       db.close();
       return userModelList;
    }
    public int getTotalCount(){
        String query = "SELECT * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,null);
        return cursor.getCount();
    }

    public UserModel getUser(String user){
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_NAME+ " WHERE "+ username +"="+user;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        UserModel userModel = new UserModel(
                cursor.getString(0),
                cursor.getString(1)

        );
        db.close();
        return userModel;
    }
    public boolean isUserExist(String value){
        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT * from "+TABLE_NAME+ " WHERE "+ username +" = '"+ value + "';";
//        System.out.println("###########################################################################");
//        System.out.println(query);
//
//        System.out.println("###########################################################################");

        Cursor cursor = db.rawQuery(query,null);
//        System.out.println("###########################################################################");
//        System.out.println("done");
//        System.out.println("###########################################################################");

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }else {
            cursor.close();
            return true;
        }

    }

    public boolean isValidUser(UserModel user){
        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT * from "+TABLE_NAME+ " WHERE "+ username +" = '"+ user.getUsername() +  "' AND "+ password + " = '"+user.getPassword()   + "';";
//        System.out.println("###########################################################################");
//        System.out.println(query);
//
//        System.out.println("###########################################################################");

        Cursor cursor = db.rawQuery(query,null);
//        System.out.println("###########################################################################");
//        System.out.println("done");
//        System.out.println("###########################################################################");

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }else {
            cursor.close();
            return true;
        }

    }

    public void removeUser(String selected_user){
        System.out.println("====------------=======---------");
        System.out.println(selected_user);
        System.out.println("====------------=======---------");

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,username+"=?",new String[]{selected_user});
        db.delete(Message_TABLE_NAME,username+"=?",new String[]{selected_user});

        db.close();
    }
//    public List<UserModel> getAllUsers(String selected_user_substring){
//        List<UserModel> userModelList = new ArrayList<>();
//        String query = "SELECT * from "+TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor =  db.rawQuery(query,null);
//        if(cursor.moveToFirst()){
//            if(cursor.getString(0).toString().indexOf(selected_user_substring) != -1){
//                do{
//                    UserModel userModel = new UserModel(
//                            cursor.getString(0),
//                            cursor.getString(1)
//                    );
//                    userModelList.add(userModel);
//                }while (cursor.moveToNext());
//            }else {
//                cursor.moveToNext();
//            }
//
//        }
//        db.close();
//        return userModelList;
//    }

//    #####################################################################################################################

    public boolean AddMessage(MessageModel messageModel){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(username,messageModel.getuser());
            contentValues.put(message,messageModel.getMessage());
            db.insert(Message_TABLE_NAME,null, contentValues);
            db.close();
            return  true;

        }catch (Exception e){
            return  false;
        }


    }

    public List<MessageModel> getALLUserMessages(String selected_user){
        List<MessageModel> message_list = new ArrayList<>();

//        String query = "SELECT * from "+Message_TABLE_NAME+ ";";

        String query = "SELECT * from "+Message_TABLE_NAME+ " WHERE "+ username +" = '"+ selected_user + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                MessageModel message = new MessageModel(
                        cursor.getString(0),
                        cursor.getString(1)
                );
                message_list.add(message);
            }while (cursor.moveToNext());
        }
        db.close();
        return message_list;
    }


}
