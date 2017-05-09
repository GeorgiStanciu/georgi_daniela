package com.example.georgi.shop.Activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.shop.Models.ShoppingBasket;
import com.example.georgi.shop.R;

public class PayMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);

        ShoppingBasket basket = (ShoppingBasket) getIntent().getSerializableExtra("shoppingBasket");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detalii comanda");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditText email = (EditText) findViewById(R.id.bill_user_email);
        final EditText name = (EditText) findViewById(R.id.bill_user_name);
        final EditText address = (EditText) findViewById(R.id.bill_user_address);
        final EditText phone = (EditText) findViewById(R.id.bill_user_phone);
        final EditText cardNumber = (EditText) findViewById(R.id.bill_user_card_number);
        final RadioButton onlinePayment = (RadioButton) findViewById(R.id.online_payment);
        RadioButton cashPayment = (RadioButton) findViewById(R.id.cash_payment);
        RadioButton ordinPayment = (RadioButton) findViewById(R.id.order_payment);
        TextView totalPayment = (TextView) findViewById(R.id.bill_total_payment);
        Button sentOrder = (Button) findViewById(R.id.send_order_butten);

        totalPayment.setText(String.format("%.2f", basket.calcSum()) + " Lei");
        email.setText(basket.getUser().getEmail());
        name.setText(basket.getUser().getName());
        address.setText(basket.getUser().getAddress());
        phone.setText(basket.getUser().getPhone());
        cardNumber.setText(basket.getUser().getCardNumber());

        onlinePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardNumber.setVisibility(View.VISIBLE);
            }
        });
        cashPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardNumber.setVisibility(View.GONE);
            }
        });

        ordinPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardNumber.setVisibility(View.GONE);
            }
        });

        sentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String userAddress = address.getText().toString();
                String userPhone = phone.getText().toString();
                if(userName.equals("") || userAddress.equals("") || userPhone.equals("")){
                    Toast.makeText(PayMethodActivity.this, "Completeaza toate campurile pentru a putea plasa comanda", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(onlinePayment.isChecked() == true && cardNumber.getText().toString().equals("")){
                       Toast.makeText(PayMethodActivity.this, "Completeaza toate campurile pentru a putea plasa comanda", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent = new Intent(PayMethodActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
