package com.example.cashregisterapp_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements ProductsRecyclerAdaptor.OnItemClickListener{

    ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        histories =  ((MyApp)getApplication()).HistoryList;

        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductsRecyclerAdaptor productsRecyclerAdaptor = new ProductsRecyclerAdaptor(histories,this);
        recyclerView.setAdapter(productsRecyclerAdaptor);

//        Intent historyListIntent = getIntent();
//        if (historyListIntent.hasExtra("historyList")){
//            histories = historyListIntent.getParcelableArrayListExtra("historyList");
//
//            RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            ProductsRecyclerAdaptor productsRecyclerAdaptor = new ProductsRecyclerAdaptor(histories,this);
//            recyclerView.setAdapter(productsRecyclerAdaptor);
//        }
    }

    @Override
    public void onItemClick(int position) {
        Intent historyDetailIntent = new Intent(this, HistoryDetailActivity.class);
        historyDetailIntent.putExtra("historyObject", histories.get(position));
        startActivity(historyDetailIntent);
    }
}