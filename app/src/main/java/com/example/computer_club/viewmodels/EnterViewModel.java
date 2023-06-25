package com.example.computer_club.viewmodels;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.computer_club.db.Repo;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;

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
