package com.strikalov.roomhw.model.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String userName;

    private String userSurname;

    private int userAge;

    public User(String userName, String userSurname, int userAge){
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAge = userAge;
    }

    @Ignore
    public User(long id, String userName, String userSurname, int userAge){
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAge = userAge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
