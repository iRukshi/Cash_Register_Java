package com.example.cashregisterapp_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    Button historyButton;
    Button restockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        historyButton = findViewById(R.id.buttonHistory);
        restockButton = findViewById(R.id.buttonRestock);
        historyButton.setOnClickListener(v -> onHistoryClick());
        restockButton.setOnClickListener(v -> onRestockClick());
    }

    private void onHistoryClick(){

        Intent historyListIntent = getIntent();
        ArrayList<History> histories = new ArrayList<>();
        if (historyListIntent.hasExtra("historyList")){
            histories = historyListIntent.getParcelableArrayListExtra("historyList");
        }

        Intent historyIntent = new Intent(this, HistoryActivity.class);
        historyIntent.putParcelableArrayListExtra("historyList",histories);
        startActivity(historyIntent);
    }
    private void onRestockClick(){
        Intent restockIntent = new Intent(this, RestockActivity.class);

        startActivity(restockIntent);
    }
}