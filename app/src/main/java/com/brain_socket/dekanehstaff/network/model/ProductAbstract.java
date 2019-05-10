package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductAbstract implements Serializable
{

    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("officialConsumerPrice")
    @Expose
    private Integer officialConsumerPrice;
    @SerializedName("officialMassMarketPrice")
    @Expose
    private Integer officialMassMarketPrice;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("manufacturerId")
    @Expose
    private String manufacturerId;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("subCategoryId")
    @Expose
    private String subCategoryId;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("subCategory")
    @Expose
    private SubCategory subCategory;
    @SerializedName("manufacturer")
    @Expose
    private Manufacturer manufacturer;
    @SerializedName("warehouseProducts")
    @Expose
    private List<WareHouseProduct> warehouseProducts = null;
    private final static long serialVersionUID = 6844997913652461549L;

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public Integer getOfficialConsumerPrice() {
        return officialConsumerPrice;
    }

    public void setOfficialConsumerPrice(Integer officialConsumerPrice) {
        this.officialConsumerPrice = officialConsumerPrice;
    }

    public Integer getOfficialMassMarketPrice() {
        return officialMassMarketPrice;
    }

    public void setOfficialMassMarketPrice(Integer officialMassMarketPrice) {
        this.officialMassMarketPrice = officialMassMarketPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<WareHouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(List<WareHouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

}
