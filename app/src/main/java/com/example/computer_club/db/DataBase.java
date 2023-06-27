package com.example.computer_club.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.Order;
import com.example.computer_club.tables.User;

@Database(entities = {User.class, Comp.class, Order.class}, version = 5, exportSchema = false)
abstract public class DataBase extends RoomDatabase {
    public abstract Dao getDao();
}
