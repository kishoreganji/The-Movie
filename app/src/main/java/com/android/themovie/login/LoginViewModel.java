package com.android.themovie.login;

import android.util.Patterns;

import com.android.themovie.R;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState  = new MutableLiveData<>();

    public LoginViewModel() {
    }

    protected LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public void loginDataChanged(String username, String password) {
        if (!isEmailValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        }
        else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        }
        else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isEmailValid(String username) {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5 && password.trim().length() < 13;
    }
}
