package com.example.computer_club.auth;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.computer_club.BaseViewModel;

import java.util.HashMap;

public class EnterViewModel extends BaseViewModel {

    private MutableLiveData<HashMap<String, Boolean>> _enterData = new MutableLiveData<>();
    public LiveData<HashMap<String, Boolean>> enterData = _enterData;


    public EnterViewModel(@NonNull Application application) {
        super(application);
    }


    @SuppressLint("CheckResult")
    public void enter(String email, String password){
        repository.enter(email, password)
                .subscribe(
                        result -> _enterData.postValue(result),
                        throwable -> _error.postValue(throwable.getMessage()));
    }

}
