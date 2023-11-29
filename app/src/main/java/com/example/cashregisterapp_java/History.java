package com.example.cashregisterapp_java;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class History extends Product implements Parcelable {
    double totalPrice;
    Date purchaseDate;
    public History(String name,int quantity,double price, double totalPrice, Date purchaseDate){
        super(name,quantity,price);
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Parcelable implementation
    protected History(Parcel in) {
        super(in.readString(), in.readInt(), in.readDouble());
        totalPrice = in.readDouble();
        purchaseDate = new Date(in.readLong());
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeInt(getQuantity());
        dest.writeDouble(getPrice());
        dest.writeDouble(totalPrice);
        dest.writeLong(purchaseDate.getTime());
    }
    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }
        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
