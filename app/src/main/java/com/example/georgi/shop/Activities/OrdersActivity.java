package com.example.georgi.shop.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.georgi.shop.Adapters.OrdersAdapter;
import com.example.georgi.shop.Helpers.Command;
import com.example.georgi.shop.Helpers.CommandResponse;
import com.example.georgi.shop.Models.CommandEnum;
import com.example.georgi.shop.Models.OrderModel;
import com.example.georgi.shop.R;
import com.example.georgi.shop.Services.Client;

import java.util.ArrayList;


public class OrdersActivity extends BaseActivity {

    private RelativeLayout emptyLayout;
    private ArrayList<OrderModel> orders;
    private ListView ordersList;
    private View view;
    private ProgressBar progressBar;
    @Override
    protected void addLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_orders,null);

        emptyLayout = (RelativeLayout) view.findViewById(R.id.empty_orders_layout);
        ordersList = (ListView) view.findViewById(R.id.ordered_products_list);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        //populateOrder();
        new GetOrders().execute();
        contentLayout.addView(view);
    }

    private void setOrders(){

        progressBar.setVisibility(View.GONE);
        if(orders == null || orders.size() == 0){
            emptyLayout.setVisibility(View.VISIBLE);
            ordersList.setVisibility(View.GONE);
            Button orderButton = (Button) view.findViewById(R.id.empty_orders_button);
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OrdersActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                }
            });
        }
        else{
            emptyLayout.setVisibility(View.GONE);
            ordersList.setVisibility(View.VISIBLE);
            OrdersAdapter adapter = new OrdersAdapter(this, orders);
            ordersList.setAdapter(adapter);
        }
    }

    class GetOrders extends AsyncTask{


        @Override
        protected Object doInBackground(Object[] objects) {

            Client client = new Client();
            client.connectToServer();
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt(getString(R.string.user_id_preference), 0);
            CommandResponse response = client.receiveDataFromServer(new Command(CommandEnum.GetOrderesByUserCommand, userId));
            orders = (ArrayList<OrderModel>) response.getResponse();
            client.receiveDataFromServer(new Command(CommandEnum.EndConnectionCommand));

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            setOrders();
        }

    }


}
