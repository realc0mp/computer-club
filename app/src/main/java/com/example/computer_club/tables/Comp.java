package com.example.computer_club.tables;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comp {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id = 0;


}
