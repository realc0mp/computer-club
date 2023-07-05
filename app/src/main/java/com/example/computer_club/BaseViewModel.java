package com.example.computer_club;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.computer_club.db.Dao;
import com.example.computer_club.db.DataBase;
import com.example.computer_club.db.Repo;

import java.util.HashMap;

public class BaseViewModel extends AndroidViewModel {

    protected MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    private DataBase db = Room.databaseBuilder(
            this.getApplication(),
            DataBase.class,
            "database")
            .fallbackToDestructiveMigration()
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    db.execSQL("insert into user (name, date, email, password, isAdmin) " +
                            "values (\"admin\", \"00.00.0000\", \"admin@admin.com\", \"admin\", 1)");
                }
            })
            .build();

    protected Repo repository = new Repo(db.getDao());

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
