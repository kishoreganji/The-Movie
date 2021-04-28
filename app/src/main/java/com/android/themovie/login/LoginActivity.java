package com.android.themovie.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.themovie.R;
import com.android.themovie.constants.AppConstants;
import com.android.themovie.movies.ui.MovieListActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppConstants.setCustomActionbar(LoginActivity.this, "Login");
        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
        final EditText etEmail               = findViewById(R.id.etEmail);
        final EditText etPassword            = findViewById(R.id.etPassword);
        final Button btnLogin                = findViewById(R.id.btnLogin);
        btnLogin.setAlpha(0.5f);
        btnLogin.setEnabled(false);
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                btnLogin.setEnabled(loginFormState.isDataValid());
                if(loginFormState.isDataValid()){
                    btnLogin.setAlpha(1f);
                }
                else {
                    btnLogin.setAlpha(0.5f);
                }
                if (loginFormState.getUsernameError() != null) {
                    etEmail.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    etPassword.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(etEmail.getText().toString(), etPassword.getText().toString());
            }
        };
        etEmail.addTextChangedListener(afterTextChangedListener);
        etPassword.addTextChangedListener(afterTextChangedListener);
        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyboard();
                }
                return false;
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MovieListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(LoginActivity.this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
