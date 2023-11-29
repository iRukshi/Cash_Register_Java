package com.example.cashregisterapp_java;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
    ArrayList<Product> ProductList = new ArrayList<>(0);

    ArrayList<History> HistoryList = new ArrayList<>(0);
}
