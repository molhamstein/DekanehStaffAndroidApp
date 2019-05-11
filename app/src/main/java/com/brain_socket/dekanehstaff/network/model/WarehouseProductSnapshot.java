package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WarehouseProductSnapshot implements Serializable {

    @SerializedName("avgBuyingPrice")
    @Expose
    private Integer avgBuyingPrice;
    @SerializedName("avgSellingPrice")
    @Expose
    private Integer avgSellingPrice;
    private final static long serialVersionUID = -7569353178342543029L;

    public Integer getAvgBuyingPrice() {
        return avgBuyingPrice;
    }

    public void setAvgBuyingPrice(Integer avgBuyingPrice) {
        this.avgBuyingPrice = avgBuyingPrice;
    }

    public Integer getAvgSellingPrice() {
        return avgSellingPrice;
    }

    public void setAvgSellingPrice(Integer avgSellingPrice) {
        this.avgSellingPrice = avgSellingPrice;
    }

}
