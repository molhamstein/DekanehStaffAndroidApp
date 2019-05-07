
package com.brain_socket.dekanehstaff.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WareHouseProduct implements Serializable {

    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("expectedCount")
    @Expose
    private Integer expectedCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("threshold")
    @Expose
    private Integer threshold;
    @SerializedName("accumulatedBuyingCountOverTime")
    @Expose
    private Integer accumulatedBuyingCountOverTime;
    @SerializedName("avgBuyingPrice")
    @Expose
    private Integer avgBuyingPrice;
    @SerializedName("accumulatedSellingCountOverTime")
    @Expose
    private Integer accumulatedSellingCountOverTime;
    @SerializedName("avgSellingPrice")
    @Expose
    private Integer avgSellingPrice;
    @SerializedName("warningThreshold")
    @Expose
    private Integer warningThreshold;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("warehouseId")
    @Expose
    private String warehouseId;
    @SerializedName("productAbstractId")
    @Expose
    private String productAbstractId;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getExpectedCount() {
        return expectedCount;
    }

    public void setExpectedCount(Integer expectedCount) {
        this.expectedCount = expectedCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getAccumulatedBuyingCountOverTime() {
        return accumulatedBuyingCountOverTime;
    }

    public void setAccumulatedBuyingCountOverTime(Integer accumulatedBuyingCountOverTime) {
        this.accumulatedBuyingCountOverTime = accumulatedBuyingCountOverTime;
    }

    public Integer getAvgBuyingPrice() {
        return avgBuyingPrice;
    }

    public void setAvgBuyingPrice(Integer avgBuyingPrice) {
        this.avgBuyingPrice = avgBuyingPrice;
    }

    public Integer getAccumulatedSellingCountOverTime() {
        return accumulatedSellingCountOverTime;
    }

    public void setAccumulatedSellingCountOverTime(Integer accumulatedSellingCountOverTime) {
        this.accumulatedSellingCountOverTime = accumulatedSellingCountOverTime;
    }

    public Integer getAvgSellingPrice() {
        return avgSellingPrice;
    }

    public void setAvgSellingPrice(Integer avgSellingPrice) {
        this.avgSellingPrice = avgSellingPrice;
    }

    public Integer getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(Integer warningThreshold) {
        this.warningThreshold = warningThreshold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getProductAbstractId() {
        return productAbstractId;
    }

    public void setProductAbstractId(String productAbstractId) {
        this.productAbstractId = productAbstractId;
    }

}