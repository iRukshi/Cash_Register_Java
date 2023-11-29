package com.example.cashregisterapp_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class RestockActivity extends AppCompatActivity {

    private Button okButton;
    private Button cancelButton;
    private EditText newQuantityEditText;
    private ListView listView;
    private ArrayList<Product> products;
    private ProductsBaseAdaptor baseAdaptor;
    Product selectedProduct;
    int newQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        okButton = findViewById(R.id.buttonOk);
        cancelButton = findViewById(R.id.buttonCancel);
        newQuantityEditText = findViewById(R.id.enterNewQuantityEditText);

        products =  ((MyApp)getApplication()).ProductList;

        baseAdaptor = new ProductsBaseAdaptor(products,this);
        listView = findViewById(R.id.listViewRestock);
        listView.setAdapter(baseAdaptor);
        listView.setOnItemClickListener((parent, view, position, id) -> onRowClick(position));

        okButton.setOnClickListener(v -> onOkClick());
        cancelButton.setOnClickListener(v -> onCancelClick());

    }

    private void onRowClick(int position) {
        selectedProduct = products.get(position);
    }
    private void onOkClick() {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please select product!!!", Toast.LENGTH_SHORT).show();
        } else {
            newQuantity = Integer.parseInt(newQuantityEditText.getText().toString());
            selectedProduct.addQuantity(newQuantity);
            baseAdaptor.notifyDataSetChanged(); // Update the ListView
            newQuantityEditText.setText("");
        }
    }
    private void onCancelClick() {
        newQuantityEditText.setText("");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//        finish();
    }
}