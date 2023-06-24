package com.example.computer_club.db;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.computer_club.User;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@androidx.room.Dao
public interface Dao {

    @Query("select exists(select id from User where email = :email)")
    Single<Boolean> checkIfUserLogged(String email);

    @Query("select exists(select * from User where email = :email and password = :password)")
    Single<Boolean> checkPassword(String email, String password);

    @Insert
    Completable insertUser(User user);

}
