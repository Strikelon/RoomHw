package com.strikalov.roomhw.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strikalov.roomhw.R;
import com.strikalov.roomhw.model.Model;
import com.strikalov.roomhw.model.database.User;
import com.strikalov.roomhw.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String LOG_TAG = "LogMainPresenter";

    private Model model;

    public MainPresenter(){
        model = new Model();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getAllUsers();
    }

    public void onClickAddButton(String userName, String userSurname, String userAge, String userCount){

        Log.i(LOG_TAG, "onClickAddButton()");

        if(firstTypeFieldsCheckIsOk(userName, userSurname, userAge, userCount)){

            getViewState().clearEditTextFields();

            int age = Integer.parseInt(userAge);
            int count = Integer.parseInt(userCount);

            User user = new User(userName, userSurname, age);
            if(count == 1){
                insertUserInDatabas(user);
            }else if(count > 1){
                List<User> userList = new ArrayList<>();
                for(int i=0; i<count; i++){
                    userList.add(user);
                }
                insertUserListInDatabase(userList);
            }
        }

    }

    public void onClickUpdateButton(String userId, String userName, String userSurname, String userAge){
        Log.i(LOG_TAG, "onClickUpdateButton()");

        if(secondTypeFieldsCheckIsOk(userId, userName, userSurname, userAge)){
            getViewState().clearEditTextFields();

            int id = Integer.parseInt(userId);
            int age = Integer.parseInt(userAge);

            updateUserInDatabase(new User(id, userName, userSurname, age));

        }

    }

    public void onClickDeleteButton(String userId, String userName, String userSurname, String userAge){
        Log.i(LOG_TAG, "onClickDeleteButton()");

        if(secondTypeFieldsCheckIsOk(userId, userName, userSurname, userAge)){
            getViewState().clearEditTextFields();

            int id = Integer.parseInt(userId);
            int age = Integer.parseInt(userAge);

            deleteUserFromDatabase(new User(id, userName, userSurname, age));
        }
    }

    private void insertUserInDatabas(User user){
        Disposable disposable = model.insertUserInDatabase(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getAllUsers(),
                        throwable -> Log.i(LOG_TAG, throwable.toString())
                );
    }

    private void insertUserListInDatabase(List<User> userList){
        Disposable disposable = model.insertUserListInDatabase(userList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getAllUsers(),
                        throwable -> Log.i(LOG_TAG, throwable.toString())
                );
    }

    private void updateUserInDatabase(User user){
        Disposable disposable = model.updateUserInDatabase(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getAllUsers(),
                        throwable -> Log.i(LOG_TAG, throwable.toString())
                );
    }

    private void deleteUserFromDatabase(User user){
        Disposable disposable = model.deleteUserFromDatabase(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> getAllUsers(),
                        throwable -> Log.i(LOG_TAG, throwable.toString())
                );
    }

    private void getAllUsers(){
        Disposable disposable = model.getAllUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            StringBuilder stringBuilder = new StringBuilder();
                            for(User user : users){
                                stringBuilder.append(user).append("\n\n");
                            }
                            getViewState().showTextResult(stringBuilder.toString());
                        },
                        throwable -> Log.i(LOG_TAG, throwable.toString())
                );
    }

    private boolean firstTypeFieldsCheckIsOk(String userName, String userSurname, String userAge, String userCount) {

        if (TextUtils.isEmpty(userName)) {
            getViewState().showToastMessage(R.string.user_name_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userSurname)) {
            getViewState().showToastMessage(R.string.user_surname_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userAge)) {
            getViewState().showToastMessage(R.string.user_age_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userCount)) {
            getViewState().showToastMessage(R.string.user_count_is_empty);
            return false;
        } else {
            int age = Integer.parseInt(userAge);
            int count = Integer.parseInt(userCount);

            if (age <= 0) {
                getViewState().showToastMessage(R.string.user_age_error);
                return false;
            } else if (count <= 0) {
                getViewState().showToastMessage(R.string.user_count_error);
                return false;
            } else {

                return true;
            }

        }
    }

    private boolean secondTypeFieldsCheckIsOk(String userId, String userName, String userSurname, String userAge){

        if (TextUtils.isEmpty(userId)) {
            getViewState().showToastMessage(R.string.user_id_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userName)) {
            getViewState().showToastMessage(R.string.user_name_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userSurname)) {
            getViewState().showToastMessage(R.string.user_surname_is_empty);
            return false;
        } else if (TextUtils.isEmpty(userAge)) {
            getViewState().showToastMessage(R.string.user_age_is_empty);
            return false;
        } else {
            int age = Integer.parseInt(userAge);
            int id = Integer.parseInt(userId);

            if (age <= 0) {
                getViewState().showToastMessage(R.string.user_age_error);
                return false;
            } else if (id <= 0) {
                getViewState().showToastMessage(R.string.user_id_error);
                return false;
            } else {
                return true;
            }

        }

    }

}
