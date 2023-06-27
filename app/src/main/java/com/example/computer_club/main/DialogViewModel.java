package com.example.computer_club.main;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.computer_club.BaseViewModel;
import com.example.computer_club.DateTime;
import com.example.computer_club.tables.Order;

import java.util.List;

public class DialogViewModel extends BaseViewModel {

    private MutableLiveData<List<DateTime>> _orderedTimeData = new MutableLiveData<>();
    public LiveData<List<DateTime>> orderedTimeData = _orderedTimeData;


    public DialogViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public void getCompOrderedTime(int compId){
        repository.getCompOrderedTime(compId)
                .subscribe(time -> _orderedTimeData.postValue(time));
    }

    public void addOrder(Order order){
        repository.insertOrder(order)
                .subscribe();
    }
}
