package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnUserLogin;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.LoginFirebase;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.otto.Subscribe;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements FacebookCallback{

    private EditText user;
    private EditText password;
    private Button loginButton;
    private LinearLayout facebookLogin;
    private TextView register;
    private TextView forgot;
    private CallbackManager facebookCallbackManager;
    private LoginFirebase firebase;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        user = (EditText) findViewById(R.id.user_edit_text);
        password = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.auth_button);
        facebookLogin = (LinearLayout) findViewById(R.id.facebook_login);
        register = (TextView) findViewById(R.id.register_button);
        forgot = (TextView) findViewById(R.id.forgot_button);
        firebase = new LoginFirebase(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_login_screen);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String passwordText = password.getText().toString();
                if (userName == null || userName.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_user_empty, Toast.LENGTH_SHORT).show();

                } else if (passwordText == null || passwordText.equals("")) {
                    Toast.makeText(LoginActivity.this, R.string.login_password_empty, Toast.LENGTH_SHORT).show();

                } else {
                    firebase.login(userName, passwordText);
                    progressBar.setVisibility(View.VISIBLE);

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });


        facebookCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(facebookCallbackManager, this);
        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

    }

    @Override
    public void onSuccess(Object o) {
        final LoginResult loginResult = (LoginResult) o;
        firebase.handleFacebookAccessToken(loginResult.getAccessToken());

    }

    @Override
    public void onCancel() {
        Toast.makeText(this, R.string.cancel, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        error.printStackTrace();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode,resultCode, data);
    }

    @Subscribe
    public void onUserLogin(OnUserLogin event){
        progressBar.setVisibility(View.INVISIBLE);
        if(event.getLoginStatus()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}
