
package com.brain_socket.dekanehstaff.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report implements Serializable {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("warehouseProudctId")
    @Expose
    private String warehouseProudctId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("note")
    @Expose
    private String note = "";
    private final static long serialVersionUID = 2139165656021810278L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWarehouseProudctId() {
        return warehouseProudctId;
    }

    public void setWarehouseProudctId(String warehouseProudctId) {
        this.warehouseProudctId = warehouseProudctId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}