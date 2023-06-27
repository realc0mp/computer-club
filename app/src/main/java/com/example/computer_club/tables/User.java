package com.example.computer_club.tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "name")
    public String name = "";
    @ColumnInfo(name = "date")
    public String date = "";
    @ColumnInfo(name = "email")
    public String email = "";
    @ColumnInfo(name = "password")
    public String password = "";
    @ColumnInfo(name = "isAdmin")
    public boolean isAdmin = false;


    public User(String name, String date, String email, String password){
        this.name = name;
        this.date = date;
        this.email = email;
        this.password = password;
    }

    public User(){}
}
