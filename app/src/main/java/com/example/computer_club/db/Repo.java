package com.example.computer_club.db;

import android.database.Observable;

import com.example.computer_club.User;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo {

    private Dao dao;

    public Repo(Dao dao){
        this.dao = dao;
    }

    public Completable insertUser(User user){
        return dao.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> checkIfUserLogged(String email){
        return dao.checkIfUserLogged(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> checkPassword(String email, String password){
        return dao.checkPassword(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
