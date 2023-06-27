package com.example.computer_club.db;

import com.example.computer_club.DateTime;
import com.example.computer_club.tables.Comp;
import com.example.computer_club.tables.Order;
import com.example.computer_club.tables.User;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repo {

    private Dao dao;

    public Repo(Dao dao){
        this.dao = dao;
    }

    private Completable insertUser(User user){
        return dao.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<Boolean> checkIfEmailLogged(String email){
        return dao.checkIfUserLogged(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<Boolean> checkPassword(String email, String password){
        return dao.checkPassword(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public @NonNull Single<HashMap<String, Boolean>> enter(String email, String password){
        Single<Boolean> checkIfLogged = checkIfEmailLogged(email);
        Single<Boolean> checkPassword = checkPassword(email, password);

        BiFunction<Boolean, Boolean, HashMap<String, Boolean>> biFunc = (emailCheck, passCheck) -> {
            HashMap<String, Boolean> map = new HashMap<>();
            map.put("EMAIL_CHECK", emailCheck);
            map.put("PASS_CHECK", passCheck);
            return map;
        };

        return Single.zip(checkIfLogged, checkPassword, biFunc);
    }


    public Completable register(User user){
        return checkIfEmailLogged(user.email)
            .flatMapCompletable(emailExist -> {
                if (emailExist) {
                    throw new IllegalStateException("Почта уже зарегестрирована!");
                }
                return insertUser(user);
            });
    }


    public Single<User> getUserByEmail(String email){
        return dao.getUserByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Order>> getAllHistory(int userId){
        return dao.getAllUserOrders(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable addCompsList(List<Comp> comps){
        return dao.insertCompList(comps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Comp>> getAllComps(){
        return dao.getAllComps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteComp(int id){
        return dao.deleteComp(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertOrder(Order order){
        return dao.insertOrder(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<DateTime>> getCompOrderedTime(int compId){
        return dao.getCompOrderedDateTime(compId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
