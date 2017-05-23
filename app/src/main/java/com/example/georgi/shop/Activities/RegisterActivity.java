package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnUserLogin;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.LoginFirebase;
import com.squareup.otto.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {


    private EditText user;
    private EditText password;
    private EditText confirm;
    private Button registerButton;
    private LinearLayout facebookLogin;
    private LinearLayout registerForgot;
    private LoginFirebase firebase;
    private ProgressBar progressBar;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user_edit_text);
        password = (EditText) findViewById(R.id.password_edit_text);
        registerButton = (Button) findViewById(R.id.auth_button);
        facebookLogin = (LinearLayout) findViewById(R.id.facebook_login);
        registerForgot = (LinearLayout) findViewById(R.id.register_forgot_buttons);
        confirm = (EditText) findViewById(R.id.confirm_edit_text);
        ImageView backArrow = (ImageView) findViewById(R.id.back_arrow);
        backArrow.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        registerForgot.setVisibility(View.INVISIBLE);
        registerButton.setText(R.string.register);
        facebookLogin.setVisibility(View.GONE);
        firebase = new LoginFirebase(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_login_screen);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String passwordText = password.getText().toString();
                String confirmText = confirm.getText().toString();
                if (userName == null || userName.equals("")) {
                    Toast.makeText(RegisterActivity.this, R.string.login_user_empty, Toast.LENGTH_SHORT).show();

                } else if (passwordText == null || passwordText.equals("")) {
                    Toast.makeText(RegisterActivity.this, R.string.login_password_empty, Toast.LENGTH_SHORT).show();

                }
                else if(confirmText == null || confirmText.equals("") || !confirmText.equals(passwordText)){
                    Toast.makeText(RegisterActivity.this, R.string.password_match_text,Toast.LENGTH_SHORT).show();;

                }
                else if(validatePassword(passwordText)) {
                    firebase.register(userName, passwordText);
                    progressBar.setProgress(View.VISIBLE);
                }
            }
        });


    }*/


    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_login,null);
        user = (EditText) view.findViewById(R.id.user_edit_text);
        password = (EditText) view.findViewById(R.id.password_edit_text);
        registerButton = (Button) view.findViewById(R.id.auth_button);
        facebookLogin = (LinearLayout) view.findViewById(R.id.facebook_login);
        registerForgot = (LinearLayout) view.findViewById(R.id.register_forgot_buttons);
        confirm = (EditText) view.findViewById(R.id.confirm_edit_text);
        ImageView backArrow = (ImageView) view.findViewById(R.id.back_arrow);
        backArrow.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
        registerForgot.setVisibility(View.INVISIBLE);
        registerButton.setText(R.string.register);
        facebookLogin.setVisibility(View.GONE);
        firebase = new LoginFirebase(this);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_login_screen);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String passwordText = password.getText().toString();
                String confirmText = confirm.getText().toString();
                if (userName == null || userName.equals("")) {
                    Toast.makeText(RegisterActivity.this, R.string.login_user_empty, Toast.LENGTH_SHORT).show();

                } else if (passwordText == null || passwordText.equals("")) {
                    Toast.makeText(RegisterActivity.this, R.string.login_password_empty, Toast.LENGTH_SHORT).show();

                }
                else if(confirmText == null || confirmText.equals("") || !confirmText.equals(passwordText)){
                    Toast.makeText(RegisterActivity.this, R.string.password_match_text,Toast.LENGTH_SHORT).show();;

                }
                else if(validatePassword(passwordText)) {
                    firebase.register(userName, passwordText);
                    progressBar.setProgress(View.VISIBLE);
                }
            }
        });

        contentLayout.addView(view);
    }

    private void startMainScreen(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
        if (userId != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Subscribe
    public void onUserLogin(OnUserLogin event){
        progressBar.setVisibility(View.INVISIBLE);
        if(event.getLoginStatus()) {
            startMainScreen();
        }
    }


    private boolean validatePassword(String password){
        if(password.length() < 6 || password.length() > 20){
            Toast.makeText(RegisterActivity.this, R.string.password_size_text,Toast.LENGTH_SHORT).show();
            return false;
        }
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.])(?=.*[0-9]).*$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (!password.matches(".*\\d.*") || !matcher.matches()) {
            Toast.makeText(this, R.string.password_match_text, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
