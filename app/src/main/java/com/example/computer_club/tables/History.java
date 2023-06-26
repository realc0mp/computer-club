package com.example.computer_club.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class History {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id = 0;

}
