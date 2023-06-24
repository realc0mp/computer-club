package com.example.computer_club.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.computer_club.db.Dao;
import com.example.computer_club.db.DataBase;
import com.example.computer_club.db.Repo;

public class BaseViewModel extends AndroidViewModel {

    private DataBase db = Room.databaseBuilder(
            this.getApplication(),
            DataBase.class,
            "database")
            .build();

    public Repo repository = new Repo(db.getDao());

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
