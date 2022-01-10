package com.example.project;

public class MessageModel {
    String user = "";
    String message = "";



    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageModel() {
    }

    public MessageModel(String user, String message) {
        this.user = user;
        this.message = message;
    }






}
