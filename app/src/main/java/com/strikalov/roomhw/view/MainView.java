package com.strikalov.roomhw.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void clearEditTextFields();
    void showToastMessage(@StringRes int stringId);
    void showTextResult(String result);

}
