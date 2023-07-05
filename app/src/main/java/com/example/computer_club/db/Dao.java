package com.example.computer_club.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.computer_club.DateTime;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.Order;
import com.example.computer_club.tables.User;

import java.util.List;

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

    @Query("select * from user where email = :email")
    Single<User> getUserByEmail(String email);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCompList(List<Comp> comps);

    @Query("select * from comp")
    Single<List<Comp>> getAllComps();

    @Query("delete from comp where id = :id")
    Completable deleteComp(int id);

    @Query("select exists(select * from `Order` where compId = :compId)")
    Single<Boolean> isComputerOrdered(int compId);


    @Query("select * from `order` where userId = :userId")
    Single<List<Order>> getAllUserOrders(int userId);

    @Insert
    Completable insertOrder(Order order);

    @Query("select date, time from `order` where compId = :compId")
    Single<List<DateTime>> getCompOrderedDateTime(int compId);

}
