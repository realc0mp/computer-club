package com.example.computer_club.tables;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Comp implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id = 0;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "characteristics")
    public String characteristics;
    @ColumnInfo(name = "image")
    public @DrawableRes int image;


    public Comp(String name, String characteristics, @DrawableRes int image){
        this.name = name;
        this.characteristics = characteristics;
        this.image = image;
    }

}
