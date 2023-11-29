package com.example.cashregisterapp_java;

public class Product {
    String name;
    int quantity;
    double price;
    public Product(String name,int quantity,double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public void removeQuantity(int quantity) {
         this.quantity -= quantity;
    }
    public void   addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
