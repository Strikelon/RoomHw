package com.strikalov.roomhw.view;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.strikalov.roomhw.R;
import com.strikalov.roomhw.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String LOG_TAG = "LogMainActivity";
    private static final String EMPTY_TEXT = "";

    @BindView(R.id.user_id)
    EditText userIdTextField;

    @BindView(R.id.user_name)
    EditText userNameTextField;

    @BindView(R.id.user_surname)
    EditText userSurnameTextField;

    @BindView(R.id.user_age)
    EditText userAgeTextField;

    @BindView(R.id.user_count)
    EditText userCountTextField;

    @BindView(R.id.text_result)
    TextView textResult;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_button)
    void onClickAddButton(){
        Log.i(LOG_TAG, "onClickAddButton()");
        String userName = userNameTextField.getText().toString();
        String userSurname = userSurnameTextField.getText().toString();
        String userAge = userAgeTextField.getText().toString();
        String userCount = userCountTextField.getText().toString();
        presenter.onClickAddButton(userName, userSurname, userAge, userCount);
    }

    @OnClick(R.id.update_button)
    void onClickUpdateButton(){
        Log.i(LOG_TAG, "onClickUpdateButton(");
        String userId = userIdTextField.getText().toString();
        String userName = userNameTextField.getText().toString();
        String userSurname = userSurnameTextField.getText().toString();
        String userAge = userAgeTextField.getText().toString();
        presenter.onClickUpdateButton(userId, userName, userSurname, userAge);
    }

    @OnClick(R.id.delete_button)
    void onClickDeleteButton(){
        Log.i(LOG_TAG, "onClickDeleteButton()");
        String userId = userIdTextField.getText().toString();
        String userName = userNameTextField.getText().toString();
        String userSurname = userSurnameTextField.getText().toString();
        String userAge = userAgeTextField.getText().toString();
        presenter.onClickDeleteButton(userId, userName, userSurname, userAge);
    }

    @Override
    public void clearEditTextFields() {

        userIdTextField.setText(EMPTY_TEXT);

        userNameTextField.setText(EMPTY_TEXT);

        userSurnameTextField.setText(EMPTY_TEXT);

        userAgeTextField.setText(EMPTY_TEXT);

        userCountTextField.setText(EMPTY_TEXT);
    }

    @Override
    public void showToastMessage(@StringRes int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTextResult(String result) {
        textResult.setText(result);
    }
}
