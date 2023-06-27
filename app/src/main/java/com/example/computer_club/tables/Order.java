package com.example.computer_club.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.computer_club.DateProvider;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Comp.class, parentColumns = "id", childColumns = "compId"),
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
}
)
public class Order {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id = 0;
    @ColumnInfo(name = "time")
    public String time;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "compName")
    public String compName;
    @ColumnInfo(name = "compId")
    public int compId;
    @ColumnInfo(name = "userId")
    public int userId;

    public Order(String time, String compName, int compId, int userId){
        this.time = time;
        this.compId = compId;
        this.userId = userId;
        this.compName = compName;
        this.date = DateProvider.getDate();
    }

}
