package com.example.computer_club.profile;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.computer_club.History;
import com.example.computer_club.User;
import com.example.computer_club.viewmodels.BaseViewModel;

import java.util.List;

public class ProfileViewModel extends BaseViewModel {

    private MutableLiveData<User> _userData = new MutableLiveData<>();
    public LiveData<User> userData = _userData;

    private MutableLiveData<List<History>> _historyData = new MutableLiveData<>();
    public LiveData<List<History>> historyData = _historyData;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public void getUserByEmail(String email){
        repository.getUserByEmail(email)
                .subscribe(user -> _userData.postValue(user));
    }

    @SuppressLint("CheckResult")
    public void getAllHistory(){
        repository.getAllHistory()
                .subscribe(list -> _historyData.postValue(list));
    }
}
