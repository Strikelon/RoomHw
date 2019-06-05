package com.strikalov.roomhw.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Insert
    Long insert(User user);

    @Insert
    Long[] insertList(List<User> users);

    @Update
    int update(User user);

    @Delete
    int delete(User user);

    @Query("SELECT * FROM user_table")
    Maybe<List<User>> getAllUsers();
}
