package com.example.computer_club.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.computer_club.Comp;
import com.example.computer_club.User;

@Database(entities = {User.class, Comp.class}, version = 0, exportSchema = false)
abstract public class DataBase extends RoomDatabase {
    public abstract Dao getDao();
}
