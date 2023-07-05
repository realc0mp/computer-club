package com.example.computer_club.main;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.computer_club.BaseViewModel;
import com.example.computer_club.SingleLiveEvent;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.User;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    private SingleLiveEvent<List<Comp>> _allCompsData = new SingleLiveEvent<>();
    public LiveData<List<Comp>> allCompsData = _allCompsData;

    private MutableLiveData<User> _userData = new MutableLiveData<>();
    public LiveData<User> userData = _userData;

    private MutableLiveData<Comp> _deletedData = new MutableLiveData();
    public LiveData<Comp> deletedData = _deletedData;

    @SuppressLint("CheckResult")
    public void getAllComps(){
        repository.getAllComps()
                .subscribe(comps -> _allCompsData.postValue(comps));
    }

    @SuppressLint("CheckResult")
    public void getUserByEmail(String email){
        repository.getUserByEmail(email)
                .subscribe(user -> _userData.postValue(user));
    }

    public void addCompsList(List<Comp> comps){
        repository.addCompsList(comps)
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void deleteComp(Comp item){
        repository.deleteCompIfNotOrdered(item.id)
                .subscribe(() -> _deletedData.postValue(item), er -> _error.postValue(er.getMessage()));
    }
}
