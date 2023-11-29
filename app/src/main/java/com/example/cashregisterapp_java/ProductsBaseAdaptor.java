package com.example.cashregisterapp_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductsBaseAdaptor extends BaseAdapter {
    ArrayList<Product> productArrayList;
    Context activityContext;

    ProductsBaseAdaptor(ArrayList<Product> list, Context context){
        this.productArrayList = list;
        activityContext = context;
    }
    @Override
    public int getCount() {
        return productArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return productArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // covert xml file into a view object.
        //Inflate a new view hierarchy from the specified xml resource.
        View rowView =  LayoutInflater.from(activityContext).inflate(R.layout.product_list_row, viewGroup, false);
        TextView nameTextView = rowView.findViewById(R.id.topLeftTextView);
        TextView quantityTextView = rowView.findViewById(R.id.topRightTextView);
        TextView priceTextView = rowView.findViewById(R.id.bottomLeftTextView);

        nameTextView.setText(productArrayList.get(i).getName());
        quantityTextView.setText(String.valueOf(productArrayList.get(i).getQuantity()));
        priceTextView.setText(String.valueOf(productArrayList.get(i).getPrice()));

        return rowView;
    }
}
