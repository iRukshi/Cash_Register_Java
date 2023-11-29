package com.example.cashregisterapp_java;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView productTypeTextView;
    private TextView totalAmountTextView;
    private TextView addQuantityTextView;
    private Button[] digitButtons;
    private Button clearButton;
    private Button buyButton;
    private Button managerButton;

    private ArrayList<Product> products;
    private ArrayList<History> histories = new ArrayList<>();
    private ProductsBaseAdaptor baseAdaptor;
    Product selectedProduct;
    int orderedQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productTypeTextView = findViewById(R.id.productTypeTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        addQuantityTextView = findViewById(R.id.addQuantityTextView);
        managerButton = findViewById(R.id.buttonManager);
        clearButton = findViewById(R.id.buttonClear);
        buyButton = findViewById(R.id.buttonBuy);

        digitButtons = new Button[10];//0-9
        for (int i = 0; i < 10; i++) {
            int resourceId = getResources().getIdentifier("button" + i, "id", getPackageName());
            digitButtons[i] = findViewById(resourceId);
            Button digitButton = digitButtons[i];
            digitButton.setOnClickListener(v -> onDigitClick(digitButton));
        }
        clearButton.setOnClickListener(v -> onClearClick(clearButton));
        buyButton.setOnClickListener(v -> onBuyClick(buyButton));
        managerButton.setOnClickListener(v -> onManagerClick(managerButton));

        if (((MyApp)getApplication()).ProductList.isEmpty()) {
            ((MyApp)getApplication()).ProductList = getInitialProductList(); //set myApp data for the first time
        }
        products = ((MyApp)getApplication()).ProductList;

        baseAdaptor = new ProductsBaseAdaptor(products, this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(baseAdaptor);
        listView.setOnItemClickListener((parent, view, position, id) -> onRowClick(position));
    }

    private ArrayList<Product> getInitialProductList() {
        products = new ArrayList<>();
        products.add(new Product("Pants", 10, 25.99));
        products.add(new Product("Shoes", 100, 15.49));
        products.add(new Product("Hats", 30, 9.99));
        products.add(new Product("Shirts", 15, 12.69));
        return products;
    }

    private void onRowClick(int position) {
        selectedProduct = products.get(position);
        productTypeTextView.setText(selectedProduct.getName());
    }
    private void onDigitClick(Button button) {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please add product!!!", Toast.LENGTH_SHORT).show();
        } else {
            String digit = button.getText().toString();
            addQuantityTextView.setText(addQuantityTextView.getText() + digit);
            orderedQuantity = Integer.parseInt(addQuantityTextView.getText().toString());
            if (orderedQuantity > selectedProduct.getQuantity()) {
                Toast.makeText(this, "Not enough quantity in the stock!!!", Toast.LENGTH_SHORT).show();
                addQuantityTextView.setText("");
                totalAmountTextView.setText("");
            } else {
                double totalAmount = orderedQuantity * selectedProduct.getPrice();
                totalAmountTextView.setText(String.valueOf(totalAmount));
            }
        }
    }
    private void onClearClick(Button button) {
        resetTextViews();
    }
    private void onBuyClick(Button button) {
        if (productTypeTextView.getText().length() == 0 || addQuantityTextView.getText().length() == 0) {
            Toast.makeText(this, "All fields required!!!", Toast.LENGTH_SHORT).show();
        } else {
            refreshList();
            showAlert();
            saveHistory();
            resetTextViews();
        }
    }
    private void onManagerClick(Button button) {
        Intent mActivity = new Intent(this, ManagerActivity.class);
        startActivity(mActivity);
    }

    private void refreshList(){
        selectedProduct.removeQuantity(orderedQuantity);
        baseAdaptor.notifyDataSetChanged();
    }
    private void saveHistory(){
        double totalAmount = Double.parseDouble(totalAmountTextView.getText().toString());
        histories.add(new History(selectedProduct.getName(), orderedQuantity, selectedProduct.getPrice(), totalAmount, new Date()));
        ((MyApp)getApplication()).HistoryList = histories; // set history to myApp
    }
    private void resetTextViews(){
        addQuantityTextView.setText("");
        totalAmountTextView.setText("");
        productTypeTextView.setText("");
        selectedProduct = null;
    }
    public void showAlert() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Your purchase is " + orderedQuantity + " " + productTypeTextView.getText() + " for $" + totalAmountTextView.getText());

        // Set Alert Title
        builder.setTitle("Thank You for your purchase!");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(true);

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

}
