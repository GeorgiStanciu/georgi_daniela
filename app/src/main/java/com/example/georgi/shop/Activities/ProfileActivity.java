package com.example.georgi.shop.Activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.georgi.shop.Fragments.DatePickerFragment;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Helpers.GlobalBus;
import com.example.georgi.shop.Helpers.OnSetBirthDate;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.UserModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;
import com.squareup.otto.Subscribe;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProfileActivity extends BaseActivity {

    private EditText birth;
    private int userId;
    private UserModel user;
    private  View view;
    private  RelativeLayout logoutLayout;
    private LinearLayout loginLayout;
    private  View profileLayout;
    private  SharedPreferences sharedPreferences;

    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_profile,null);

        logoutLayout = (RelativeLayout) view.findViewById(R.id.profile_logout);
        loginLayout = (LinearLayout) view.findViewById(R.id.profile_login);
        profileLayout = view.findViewById(R.id.profile_picture);
        sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);

        if(userId == 0){
            loginLayout.setVisibility(View.GONE);
            profileLayout.setVisibility(View.GONE);
            logoutLayout.setVisibility(View.VISIBLE);

            Button login = (Button) view.findViewById(R.id.login_profile_button);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            new GetUser().execute();
        }
        contentLayout.addView(view);
    }

    @Subscribe
    public void onSetBirthDate(OnSetBirthDate event){

        birth.setText(event.getDate());

    }

    private void setProfile(){

        loginLayout.setVisibility(View.VISIBLE);
        profileLayout.setVisibility(View.VISIBLE);
        logoutLayout.setVisibility(View.GONE);

        EditText email = (EditText) view.findViewById(R.id.profile_email);
        final EditText name = (EditText) view.findViewById(R.id.profile_name);
        final Spinner sex = (Spinner) view.findViewById(R.id.profile_sex);
        final EditText phone = (EditText) view.findViewById(R.id.profile_phone);
        birth = (EditText) view.findViewById(R.id.profile_birth);
        final EditText address = (EditText) view.findViewById(R.id.profile_address);
        final EditText cardNumber = (EditText) view.findViewById(R.id.profile_card_number);
        Button orders = (Button) view.findViewById(R.id.view_orders_button);
        Button save = (Button) view.findViewById(R.id.save_profile_button);
        Button logout = (Button) view.findViewById(R.id.logout_button);

        email.setText(user.getEmail());
        name.setText(user.getName());
        if (user.getSex() != null)
            if( user.getSex().equals("Masculin"))
                sex.setSelection(0);
            else
                sex.setSelection(1);
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        cardNumber.setText(user.getCardNumber());
        final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(user.getBirthDate() != null)
           birth.setText(format.format(user.getBirthDate()));
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager().beginTransaction(), "datePicker");
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, OrdersActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(name.getText().toString());
                user.setSex((String) sex.getSelectedItem());
                java.util.Date date = null;
                try {
                    date = format.parse(birth.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(date != null)
                user.setBirthDate(new Date(date.getTime()));
                user.setPhone(phone.getText().toString());
                user.setAddress(address.getText().toString());
                user.setCardNumber(cardNumber.getText().toString());
                new SaveUser().execute();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(ProfileActivity.this.getString(R.string.user_id_preference), 0);
                editor.commit();
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
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

    class GetUser extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetUserCommand, userId));
            user = (UserModel) response.getResponse();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            setProfile();
        }
    }

    class SaveUser extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            Client client = new Client();
            client.connectToServer();
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.UpdateUserCommand, user));
            return null;
        }
    }
}
