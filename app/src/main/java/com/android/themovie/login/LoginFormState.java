package com.android.themovie.login;

import androidx.annotation.Nullable;

public class LoginFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean isDataValid;

    public LoginFormState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    public LoginFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    protected Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    protected Integer getPasswordError() {
        return passwordError;
    }

    protected boolean isDataValid() {
        return isDataValid;
    }
}
