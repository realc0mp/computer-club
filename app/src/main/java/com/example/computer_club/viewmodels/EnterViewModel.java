package com.example.computer_club.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.computer_club.db.Repo;

public class EnterViewModel extends BaseViewModel {

    private Repo repository;


    public EnterViewModel(@NonNull Application application) {
        super(application);
    }
}
