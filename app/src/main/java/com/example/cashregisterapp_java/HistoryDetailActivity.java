package com.example.cashregisterapp_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView detailTextView;
    History history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        detailTextView = findViewById(R.id.detailTextView);

        Intent newIntent = getIntent();
        if (newIntent.hasExtra("historyObject")){
            history = newIntent.getParcelableExtra("historyObject");
            detailTextView.setText("Product: "+history.getName()+" \nPrice: $"+history.getTotalPrice()+" \nPurchase Date: "+history.getPurchaseDate());
        }
    }
}