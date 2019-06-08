package com.strikalov.roomhw.model;

import com.strikalov.roomhw.App;
import com.strikalov.roomhw.model.database.AppDatabase;
import com.strikalov.roomhw.model.database.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class Model {

    private AppDatabase appDatabase;

    public Model(){
        this.appDatabase = App.getInstance().getAppDatabase();
    }


    public Completable insertUserInDatabase(User user){

        return Completable.fromCallable(
                () -> appDatabase.userDao().insert(user)
        ).subscribeOn(Schedulers.io());

    }

    public Completable insertUserListInDatabase(List<User> users){

        return Completable.fromCallable(
                () -> appDatabase.userDao().insertList(users)
        ).subscribeOn(Schedulers.io());

    }

    public Completable updateUserInDatabase(User user){

        return Completable.fromCallable(
                () -> appDatabase.userDao().update(user)
        ).subscribeOn(Schedulers.io());

    }

    public Completable deleteUserFromDatabase(User user){

        return Completable.fromCallable(
                () -> appDatabase.userDao().delete(user)
        ).subscribeOn(Schedulers.io());

    }

    public Maybe<List<User>> getAllUsers(){

        return appDatabase.userDao().getAllUsers()
                .subscribeOn(Schedulers.io());

    }

}
