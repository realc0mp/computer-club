package com.example.computer_club.auth;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import com.example.computer_club.tables.User;
import com.example.computer_club.BaseViewModel;


public class RegisterViewModel extends BaseViewModel {

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }


    @SuppressLint("CheckResult")
    public void register(User user){
        repository.register(user)
                .subscribe(() -> {}, (err) -> _error.postValue(err.getMessage()));
    }


}
