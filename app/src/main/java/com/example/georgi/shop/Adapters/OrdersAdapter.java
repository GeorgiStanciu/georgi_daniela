package com.example.georgi.shop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.georgi.shop.Models.OrderModel;
import com.example.georgi.shop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Georgi on 11-May-17.
 */

public class OrdersAdapter extends ArrayAdapter{

    private Context context;
    private ArrayList<OrderModel> orders;
    public OrdersAdapter(Context context, ArrayList<OrderModel> orders) {
        super(context, R.layout.order_item_layout, orders);
        this.context = context;
        this.orders = orders;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_item_layout, null);

        OrderModel order = orders.get(position);
        TextView orderNumber = (TextView) view.findViewById(R.id.order_number);
        TextView orderDate = (TextView )view.findViewById(R.id.order_date);
        TextView orderCost = (TextView) view.findViewById(R.id.order_cost);
        ListView productList = (ListView) view.findViewById(R.id.order_product_list);

        orderNumber.setText("Comanda nr. " + (position + 1));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, hh:mm");
        String dateString = format.format(order.getOrderDate());
        orderDate.setText("Plasata pe : " + dateString);
        orderCost.setText("Total: " + String.format("%.2f",order.getCost()));

        ViewGroup.LayoutParams layoutParams = productList.getLayoutParams();
        layoutParams.height = (int) context.getResources().getDimension(R.dimen.rowheight) * order.getProducts().size();
        productList.setLayoutParams(layoutParams);
        ProductOfOrderAdapter adapter = new ProductOfOrderAdapter(context, order.getProducts(), order.getProductNumber());
        productList.setAdapter(adapter);
        return view;
    }
}
