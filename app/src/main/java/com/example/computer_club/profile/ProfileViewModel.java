package com.example.computer_club.profile;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.computer_club.SingleLiveEvent;
import com.example.computer_club.tables.Order;
import com.example.computer_club.tables.User;
import com.example.computer_club.BaseViewModel;

import java.util.List;

public class ProfileViewModel extends BaseViewModel {

    private SingleLiveEvent<User> _userData = new SingleLiveEvent<>();
    public LiveData<User> userData = _userData;

    private SingleLiveEvent<List<Order>> _historyData = new SingleLiveEvent<>();
    public LiveData<List<Order>> historyData = _historyData;


    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public void getUserByEmail(String email){
        repository.getUserByEmail(email)
                .subscribe(user -> _userData.postValue(user), err -> _error.postValue(err.getMessage()));
    }

    @SuppressLint("CheckResult")
    public void getAllHistory(int userId){
        repository.getAllHistory(userId)
                .subscribe(list -> _historyData.postValue(list));
    }

}
