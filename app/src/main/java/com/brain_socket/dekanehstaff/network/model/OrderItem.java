package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderItem implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("price")
    @Expose
    private int retailPrice;

    public int getCount() {
        return count;
    }

    public String getNameAr() {
        return nameAr;
    }

    public int getRetailPrice() {
        return retailPrice;
    }
}