package com.example.georgi.shop.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.georgi.shop.Helpers.OnUserLogin;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.LoginFirebase;
import com.squareup.otto.Subscribe;

public class ForgotActivity extends BaseActivity {


    private EditText user;
    private Button forgotButton;
    private LoginFirebase firebase;
    private ProgressBar progressBar;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        user = (EditText) findViewById(R.id.user_edit_text);

       forgotButton = (Button) findViewById(R.id.auth_button);
        ImageView backArrow = (ImageView) findViewById(R.id.back_arrow);
        backArrow.setVisibility(View.VISIBLE);
        firebase = new LoginFirebase(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_login_screen);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = user.getText().toString();
                if (email == null || email.equals("")) {
                    Toast.makeText(ForgotActivity.this, R.string.login_user_empty, Toast.LENGTH_SHORT).show();

                } else {
                    firebase.forgot(email);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

    }*/

    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_forgot,null);

        user = (EditText) view.findViewById(R.id.user_edit_text);

        forgotButton = (Button) view.findViewById(R.id.auth_button);
        ImageView backArrow = (ImageView) view.findViewById(R.id.back_arrow);
        backArrow.setVisibility(View.VISIBLE);
        firebase = new LoginFirebase(this);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_login_screen);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = user.getText().toString();
                if (email == null || email.equals("")) {
                    Toast.makeText(ForgotActivity.this, R.string.login_user_empty, Toast.LENGTH_SHORT).show();

                } else {
                    firebase.forgot(email);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        contentLayout.addView(view);
    }


    @Subscribe
    public void onUserLogin(OnUserLogin event){
        progressBar.setVisibility(View.GONE);
        finish();
    }

}
